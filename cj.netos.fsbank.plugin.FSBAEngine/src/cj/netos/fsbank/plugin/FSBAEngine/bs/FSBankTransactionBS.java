package cj.netos.fsbank.plugin.FSBAEngine.bs;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.netos.fsbank.args.CashoutBill;
import cj.netos.fsbank.args.DepositBill;
import cj.netos.fsbank.args.ExchangeBill;
import cj.netos.fsbank.args.SeparateBillRuler;
import cj.netos.fsbank.args.SepareteBill;
import cj.netos.fsbank.bs.IFSBankBalanceBS;
import cj.netos.fsbank.bs.IFSBankPropertiesBS;
import cj.netos.fsbank.bs.IFSBankTransactionBS;
import cj.netos.fsbank.plugin.FSBAEngine.bo.BankProperty;
import cj.netos.fsbank.plugin.FSBAEngine.util.BigDecimalConstants;
import cj.studio.ecm.EcmException;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.annotation.CjServiceSite;
import cj.ultimate.util.StringUtil;

@CjService(name = "fSBankTransactionBS")
public class FSBankTransactionBS implements IFSBankTransactionBS {
	@CjServiceSite
	IServiceSite site;
	@CjServiceRef
	IFSBankBalanceBS fSBankBalance;
	@CjServiceRef
	IFSBankPropertiesBS fSBankPropertiesBS;
	ICube cubeBank;

	private ICube getBankCube(String bank) {
		if (cubeBank != null) {
			return cubeBank;
		}
		cubeBank = (ICube) site.getService("mongodb.fsbank." + bank + ":autocreate");
		return cubeBank;
	}

	@Override
	public void separateBill(String bank, String depositor, String currency, BigDecimal amount,
			SeparateBillRuler ruler) {
		DepositBill bill = new DepositBill();
		bill.setAmount(amount);
		if (StringUtil.isEmpty(currency)) {
			currency = "CNY";
		}
		bill.setCurrency(currency);
		bill.setDepositor(depositor);
		bill.setCode(null);
		bill.setDtime(System.currentTimeMillis());

		BigDecimal bondPrice = fSBankBalance.getBondPrice(bank);
		if (bondPrice == null) {
			bondPrice = new BigDecimal(0.001,
					new MathContext(BigDecimalConstants.setPrecision, BigDecimalConstants.roundingMode_forNewDecimal));
			bill.setCurrBondPrice(bondPrice);
		} else {
			bill.setCurrBondPrice(bondPrice);
		}

		// 拆单:如果全部已提现，则帑价不变而不是以0.001开始
		SepareteBill sbill = new SepareteBill();
		bill.setSepareteBill(sbill);

		sbill.setBondRate(ruler.getBondRate());
		sbill.setFreeMRate(ruler.getFreeMRate());
		sbill.setReserveRate(ruler.getReserveRate());
		sbill.setBondKind(ruler.getBondKind());

		sbill.setBondAmount(amount.multiply(ruler.getBondRate()));
		BigDecimal bondQuantities = sbill.getBondAmount().divide(bill.getCurrBondPrice(), BigDecimalConstants.scale,
				BigDecimalConstants.roundingMode);
		sbill.setBondQuantities(bondQuantities);

		sbill.setReserveAmount(amount.multiply(ruler.getReserveRate()));

		sbill.setFreeMAmount(amount.multiply(ruler.getFreeMRate()));

		bill.setTailAmount(amount.subtract(sbill.getBondAmount()).subtract(sbill.getReserveAmount())
				.subtract(sbill.getFreeMAmount()));

		BigDecimal tailAmountBalance = fSBankBalance.getTailAmountBalance(bank);
		if (tailAmountBalance == null) {
			tailAmountBalance = new BigDecimal(0.0,
					new MathContext(BigDecimalConstants.setPrecision, BigDecimalConstants.roundingMode_forNewDecimal));
		}
		tailAmountBalance = tailAmountBalance.add(bill.getTailAmount());

		BigDecimal freeAmountBalance = fSBankBalance.getFreeAmountBalance(bank);
		if (freeAmountBalance == null) {
			freeAmountBalance = new BigDecimal(0.0,
					new MathContext(BigDecimalConstants.setPrecision, BigDecimalConstants.roundingMode_forNewDecimal));
		}
		freeAmountBalance = freeAmountBalance.add(sbill.getFreeMAmount());

		BigDecimal bondAmountBalance = fSBankBalance.getBondAmountBalance(bank);
		if (bondAmountBalance == null) {
			bondAmountBalance = new BigDecimal(0.0,
					new MathContext(BigDecimalConstants.setPrecision, BigDecimalConstants.roundingMode_forNewDecimal));
		}
		BigDecimal reserveAmountBalance = fSBankBalance.getReserveAmountBalance(bank);
		if (reserveAmountBalance == null) {
			reserveAmountBalance = new BigDecimal(0.0,
					new MathContext(BigDecimalConstants.setPrecision, BigDecimalConstants.roundingMode_forNewDecimal));
		}
		BigDecimal storedAmountBalance = bondAmountBalance.add(reserveAmountBalance).add(sbill.getBondAmount())
				.add(sbill.getReserveAmount());

		BigDecimal bondQuantitiesBalance = fSBankBalance.getBondQuantitiesBalance(bank);
		if (bondQuantitiesBalance == null) {
			bondQuantitiesBalance = new BigDecimal(0.0,
					new MathContext(BigDecimalConstants.setPrecision, BigDecimalConstants.roundingMode_forNewDecimal));
		}

		bondQuantitiesBalance = bondQuantitiesBalance.add(sbill.getBondQuantities());

		BigDecimal newBondPrice = storedAmountBalance.divide(bondQuantitiesBalance, BigDecimalConstants.scale,
				BigDecimalConstants.roundingMode);
		bill.setNewBondPrice(newBondPrice);
		fSBankBalance.updateBondPrice(bank, newBondPrice);
		fSBankBalance.updateBondAmountBalance(bank, bondAmountBalance.add(sbill.getBondAmount()));
		fSBankBalance.updateReserveAmountBalance(bank, reserveAmountBalance.add(sbill.getReserveAmount()));
		fSBankBalance.updateBondQuantitiesBalance(bank, bondQuantitiesBalance);
		fSBankBalance.updateTailAmountBalance(bank, tailAmountBalance);
		fSBankBalance.updateFreeAmountBalance(bank, freeAmountBalance);
		String bondKind = fSBankBalance.getBondKind(bank);
		if (StringUtil.isEmpty(bondKind)) {
			bondKind = ruler.getBondKind();
			if (!StringUtil.isEmpty(bondKind)) {
				sbill.setBondKind(bondKind);
				fSBankBalance.updateBondKind(bank, bondKind);
			}
		}
		String existscurrency = fSBankBalance.getCurrency(bank);
		if (StringUtil.isEmpty(existscurrency)) {
			fSBankBalance.updateCurrency(bank, currency);
		}
		ICube cubeBank = getBankCube(bank);
		String id = cubeBank.saveDoc(TABLE_Deposits, new TupleDocument<>(bill));
		bill.setCode(id);
	}

	@Override
	public void cashoutBill(String bank, String balanceType, String cashoutor, String identity, BigDecimal reqAmount,
			String memo) {
		if (StringUtil.isEmpty(balanceType)) {
			throw new EcmException("余额类型为空");
		}
		switch (balanceType) {
		case "freeBalance":
			cashoutFreeBalance(bank, cashoutor, identity, reqAmount, memo);
			break;
		case "tailBalance":
			cashoutTailBalance(bank, cashoutor, identity, reqAmount, memo);
			break;
		default:
			throw new EcmException("不支持的提现目标：" + balanceType);
		}

	}

	private void cashoutTailBalance(String bank, String cashoutor, String identity, BigDecimal reqAmount, String memo) {
		BigDecimal tailAmountBalance = fSBankBalance.getTailAmountBalance(bank);
		if (tailAmountBalance == null
				|| tailAmountBalance.compareTo(new BigDecimal(0, new MathContext(10, RoundingMode.FLOOR))) <= 0) {
			throw new EcmException(String.format("银行:%s 无现可提", bank));
		}
		if (tailAmountBalance.compareTo(reqAmount) <= 0) {
			throw new EcmException(String.format("银行:%s 现金存量不足", bank));
		}
		String poundageRate = fSBankPropertiesBS.get(bank, BankProperty.CONSTANS_KEY_poundageRate);// 手续费率
		if (StringUtil.isEmpty(poundageRate)) {
			poundageRate = "0";
		}
		String precision = fSBankPropertiesBS.get(bank, BankProperty.CONSTANS_KEY_bigDecimal_setPrecision);
		if (StringUtil.isEmpty(precision)) {
			precision = BigDecimalConstants.setPrecision + "";
		}
		String roundingMode = fSBankPropertiesBS.get(bank, BankProperty.CONSTANS_KEY_bigDecimal_roundingMode);
		if (StringUtil.isEmpty(roundingMode)) {
			roundingMode = BigDecimalConstants.roundingMode_forNewDecimal + "";
		}
		CashoutBill bill = new CashoutBill();
		bill.setCashoutor(cashoutor);
		bill.setCode(null);
		bill.setCtime(System.currentTimeMillis());
		bill.setIdentity(identity);
		bill.setMemo(memo);
		bill.setReqAmount(reqAmount);
		bill.setPoundageRate(new BigDecimal(poundageRate,
				new MathContext(Integer.valueOf(precision), RoundingMode.valueOf(roundingMode))));
		bill.setPoundageAmount(bill.getReqAmount().multiply(bill.getPoundageRate()));// 为提现*手续费义
		bill.setResAmount(bill.getReqAmount().subtract(bill.getPoundageAmount()));
		tailAmountBalance = tailAmountBalance.subtract(bill.getReqAmount());
		bill.setBalanceType("tailBalance");
		bill.setBalance(tailAmountBalance);
		fSBankBalance.updateTailAmountBalance(bank, tailAmountBalance);
	}

	private void cashoutFreeBalance(String bank, String cashoutor, String identity, BigDecimal reqAmount, String memo) {
		BigDecimal freeAmountBalance = fSBankBalance.getFreeAmountBalance(bank);
		if (freeAmountBalance == null
				|| freeAmountBalance.compareTo(new BigDecimal(0, new MathContext(10, RoundingMode.FLOOR))) <= 0) {
			throw new EcmException(String.format("银行:%s 无现可提", bank));
		}
		if (freeAmountBalance.compareTo(reqAmount) <= 0) {
			throw new EcmException(String.format("银行:%s 现金存量不足", bank));
		}
		String poundageRate = fSBankPropertiesBS.get(bank, BankProperty.CONSTANS_KEY_poundageRate);// 手续费率
		if (StringUtil.isEmpty(poundageRate)) {
			poundageRate = "0";
		}
		String precision = fSBankPropertiesBS.get(bank, BankProperty.CONSTANS_KEY_bigDecimal_setPrecision);
		if (StringUtil.isEmpty(precision)) {
			precision = BigDecimalConstants.setPrecision + "";
		}
		String roundingMode = fSBankPropertiesBS.get(bank, BankProperty.CONSTANS_KEY_bigDecimal_roundingMode);
		if (StringUtil.isEmpty(roundingMode)) {
			roundingMode = BigDecimalConstants.roundingMode_forNewDecimal + "";
		}
		CashoutBill bill = new CashoutBill();
		bill.setCashoutor(cashoutor);
		bill.setCode(null);
		bill.setCtime(System.currentTimeMillis());
		bill.setIdentity(identity);
		bill.setMemo(memo);
		bill.setReqAmount(reqAmount);
		bill.setPoundageRate(new BigDecimal(poundageRate,
				new MathContext(Integer.valueOf(precision), RoundingMode.valueOf(roundingMode))));
		bill.setPoundageAmount(bill.getReqAmount().multiply(bill.getPoundageRate()));// 为提现*手续费义
		bill.setResAmount(bill.getReqAmount().subtract(bill.getPoundageAmount()));
		freeAmountBalance = freeAmountBalance.subtract(bill.getReqAmount());
		bill.setBalanceType("freeBalance");
		bill.setBalance(freeAmountBalance);
		fSBankBalance.updateFreeAmountBalance(bank, freeAmountBalance);
		String id=getBankCube(bank).saveDoc(TABLE_Cashouts, new TupleDocument<>(bill));
		bill.setCode(id);
	}
	
	@Override
	public void exchangeBill(String bank, String exchanger, BigDecimal bondQuantities) {
		ExchangeBill bill=new ExchangeBill();
		
	}

}
