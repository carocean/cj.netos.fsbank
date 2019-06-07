package cj.netos.fsbank.program.stub;

import java.math.BigDecimal;

import cj.netos.fsbank.stub.IFSBankTransactionStub;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceSite;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
import cj.studio.util.reactor.Event;
import cj.studio.util.reactor.IReactor;

@CjService(name = "/transaction.service")
public class FSBankTransactionStub extends GatewayAppSiteRestStub implements IFSBankTransactionStub {
	@CjServiceSite
	IServiceSite site;
	IReactor reactor;

	protected IReactor getReactor() {
		if (reactor == null) {
			reactor = (IReactor) site.getService("$.reactor");
		}
		return reactor;
	}

	@Override
	public void deposit(String bank, String depositor, BigDecimal amount, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "transaction.deposit");
		e.getParameters().put("depositor", depositor);
		e.getParameters().put("amount", amount);
		e.getParameters().put("address",informAddress);
		reactor.input(e);
	}

	@Override
	public void cashout(String bank, String balanceType, String cashoutor, String identity, BigDecimal reqAmount,
			String memo, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "transaction.cashout");
		e.getParameters().put("cashoutor", cashoutor);
		e.getParameters().put("reqAmount", reqAmount);
		e.getParameters().put("identity", identity);
		e.getParameters().put("memo", memo);
		e.getParameters().put("balanceType", balanceType);
		e.getParameters().put("address",informAddress);
		reactor.input(e);
	}

	@Override
	public void exchange(String bank, String exchanger, BigDecimal bondQuantities, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "transaction.exchange");
		e.getParameters().put("exchanger", exchanger);
		e.getParameters().put("bondQuantities", bondQuantities);
		e.getParameters().put("address",informAddress);
		reactor.input(e);
	}

}
