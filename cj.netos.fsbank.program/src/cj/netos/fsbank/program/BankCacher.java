package cj.netos.fsbank.program;

import java.util.HashMap;
import java.util.Map;

import cj.netos.fsbank.args.BankInfo;
import cj.netos.fsbank.bs.IFSBankInfoBS;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;

@CjService(name = "bankCacher")
public class BankCacher implements IBankCacher {
	@CjServiceRef(refByName = "FSBAEngine.fSBankInfoBS")
	IFSBankInfoBS fSBankInfoBS;
	Map<String, BankInfo> bankInfos;

	public BankCacher() {
		bankInfos = new HashMap<String, BankInfo>();
	}


	@Override
	public BankInfo getBankInfo(String bank) {
		BankInfo r = bankInfos.get(bank);
		if (r != null) {
			return r;
		}
		r = fSBankInfoBS.getBankInfo(bank);
		bankInfos.put(bank, r);
		return r;
	}

}
