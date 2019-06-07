package cj.netos.fsbank.program.reactor.valve.transaction;

import java.math.BigDecimal;
import java.util.Map;

import cj.netos.fsbank.bs.IFSBankTransactionBS;
import cj.netos.inform.Informer;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.Circuit;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;
import cj.studio.ecm.net.io.MemoryOutputChannel;
import cj.studio.util.reactor.Event;
import cj.studio.util.reactor.IPipeline;
import cj.studio.util.reactor.IValve;

@CjService(name = "transaction.exchange")
public class ExchangeValve implements IValve {
	@CjServiceRef(refByName = "FSBAEngine.fSBankTransactionBS")
	IFSBankTransactionBS fSBankTransactionBS;
	@CjServiceRef(refByName = "$.netos.informer")
	Informer informer;
	@Override
	public void flow(Event e, IPipeline pipeline) throws CircuitException {
		String exchanger = (String) e.getParameters().get("exchanger");
		BigDecimal bondQuantities = (BigDecimal) e.getParameters().get("bondQuantities");
		String informAddress = (String) e.getParameters().get("address");
		Map<String, Object> map =fSBankTransactionBS.exchangeBill(e.getKey(), exchanger,bondQuantities);
		
		Frame frame = informer.createFrame(informAddress, map);
		MemoryOutputChannel oc = new MemoryOutputChannel();
		Circuit circuit = new Circuit(oc, "http/1.1 200 ok");
		informer.inform(frame, circuit);
	}

}
