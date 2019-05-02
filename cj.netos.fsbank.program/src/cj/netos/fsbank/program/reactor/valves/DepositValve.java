package cj.netos.fsbank.program.reactor.valves;

import java.math.BigDecimal;

import cj.netos.fsbank.args.SeparateBillRuler;
import cj.netos.fsbank.bs.IFSBankTransactionBS;
import cj.netos.fsbank.program.IBankCacher;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.util.reactor.Event;
import cj.studio.util.reactor.IPipeline;
import cj.studio.util.reactor.IValve;

@CjService(name = "deposit")
public class DepositValve implements IValve {
	@CjServiceRef
	IBankCacher bankCacher;
	@CjServiceRef(refByName = "FSBAEngine.fSBankTransactionBS")
	IFSBankTransactionBS fSBankTransactionBS;

	@Override
	public void flow(Event e, IPipeline pipeline) {
		SeparateBillRuler ruler = bankCacher.getBankRuler(e.getKey());
		String depositor = (String) e.getParameters().get("depositor");
		String currency = (String) e.getParameters().get("currency");
		BigDecimal amount = (BigDecimal) e.getParameters().get("amount");
		
		fSBankTransactionBS.separateBill(e.getKey(),depositor,currency,amount, ruler);
	}

}
