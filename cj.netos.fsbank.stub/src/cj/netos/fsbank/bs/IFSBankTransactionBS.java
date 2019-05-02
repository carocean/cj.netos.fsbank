package cj.netos.fsbank.bs;

import java.math.BigDecimal;

import cj.netos.fsbank.args.SeparateBillRuler;

public interface IFSBankTransactionBS {
	static String TABLE_Deposits = "deposits";
	static String TABLE_Cashouts = "cashouts";
	static String TABLE_Exchanges = "exchanges";
	void separateBill(String bank, String depositor, String currency, BigDecimal amount, SeparateBillRuler ruler);

	void cashoutBill(String bank,String balanceType, String cashoutor, String identity, BigDecimal reqAmount, String memo);

	void exchangeBill(String key, String exchanger, BigDecimal bondQuantities);

}
