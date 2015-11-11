package linn.core.math;

import linn.core.execute.state.LinnTurtle;

import org.junit.Test;

public class TurtleQuaternionTest {

	@Test
	public void testQuaternion() {
		Quaternion rot = Quaternion.rotation(0, Math.PI * 0.5, 0);
		Quaternion distance = Quaternion.vector(1, 0, 0);
		Quaternion rotc = rot.conjugated();
		System.out.println(rot.times(distance).times(rotc).normalized());
	}

	@Test
	public void testTurtleMove() {
		LinnTurtle turtle = new LinnTurtle();
		System.out.println(turtle.getX() + ", " + turtle.getY() + ", "
				+ turtle.getZ());
		turtle.move(3);
		System.out.println(turtle.getX() + ", " + turtle.getY() + ", "
				+ turtle.getZ());
		turtle.pitch(-1);
		turtle.move(2);
		System.out.println(turtle.getX() + ", " + turtle.getY() + ", "
				+ turtle.getZ());
	}
}
