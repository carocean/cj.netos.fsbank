package cj.netos.fsbank.plugin.FSBAEngine.bs;

import java.math.BigDecimal;

import cj.netos.fsbank.args.Balance;

public interface IFSBankBalanceBS {
	static String TABLE_NAME = "balances";
	Balance loadBalance(String bank);

	BigDecimal getBondPrice(String bank);

	void updateBondPrice(String bank, BigDecimal balance);

	String getBondKind(String bank);

	void updateBondKind(String bank, String kind);

	String getCurrencyKind(String bank);

	void updateCurrencyKind(String bank, String kind);

	BigDecimal getBondBalance(String bank);

	void updateBondBalance(String bank, BigDecimal balance);

	BigDecimal getReserveBalance(String bank);

	void updateReserveBalance(String bank, BigDecimal balance);

	BigDecimal getFreeBalance(String bank);

	void updateFreeBalance(String bank, BigDecimal balance);

	BigDecimal getTailBalance(String bank);

	void updateTailBalance(String bank, BigDecimal balance);

}