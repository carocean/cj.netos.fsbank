package cj.netos.fsbank.program.reactor.valve.individual;

import java.util.HashMap;
import java.util.Map;

import cj.netos.fsbank.bs.IFSBankIndividualAccountAssetBS;
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

@CjService(name = "individual.depositBillCount")
public class DepositBillCountValve implements IValve {
	@CjServiceRef(refByName = "$.netos.informer")
	Informer informer;
	@CjServiceRef(refByName = "FSBAEngine.fSBankIndividualAccountAssetBS")
	IFSBankIndividualAccountAssetBS fSBankIndividualAccountAssetBS;

	@Override
	public void flow(Event e, IPipeline pipeline) throws CircuitException {
		String depositor = (String) e.getParameters().get("depositor");
		String informAddress = (String) e.getParameters().get("address");
		long depositBillCount = fSBankIndividualAccountAssetBS.depositBillCount(e.getKey(), depositor);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("depositBillCount", new Gson().toJson(depositBillCount));
		Frame frame = informer.createFrame(informAddress, map);
		MemoryOutputChannel oc = new MemoryOutputChannel();
		Circuit circuit = new Circuit(oc, "http/1.1 200 ok");
		informer.inform(frame, circuit);
	}

}
