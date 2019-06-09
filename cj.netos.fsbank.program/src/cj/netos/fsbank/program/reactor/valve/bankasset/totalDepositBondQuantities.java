package cj.netos.fsbank.program.reactor.valve.bankasset;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import cj.netos.fsbank.bs.IBankAssetBS;
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

@CjService(name = "bankasset.totalDepositBondQuantities")
public class totalDepositBondQuantities implements IValve {
	@CjServiceRef(refByName = "$.netos.informer")
	Informer informer;
	@CjServiceRef(refByName = "FSBAEngine.bankAssetBS")
	IBankAssetBS bankAssetBS;

	@Override
	public void flow(Event e, IPipeline pipeline) throws CircuitException {
		String informAddress = (String) e.getParameters().get("address");
		BigDecimal totalDepositBondQuantities = bankAssetBS.totalDepositBondQuantities(e.getKey());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalDepositBondQuantities", new Gson().toJson(totalDepositBondQuantities));
		Frame frame = informer.createFrame(informAddress, map);
		MemoryOutputChannel oc = new MemoryOutputChannel();
		Circuit circuit = new Circuit(oc, "http/1.1 200 ok");
		informer.inform(frame, circuit);
	}

}