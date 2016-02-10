package linn.examples;

import linn.core.Linn;
import linn.core.execute.LinnExecutor;
import linn.core.execute.state.LinnTurtle;
import linn.core.lang.LinnBuilder;
import processing.core.PApplet;

public class BasicExample extends PApplet {

	private LinnExecutor linnExecutor;
	private LinnTurtle previousState;

	@Override
	public void setup() {
		/*
		 * Defining the following L-System definition:
		 * 1) H --5.5-> F H
		 * 2) H --0.5-> F [ F ];
		 */
		final Linn linn = LinnBuilder.newLinn("BasicExample")
				.withAuthor("Thomas Trojer")
				// rule 1)
				.withRule("H").andWeight(2).andProduction().F(10).rewrite("H")
				.done()
				// rule 2)
				.withRule("H").andWeight(1).andProduction().F(20).branch()
				.yaw(20f).F(10).done().rewrite("H").done()
				// finalize
				.build();
		/*
		 * Configuring the execution environment with:
		 * - the given L-System definition
		 * - the axiom 'H'
		 */
		this.linnExecutor = LinnExecutor.newExecutor().useLinn(linn)
				.onStateChanged(t -> {
					// System.out.println("Turtle: " + t.getX() + ", " +
					// t.getY()
					// + ", " + t.getZ());
					if (this.previousState == null) {
						this.previousState = t;
					} else {
						this.line(400 + (float) this.previousState.getX(),
								300 + (float) this.previousState.getY(),
								400 + (float) t.getX(), 300 + (float) t.getY());
						this.previousState = t;
					}
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
