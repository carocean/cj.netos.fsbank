package cj.netos.fsbank.program.reactor.valves;

import java.math.BigDecimal;

import cj.netos.fsbank.bs.IFSBankTransactionBS;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.util.reactor.Event;
import cj.studio.util.reactor.IPipeline;
import cj.studio.util.reactor.IValve;

@CjService(name = "exchange")
public class ExchangeValve implements IValve {
	@CjServiceRef(refByName = "FSBAEngine.fSBankTransactionBS")
	IFSBankTransactionBS fSBankTransactionBS;

	@Override
	public void flow(Event e, IPipeline pipeline) {
		String exchanger = (String) e.getParameters().get("exchanger");
		BigDecimal bondQuantities = (BigDecimal) e.getParameters().get("bondQuantities");
		
		fSBankTransactionBS.exchangeBill(e.getKey(), exchanger,bondQuantities);
	}

}
