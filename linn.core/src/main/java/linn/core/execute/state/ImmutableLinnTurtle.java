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

package linn.core.execute.state;

import linn.core.execute.LinnExecutor;

/**
 * An immutable version of {@link LinnTurtle}. Any attempt to modify the state
 * of this turtle will result in a {@link UnsupportedOperationException}.
 * Objects of this type are used to reflect the history of state changes that is
 * accessible through {@link LinnTurtle#getPreviousState()}, in case state
 * tracing is enabled in {@link LinnExecutor#traceStates(boolean)}.
 * <p>
 *
 * @author Thomas Trojer <thomas@trojer.net> -- Initial contribution
 */
public class ImmutableLinnTurtle extends LinnTurtle {

	public ImmutableLinnTurtle(final LinnTurtle copy) {
		super(copy.position.getX(), copy.position.getY(), copy.position.getZ(),
				copy.getView(), copy.getRotation(), copy.getUp(),
				copy.properties);
		// fields additionally considered for copying
		// NOTE: no state change handlers
		this.previousState = copy.previousState;
		this.traceStates = copy.traceStates;
	}

	@Override
	public void move(double distance) {
		throw new UnsupportedOperationException("Immutable copy of a LinnTurtle");
	}

	@Override
	public void yaw(double delta) {
		throw new UnsupportedOperationException("Immutable copy of a LinnTurtle");
	}

	@Override
	public void pitch(double delta) {
		throw new UnsupportedOperationException("Immutable copy of a LinnTurtle");
	}

	@Override
	public void roll(double delta) {
		throw new UnsupportedOperationException("Immutable copy of a LinnTurtle");
	}
}
