package linn.core.execute.state;

import java.util.Map;

import linn.core.math.Quaternion;

import com.google.common.collect.Maps;

import static com.google.common.base.Preconditions.*;

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

	private final Quaternion baseDirection;
	private Quaternion position;

	private Quaternion rotation;
	private double yaw;
	private double pitch;
	private double roll;

	private Map<String, Object> properties;

	public LinnTurtle() {
		this(0, 0, 0, 0, 0, 0, Maps.newHashMap(), Quaternion.vector(0, 1, 0));
	}

	public LinnTurtle(double x, double y, double z) {
		this(x, y, z, 0, 0, 0, Maps.newHashMap(), Quaternion.vector(0, 1, 0));
	}

	public LinnTurtle(double x, double y, double z, double yaw, double pitch,
			double roll) {
		this(x, y, z, yaw, pitch, roll, Maps.newHashMap(), Quaternion.vector(0,
				1, 0));
	}

	public LinnTurtle(double x, double y, double z, double yaw, double pitch,
			double roll, final Map<String, Object> initialProperties,
			final Quaternion baseDirection) {
		checkNotNull(initialProperties);
		this.position = Quaternion.vector(x, y, z);
		this.yaw = yaw;
		this.pitch = pitch;
		this.roll = roll;
		this.baseDirection = new Quaternion(baseDirection);
		this.properties = Maps.newHashMap(initialProperties);
		this.updateRotation();
	}

	private void updateRotation() {
		this.rotation = Quaternion.rotation(this.yaw, this.pitch, this.roll);
	}

	public void move(double distance) {
		Quaternion moveDir = this.baseDirection.scalar(distance);
		Quaternion rotationCon = this.rotation.conjugated();
		Quaternion move = this.rotation.times(moveDir).times(rotationCon);
		this.position = this.position.plus(move);
	}

	public void yaw(double delta) {
		this.yaw += delta;
		if (this.yaw >= 2 * Math.PI) {
			this.yaw -= 2 * Math.PI;
		} else if (this.yaw < 0) {
			this.yaw = 2 * Math.PI - this.yaw;
		}
		this.updateRotation();
	}

	public void pitch(double delta) {
		this.pitch += delta;
		if (this.pitch >= 2 * Math.PI) {
			this.pitch -= 2 * Math.PI;
		} else if (this.pitch < 0) {
			this.pitch = 2 * Math.PI - this.pitch;
		}
		this.updateRotation();
	}

	public void roll(double delta) {
		this.roll += delta;
		if (this.roll >= 2 * Math.PI) {
			this.roll -= 2 * Math.PI;
		} else if (this.roll < 0) {
			this.roll = 2 * Math.PI - this.roll;
		}
		this.updateRotation();
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
}
