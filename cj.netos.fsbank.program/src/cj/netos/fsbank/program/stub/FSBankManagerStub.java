package cj.netos.fsbank.program.stub;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cj.netos.fsbank.args.BState;
import cj.netos.fsbank.args.BankInfo;
import cj.netos.fsbank.args.BankState;
import cj.netos.fsbank.bs.IFSBankInfoBS;
import cj.netos.fsbank.bs.IFSBankStateBS;
import cj.netos.fsbank.stub.IFSBankManagerStub;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
import cj.ultimate.util.StringUtil;

@CjService(name = "/manager.service")
public class FSBankManagerStub extends GatewayAppSiteRestStub implements IFSBankManagerStub {
	@CjServiceRef(refByName = "FSBAEngine.fSBankInfoBS")
	IFSBankInfoBS fSBankInfoBS;

	@CjServiceRef(refByName = "FSBAEngine.fSBankStateBS")
	IFSBankStateBS fSBankStateBS;

	@Override
	public String registerBank(String bankName, String president, String company,String expiredDate) throws CircuitException {
		if (StringUtil.isEmpty(bankName)) {
			throw new CircuitException("404", String.format("银行名为空"));
		}
		if (StringUtil.isEmpty(president)) {
			throw new CircuitException("404", String.format("行长为空"));
		}
		if (StringUtil.isEmpty(expiredDate)) {
			throw new CircuitException("404", String.format("到期日期为空"));
		}
		BankInfo info = new BankInfo();
		info.setCode(null);
		info.setPresident(president);
		info.setCompany(company);
		info.setName(bankName);
		info.setCtime(System.currentTimeMillis());
		BankState state = new BankState();
		state.setState(BState.opened);
		info.setBstate(state.getState());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date expire=null;
		try {
			expire=sdf.parse(expiredDate);
		} catch (ParseException e) {
			throw new CircuitException("500",e);
		}
		info.setExpiredTime(expire.getTime());
		
		fSBankInfoBS.saveBank(info);
		// 插入营业状态为正常
		state.setBank(info.getCode());
		state.setCtime(System.currentTimeMillis());
		fSBankStateBS.save(state);
		return info.getCode();
	}

	@Override
	public void revokeBank(String bankCode) {
		fSBankStateBS.revokeBank(bankCode);
	}

	@Override
	public void freezeBank(String bankCode) {
		fSBankStateBS.freezeBank(bankCode);
	}

	@Override
	public void closedBank(String bankCode) {
		fSBankStateBS.closedBank(bankCode);
	}

	@Override
	public void resumeBank(String bankCode) {
		fSBankStateBS.resumeBank(bankCode);
	}

	@Override
	public BankInfo getBankInfo(String bankCode) {
		return fSBankInfoBS.getBankInfo(bankCode);
	}

	@Override
	public List<BankInfo> pageBankInfo(int currPage, int pageSize) {
		return fSBankInfoBS.pageBankInfo(currPage, pageSize);
	}

	@Override
	public void updateBankName(String bank, String name) throws CircuitException {
		fSBankInfoBS.updateBankName(bank, name);
	}

	@Override
	public void updateBankPresident(String bank, String president) throws CircuitException {
		fSBankInfoBS.updateBankPresident(bank, president);
	}

	@Override
	public void updateBankCompany(String bank, String company) throws CircuitException {
		fSBankInfoBS.updateBankCompany(bank, company);
	}

	@Override
	public BState getBankState(String bankCode) {
		return fSBankStateBS.getState(bankCode).getState();
	}
}
