package cj.netos.fsbank.bs;

import java.math.BigDecimal;
import java.util.List;

import cj.netos.fsbank.args.CashoutBill;
import cj.netos.fsbank.args.DepositBill;
import cj.netos.fsbank.args.ExchangeBill;

public interface IPersonalAssetBS {
	long depositBillCount(String bank, String depositor);

	long cashoutBillCount(String bank, String cashoutor, String identity);

	long exchangeBillCount(String bank, String exchanger);

	List<DepositBill> pageDepositBill(String bank, String depositor, int currPage, int pageSize);

	List<CashoutBill> pageCashoutBill(String bank, String cashoutor, String identity, int currPage, int pageSize);

	List<ExchangeBill> pageExchangeBill(String bank, String exchanger, int currPage, int pageSize);

	List<CashoutBill> pageCashoutBillByIdentity(String bank, String identity, int currPage, int pageSize);

	BigDecimal totalDepositBillAmount(String bank, String depositor);

	BigDecimal totalDepositBondQuantities(String bank, String depositor);

	BigDecimal totalDepositBondAmount(String bank, String depositor);

	BigDecimal totalDepositReserveAmount(String bank, String depositor);

	BigDecimal totalDepositFreeAmount(String bank, String depositor);

	BigDecimal totalExchangeBondQuantities(String bank, String exchanger);

	BigDecimal totalExchangeBillAmount(String bank, String exchanger);

	BigDecimal totalExchangeTailAmount(String bank, String exchanger);

	BigDecimal totalCashoutReqAmount(String bank, String cashoutor, String identity);

	BigDecimal totalCashoutResAmount(String bank, String cashoutor, String identity);

	BigDecimal totalCashoutPoundageAmount(String bank, String cashoutor, String identity);

	BigDecimal totalCashoutReqAmountByIdentity(String bank, String identity);

	BigDecimal totalCashoutResAmountByIdentity(String bank, String identity);

	BigDecimal totalCashoutPoundageAmountByIdentity(String bank, String identity);
}
