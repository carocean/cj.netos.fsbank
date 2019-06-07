package cj.netos.fsbank.program.stub;

import cj.netos.fsbank.stub.IBankAssetStub;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceSite;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
import cj.studio.util.reactor.Event;
import cj.studio.util.reactor.IReactor;

@CjService(name = "/asset/bank.service")
public class BankAssetStub extends GatewayAppSiteRestStub implements IBankAssetStub {
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
	public void depositBillCount(String bank, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "bankasset.depositBillCount");
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void cashBillCount(String bank, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "bankasset.cashBillCount");
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void exchangeBillCount(String bank, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "bankasset.exchangeBillCount");
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void pageDepositBill(String bank, int currPage, int pageSize, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "bankasset.pageDepositBill");
		e.getParameters().put("address", informAddress);
		e.getParameters().put("currPage", currPage);
		e.getParameters().put("pageSize", pageSize);
		reactor.input(e);

	}

	@Override
	public void pageCashoutBill(String bank, int currPage, int pageSize, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "bankasset.pageCashoutBill");
		e.getParameters().put("address", informAddress);
		e.getParameters().put("currPage", currPage);
		e.getParameters().put("pageSize", pageSize);
		reactor.input(e);
	}

	@Override
	public void pageExchangeBill(String bank, int currPage, int pageSize, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "bankasset.pageExchangeBill");
		e.getParameters().put("address", informAddress);
		e.getParameters().put("currPage", currPage);
		e.getParameters().put("pageSize", pageSize);
		reactor.input(e);
	}

	@Override
	public void totalDepositBillAmount(String bank, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "bankasset.totalDepositBillAmount");
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalDepositBondQuantities(String bank, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "bankasset.totalDepositBillAmount");
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalDepositBondAmount(String bank, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "bankasset.totalDepositBondAmount");
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalDepositReserveAmount(String bank, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "bankasset.totalDepositReserveAmount");
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalDepositFreeAmount(String bank, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "bankasset.totalDepositFreeAmount");
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalExchangeBondQuantities(String bank, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "bankasset.totalExchangeBondQuantities");
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalExchangeBillAmount(String bank, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "bankasset.totalExchangeBondQuantities");
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalExchangeTailAmount(String bank, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "bankasset.totalExchangeTailAmount");
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalCashoutReqAmount(String bank, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "bankasset.totalCashoutReqAmount");
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalCashoutResAmount(String bank, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "bankasset.totalCashoutResAmount");
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void totalCashoutPoundageAmount(String bank, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "bankasset.totalCashoutPoundageAmount");
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

}
