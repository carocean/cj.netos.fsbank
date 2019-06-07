package cj.netos.fsbank.plugin.FSBAEngine.bs;

import java.math.BigDecimal;
import java.math.RoundingMode;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.netos.fsbank.args.CashoutBill;
import cj.netos.fsbank.args.DepositBill;
import cj.netos.fsbank.args.ExchangeBill;
import cj.netos.fsbank.args.SepareteBill;
import cj.netos.fsbank.bs.IFSBankBalanceBS;
import cj.netos.fsbank.bs.IFSBankIndividualAccountAssetBS;
import cj.netos.fsbank.bs.IFSBankPropertiesBS;
import cj.netos.fsbank.bs.IFSBankTransactionBS;
import cj.netos.fsbank.plugin.FSBAEngine.util.BigDecimalConstants;
import cj.studio.ecm.EcmException;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.annotation.CjServiceSite;
import cj.ultimate.util.StringUtil;

@CjService(name = "fSBankTransactionBS")
public class FSBankTransactionBS implements IFSBankTransactionBS, BigDecimalConstants {
	@CjServiceSite
	IServiceSite site;
	@CjServiceRef
	IFSBankBalanceBS fSBankBalance;
	@CjServiceRef
	IFSBankPropertiesBS fSBankPropertiesBS;
	@CjServiceRef
	IFSBankIndividualAccountAssetBS fSBankIndividualAccountAssetBS;
	ICube cubeBank;

	private ICube getBankCube(String bank) {
		if (cubeBank != null) {
			return cubeBank;
		}
		cubeBank = (ICube) site.getService("mongodb.fsbank." + bank + ":autocreate");
		return cubeBank;
	}

	@Override
	public void depositBill(String bank, String depositor, BigDecimal amount) {
		DepositBill bill = new DepositBill();
		bill.setAmount(amount);
		bill.setDepositor(depositor);
		bill.setCode(null);
		bill.setDtime(System.currentTimeMillis());

		BigDecimal bondPrice = fSBankBalance.getBondPrice(bank);
		if (bondPrice == null) {
			bondPrice = ultimateBondPrice(fSBankPropertiesBS, bank);
			fSBankBalance.updateBondPrice(bank, bondPrice);
		}
		bill.setCurrBondPrice(bondPrice);
		// 拆单:如果全部已提现，则帑价不变而不是以0.001开始
		SepareteBill sbill = new SepareteBill();
		bill.setSepareteBill(sbill);
		BigDecimal bondRate = bondRate(fSBankPropertiesBS, bank);
		BigDecimal freeRate = freeRate(fSBankPropertiesBS, bank);
		BigDecimal reserveRate = reserveRate(fSBankPropertiesBS, bank);
		sbill.setBondRate(bondRate);
		sbill.setFreeRate(freeRate);
		sbill.setReserveRate(reserveRate);

		int scale = bigDecimalScale(fSBankPropertiesBS, bank);
		RoundingMode roundingMode = bigDecimalRoundingMode(fSBankPropertiesBS, bank);

		sbill.setBondAmount(amount.multiply(bondRate).setScale(scale, roundingMode));

		BigDecimal bondQuantities = sbill.getBondAmount().divide(bill.getCurrBondPrice(), scale, roundingMode);
		sbill.setBondQuantities(bondQuantities);
		
		sbill.setReserveAmount(amount.multiply(reserveRate).setScale(scale, roundingMode));

		sbill.setFreeAmount(amount.multiply(freeRate).setScale(scale, roundingMode));

		bill.setTailAmount(amount.subtract(sbill.getBondAmount()).subtract(sbill.getReserveAmount())
				.subtract(sbill.getFreeAmount()));

		BigDecimal tailAmountBalance = fSBankBalance.getTailAmountBalance(bank);
		if (tailAmountBalance == null) {
			tailAmountBalance = new BigDecimal(0.0).setScale(scale, roundingMode);
		}
		tailAmountBalance = tailAmountBalance.add(bill.getTailAmount());
		fSBankBalance.updateTailAmountBalance(bank, tailAmountBalance);

		BigDecimal freeAmountBalance = fSBankBalance.getFreeAmountBalance(bank);
		if (freeAmountBalance == null) {
			freeAmountBalance = new BigDecimal(0.0).setScale(scale, roundingMode);
		}
		freeAmountBalance = freeAmountBalance.add(sbill.getFreeAmount());
		fSBankBalance.updateFreeAmountBalance(bank, freeAmountBalance);

		BigDecimal freezeAmountBalance = fSBankBalance.getFreezeAmountBalance(bank);
		if (freezeAmountBalance == null) {
			freezeAmountBalance = new BigDecimal(0.0).setScale(scale, roundingMode);
		}
		freezeAmountBalance = freezeAmountBalance.add(sbill.getBondAmount().add(sbill.getReserveAmount()));
		fSBankBalance.updateFreezeAmountBalance(bank, freezeAmountBalance);

		BigDecimal bondQuantitiesBalance = fSBankBalance.getBondQuantitiesBalance(bank);
		if (bondQuantitiesBalance == null) {
			bondQuantitiesBalance = new BigDecimal(0.0).setScale(scale, roundingMode);
		}
		bondQuantitiesBalance = bondQuantitiesBalance.add(sbill.getBondQuantities());
		fSBankBalance.updateBondQuantitiesBalance(bank, bondQuantitiesBalance);

		BigDecimal newBondPrice = freezeAmountBalance.divide(bondQuantitiesBalance, scale, roundingMode);
		bill.setNewBondPrice(newBondPrice);
		fSBankBalance.updateBondPrice(bank, newBondPrice);

		ICube cubeBank = getBankCube(bank);
		String id = cubeBank.saveDoc(TABLE_Deposits, new TupleDocument<>(bill));
		bill.setCode(id);
		
		BigDecimal individual=this.fSBankIndividualAccountAssetBS.bondBalance(bank, depositor);
		this.fSBankIndividualAccountAssetBS.updateBoundBalance(bank,depositor,bondQuantities.add(individual));
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
		if (tailAmountBalance == null || tailAmountBalance.compareTo(new BigDecimal(0)) <= 0) {
			throw new EcmException(String.format("银行:%s 无现可提", bank));
		}
		if (tailAmountBalance.compareTo(reqAmount) <= 0) {
			throw new EcmException(String.format("银行:%s 现金存量不足", bank));
		}
		BigDecimal poundageRate = poundageRate(fSBankPropertiesBS, bank);
		int scale = bigDecimalScale(fSBankPropertiesBS, bank);
		RoundingMode roundingMode = bigDecimalRoundingMode(fSBankPropertiesBS, bank);

		CashoutBill bill = new CashoutBill();
		bill.setCashoutor(cashoutor);
		bill.setCode(null);
		bill.setCtime(System.currentTimeMillis());
		bill.setIdentity(identity);
		bill.setMemo(memo);
		bill.setReqAmount(reqAmount);
		bill.setPoundageRate(poundageRate);
		bill.setPoundageAmount(bill.getReqAmount().multiply(bill.getPoundageRate()).setScale(scale, roundingMode));// 为提现*手续费义
		bill.setResAmount(bill.getReqAmount().subtract(bill.getPoundageAmount()));
		tailAmountBalance = tailAmountBalance.subtract(bill.getReqAmount());
		bill.setBalanceType("tailBalance");
		bill.setBalance(tailAmountBalance);
		fSBankBalance.updateTailAmountBalance(bank, tailAmountBalance);
	}

	private void cashoutFreeBalance(String bank, String cashoutor, String identity, BigDecimal reqAmount, String memo) {
		BigDecimal freeAmountBalance = fSBankBalance.getFreeAmountBalance(bank);
		if (freeAmountBalance == null
				|| freeAmountBalance.compareTo(new BigDecimal(0)) <= 0) {
			throw new EcmException(String.format("银行:%s 无现可提", bank));
		}
		if (freeAmountBalance.compareTo(reqAmount) < 0) {
			throw new EcmException(String.format("银行:%s 现金存量不足", bank));
		}
		int scale = bigDecimalScale(fSBankPropertiesBS, bank);
		RoundingMode roundingMode = bigDecimalRoundingMode(fSBankPropertiesBS, bank);
		
		CashoutBill bill = new CashoutBill();
		bill.setCashoutor(cashoutor);
		bill.setCode(null);
		bill.setCtime(System.currentTimeMillis());
		bill.setIdentity(identity);
		bill.setMemo(memo);
		bill.setReqAmount(reqAmount);
		bill.setPoundageRate(this.poundageRate(fSBankPropertiesBS, bank));
		bill.setPoundageAmount(bill.getReqAmount().multiply(bill.getPoundageRate()).setScale(scale, roundingMode));// 为提现*手续费义
		bill.setResAmount(bill.getReqAmount().subtract(bill.getPoundageAmount()));
		freeAmountBalance = freeAmountBalance.subtract(bill.getReqAmount());
		bill.setBalanceType("freeBalance");
		bill.setBalance(freeAmountBalance);
		fSBankBalance.updateFreeAmountBalance(bank, freeAmountBalance);
		String id = getBankCube(bank).saveDoc(TABLE_Cashouts, new TupleDocument<>(bill));
		bill.setCode(id);
	}

	@Override
	public void exchangeBill(String bank, String exchanger, BigDecimal bondQuantities) {
		BigDecimal price = fSBankBalance.getBondPrice(bank);
		
		int scale = bigDecimalScale(fSBankPropertiesBS, bank);
		RoundingMode roundingMode = bigDecimalRoundingMode(fSBankPropertiesBS, bank);
		
		BigDecimal realFloat = bondQuantities.multiply(price).setScale(scale, roundingMode);// 实际精度
		BigDecimal getFloat = bondQuantities.multiply(price).setScale(2, roundingMode);// 两个小数位
		BigDecimal freezeAmountBalance = fSBankBalance.getFreezeAmountBalance(bank);
		if (realFloat.compareTo(freezeAmountBalance) > 0) {
			throw new EcmException("资金不足，请稍后再取");
		}
		BigDecimal bondQuantitiesBalance = fSBankBalance.getBondQuantitiesBalance(bank);
		if (bondQuantities.compareTo(bondQuantitiesBalance) > 0) {
			throw new EcmException("申请承兑债券量超出存量");
		}

		ExchangeBill bill = new ExchangeBill();
		bill.setBondQuantities(bondQuantities);
		bill.setCode(null);
		bill.setCurrBondPrice(price);
		bill.setEtime(System.currentTimeMillis());
		bill.setExchanger(exchanger);

		bill.setDeservedAmount(getFloat);// 实际承兑的金额
		bill.setTailAmount(realFloat.subtract(getFloat));
		BigDecimal tailAmountBalance = fSBankBalance.getTailAmountBalance(bank);
		fSBankBalance.updateTailAmountBalance(bank, tailAmountBalance.add(bill.getTailAmount()));

		BigDecimal bondQuantitiesBalanceRemainer = bondQuantitiesBalance.subtract(bondQuantities);
		fSBankBalance.updateBondQuantitiesBalance(bank, bondQuantitiesBalanceRemainer);
		
		BigDecimal freezeAmountBalanceRemainer = freezeAmountBalance.subtract(realFloat);
		fSBankBalance.updateFreezeAmountBalance(bank, freezeAmountBalanceRemainer);

		BigDecimal newprice = null;
		if (bondQuantitiesBalanceRemainer.compareTo(new BigDecimal(0).setScale(0, roundingMode)) == 0) {// 如果债已兑换完则采用当前价格
			newprice = price;
		} else {
			newprice = (freezeAmountBalanceRemainer).divide(bondQuantitiesBalanceRemainer, scale,
					roundingMode);
		}

		fSBankBalance.updateBondPrice(bank, newprice);
		bill.setNewBondPrice(newprice);
		this.getBankCube(bank).saveDoc(TABLE_Exchanges, new TupleDocument<>(bill));
		
		BigDecimal individualBalance=fSBankIndividualAccountAssetBS.bondBalance(bank, exchanger);
		this.fSBankIndividualAccountAssetBS.updateBoundBalance(bank,exchanger,individualBalance.subtract(bondQuantities));
	}

}
