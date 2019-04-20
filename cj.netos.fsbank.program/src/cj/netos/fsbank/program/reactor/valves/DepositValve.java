package cj.netos.fsbank.program.reactor.valves;

import cj.netos.fsbank.args.SeparateBillRuler;
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

	@Override
	public void flow(Event e, IPipeline pipeline) {
		SeparateBillRuler ruler = bankCacher.getBankRuler(e.getKey());
		System.out.println("-----" + e.getCmd()+" "+ruler);
	}

}
