package cj.netos.fsbank.program;

import java.util.HashMap;
import java.util.Map;

import cj.netos.fsbank.args.SeparateBillRuler;
import cj.netos.fsbank.bs.IFSBankSeparateBillRulerBS;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;

@CjService(name = "bankCacher")
public class BankCacher implements IBankCacher {
	@CjServiceRef(refByName = "FSBAEngine.fSBankSeparateBillBS")
	IFSBankSeparateBillRulerBS ruler;
	Map<String, SeparateBillRuler> rules;

	public BankCacher() {
		rules = new HashMap<String, SeparateBillRuler>();
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

}
