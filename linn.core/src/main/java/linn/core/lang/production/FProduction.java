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

import static com.google.common.base.Preconditions.*;

import linn.core.execute.PostExecutionHandler;
import linn.core.execute.StateChangeHandler;
import linn.core.execute.state.LinnTurtle;
import linn.core.math.NumberUtil;

/**
 * An F production refers the classic "move and draw" action of a
 * {@link LinnTurtle}. As the actual outcomes of actions in LINN are arbitrary
 * (as they are user defined) this production reflects just a "move" action.
 * <p>
 * Whether "drawing" or any other behavior is applied for this production is
 * defined either in {@link StateChangeHandler}s or (after each iteration) in a
 * {@link PostExecutionHandler}.
 * <p>
 *
 * When considering the following example rule
 *
 * <pre>
 * A ---> F F(100)
 * </pre>
 *
 * then the final outcome is expected to be "2 moves" of the {@link LinnTurtle}
 * along its view current direction. The second move is parameterized with the
 * length (or distance) of the move, while for the first one a length of
 * <code>1.0</code> is assumed.
 * <p>
 *
 * @author Thomas Trojer <thomas@trojer.net> -- Initial contribution
 */
public class FProduction implements Production {

	private final double length;
	private final boolean jump;

	public FProduction(double length) {
		this(length, false);
	}

	public FProduction(double length, boolean jump) {
		checkArgument(NumberUtil.doubleIsDifferent(length, 0, 1e-9));
		this.length = length;
		this.jump = jump;
	}

	@Override
	public List<Production> execute(final LinnTurtle state,
			final ProductionParameter... parameters) {
		if (this.jump) {
			state.jump(this.length);
		} else {
			state.move(this.length);
		}
		return Lists.newArrayList(this);
	}

	@Override
	public String getName() {
		char type = 'F';
		if (this.jump) {
			type = 'f';
		}
		return type + "(" + this.length + ")";
	}
}
