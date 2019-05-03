package cj.netos.fsbank.bs;

import java.math.BigDecimal;

import cj.netos.fsbank.args.Balance;

public interface IFSBankBalanceBS {
	static String TABLE_NAME = "balances";

	Balance loadBalance(String bank);

	BigDecimal getBondPrice(String bank);

	void updateBondPrice(String bank, BigDecimal price);

	BigDecimal getBondQuantitiesBalance(String bank);

	void updateBondQuantitiesBalance(String bank, BigDecimal balance);

	BigDecimal getFreezeAmountBalance(String bank);

	void updateFreezeAmountBalance(String bank, BigDecimal balance);

	BigDecimal getFreeAmountBalance(String bank);

	void updateFreeAmountBalance(String bank, BigDecimal balance);

	BigDecimal getTailAmountBalance(String bank);

	void updateTailAmountBalance(String bank, BigDecimal balance);

}