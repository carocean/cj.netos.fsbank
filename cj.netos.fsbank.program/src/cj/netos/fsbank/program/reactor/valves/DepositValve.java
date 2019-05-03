package cj.netos.fsbank.program.reactor.valves;

import java.math.BigDecimal;

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
		String depositor = (String) e.getParameters().get("depositor");
		BigDecimal amount = (BigDecimal) e.getParameters().get("amount");
		
		fSBankTransactionBS.depositBill(e.getKey(),depositor,amount);
	}

}
