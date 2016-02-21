package linn.core.math;

import org.junit.Test;

import linn.core.execute.state.LinnTurtle;

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
		System.out.println(turtle);

		turtle.move(3);
		System.out.println(turtle);

		turtle = new LinnTurtle(turtle);
		turtle.pitch(-Math.PI * 0.5);
		turtle.move(2);
		System.out.println(turtle);

		turtle = new LinnTurtle(turtle);
		turtle.roll(-Math.PI * 0.5f);
		turtle.move(10);
		System.out.println(turtle);
		turtle = new LinnTurtle(turtle);

		turtle.yaw(-Math.PI * 0.5);
		turtle.move(20);
		System.out.println(turtle);
	}

	@Test
	public void testTurtleRoll() {
		LinnTurtle turtle = new LinnTurtle();
		System.out.println(turtle);

		turtle.move(3);
		System.out.println(turtle);

		turtle = new LinnTurtle(turtle);
		turtle.roll(-Math.PI * 0.5);
		turtle.move(2);
		System.out.println(turtle);

		turtle = new LinnTurtle(turtle);
		turtle.roll(-Math.PI * 0.5f);
		turtle.move(10);
		System.out.println(turtle);
	}
}
