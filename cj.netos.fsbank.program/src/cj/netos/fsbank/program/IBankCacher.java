package cj.netos.fsbank.program;

import cj.netos.fsbank.args.SeparateBillRuler;

public interface IBankCacher {

	SeparateBillRuler getBankRuler(String key);

}
