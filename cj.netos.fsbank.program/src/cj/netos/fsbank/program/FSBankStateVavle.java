package cj.netos.fsbank.program;

import cj.netos.fsbank.args.BState;
import cj.netos.fsbank.args.BankState;
import cj.netos.fsbank.bs.IFSBankLicenseBS;
import cj.netos.fsbank.bs.IFSBankStateBS;
import cj.netos.fsbank.stub.IFSBankManagerStub;
import cj.studio.ecm.Scope;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;
import cj.studio.gateway.socket.pipeline.IAnnotationInputValve;
import cj.studio.gateway.socket.pipeline.IIPipeline;
import cj.ultimate.util.StringUtil;

@CjService(name = "fSBankStateVavle", scope = Scope.multiton)
public class FSBankStateVavle implements IAnnotationInputValve {

	@CjServiceRef(refByName = "FSBAEngine.fSBankStateBS")
	IFSBankStateBS fSBankStateBS;
	@CjServiceRef(refByName = "FSBAEngine.fSBankLicenseBS")
	IFSBankLicenseBS fSBankLicenseBS;

	@Override
	public void onActive(String inputName, IIPipeline pipeline) throws CircuitException {
		pipeline.nextOnActive(inputName, this);
	}

	@Override
	public void flow(Object request, Object response, IIPipeline pipeline) throws CircuitException {
		if (!(request instanceof Frame)) {
			pipeline.nextFlow(request, response, this);
			return;
		}
		Frame f = (Frame) request;
		if (IFSBankManagerStub.class.getName().equals(f.head("Rest-StubFace"))) {
			pipeline.nextFlow(request, response, this);
			return;
		}
		String bank = f.parameter("bankCode");
		if (StringUtil.isEmpty(bank)) {
			bank = f.parameter("bank");
		}
		if (StringUtil.isEmpty(bank)) {
			pipeline.nextFlow(request, response, this);
			return;
		}
		if (this.fSBankLicenseBS.isExpired(bank)) {
			BankState state = new BankState();
			state.setBank(bank);
			state.setCtime(System.currentTimeMillis());
			state.setState(BState.freeze);
			state.setDesc("牌照过期，银行已被冻结，请重新申请牌照");
			fSBankStateBS.save(state);
		}
		BankState state = fSBankStateBS.getState(bank);
		switch (state.getState()) {
		case closed:
		case freeze:
		case revoke:
			throw new CircuitException("503", String.format("银行:%s 拒绝服务，原因：%s %s", bank, state.getState(),
					state.getDesc() == null ? "" : state.getDesc()));
		case opened:
			pipeline.nextFlow(request, response, this);
			break;
		}

	}

	@Override
	public void onInactive(String inputName, IIPipeline pipeline) throws CircuitException {
		pipeline.nextOnInactive(inputName, this);
	}

	@Override
	public int getSort() {
		return 0;
	}

}
