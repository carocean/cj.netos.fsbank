package cj.netos.fsbank.bs;

import java.util.List;

import cj.netos.fsbank.args.BankInfo;

public interface IFSBankInfoBS {
	static String TABLE_BANK_INFO = "banks";

	BankInfo getBankInfo(String bankCode);

	List<BankInfo> pageBankInfo(int currPage, int pageSize);

	boolean existsBankName(String name);

	void saveBank(BankInfo info);

	boolean existsBankCode(String bank);

	void updateBankName(String bank, String name);

	void updateBankPresident(String bank, String president);

	void updateBankCompany(String bank, String company);
}
