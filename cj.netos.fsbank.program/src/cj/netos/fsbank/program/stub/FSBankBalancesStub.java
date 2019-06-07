package cj.netos.fsbank.program.stub;

import cj.netos.fsbank.stub.IFSBankBalanceStub;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceSite;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
import cj.studio.util.reactor.Event;
import cj.studio.util.reactor.IReactor;

@CjService(name = "/balance.service")
public class FSBankBalancesStub extends GatewayAppSiteRestStub implements IFSBankBalanceStub {
	@CjServiceSite
	IServiceSite site;
	IReactor reactor;
	
	protected IReactor getReactor() {
		if (reactor == null) {
			reactor = (IReactor) site.getService("$.reactor");
		}
		return reactor;
	}

	@Override
	public void loadBalance(String bank, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "balance.loadBalance");
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void getBondPrice(String bank, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "balance.getBondPrice");
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void getBondQuantitiesBalance(String bank, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "balance.getBondQuantitiesBalance");
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void getFreeAmountBalance(String bank, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "balance.getFreeAmountBalance");
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void getTailAmountBalance(String bank, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "balance.getTailAmountBalance");
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

	@Override
	public void getFreezeAmountBalance(String bank, String informAddress) {
		IReactor reactor = getReactor();
		Event e = new Event(bank, "balance.getFreezeAmountBalance");
		e.getParameters().put("address", informAddress);
		reactor.input(e);
	}

}
