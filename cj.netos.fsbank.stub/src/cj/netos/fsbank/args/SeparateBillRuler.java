package cj.netos.fsbank.args;

import java.math.BigDecimal;
//拆单规则有三部分构成：单1=债率+准备金率+分红率总和
import java.util.List;
public class SeparateBillRuler {
	BigDecimal debtRate;
	BigDecimal reserveRate;
	List<ProfitSharingRule> rules;//分红率总和
}
