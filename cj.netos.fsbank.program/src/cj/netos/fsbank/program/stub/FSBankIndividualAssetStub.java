package cj.netos.fsbank.program.stub;

import java.math.BigDecimal;
import java.util.List;

import cj.netos.fsbank.args.CashoutBill;
import cj.netos.fsbank.args.DepositBill;
import cj.netos.fsbank.args.ExchangeBill;
import cj.netos.fsbank.bs.IFSBankIndividualAccountAssetBS;
import cj.netos.fsbank.stub.IFSBankIndividualAssetStub;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.annotation.CjServiceSite;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
import cj.studio.util.reactor.Event;
import cj.studio.util.reactor.IReactor;

@CjService(name = "/asset/individualAccount.service")
public class FSBankIndividualAssetStub extends GatewayAppSiteRestStub implements IFSBankIndividualAssetStub {
	@CjServiceRef(refByName = "FSBAEngine.fSBankIndividualAccountAssetBS")
	IFSBankIndividualAccountAssetBS fSBankIndividualAccountAssetBS;
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
	public void getBondBalance(String bank, String user,String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "individual.getBondBalance");
		e.getParameters().put("user", user);
		e.getParameters().put("address",informAddress);
		reactor.input(e);
	}
	@Override
	public long depositBillCount(String bank, String depositor) {
		return fSBankIndividualAccountAssetBS.depositBillCount(bank, depositor);
	}

	@Override
	public long cashoutBillCount(String bank, String cashoutor, String identity) {
		return fSBankIndividualAccountAssetBS.cashoutBillCount(bank, cashoutor,identity);
	}

	@Override
	public long exchangeBillCount(String bank, String exchanger) {
		return fSBankIndividualAccountAssetBS.exchangeBillCount(bank, exchanger);
	}

	@Override
	public List<DepositBill> pageDepositBill(String bank, String depositor, int currPage, int pageSize) {
		return fSBankIndividualAccountAssetBS.pageDepositBill(bank, depositor, currPage, pageSize);
	}

	@Override
	public List<CashoutBill> pageCashoutBill(String bank, String cashoutor, String identity, int currPage, int pageSize) {
		return fSBankIndividualAccountAssetBS.pageCashoutBill(bank, cashoutor,identity, currPage, pageSize);
	}

	@Override
	public List<ExchangeBill> pageExchangeBill(String bank, String exchanger, int currPage, int pageSize) {
		return fSBankIndividualAccountAssetBS.pageExchangeBill(bank, exchanger, currPage, pageSize);
	}

	@Override
	public List<CashoutBill> pageCashoutBillByIdentity(String bank, String identity, int currPage, int pageSize) {
		return fSBankIndividualAccountAssetBS.pageCashoutBillByIdentity(bank, identity, currPage, pageSize);
	}

	@Override
	public BigDecimal totalDepositBillAmount(String bank, String depositor) {
		return fSBankIndividualAccountAssetBS.totalDepositBillAmount(bank, depositor);
	}

	@Override
	public BigDecimal totalDepositBondQuantities(String bank, String depositor) {
		return fSBankIndividualAccountAssetBS.totalDepositBondQuantities(bank, depositor);
	}

	@Override
	public BigDecimal totalDepositBondAmount(String bank, String depositor) {
		return fSBankIndividualAccountAssetBS.totalDepositBondAmount(bank, depositor);
	}

	@Override
	public BigDecimal totalDepositReserveAmount(String bank, String depositor) {
		return fSBankIndividualAccountAssetBS.totalDepositReserveAmount(bank, depositor);
	}

	@Override
	public BigDecimal totalDepositFreeAmount(String bank, String depositor) {
		return fSBankIndividualAccountAssetBS.totalDepositFreeAmount(bank, depositor);
	}

	@Override
	public BigDecimal totalExchangeBondQuantities(String bank, String exchanger) {
		return fSBankIndividualAccountAssetBS.totalExchangeBondQuantities(bank, exchanger);
	}

	@Override
	public BigDecimal totalExchangeBillAmount(String bank, String exchanger) {
		return fSBankIndividualAccountAssetBS.totalExchangeBillAmount(bank, exchanger);
	}

	@Override
	public BigDecimal totalExchangeTailAmount(String bank, String exchanger) {
		return fSBankIndividualAccountAssetBS.totalExchangeTailAmount(bank, exchanger);
	}

	@Override
	public BigDecimal totalCashoutReqAmount(String bank, String cashoutor, String identity) {
		return fSBankIndividualAccountAssetBS.totalCashoutReqAmount(bank, cashoutor,identity);
	}

	@Override
	public BigDecimal totalCashoutResAmount(String bank, String cashoutor, String identity) {
		return fSBankIndividualAccountAssetBS.totalCashoutResAmount(bank, cashoutor,identity);
	}

	@Override
	public BigDecimal totalCashoutPoundageAmount(String bank, String cashoutor,String identity) {
		return fSBankIndividualAccountAssetBS.totalCashoutPoundageAmount(bank, cashoutor,identity);
	}

	@Override
	public BigDecimal totalCashoutReqAmountByIdentity(String bank, String identity) {
		return fSBankIndividualAccountAssetBS.totalCashoutReqAmountByIdentity(bank, identity);
	}

	@Override
	public BigDecimal totalCashoutResAmountByIdentity(String bank, String identity) {
		return fSBankIndividualAccountAssetBS.totalCashoutResAmountByIdentity(bank, identity);
	}

	@Override
	public BigDecimal totalCashoutPoundageAmountByIdentity(String bank, String identity) {
		return fSBankIndividualAccountAssetBS.totalCashoutPoundageAmountByIdentity(bank, identity);
	}

}
