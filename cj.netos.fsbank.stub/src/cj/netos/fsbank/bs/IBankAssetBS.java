package cj.netos.fsbank.bs;

import java.math.BigDecimal;
import java.util.List;

import cj.netos.fsbank.args.CashoutBill;
import cj.netos.fsbank.args.DepositBill;
import cj.netos.fsbank.args.ExchangeBill;

public interface IBankAssetBS {
	long depositBillCount(String bank);

	long cashBillCount(String bank);

	long exchangeBillCount(String bank);

	List<DepositBill> pageDepositBill(String bank, int currPage, int pageSize);

	List<CashoutBill> pageCashoutBill(String bank, int currPage, int pageSize);

	List<ExchangeBill> pageExchangeBill(String bank, int currPage, int pageSize);

	BigDecimal totalDepositBillAmount(String bank);

	BigDecimal totalDepositBondQuantities(String bank);

	BigDecimal totalDepositBondAmount(String bank);

	BigDecimal totalDepositReserveAmount(String bank);

	BigDecimal totalDepositFreeAmount(String bank);

	BigDecimal totalExchangeBondQuantities(String bank);

	BigDecimal totalExchangeBillAmount(String bank);

	BigDecimal totalExchangeTailAmount(String bank);

	BigDecimal totalCashoutReqAmount(String bank);

	BigDecimal totalCashoutResAmount(String bank);

	BigDecimal totalCashoutPoundageAmount(String bank);
}
