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

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import static com.google.common.base.Preconditions.*;

import linn.core.execute.StateChangeHandler;
import linn.core.math.NumberUtil;
import linn.core.math.Quaternion;

/**
 * A simple container implementation to store the current state of a Linn
 * production execution. Basic properties like position and rotation are
 * provided out of the box and hence this class reflects the Turtle that is used
 * in Turtle
 * graphics.
 * <p>
 *
 * Extending properties (e.g. to store render color or any other user payload)
 * can be stored as well by means of a key - value pairs.
 * <p>
 *
 * The default initial orientation of the Turtle is along the positive y-axis
 * (i.e. pitch or attitude).
 * <p>
 *
 * @author Thomas Trojer <thomas@trojer.net> -- Initial contribution
 */
public class LinnTurtle {

	protected Quaternion position;
	protected Quaternion forward;
	protected Quaternion up;
	protected Quaternion rotation;

	protected Map<String, Object> properties;

	protected LinnTurtle previousState = null;
	protected boolean traceStates = false;

	protected List<StateChangeHandler> stateChangeHandlers = Lists.newArrayList();

	public LinnTurtle(final LinnTurtle copy) {
		this(copy.position.getX(), copy.position.getY(), copy.position.getZ(),
				copy.getView(), copy.getUp(), copy.getRotation(),
				copy.properties);
		// fields additionally considered for copying
		this.stateChangeHandlers = Lists.newArrayList(copy.stateChangeHandlers);
		this.previousState = copy.previousState;
		this.traceStates = copy.traceStates;
	}

	public LinnTurtle() {
		this(0, 0, 0);
	}

	public LinnTurtle(double x, double y, double z) {
		this(x, y, z, Quaternion.vector(0, 1, 0), Quaternion.vector(0, 0, -1));
	}

	public LinnTurtle(double x, double y, double z, final Quaternion view, final Quaternion up) {
		this(x, y, z, view, up, Quaternion.rotation(0, 0, 0), Maps.newHashMap());
	}

	public LinnTurtle(double x, double y, double z, final Quaternion view, final Quaternion up, final Quaternion rotation, final Map<String, Object> initialProperties) {
		checkNotNull(initialProperties);
		this.position = Quaternion.vector(x, y, z);
		this.rotation = rotation.normalized();
		this.forward = view.normalized();
		this.up = up.normalized();
		// TODO assert or correct orthogonality between forward and up via
		// temporary left and recalculated up
		this.properties = Maps.newHashMap(initialProperties);
	}

	public void setTrace(boolean trace) {
		this.traceStates = trace;
	}

	public LinnTurtle getPreviousState() {
		checkArgument(this.traceStates, "Tracing of previous states is disabled. Enable with LinnExecutor#traceStates(true).");
		return this.previousState;
	}

	public void addStateChangeHandler(final StateChangeHandler handler) {
		this.stateChangeHandlers.add(handler);
	}

	public void removeStateChangeHandler(final StateChangeHandler handler) {
		this.stateChangeHandlers.remove(handler);
	}

	public void clearStateChangeHandlers() {
		this.stateChangeHandlers.clear();
	}

	public void move(double distance) {
		this.traceStateChange();
		Quaternion moveBy = this.forward.vectorized().normalized().scalar(distance);
		this.position = this.position.plus(moveBy);
		this.notifyStateChange();
	}

	public void yaw(double delta) {
		this.traceStateChange();
		Quaternion deltaRotation = Quaternion.rotation(this.up.getX(), this.up.getY(), this.up.getZ(), delta).normalized();
		this.rotation = this.rotation.times(deltaRotation);
		this.forward = deltaRotation.times(this.forward).times(deltaRotation.conjugated()).normalized();
		// up is not modified on yaw
		this.notifyStateChange();
	}

	public void pitch(double delta) {
		this.traceStateChange();
		Quaternion deltaRotation = Quaternion.rotation(0, delta, 0).normalized();
		this.rotation = this.rotation.times(deltaRotation);
		this.forward = deltaRotation.times(this.forward).times(deltaRotation.conjugated()).normalized();
		this.up = this.rotation.times(this.up).times(this.rotation.conjugated()).normalized();
		this.notifyStateChange();
	}

	public void roll(double delta) {
		this.traceStateChange();
		Quaternion deltaRotation = Quaternion.rotation(this.forward.getX(), this.forward.getY(), this.forward.getZ(), delta).normalized();
		this.rotation = this.rotation.times(deltaRotation);
		this.up = deltaRotation.times(this.up).times(deltaRotation.conjugated()).normalized();
		// forward is not modified on roll
		this.notifyStateChange();
	}

	public double getX() {
		return this.position.getX();
	}

	public double getY() {
		return this.position.getY();
	}

	public double getZ() {
		return this.position.getZ();
	}

	public double getYaw() {
		Quaternion q = this.rotation;
		return Math.asin(2 * q.getX() * q.getY() + 2 * q.getZ() * q.getW());
	}

	public double getYawDegrees() {
		return this.getYaw() * NumberUtil.RAD_TO_DEG;
	}

	public double getPitch() {
		Quaternion q = this.rotation;
		double gimbalPole = q.getX() * q.getY() + q.getZ() * q.getW();
		if (gimbalPole > 0.499) {
			return 2 * Math.atan2(q.getX(), q.getW());
		} else if (gimbalPole < -0.499) {
			return -2 * Math.atan2(q.getX(), q.getW());
		}
		return Math.atan2(2 * q.getX() * q.getW() - 2 * q.getY() * q.getZ(), 1 - 2 * q.getX() * q.getX() - 2 * q.getZ() * q.getZ());
	}

	public double getPitchDegrees() {
		return this.getPitch() * NumberUtil.RAD_TO_DEG;
	}

	public double getRoll() {
		Quaternion q = this.rotation;
		double gimbalPole = q.getX() * q.getY() + q.getZ() * q.getW();
		if (gimbalPole > 0.499 || gimbalPole < -0.499) {
			return 0;
		}
		return Math.atan2(2 * q.getY() * q.getW() - 2 * q.getX() * q.getZ(), 1 - 2 * q.getY() * q.getY() - 2 * q.getZ() * q.getZ());
	}

	public double getRollDegrees() {
		return this.getRoll() * NumberUtil.RAD_TO_DEG;
	}

	public Quaternion getRotation() {
		return new Quaternion(this.rotation);
	}

	public Quaternion getView() {
		return new Quaternion(this.forward);
	}

	public Quaternion getUp() {
		return new Quaternion(this.up);
	}

	private void traceStateChange() {
		if (this.traceStates) {
			LinnTurtle currentState = new ImmutableLinnTurtle(this);
			this.previousState = currentState;
		}
	}

	private void notifyStateChange() {
		// notify state change handlers
		for (StateChangeHandler handler : this.stateChangeHandlers) {
			handler.handle(this);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("state {\n");
		sb.append("\tposition (" + this.getX() + ", " + this.getY() + ", " + this.getZ() + ")\n");
		if (this.traceStates && this.previousState != null) {
			sb.append("\tprevious position (" + this.getX() + ", " + this.getY() + ", " + this.getZ() + ")\n");
		} else if (this.traceStates) {
			sb.append("\tprevious position (n/a)\n");
		}
		// sb.append("\trotation euler (" + this.getYawDegrees() + ", "
		// + this.getPitchDegrees() + ", " + this.getRollDegrees()
		// + ")\n");
		// sb.append("\trotation quaternion (" + this.getRotation().getX() + ",
		// "
		// + this.getRotation().getY() + ", " + this.getRotation().getZ()
		// + ", " + this.getRotation().getW() + ")\n");
		sb.append("\tview direction (" + this.getView().getX() + ", " + this.getView().getY() + ", " + this.getView().getZ() + ")\n");
		sb.append("}");
		return sb.toString();
	}
}
