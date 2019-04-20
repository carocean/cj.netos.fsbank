package cj.netos.fsbank.bs;

import cj.netos.fsbank.args.SeparateBillRuler;

public interface IFSBankSeparateBillRulerBS {
	static String TABLE_NAME = "rules";

	boolean hasRulerOfBank(String bank);

	void saveRuler(SeparateBillRuler ruler);

}
