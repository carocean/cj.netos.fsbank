package cj.netos.fsbank.program;

import java.util.HashMap;
import java.util.Map;

import cj.netos.fsbank.args.BankInfo;
import cj.netos.fsbank.args.SeparateBillRuler;
import cj.netos.fsbank.bs.IFSBankInfoBS;
import cj.netos.fsbank.bs.IFSBankSeparateBillRulerBS;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;

@CjService(name = "bankCacher")
public class BankCacher implements IBankCacher {
	@CjServiceRef(refByName = "FSBAEngine.fSBankSeparateBillBS")
	IFSBankSeparateBillRulerBS ruler;
	@CjServiceRef(refByName = "FSBAEngine.fSBankInfoBS")
	IFSBankInfoBS fSBankInfoBS;
	Map<String, SeparateBillRuler> rules;
	Map<String, BankInfo> bankInfos;

	public BankCacher() {
		rules = new HashMap<String, SeparateBillRuler>();
		bankInfos = new HashMap<String, BankInfo>();
	}

	@Override
	public SeparateBillRuler getBankRuler(String bank) {
		SeparateBillRuler r = rules.get(bank);
		if (r != null) {
			return r;
		}
		r = ruler.getRuler(bank);
		rules.put(bank, r);
		return r;
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
