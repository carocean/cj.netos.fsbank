package cj.netos.fsbank.stub;

import java.math.BigDecimal;

import cj.netos.fsbank.args.Balance;
import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubReturn;
import cj.studio.gateway.stub.annotation.CjStubService;
//仅开发读操作，在读的时候可能已有变化，所以读出的永远都是旧的
@CjStubService(bindService = "/balance.service", usage = "余额服务")
public interface IFSBankBalanceStub {
	@CjStubMethod(usage = "加载余额")
	@CjStubReturn(usage = "返回余额表", type = Balance.class)
	Balance loadBalance(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "返回债价")
	@CjStubReturn(usage = "债价", type = BigDecimal.class)
	BigDecimal getBondPrice(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "债种")
	String getBondKind(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "币种")
	String getCurrency(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "债金余额")
	BigDecimal getBondAmountBalance(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "债券存量")
	BigDecimal getBondQuantitiesBalance(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "准备金余额")
	BigDecimal getReserveAmountBalance(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "自由金余额")
	BigDecimal getFreeAmountBalance(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "尾金余额")
	BigDecimal getTailAmountBalance(@CjStubInParameter(key = "bank", usage = "银行") String bank);

}
