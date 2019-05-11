package cj.netos.fsbank.bs;

import cj.netos.fsbank.args.BankState;

public interface IFSBankStateBS {
	static String TABLE_BANK_STATE="bstates";
	void save(BankState state);
	BankState getState(String bank);
	void revokeBank(String bankCode);

	void freezeBank(String bankCode);

	void closedBank(String bankCode);

	void resumeBank(String bankCode);
}
