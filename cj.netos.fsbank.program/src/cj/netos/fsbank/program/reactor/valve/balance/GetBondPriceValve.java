package cj.netos.fsbank.program.reactor.valve.balance;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import cj.netos.fsbank.bs.IFSBankBalanceBS;
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
import cj.ultimate.gson2.com.google.gson.Gson;

@CjService(name = "balance.getBondPrice")
public class GetBondPriceValve implements IValve {
	@CjServiceRef(refByName = "$.netos.informer")
	Informer informer;
	@CjServiceRef(refByName = "FSBAEngine.fSBankBalance")
	IFSBankBalanceBS fSBankBalance;

	@Override
	public void flow(Event e, IPipeline pipeline) throws CircuitException {
		String informAddress = (String) e.getParameters().get("address");
		BigDecimal bondPrice = fSBankBalance.getBondPrice(e.getKey());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bondPrice", new Gson().toJson(bondPrice));
		Frame frame = informer.createFrame(informAddress, map);
		MemoryOutputChannel oc = new MemoryOutputChannel();
		Circuit circuit = new Circuit(oc, "http/1.1 200 ok");
		informer.inform(frame, circuit);
	}

}
