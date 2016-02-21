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

import linn.core.execute.state.LinnTurtle;

/**
 * A quaternion. There's nothing more to say! ;) ... well, it's used for
 * rotating any {@link LinnTurtle}.
 *
 * @author Thomas Trojer <thomas@trojer.net> -- Initial contribution
 */
public class Quaternion {

	private final double w;
	private final double x;
	private final double y;
	private final double z;

	/**
	 * Copy constructor.
	 *
	 * @param copy
	 *            The quaternion to copy.
	 */
	public Quaternion(final Quaternion copy) {
		this.w = copy.getW();
		this.x = copy.getX();
		this.y = copy.getY();
		this.z = copy.getZ();
	}

	public Quaternion(double w, double x, double y, double z) {
		this.w = w;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String toString() {
		return this.w + " + " + this.x + "i + " + this.y + "j + " + this.z + "k";
	}

	public double norm() {
		return Math.sqrt(this.w * this.w + this.x * this.x + this.y * this.y + this.z * this.z);
	}

	public Quaternion normalized() {
		double norm = this.norm();
		return new Quaternion(this.w / norm, this.x / norm, this.y / norm, this.z / norm);
	}

	public Quaternion conjugated() {
		return new Quaternion(this.w, -this.x, -this.y, -this.z);
	}

	public Quaternion plus(Quaternion b) {
		Quaternion a = this;
		return new Quaternion(a.w + b.w, a.x + b.x, a.y + b.y, a.z + b.z);
	}

	public Quaternion scalar(double val) {
		Quaternion a = this;
		return new Quaternion(a.w * val, a.x * val, a.y * val, a.z * val);
	}

	public Quaternion times(Quaternion b) {
		Quaternion a = this;
		double nw = a.w * b.w - a.x * b.x - a.y * b.y - a.z * b.z;
		double nx = a.w * b.x + a.x * b.w + a.y * b.z - a.z * b.y;
		double ny = a.w * b.y - a.x * b.z + a.y * b.w + a.z * b.x;
		double nz = a.w * b.z + a.x * b.y - a.y * b.x + a.z * b.w;
		return new Quaternion(nw, nx, ny, nz);
	}

	public Quaternion reverseTimes(Quaternion b) {
		Quaternion a = this;
		double nx = b.w * a.x + b.x * a.w + b.y * a.z - b.z * a.y;
		double ny = b.w * a.y + b.y * a.w + b.z * a.x - b.x * a.z;
		double nz = b.w * a.z + b.z * a.w + b.x * a.y - b.y * a.x;
		double nw = b.w * a.w - b.x * a.x - b.y * a.y - b.z * a.z;
		return new Quaternion(nw, nx, ny, nz);
	}

	public Quaternion inverted() {
		double d = this.w * this.w + this.x * this.x + this.y * this.y + this.z * this.z;
		return new Quaternion(this.w / d, -this.x / d, -this.y / d, -this.z / d);
	}

	public Quaternion divides(Quaternion b) {
		return this.times(b.inverted());
	}

	public static Quaternion identity() {
		return new Quaternion(1, 0, 0, 0);
	}

	public static Quaternion rotation(double yaw, double pitch, double roll) {
		// ratios of euler angles
		double yawH = yaw * 0.5;
		double pitchH = pitch * 0.5;
		double rollH = roll * 0.5;
		double cYaw = Math.cos(yawH);
		double cPitch = Math.cos(pitchH);
		double cRoll = Math.cos(rollH);
		double sYaw = Math.sin(yawH);
		double sPitch = Math.sin(pitchH);
		double sRoll = Math.sin(rollH);
		// quaternion components
		double nw = cYaw * cPitch * cRoll + sYaw * sPitch * sRoll;
		double nx = cYaw * sPitch * cRoll + sYaw * cPitch * sRoll;
		double ny = sYaw * cPitch * cRoll - cYaw * sPitch * sRoll;
		double nz = cYaw * cPitch * sRoll - sYaw * cPitch * sRoll;
		return new Quaternion(nw, nx, ny, nz);
	}

	public static Quaternion rotation(double x, double y, double z, double angle) {
		double d = Quaternion.vector(x, y, z).norm();
		if (d == 0f) {
			return Quaternion.identity();
		}
		d = 1.0 / d;
		angle = angle < 0 ? Math.PI * 2.0 - (-angle % (Math.PI * 2.0)) : angle % (Math.PI * 2.0);
		float angleSin = (float) Math.sin(angle / 2);
		float angleCos = (float) Math.cos(angle / 2);
		return new Quaternion(angleCos, d * x * angleSin, d * y * angleSin, d * z * angleSin).normalized();
	}

	public static Quaternion vector(double x, double y, double z) {
		return new Quaternion(0, x, y, z);
	}

	public Quaternion vectorized() {
		return new Quaternion(0, this.getX(), this.getY(), this.getZ());
	}

	public double getW() {
		return this.w;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public double getZ() {
		return this.z;
	}
}
