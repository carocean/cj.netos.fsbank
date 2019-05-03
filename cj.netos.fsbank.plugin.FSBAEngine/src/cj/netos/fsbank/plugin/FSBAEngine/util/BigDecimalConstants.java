package cj.netos.fsbank.plugin.FSBAEngine.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import cj.netos.fsbank.bs.IFSBankPropertiesBS;
import cj.netos.fsbank.plugin.FSBAEngine.bo.BankProperty;
import cj.ultimate.util.StringUtil;

public interface BigDecimalConstants {
	static String bondKind = "TY";
	static String currency = "CNY";
	static int scale = 16;// 小数位数为16
	static RoundingMode roundingMode = RoundingMode.FLOOR;
	static float ultimate_BondPrice = 0.001F;
	static float bondRate = 0.7F;
	static float reserveRate = 0.1F;
	static float freeRate = 0.2F;
	default BigDecimal bondRate(IFSBankPropertiesBS fSBankPropertiesBS, String bank) {
		String strbondRate = fSBankPropertiesBS.get(bank, BankProperty.CONSTANS_KEY_Rule_bondRate);
		if (StringUtil.isEmpty(strbondRate)) {
			strbondRate = bondRate + "";
		}
		return new BigDecimal(strbondRate).setScale(scale, roundingMode);
	}
	default BigDecimal reserveRate(IFSBankPropertiesBS fSBankPropertiesBS, String bank) {
		String strreserveRate = fSBankPropertiesBS.get(bank, BankProperty.CONSTANS_KEY_Rule_reserveRate);
		if (StringUtil.isEmpty(strreserveRate)) {
			strreserveRate = reserveRate + "";
		}
		return new BigDecimal(strreserveRate).setScale(scale, roundingMode);
	}
	default BigDecimal freeRate(IFSBankPropertiesBS fSBankPropertiesBS, String bank) {
		String strfreeRate = fSBankPropertiesBS.get(bank, BankProperty.CONSTANS_KEY_Rule_freeRate);
		if (StringUtil.isEmpty(strfreeRate)) {
			strfreeRate = freeRate + "";
		}
		return new BigDecimal(strfreeRate).setScale(scale, roundingMode);
	}
	default BigDecimal ultimateBondPrice(IFSBankPropertiesBS fSBankPropertiesBS, String bank) {
		String price = fSBankPropertiesBS.get(bank, BankProperty.CONSTANS_KEY_ultimate_BondPrice);
		if (StringUtil.isEmpty(price)) {
			price = ultimate_BondPrice + "";
		}
		return new BigDecimal(price).setScale(scale, roundingMode);
	}

	default String bondKind(IFSBankPropertiesBS fSBankPropertiesBS, String bank) {
		String strbondKind = fSBankPropertiesBS.get(bank, BankProperty.CONSTANS_KEY_BondKind);
		if (StringUtil.isEmpty(strbondKind)) {
			strbondKind = bondKind;
		}
		return strbondKind;
	}

	default String currency(IFSBankPropertiesBS fSBankPropertiesBS, String bank) {
		String strcurrency = fSBankPropertiesBS.get(bank, BankProperty.CONSTANS_KEY_Currency);
		if (StringUtil.isEmpty(strcurrency)) {
			strcurrency = currency;
		}
		return strcurrency;
	}

	default BigDecimal poundageRate(IFSBankPropertiesBS fSBankPropertiesBS, String bank) {
		String poundageRate = fSBankPropertiesBS.get(bank, BankProperty.CONSTANS_KEY_poundageRate);// 手续费率
		if (StringUtil.isEmpty(poundageRate)) {
			poundageRate = "0";
		}
		return new BigDecimal(poundageRate).setScale(scale, roundingMode);
	}

	default int bigDecimalScale(IFSBankPropertiesBS fSBankPropertiesBS, String bank) {
		String strscale = fSBankPropertiesBS.get(bank, BankProperty.CONSTANS_KEY_bigDecimal_scale);
		if (StringUtil.isEmpty(strscale)) {
			strscale = scale + "";
		}
		return Integer.valueOf(strscale);
	}

	default RoundingMode bigDecimalRoundingMode(IFSBankPropertiesBS fSBankPropertiesBS, String bank) {
		String strroundingMode = fSBankPropertiesBS.get(bank, BankProperty.CONSTANS_KEY_bigDecimal_roundingMode);
		if (StringUtil.isEmpty(strroundingMode)) {
			strroundingMode = roundingMode + "";
		}
		return RoundingMode.valueOf(strroundingMode);
	}
}
