package cj.netos.fsbank.program;

import cj.netos.fsbank.args.BankInfo;
import cj.netos.fsbank.args.SeparateBillRuler;

public interface IBankCacher {

	SeparateBillRuler getBankRuler(String bank);

	BankInfo getBankInfo(String bank);

}
