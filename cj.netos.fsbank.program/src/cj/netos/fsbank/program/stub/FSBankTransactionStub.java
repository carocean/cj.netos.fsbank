package cj.netos.fsbank.program.stub;

import cj.netos.fsbank.args.CashoutBill;
import cj.netos.fsbank.args.DepositBill;
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
	@Override
	public void deposit(String bank,DepositBill bill) {
		IReactor reactor = (IReactor) site.getService("$.reactor");   
		Event e=new Event(bank, "deposit");
		e.getParameters().put("bill", bill);
		reactor.input(e);
	}

	@Override
	public void cashout(String bank,CashoutBill bill) {
		IReactor reactor = (IReactor) site.getService("$.reactor");
		Event e=new Event(bank, "deposit");
		e.getParameters().put("bill", bill);
		reactor.input(e);
	}



}
