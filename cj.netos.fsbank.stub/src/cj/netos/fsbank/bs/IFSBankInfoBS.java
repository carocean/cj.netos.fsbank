package cj.netos.fsbank.bs;

import java.util.List;

import cj.netos.fsbank.args.BankInfo;
import cj.netos.fsbank.args.BankLicense;

public interface IFSBankInfoBS {
	static String TABLE_BANK_INFO="banks";
	void deregisterBank(String bankCode);

	void freezeBank(String bankCode);

	void pauseBank(String bankCode);

	void resumeBank(String bankCode);

	BankInfo getBankInfo(String bankCode);

	BankLicense getBankLicense(String bankCode);

	List<BankInfo> pageBankInfo(int currPage, int pageSize);

	List<BankLicense> pageBankLicense(int currPage, int pageSize);

	boolean existsBankName(String name);

	void saveBank(BankInfo info);

	boolean existsBankCode(String bank);
}
