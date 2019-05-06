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

@CjStubService(bindService = "/asset/bank.service", usage = "银行资产服务")
public interface IBankAssetStub {
	@CjStubMethod(usage = "存单总数")
	long depositBillCount(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "提现单总数")
	long cashBillCount(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "承兑单总数")
	long exchangeBillCount(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "存单分页")
	@CjStubReturn(type = ArrayList.class, elementType = DepositBill.class, usage = "列表")
	List<DepositBill> pageDepositBill(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "currPage", usage = "当前分页") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "分页大小") int pageSize);

	@CjStubMethod(usage = "提现单分页")
	@CjStubReturn(type = ArrayList.class, elementType = CashoutBill.class, usage = "列表")
	List<CashoutBill> pageCashoutBill(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "currPage", usage = "当前分页") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "分页大小") int pageSize);

	@CjStubMethod(usage = "承兑单分页")
	@CjStubReturn(type = ArrayList.class, elementType = ExchangeBill.class, usage = "列表")
	List<ExchangeBill> pageExchangeBill(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "currPage", usage = "当前分页") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "分页大小") int pageSize);

	@CjStubMethod(usage = "统计银行总存入金额")
	BigDecimal totalDepositBillAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "统计银行总发债量")
	BigDecimal totalDepositBondQuantities(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "统计银行总发债金额")
	BigDecimal totalDepositBondAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "统计银行总准备金额")
	BigDecimal totalDepositReserveAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "统计银行总自由金额")
	BigDecimal totalDepositFreeAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "统计银行总承兑的债量")
	BigDecimal totalExchangeBondQuantities(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "统计银行总承兑的金额")
	BigDecimal totalExchangeBillAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "统计银行总尾金金额")
	BigDecimal totalExchangeTailAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "统计银行总请求提现金额")
	BigDecimal totalCashoutReqAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "统计银行总实际晌应金额")
	BigDecimal totalCashoutResAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank);

	@CjStubMethod(usage = "统计银行总手续费收取金额")
	BigDecimal totalCashoutPoundageAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank);
}
