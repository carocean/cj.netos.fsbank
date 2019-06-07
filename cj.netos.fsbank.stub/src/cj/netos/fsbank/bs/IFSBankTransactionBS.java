package cj.netos.fsbank.bs;

import java.math.BigDecimal;
import java.util.Map;

public interface IFSBankTransactionBS {
	static String TABLE_Deposits = "deposits";
	static String TABLE_Cashouts = "cashouts";
	static String TABLE_Exchanges = "exchanges";
	Map<String,Object> depositBill(String bank, String depositor, BigDecimal amount);

	Map<String, Object>  cashoutBill(String bank,String balanceType, String cashoutor, String identity, BigDecimal reqAmount, String memo);

	Map<String, Object>  exchangeBill(String key, String exchanger, BigDecimal bondQuantities);

}
