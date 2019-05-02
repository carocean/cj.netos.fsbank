package cj.netos.fsbank.plugin.FSBAEngine.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface BigDecimalConstants {
	static int scale=12;//小数位数为12
	static int roundingMode=BigDecimal.ROUND_FLOOR;
	static int setPrecision=12;//小数位数为12
	static RoundingMode roundingMode_forNewDecimal=RoundingMode.FLOOR;
}
