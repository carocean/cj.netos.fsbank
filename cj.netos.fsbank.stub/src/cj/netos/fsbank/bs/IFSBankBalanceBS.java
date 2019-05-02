package cj.netos.fsbank.bs;

import java.math.BigDecimal;

import cj.netos.fsbank.args.Balance;

public interface IFSBankBalanceBS {
	static String TABLE_NAME = "balances";
	Balance loadBalance(String bank);

	BigDecimal getBondPrice(String bank);

	void updateBondPrice(String bank, BigDecimal price);

	String getBondKind(String bank);

	void updateBondKind(String bank, String kind);

	String getCurrency(String bank);

	void updateCurrency(String bank, String currency);

	BigDecimal getBondAmountBalance(String bank);

	void updateBondAmountBalance(String bank, BigDecimal balance);

	BigDecimal getBondQuantitiesBalance(String bank);

	void updateBondQuantitiesBalance(String bank, BigDecimal balance);

	BigDecimal getReserveAmountBalance(String bank);

	void updateReserveAmountBalance(String bank, BigDecimal balance);

	BigDecimal getFreeAmountBalance(String bank);

	void updateFreeAmountBalance(String bank, BigDecimal balance);

	BigDecimal getTailAmountBalance(String bank);

	void updateTailAmountBalance(String bank, BigDecimal balance);

}