package linn.examples;

import linn.core.Linn;
import linn.core.execute.LinnExecutor;
import linn.core.execute.state.LinnTurtle;
import linn.core.lang.LinnBuilder;
import processing.core.PApplet;

public class BasicExample extends PApplet {

	private LinnExecutor linnExecutor;

	@Override
	public void setup() {
		final Linn linn = LinnBuilder.newLinn("BasicExample")
				.withAuthor("Thomas Trojer")
				// rule
				.withRule("H").andProduction().F(10).yaw(1.0f).rewrite("H")
				.done()
				// finalize
				.build();
		/*
		 * Configuring the execution environment with:
		 * - the given L-System definition
		 * - the axiom 'H'
		 */
		this.linnExecutor = LinnExecutor.newExecutor().useLinn(linn)
				.traceStates(true).onStateChanged(t -> {
					System.out.println("Turtle: " + t.getX() + ", " + t.getY()
							+ ", " + t.getZ());
					if (t.getPreviousState() == null) {
						return;
					}
					LinnTurtle tp = t.getPreviousState();
					this.line(400 + (float) tp.getX(), 300 + (float) tp.getY(),
							400 + (float) t.getX(), 300 + (float) t.getY());
				})
				// axiom to start the L-System with
				.withAxiom().rewrite("H").done();
	}

	@Override
	public void settings() {
		this.size(800, 600);
		this.noLoop();
	}

	@Override
	public void mouseClicked() {
		super.mouseClicked();
		this.background(255);
		this.redraw();
	}

	@Override
	public void draw() {
		this.linnExecutor.executeOnce();

	}

	public static void main(String _args[]) {
		PApplet.main(
				new String[] { linn.examples.BasicExample.class.getName() });
	}
}
