package cj.netos.fsbank.program.stub;

import cj.netos.fsbank.stub.IFSBankIndividualAssetStub;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceSite;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
import cj.studio.util.reactor.Event;
import cj.studio.util.reactor.IReactor;

//个人资产查询交易
@CjService(name = "/asset/individualAccount.service")
public class FSBankIndividualAssetStub extends GatewayAppSiteRestStub implements IFSBankIndividualAssetStub {
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
	public void getBondBalance(String bank, String user, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.getBondBalance");
		e.getParameters().put("user", user);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void depositBillCount(String bank, String depositor, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.depositBillCount");
		e.getParameters().put("depositor", depositor);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void cashoutBillCount(String bank, String cashoutor, String identity, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.cashoutBillCount");
		e.getParameters().put("cashoutor", cashoutor);
		e.getParameters().put("identity", identity);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void exchangeBillCount(String bank, String exchanger, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.exchangeBillCount");
		e.getParameters().put("exchanger", exchanger);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void pageDepositBill(String bank, String depositor, int currPage, int pageSize, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.pageDepositBill");
		e.getParameters().put("depositor", depositor);
		e.getParameters().put("currPage", currPage);
		e.getParameters().put("pageSize", pageSize);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void pageCashoutBill(String bank, String cashoutor, String identity, int currPage, int pageSize,
			String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.pageCashoutBill");
		e.getParameters().put("cashoutor", cashoutor);
		e.getParameters().put("identity", identity);
		e.getParameters().put("currPage", currPage);
		e.getParameters().put("pageSize", pageSize);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void pageExchangeBill(String bank, String exchanger, int currPage, int pageSize, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.pageExchangeBill");
		e.getParameters().put("exchanger", exchanger);
		e.getParameters().put("currPage", currPage);
		e.getParameters().put("pageSize", pageSize);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void pageCashoutBillByIdentity(String bank, String identity, int currPage, int pageSize,
			String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.pageCashoutBillByIdentity");
		e.getParameters().put("identity", identity);
		e.getParameters().put("currPage", currPage);
		e.getParameters().put("pageSize", pageSize);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalDepositBillAmount(String bank, String depositor, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.totalDepositBillAmount");
		e.getParameters().put("depositor", depositor);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalDepositBondQuantities(String bank, String depositor, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.totalDepositBondQuantities");
		e.getParameters().put("depositor", depositor);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalDepositBondAmount(String bank, String depositor, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.totalDepositBondAmount");
		e.getParameters().put("depositor", depositor);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalDepositReserveAmount(String bank, String depositor, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.totalDepositReserveAmount");
		e.getParameters().put("depositor", depositor);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalDepositFreeAmount(String bank, String depositor, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.totalDepositFreeAmount");
		e.getParameters().put("depositor", depositor);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalExchangeBondQuantities(String bank, String exchanger, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.totalExchangeBondQuantities");
		e.getParameters().put("exchanger", exchanger);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalExchangeBillAmount(String bank, String exchanger, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.totalExchangeBillAmount");
		e.getParameters().put("exchanger", exchanger);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalExchangeTailAmount(String bank, String exchanger, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.totalExchangeTailAmount");
		e.getParameters().put("exchanger", exchanger);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalCashoutReqAmount(String bank, String cashoutor, String identity, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.totalCashoutReqAmount");
		e.getParameters().put("cashoutor", cashoutor);
		e.getParameters().put("identity", identity);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalCashoutResAmount(String bank, String cashoutor, String identity, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.totalCashoutResAmount");
		e.getParameters().put("cashoutor", cashoutor);
		e.getParameters().put("identity", identity);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalCashoutPoundageAmount(String bank, String cashoutor, String identity, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.totalCashoutPoundageAmount");
		e.getParameters().put("cashoutor", cashoutor);
		e.getParameters().put("identity", identity);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalCashoutReqAmountByIdentity(String bank, String identity, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.totalCashoutReqAmountByIdentity");
		e.getParameters().put("identity", identity);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalCashoutResAmountByIdentity(String bank, String identity, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.totalCashoutResAmountByIdentity");
		e.getParameters().put("identity", identity);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalCashoutPoundageAmountByIdentity(String bank, String identity, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.totalCashoutPoundageAmountByIdentity");
		e.getParameters().put("identity", identity);
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

}
