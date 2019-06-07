package cj.netos.fsbank.program.reactor.valve.balance;

import java.util.HashMap;
import java.util.Map;

import cj.netos.fsbank.args.Balance;
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

@CjService(name = "balance.loadBalance")
public class LoadBalanceValve implements IValve {
	@CjServiceRef(refByName = "FSBAEngine.fSBankBalance")
	IFSBankBalanceBS fSBankBalance;
	@CjServiceRef(refByName = "$.netos.informer")
	Informer informer;

	@Override
	public void flow(Event e, IPipeline pipeline) throws CircuitException {
		String informAddress = (String) e.getParameters().get("address");
		Balance balance = fSBankBalance.loadBalance(e.getKey());
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("balance", new Gson().toJson(balance));
		Frame frame = informer.createFrame(informAddress, new Gson().toJson(map).getBytes());
		MemoryOutputChannel oc = new MemoryOutputChannel();
		Circuit circuit = new Circuit(oc, "http/1.1 200 ok");
		informer.inform(frame, circuit);
	}

}
