package cj.netos.fsbank.bs;

import cj.netos.fsbank.args.BankState;

public interface IFSBankStateBS {
	static String TABLE_BANK_STATE="bstates";
	void save(BankState state);

}
