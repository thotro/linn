package linn.examples;

import linn.core.Linn;
import linn.core.execute.LinnExecutor;
import linn.core.execute.state.LinnTurtle;
import linn.core.lang.LinnBuilder;
import processing.core.PApplet;

public class SierpinskiExample extends PApplet {

	private static double angle = 1 / 3.0 * Math.PI;
	private static int len = 2;

	private LinnExecutor linnExecutor;

	@Override
	public void setup() {
		final Linn linn = LinnBuilder.newLinn("SierpinskiExample").withAuthor("Thomas Trojer")
				// rule: A ---> B - A - B
				.withRule("A").andProduction().F(len, "B").yaw(-angle).F(len, "A").yaw(-angle).F(len, "B").done()
				// rule: B ---> A + B + A
				.withRule("B").andProduction().F(len, "A").yaw(angle).F(len, "B").yaw(angle).F(len, "A").done()
				// finalize
				.build();
		// configuring the execution environment
		this.linnExecutor = LinnExecutor.newExecutor().useLinn(linn).traceStates(true).onStateChanged(t -> {
			if (t.getPreviousState() == null) {
				// await a second position to draw a line
				return;
			}
			// connect previous and current position with a line
			LinnTurtle tp = t.getPreviousState();
			this.line((float) tp.getX(), (float) tp.getY(), (float) t.getX(), (float) t.getY());
		})
				// axiom to start the L-System with: H
				.withAxiom().F(len, "A").done();
	}

	@Override
	public void settings() {
		this.size(800, 600);
		this.noLoop();
	}

	@Override
	public void mouseClicked() {
		this.redraw();
	}

	@Override
	public void draw() {
		this.background(255);
		this.linnExecutor.executeOnce();
	}

	public static void main(String _args[]) {
		PApplet.main(new String[] { linn.examples.SierpinskiExample.class.getName() });
	}
}
