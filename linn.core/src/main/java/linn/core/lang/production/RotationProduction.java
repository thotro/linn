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

package linn.core.lang.production;

import java.util.List;

import com.google.common.collect.Lists;

import linn.core.execute.state.LinnTurtle;
import linn.core.math.NumberUtil;
import linn.core.math.Quaternion;

/**
 * A rotation production defines change in yaw, pitch and roll (in the
 * Tait-Bryan world of angles) to be applied to an active {@link LinnTurtle}.
 *
 * Internally {@link Quaternion} based calculations on orientation and rotation
 * are performed (see {@link LinnTurtle#yaw(double),
 * {@link LinnTurtle#ptich(double) and {@link LinnTurtle#roll(double)}.
 *
 * @author Thomas Trojer <thomas@trojer.net> -- Initial contribution
 */
public class RotationProduction implements Production {

	private double deltaYaw = 0.0;
	private double deltaPitch = 0.0;
	private double deltaRoll = 0.0;

	public RotationProduction(double deltaYaw, double deltaPitch,
			double deltaRoll) {
		this.deltaYaw = deltaYaw;
		this.deltaPitch = deltaPitch;
		this.deltaRoll = deltaRoll;
	}

	@Override
	public List<Production> execute(final LinnTurtle state,
			ProductionParameter... parameters) {
		if (NumberUtil.doubleIsDifferent(this.deltaYaw, 0, 1e-9)) {
			state.yaw(this.deltaYaw);
		}
		if (NumberUtil.doubleIsDifferent(this.deltaPitch, 0, 1e-9)) {
			state.pitch(this.deltaPitch);
		}
		if (NumberUtil.doubleIsDifferent(this.deltaRoll, 0, 1e-9)) {
			state.roll(this.deltaRoll);
		}
		return Lists.newArrayList(this);
	}

	@Override
	public String getName() {
		return "R(" + NumberUtil.formatDouble(this.deltaYaw * NumberUtil.RAD_TO_DEG, 3) + ", " +
				NumberUtil.formatDouble(this.deltaPitch * NumberUtil.RAD_TO_DEG, 3) + ", " +
				NumberUtil.formatDouble(this.deltaRoll * NumberUtil.RAD_TO_DEG, 3) + ")";
	}
}
