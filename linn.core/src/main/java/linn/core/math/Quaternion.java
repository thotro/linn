package linn.core.math;

public class Quaternion {

	private final double w;
	private final double x;
	private final double y;
	private final double z;

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
		return this.w + " + " + this.x + "i + " + this.y + "j + " + this.z
				+ "k";
	}

	public double norm() {
		return Math.sqrt(this.w * this.w + this.x * this.x + this.y * this.y
				+ this.z * this.z);
	}

	public Quaternion normalized() {
		double norm = this.norm();
		return new Quaternion(this.w / norm, this.x / norm, this.y / norm,
				this.z / norm);
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

	public Quaternion inverted() {
		double d = this.w * this.w + this.x * this.x + this.y * this.y + this.z
				* this.z;
		return new Quaternion(this.w / d, -this.x / d, -this.y / d, -this.z / d);
	}

	public Quaternion divides(Quaternion b) {
		return this.times(b.inverted());
	}

	public static Quaternion rotation(double yaw, double pitch, double roll) {
		// ratios of euler angles
		double c1 = Math.cos(yaw / 2);
		double c2 = Math.cos(pitch / 2);
		double c3 = Math.cos(roll / 2);
		double s1 = Math.sin(yaw / 2);
		double s2 = Math.sin(pitch / 2);
		double s3 = Math.sin(roll / 2);
		// quaternion components
		double nw = c1 * c2 * c3 - s1 * s2 * s3;
		double nx = s1 * s2 * c3 + c1 * c2 * s3;
		double ny = s1 * c2 * c3 + c1 * s2 * s3;
		double nz = c1 * s2 * c3 - s1 * c2 * s3;
		return new Quaternion(nw, nx, ny, nz);
	}

	public static Quaternion vector(double x, double y, double z) {
		return new Quaternion(0, x, y, z);
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
