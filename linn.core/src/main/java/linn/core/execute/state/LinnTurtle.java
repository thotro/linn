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
 *
 * @author Thomas Trojer <thomas@trojer.net>
 */
public class LinnTurtle {

	protected Quaternion position;
	protected Quaternion view;
	protected Quaternion rotation;

	protected Map<String, Object> properties;

	protected LinnTurtle previousState = null;
	protected boolean traceStates = false;

	protected List<StateChangeHandler> stateChangeHandlers = Lists
			.newArrayList();

	public LinnTurtle(final LinnTurtle copy) {
		this(copy.position.getX(), copy.position.getY(), copy.position.getZ(),
				copy.getView(), copy.getRotation(), copy.properties);
		// fields additionally considered for copying
		this.stateChangeHandlers = Lists.newArrayList(copy.stateChangeHandlers);
		this.previousState = copy.previousState;
		this.traceStates = copy.traceStates;
	}

	public LinnTurtle() {
		this(0, 0, 0);
	}

	public LinnTurtle(double x, double y, double z) {
		this(x, y, z, Quaternion.vector(0, 1, 0));
	}

	public LinnTurtle(double x, double y, double z, final Quaternion view) {
		this(x, y, z, view, Quaternion.vector(0, 0, 0), Maps.newHashMap());
	}

	public LinnTurtle(double x, double y, double z, final Quaternion view,
			final Quaternion rotation,
			final Map<String, Object> initialProperties) {
		checkNotNull(initialProperties);
		this.position = Quaternion.vector(x, y, z);
		this.rotation = rotation;
		this.view = view.normalized();
		this.properties = Maps.newHashMap(initialProperties);
	}

	public void setTrace(boolean trace) {
		this.traceStates = trace;
	}

	public LinnTurtle getPreviousState() {
		checkArgument(this.traceStates,
				"Tracing of previous states is disabled. Enable with LinnExecutor#traceStates(true).");
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

	// private void updateRotation() {
	// this.rotation = Quaternion.rotation(this.yaw, this.pitch, this.roll);
	// }

	public void move(double distance) {
		this.traceStateChange();
		Quaternion moveBy = this.view.scalar(distance);
		this.position = this.position.plus(moveBy);
		this.notifyStateChange();
	}

	public void yaw(double delta) {
		this.traceStateChange();
		Quaternion deltaRotation = Quaternion.rotation(delta, 0, 0)
				.normalized();
		this.view = deltaRotation.times(this.view)
				.times(deltaRotation.conjugated()).normalized();
		this.notifyStateChange();
	}

	public void pitch(double delta) {
		this.traceStateChange();
		Quaternion deltaRotation = Quaternion.rotation(0, delta, 0)
				.normalized();
		this.view = deltaRotation.times(this.view)
				.times(deltaRotation.conjugated()).normalized();
		this.notifyStateChange();
	}

	public void roll(double delta) {
		this.traceStateChange();
		Quaternion deltaRotation = Quaternion.rotation(0, 0, delta)
				.normalized();
		this.view = deltaRotation.times(this.view)
				.times(deltaRotation.conjugated()).normalized();
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
		return Math.atan2(2 * q.getX() * q.getW() - 2 * q.getY() * q.getZ(),
				1 - 2 * q.getX() * q.getX() - 2 * q.getZ() * q.getZ());
	}

	public double getPitchDegrees() {
		return this.getPitch() * NumberUtil.RAD_TO_DEG;
	}

	public double getRoll() {
		Quaternion q = this.rotation;
		return Math.atan2(2 * q.getY() * q.getW() - 2 * q.getX() * q.getZ(),
				1 - 2 * q.getY() * q.getY() - 2 * q.getZ() * q.getZ());
	}

	public double getRollDegrees() {
		return this.getRoll() * NumberUtil.RAD_TO_DEG;
	}

	public Quaternion getRotation() {
		return new Quaternion(this.rotation);
	}

	public Quaternion getView() {
		return new Quaternion(this.view);
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
		sb.append("\tposition (" + this.getX() + ", " + this.getY() + ", "
				+ this.getZ() + ")\n");
		if (this.traceStates && this.previousState != null) {
			sb.append("\tprevious position (" + this.getX() + ", " + this.getY()
					+ ", " + this.getZ() + ")\n");
		} else if (this.traceStates) {
			sb.append("\tprevious position (n/a)\n");
		}
		sb.append("\trotation euler (" + this.getYawDegrees() + ", "
				+ this.getPitchDegrees() + ", " + this.getRollDegrees()
				+ ")\n");
		sb.append("\trotation quaternion (" + this.getRotation().getX() + ", "
				+ this.getRotation().getY() + ", " + this.getRotation().getZ()
				+ ", " + this.getRotation().getW() + ")\n");
		sb.append("}");
		return sb.toString();
	}
}
