package cj.netos.fsbank.stub;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cj.netos.fsbank.args.CashoutBill;
import cj.netos.fsbank.args.DepositBill;
import cj.netos.fsbank.args.ExchangeBill;
import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubReturn;
import cj.studio.gateway.stub.annotation.CjStubService;

@CjStubService(bindService = "/asset/individualAccount.service", usage = "个人账户资产服务")
public interface IFSBankIndividualAccountAssetStub {
	@CjStubMethod(usage = "用户债券余额")
	@CjStubReturn(type = BigDecimal.class, usage = "用户的债券余额")
	BigDecimal boudBalance(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "user", usage = "用户") String user);

	@CjStubMethod(usage = "存款笔数")
	long depositBillCount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "depositor", usage = "存款人") String depositor);

	@CjStubMethod(usage = "提现笔数")
	long cashoutBillCount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "cashoutor", usage = "提现人") String cashoutor,
			@CjStubInParameter(key = "identity", usage = "身份") String identity);

	@CjStubMethod(usage = "承兑笔数")
	long exchangeBillCount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "exchanger", usage = "承兑人") String exchanger);

	@CjStubMethod(usage = "存单分页")
	@CjStubReturn(elementType = DepositBill.class, type = ArrayList.class, usage = "一页数据")
	List<DepositBill> pageDepositBill(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "depositor", usage = "存款人") String depositor,
			@CjStubInParameter(key = "currPage", usage = "当前页号") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "页大小") int pageSize);

	@CjStubMethod(usage = "提现单分页")
	@CjStubReturn(elementType = CashoutBill.class, type = ArrayList.class, usage = "一页数据")
	List<CashoutBill> pageCashoutBill(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "cashoutor", usage = "提现人") String cashoutor,
			@CjStubInParameter(key = "identity", usage = "身份") String identity,
			@CjStubInParameter(key = "currPage", usage = "当前页号") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "页大小") int pageSize);

	@CjStubMethod(usage = "承兑单分页")
	@CjStubReturn(elementType = ExchangeBill.class, type = ArrayList.class, usage = "一页数据")
	List<ExchangeBill> pageExchangeBill(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "exchanger", usage = "承兑人") String exchanger,
			@CjStubInParameter(key = "currPage", usage = "当前页号") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "页大小") int pageSize);

	@CjStubMethod(usage = "提现单分页")
	@CjStubReturn(elementType = CashoutBill.class, type = ArrayList.class, usage = "一页数据")
	List<CashoutBill> pageCashoutBillByIdentity(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "identity", usage = "提现者身份") String identity,
			@CjStubInParameter(key = "currPage", usage = "当前页号") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "页大小") int pageSize);

	@CjStubMethod(usage = "存单总金额")
	BigDecimal totalDepositBillAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "depositor", usage = "存款人") String depositor);

	@CjStubMethod(usage = "存单总债数")
	BigDecimal totalDepositBondQuantities(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "depositor", usage = "存款人") String depositor);

	@CjStubMethod(usage = "存单总债金")
	BigDecimal totalDepositBondAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "depositor", usage = "存款人") String depositor);

	@CjStubMethod(usage = "存单总准备金")
	BigDecimal totalDepositReserveAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "depositor", usage = "存款人") String depositor);

	@CjStubMethod(usage = "存单总自由金")
	BigDecimal totalDepositFreeAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "depositor", usage = "存款人") String depositor);

	@CjStubMethod(usage = "总承兑债数")
	BigDecimal totalExchangeBondQuantities(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "exchanger", usage = "承兑人") String exchanger);

	@CjStubMethod(usage = "总承兑金")
	BigDecimal totalExchangeBillAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "exchanger", usage = "承兑人") String exchanger);

	@CjStubMethod(usage = "总承兑尾金")
	BigDecimal totalExchangeTailAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "exchanger", usage = "承兑人") String exchanger);

	@CjStubMethod(usage = "总提现请求金")
	BigDecimal totalCashoutReqAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "cashoutor", usage = "提现人") String cashoutor,
			@CjStubInParameter(key = "identity", usage = "身份") String identity);

	@CjStubMethod(usage = "总提现响应金")
	BigDecimal totalCashoutResAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "cashoutor", usage = "提现人") String cashoutor,
			@CjStubInParameter(key = "identity", usage = "身份") String identity);

	@CjStubMethod(usage = "总收续费")
	BigDecimal totalCashoutPoundageAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "cashoutor", usage = "提现人") String cashoutor,
			@CjStubInParameter(key = "identity", usage = "身份") String identity);

	@CjStubMethod(usage = "统计指定身份的总请求金")
	BigDecimal totalCashoutReqAmountByIdentity(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "identity", usage = "身份") String identity);

	@CjStubMethod(usage = "统计指定身份的总响应金")
	BigDecimal totalCashoutResAmountByIdentity(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "identity", usage = "身份") String identity);

	@CjStubMethod(usage = "统计指定身份的总手续费")
	BigDecimal totalCashoutPoundageAmountByIdentity(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "identity", usage = "身份") String identity);
}
