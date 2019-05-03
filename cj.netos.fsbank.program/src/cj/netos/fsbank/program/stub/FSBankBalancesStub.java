package cj.netos.fsbank.program.stub;

import java.math.BigDecimal;

import cj.netos.fsbank.args.Balance;
import cj.netos.fsbank.bs.IFSBankBalanceBS;
import cj.netos.fsbank.stub.IFSBankBalanceStub;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;

@CjService(name = "/balance.service")
public class FSBankBalancesStub extends GatewayAppSiteRestStub implements IFSBankBalanceStub {
	@CjServiceRef(refByName = "FSBAEngine.fSBankBalance")
	IFSBankBalanceBS fSBankBalance;

	@Override
	public Balance loadBalance(String bank) {
		return fSBankBalance.loadBalance(bank);
	}

	@Override
	public BigDecimal getBondPrice(String bank) {
		return fSBankBalance.getBondPrice(bank);
	}


	@Override
	public BigDecimal getBondQuantitiesBalance(String bank) {
		return fSBankBalance.getBondQuantitiesBalance(bank);
	}

	@Override
	public BigDecimal getFreeAmountBalance(String bank) {
		return fSBankBalance.getFreeAmountBalance(bank);
	}

	@Override
	public BigDecimal getTailAmountBalance(String bank) {
		return fSBankBalance.getTailAmountBalance(bank);
	}

	@Override
	public BigDecimal getFreezeAmountBalance(String bank) {
		return fSBankBalance.getFreezeAmountBalance(bank);
	}

}
