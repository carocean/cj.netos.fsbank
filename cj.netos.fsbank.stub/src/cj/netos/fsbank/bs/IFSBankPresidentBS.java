package cj.netos.fsbank.bs;

import cj.netos.fsbank.args.BankPresident;

public interface IFSBankPresidentBS {
	static String TABLE_PRESIDENT="presidents";
	void savePresident(BankPresident president);
	boolean existsPresident(String bank);

}
