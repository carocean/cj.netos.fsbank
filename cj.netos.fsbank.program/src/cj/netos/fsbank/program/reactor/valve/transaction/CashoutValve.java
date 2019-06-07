package cj.netos.fsbank.program.reactor.valve.transaction;

import java.math.BigDecimal;

import cj.netos.fsbank.bs.IFSBankTransactionBS;
import cj.netos.inform.Informer;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.util.reactor.Event;
import cj.studio.util.reactor.IPipeline;
import cj.studio.util.reactor.IValve;

@CjService(name = "transaction.cashout")
public class CashoutValve implements IValve {
	@CjServiceRef(refByName = "$.netos.informer")
	Informer informer;
	@CjServiceRef(refByName = "FSBAEngine.fSBankTransactionBS")
	IFSBankTransactionBS fSBankTransactionBS;

	@Override
	public void flow(Event e, IPipeline pipeline) {
		String cashoutor = (String) e.getParameters().get("cashoutor");
		String identity = (String) e.getParameters().get("identity");
		String memo = (String) e.getParameters().get("memo");
		String balanceType = (String) e.getParameters().get("balanceType");
		BigDecimal reqAmount = (BigDecimal) e.getParameters().get("reqAmount");
		fSBankTransactionBS.cashoutBill(e.getKey(), balanceType, cashoutor, identity, reqAmount, memo);
	}

}
