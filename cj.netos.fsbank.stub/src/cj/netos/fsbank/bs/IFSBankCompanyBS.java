package cj.netos.fsbank.bs;

import cj.netos.fsbank.args.BankCompany;

public interface IFSBankCompanyBS {
	static String TABLE_COMPANY="companies";
	boolean existsCompany(String bank);

	void saveCompany(BankCompany company);

}
