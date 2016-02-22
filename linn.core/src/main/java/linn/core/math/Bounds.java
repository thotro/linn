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
 * Bounds are maintained by individual states of a {@link LinnTurtle} in order
 * to determine the total expansion of turtle movements accumulated up to the
 * current state. Bounds are immutable.
 * <p>
 *
 * If e.g. a {@link LinnTurtle} is
 * <ul>
 * <li>placed at (x, y, z) (0, 0, 0),</li>
 * <li>moved by (0, 10, 0),</li>
 * <li>turns around (yaw) 90° CW and up (pitch) 45° and</li>
 * <li>moves for another 20 forward</li>
 * </ul>
 *
 * the final bounds are (20*&radic;2, 10, 20*&radic;2).
 *
 * @author Thomas Trojer <thomas@trojer.net> -- Initial contribution
 */
public class Bounds {

	private double minX;
	private double minY;
	private double maxX;
	private double maxY;
	private double minZ;
	private double maxZ;

	public Bounds(final Bounds copy) {
		this(copy.getMinX(), copy.getMinY(), copy.getMinZ(), copy.getMaxX(), copy.getMaxY(), copy.getMaxZ());
	}

	public Bounds(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		this.minX = minX;
		this.minY = minY;
		this.minZ = minZ;
		this.maxX = maxX;
		this.maxY = maxY;
		this.maxZ = maxZ;
	}

	public double getMinX() {
		return this.minX;
	}

	public double getMinY() {
		return this.minY;
	}

	public double getMinZ() {
		return this.minZ;
	}

	public double getMaxX() {
		return this.maxX;
	}

	public double getMaxY() {
		return this.maxY;
	}

	public double getMaxZ() {
		return this.maxZ;
	}

	public double getExpansionX() {
		return this.getMaxX() - this.getMinX();
	}

	public double getExpansionY() {
		return this.getMaxY() - this.getMinY();
	}

	public double getExpansionZ() {
		return this.getMaxZ() - this.getMinZ();
	}

	public Bounds expandFor(final Quaternion state) {
		double newMinX = this.minX;
		double newMinY = this.minY;
		double newMinZ = this.minZ;
		double newMaxX = this.maxX;
		double newMaxY = this.maxY;
		double newMaxZ = this.maxZ;
		// expand bounds on min-x and max-x
		if (state.getX() < this.getMinX()) {
			newMinX = state.getX();
		} else if (state.getX() > this.getMaxX()) {
			newMaxX = state.getX();
		}
		// expand bounds on min-y and max-y
		if (state.getY() < this.getMinY()) {
			newMinY = state.getY();
		} else if (state.getY() > this.getMaxY()) {
			newMaxY = state.getY();
		}
		// expand bounds on min-z and max-z
		if (state.getZ() < this.getMinZ()) {
			newMinZ = state.getZ();
		} else if (state.getZ() > this.getMaxZ()) {
			newMaxZ = state.getZ();
		}
		return new Bounds(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
	}

	@Override
	public String toString() {
		return "bounds {\n\tlower (" +
				this.minX + ", " + this.minY + ", " + this.minZ + "),\n\tupper (" +
				this.maxX + ", " + this.maxY + ", " + this.maxZ + ")\n}";
	}
}
