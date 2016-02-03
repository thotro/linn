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
				.withRule("H").andWeight(5.5).andProduction().F().rewrite("H")
				.done()
				// rule 2)
				.withRule("H").andWeight(0.5).andProduction().F().branch().F()
				.done().done()
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
	}

	@Override
	public void draw() {
		LinnTurtle beforeState = this.linnExecutor.getState();
		this.linnExecutor.executeAtMost(1);
		LinnTurtle afterState = this.linnExecutor.getState();
		System.out.println(beforeState + " -- " + afterState);
	}

	public static void main(String _args[]) {
		PApplet.main(
				new String[] { linn.examples.BasicExample.class.getName() });
	}
}
