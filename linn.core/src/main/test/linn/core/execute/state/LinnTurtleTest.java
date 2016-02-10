package linn.core.execute.state;

import org.junit.Test;

public class LinnTurtleTest {

	@Test
	public void test() {
		LinnTurtle turtle = new LinnTurtle();
		System.out.println(turtle);
		turtle.move(10);
		System.out.println(turtle);
		turtle.yaw(1.0f);
		System.out.println(turtle);
		turtle.move(10);
		System.out.println(turtle);
	}

}
