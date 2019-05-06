package cj.netos.fsbank.program.stub;

import java.math.BigDecimal;
import java.util.List;

import cj.netos.fsbank.args.CashoutBill;
import cj.netos.fsbank.args.DepositBill;
import cj.netos.fsbank.args.ExchangeBill;
import cj.netos.fsbank.bs.IBankAssetBS;
import cj.netos.fsbank.stub.IBankAssetStub;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;

@CjService(name = "/asset/bank.service")
public class BankAssetStub extends GatewayAppSiteRestStub implements IBankAssetStub {
	@CjServiceRef(refByName = "FSBAEngine.bankAssetBS")
	IBankAssetBS bankAssetBS;

	@Override
	public long depositBillCount(String bank) {
		return bankAssetBS.depositBillCount(bank);
	}

	@Override
	public long cashBillCount(String bank) {
		return bankAssetBS.cashBillCount(bank);
	}

	@Override
	public long exchangeBillCount(String bank) {
		return bankAssetBS.exchangeBillCount(bank);
	}

	@Override
	public List<DepositBill> pageDepositBill(String bank, int currPage, int pageSize) {
		return bankAssetBS.pageDepositBill(bank, currPage, pageSize);
	}

	@Override
	public List<CashoutBill> pageCashoutBill(String bank, int currPage, int pageSize) {
		return bankAssetBS.pageCashoutBill(bank, currPage, pageSize);
	}

	@Override
	public List<ExchangeBill> pageExchangeBill(String bank, int currPage, int pageSize) {
		return bankAssetBS.pageExchangeBill(bank, currPage, pageSize);
	}

	@Override
	public BigDecimal totalDepositBillAmount(String bank) {
		return bankAssetBS.totalDepositBillAmount(bank);
	}

	@Override
	public BigDecimal totalDepositBondQuantities(String bank) {
		return bankAssetBS.totalDepositBondQuantities(bank);
	}

	@Override
	public BigDecimal totalDepositBondAmount(String bank) {
		return bankAssetBS.totalDepositBondAmount(bank);
	}

	@Override
	public BigDecimal totalDepositReserveAmount(String bank) {
		return bankAssetBS.totalDepositReserveAmount(bank);
	}

	@Override
	public BigDecimal totalDepositFreeAmount(String bank) {
		return bankAssetBS.totalDepositFreeAmount(bank);
	}

	@Override
	public BigDecimal totalExchangeBondQuantities(String bank) {
		return bankAssetBS.totalExchangeBondQuantities(bank);
	}

	@Override
	public BigDecimal totalExchangeBillAmount(String bank) {
		return bankAssetBS.totalExchangeBillAmount(bank);
	}

	@Override
	public BigDecimal totalExchangeTailAmount(String bank) {
		return bankAssetBS.totalExchangeTailAmount(bank);
	}

	@Override
	public BigDecimal totalCashoutReqAmount(String bank) {
		return bankAssetBS.totalCashoutReqAmount(bank);
	}

	@Override
	public BigDecimal totalCashoutResAmount(String bank) {
		return bankAssetBS.totalCashoutResAmount(bank);
	}

	@Override
	public BigDecimal totalCashoutPoundageAmount(String bank) {
		return bankAssetBS.totalCashoutPoundageAmount(bank);
	}

}
