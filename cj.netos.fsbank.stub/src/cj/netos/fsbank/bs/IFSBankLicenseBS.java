package cj.netos.fsbank.bs;

import cj.netos.fsbank.args.BankLicense;
import cj.studio.ecm.net.CircuitException;

public interface IFSBankLicenseBS {
static String TABLE_LISENCE="lisences";
	void saveLicense(String presidentPwd,BankLicense license) throws CircuitException;

	boolean hasLicenseOfBank(String bank);

}
