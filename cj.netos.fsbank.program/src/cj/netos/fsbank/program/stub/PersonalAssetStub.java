package cj.netos.fsbank.program.stub;

import java.math.BigDecimal;
import java.util.List;

import cj.netos.fsbank.args.CashoutBill;
import cj.netos.fsbank.args.DepositBill;
import cj.netos.fsbank.args.ExchangeBill;
import cj.netos.fsbank.bs.IPersonalAssetBS;
import cj.netos.fsbank.stub.IPersonalAssetStub;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;

@CjService(name = "/asset/personal.service")
public class PersonalAssetStub extends GatewayAppSiteRestStub implements IPersonalAssetStub {
	@CjServiceRef(refByName = "FSBAEngine.personalAssetBS")
	IPersonalAssetBS personalAssetBS;

	@Override
	public long depositBillCount(String bank, String depositor) {
		return personalAssetBS.depositBillCount(bank, depositor);
	}

	@Override
	public long cashoutBillCount(String bank, String cashoutor, String identity) {
		return personalAssetBS.cashoutBillCount(bank, cashoutor,identity);
	}

	@Override
	public long exchangeBillCount(String bank, String exchanger) {
		return personalAssetBS.exchangeBillCount(bank, exchanger);
	}

	@Override
	public List<DepositBill> pageDepositBill(String bank, String depositor, int currPage, int pageSize) {
		return personalAssetBS.pageDepositBill(bank, depositor, currPage, pageSize);
	}

	@Override
	public List<CashoutBill> pageCashoutBill(String bank, String cashoutor, String identity, int currPage, int pageSize) {
		return personalAssetBS.pageCashoutBill(bank, cashoutor,identity, currPage, pageSize);
	}

	@Override
	public List<ExchangeBill> pageExchangeBill(String bank, String exchanger, int currPage, int pageSize) {
		return personalAssetBS.pageExchangeBill(bank, exchanger, currPage, pageSize);
	}

	@Override
	public List<CashoutBill> pageCashoutBillByIdentity(String bank, String identity, int currPage, int pageSize) {
		return personalAssetBS.pageCashoutBillByIdentity(bank, identity, currPage, pageSize);
	}

	@Override
	public BigDecimal totalDepositBillAmount(String bank, String depositor) {
		return personalAssetBS.totalDepositBillAmount(bank, depositor);
	}

	@Override
	public BigDecimal totalDepositBondQuantities(String bank, String depositor) {
		return personalAssetBS.totalDepositBondQuantities(bank, depositor);
	}

	@Override
	public BigDecimal totalDepositBondAmount(String bank, String depositor) {
		return personalAssetBS.totalDepositBondAmount(bank, depositor);
	}

	@Override
	public BigDecimal totalDepositReserveAmount(String bank, String depositor) {
		return personalAssetBS.totalDepositReserveAmount(bank, depositor);
	}

	@Override
	public BigDecimal totalDepositFreeAmount(String bank, String depositor) {
		return personalAssetBS.totalDepositFreeAmount(bank, depositor);
	}

	@Override
	public BigDecimal totalExchangeBondQuantities(String bank, String exchanger) {
		return personalAssetBS.totalExchangeBondQuantities(bank, exchanger);
	}

	@Override
	public BigDecimal totalExchangeBillAmount(String bank, String exchanger) {
		return personalAssetBS.totalExchangeBillAmount(bank, exchanger);
	}

	@Override
	public BigDecimal totalExchangeTailAmount(String bank, String exchanger) {
		return personalAssetBS.totalExchangeTailAmount(bank, exchanger);
	}

	@Override
	public BigDecimal totalCashoutReqAmount(String bank, String cashoutor, String identity) {
		return personalAssetBS.totalCashoutReqAmount(bank, cashoutor,identity);
	}

	@Override
	public BigDecimal totalCashoutResAmount(String bank, String cashoutor, String identity) {
		return personalAssetBS.totalCashoutResAmount(bank, cashoutor,identity);
	}

	@Override
	public BigDecimal totalCashoutPoundageAmount(String bank, String cashoutor,String identity) {
		return personalAssetBS.totalCashoutPoundageAmount(bank, cashoutor,identity);
	}

	@Override
	public BigDecimal totalCashoutReqAmountByIdentity(String bank, String identity) {
		return personalAssetBS.totalCashoutReqAmountByIdentity(bank, identity);
	}

	@Override
	public BigDecimal totalCashoutResAmountByIdentity(String bank, String identity) {
		return personalAssetBS.totalCashoutResAmountByIdentity(bank, identity);
	}

	@Override
	public BigDecimal totalCashoutPoundageAmountByIdentity(String bank, String identity) {
		return personalAssetBS.totalCashoutPoundageAmountByIdentity(bank, identity);
	}

}
