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
				.withRule("H").andWeight(1).andProduction().F(20).rewrite("H")
				.done()
				// finalize
				.build();
		/*
		 * Configuring the execution environment with:
		 * - the given L-System definition
		 * - the axiom 'H'
		 */
		this.linnExecutor = LinnExecutor.newExecutor().useLinn(linn)
				.onStateChanged(t -> {
					System.out.println("Turtle: " + t.getX() + ", " + t.getY()
							+ ", " + t.getZ());
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
		this.redraw();
	}

	@Override
	public void draw() {
		LinnTurtle beforeState = this.linnExecutor.getState();
		this.linnExecutor.executePartial(p -> {
			System.out.println("fill");
			this.fill(255, 255, 255);
		});
		LinnTurtle afterState = this.linnExecutor.getState();
		System.out.println(beforeState + " -- " + afterState);
		this.line(400 + (float) beforeState.getX(),
				300 + (float) beforeState.getY(),
				400 + (float) afterState.getX(),
				300 + (float) afterState.getY());
	}

	public static void main(String _args[]) {
		PApplet.main(
				new String[] { linn.examples.BasicExample.class.getName() });
	}
}
