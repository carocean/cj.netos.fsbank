package cj.netos.fsbank.program.reactor.valve.bankasset;

import java.util.HashMap;
import java.util.List;
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

@CjService(name = "bankasset.pageDepositBill")
public class PageDepositBillVavle implements IValve {
	@CjServiceRef(refByName = "$.netos.informer")
	Informer informer;
	@CjServiceRef(refByName = "FSBAEngine.bankAssetBS")
	IBankAssetBS bankAssetBS;

	@Override
	public void flow(Event e, IPipeline pipeline) throws CircuitException {
		String informAddress = (String) e.getParameters().get("address");
		int currPage = (int) e.getParameters().get("currPage");
		int pageSize = (int) e.getParameters().get("pageSize");
		List<?> depositBills = bankAssetBS.pageDepositBill(e.getKey(),currPage,pageSize);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("depositBills", new Gson().toJson(depositBills));
		map.put("currPage", currPage);
		map.put("pageSize", pageSize);
		Frame frame = informer.createFrame(informAddress, map);
		MemoryOutputChannel oc = new MemoryOutputChannel();
		Circuit circuit = new Circuit(oc, "http/1.1 200 ok");
		informer.inform(frame, circuit);
	}

}
