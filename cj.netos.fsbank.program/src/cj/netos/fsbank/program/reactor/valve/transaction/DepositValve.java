package cj.netos.fsbank.program.reactor.valve.transaction;

import java.math.BigDecimal;

import cj.netos.fsbank.bs.IFSBankTransactionBS;
import cj.netos.fsbank.program.IBankCacher;
import cj.netos.inform.Informer;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.util.reactor.Event;
import cj.studio.util.reactor.IPipeline;
import cj.studio.util.reactor.IValve;

@CjService(name = "transaction.deposit")
public class DepositValve implements IValve {
	@CjServiceRef
	IBankCacher bankCacher;
	@CjServiceRef(refByName = "FSBAEngine.fSBankTransactionBS")
	IFSBankTransactionBS fSBankTransactionBS;
	@CjServiceRef(refByName = "$.netos.informer")
	Informer informer;
	@Override
	public void flow(Event e, IPipeline pipeline) {
		String depositor = (String) e.getParameters().get("depositor");
		BigDecimal amount = (BigDecimal) e.getParameters().get("amount");
		
		fSBankTransactionBS.depositBill(e.getKey(),depositor,amount);
	}

}
