package cj.netos.fsbank.program.reactor.valves;

import cj.studio.ecm.annotation.CjService;
import cj.studio.util.reactor.Event;
import cj.studio.util.reactor.IPipeline;
import cj.studio.util.reactor.IValve;
@CjService(name = "deposit")
public class DepositValve implements IValve {

	@Override
	public void flow(Event e, IPipeline pipeline) {
		// TODO Auto-generated method stub
		System.out.println("-----"+e.getCmd());
	}

}
