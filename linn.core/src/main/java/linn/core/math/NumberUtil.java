/**
 * Copyright (c) 2016 by Thomas Trojer <thomas@trojer.net>
 * LINN - A small L-System interpreter.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package linn.core.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Constants, conversions and other static number helpers.
 *
 * @author Thomas Trojer <thomas@trojer.net> -- Initial contribution
 */
public class NumberUtil {

	public static final double RAD_TO_DEG = 180.0 / Math.PI;

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

	public static double formatDouble(double value, int decimalPlaces) {
		if (decimalPlaces < 0) {
			throw new IllegalArgumentException();
		}
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
