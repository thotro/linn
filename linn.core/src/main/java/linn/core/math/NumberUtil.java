package linn.core.math;

public class NumberUtil {

	public static boolean doubleIsDifferent(double d1, double d2,
			double delta) {
		if (Double.compare(d1, d2) == 0) {
			return false;
		}
		if ((Math.abs(d1 - d2) <= delta)) {
			return false;
		}
		return true;
	}
}
