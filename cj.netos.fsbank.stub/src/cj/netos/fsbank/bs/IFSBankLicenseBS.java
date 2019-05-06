package cj.netos.fsbank.bs;

import java.util.List;

import cj.netos.fsbank.args.BankLicense;
import cj.studio.ecm.net.CircuitException;

public interface IFSBankLicenseBS {
	static String TABLE_LISENCE = "lisences";

	void saveLicense(String presidentPwd, BankLicense license) throws CircuitException;

	boolean hasLicenseOfBank(String bank);

	BankLicense getBankLicense(String bankCode);

	List<BankLicense> pageBankLicense(int currPage, int pageSize);

}
