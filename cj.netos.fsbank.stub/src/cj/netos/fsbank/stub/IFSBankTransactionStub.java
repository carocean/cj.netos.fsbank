package cj.netos.fsbank.stub;

import java.math.BigDecimal;

import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubService;

@CjStubService(bindService = "/transaction.service", usage = "金证银行交易服务")
public interface IFSBankTransactionStub {
	@CjStubMethod(usage = "存款，存款即投资")
	void deposit(@CjStubInParameter(key = "bank", usage = "银行标识") String bank,
			@CjStubInParameter(key = "depositor", usage = "存款人") String depositor,
			@CjStubInParameter(key = "amount", usage = "金额") BigDecimal amount,
			@CjStubInParameter(key = "rebateRate", usage = "要求此笔的返利率，返利率是相对于自由金率的比率,如果无返现可为null") BigDecimal rebateRate,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "提现")
	void cashout(@CjStubInParameter(key = "bank", usage = "银行标识") String bank,
			@CjStubInParameter(key = "balanceType", usage = "提现的余额类型，只有两种：余额表的自由金余额和尾金余额，分别是：freeBalance，tailBalance") String balanceType,
			@CjStubInParameter(key = "cashoutor", usage = "提现人") String cashoutor,
			@CjStubInParameter(key = "identity", usage = "提现人身份") String identity,
			@CjStubInParameter(key = "reqAmount", usage = "请求金额") BigDecimal reqAmount,
			@CjStubInParameter(key = "memo", usage = "备注") String memo,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "承兑债券")
	void exchange(@CjStubInParameter(key = "bank", usage = "银行标识") String bank,
			@CjStubInParameter(key = "exchanger", usage = "承兑人") String exchanger,
			@CjStubInParameter(key = "bondQuantities", usage = "承兑数量，单位来自于银行的拆单规则表") BigDecimal bondQuantities,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);
}
