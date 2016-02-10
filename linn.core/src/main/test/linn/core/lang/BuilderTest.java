package linn.core.lang;

import org.junit.Test;

import linn.core.Linn;
import linn.core.execute.LinnExecutor;

public class BuilderTest {

	@Test
	public void testBuildSimple() {
		// define and print an L-System definition
		final Linn linn = LinnBuilder.newLinn("testBuildSimple")
				.withAuthor("Thomas Trojer")
				// H --5.5-> F H
				.withRule("H").andWeight(5.5).andProduction().F().rewrite("H")
				.done()
				// H --0.5-> F;
				.withRule("H").andWeight(0.5).andProduction().F()
				// branching
				.branch().F().done().done().build();
		System.out.println(linn);
		// configure the execution environment and execute a number of times
		final LinnExecutor executor = LinnExecutor.newExecutor().useLinn(linn)
				.onStateChanged(t -> {
					System.out.println("Turtle: " + t.getX() + ", " + t.getY()
							+ ", " + t.getZ());
				}).withAxiom().rewrite("H").done();
		System.out.println(executor.getProductionResult());
		executor.executeAtMost(100, p -> {
			System.out.println(p);
		});
		System.out.println("Iterations: " + executor.getIterationCount());
	}

	@Test
	public void testBuildPartial() {
		// define and print an L-System definition
		final Linn linn = LinnBuilder.newLinn("testBuildPartial")
				.withAuthor("Thomas Trojer")
				// H --5.5-> F H
				.withRule("H").andWeight(5.5).andProduction().F().rewrite("H")
				.done()
				// finalize
				.build();
		System.out.println(linn);
		// configure the execution environment and execute a number of times
		final LinnExecutor executor = LinnExecutor.newExecutor().useLinn(linn)
				.onStateChanged(t -> {
					System.out.println("Turtle: " + t.getX() + ", " + t.getY()
							+ ", " + t.getZ());
				}).withAxiom().rewrite("H").done();
		for (int i = 0; i < 10; i++) {
			executor.executePartial(p -> {
				System.out.println(p);
			});
			System.out.println("Got back control briefly ...");
		}
		System.out.println("Iterations: " + executor.getIterationCount());
	}

	@Test
	public void testBuildBranch() {
		// define and print an L-System definition
		final Linn linn = LinnBuilder.newLinn("testBuildSimple")
				.withAuthor("Thomas Trojer")
				// H ---> F [ H H ]
				.withRule("H").andProduction().F().branch().rewrite("H")
				.rewrite("H").done().done().build();
		System.out.println(linn);
		// configure the execution environment and execute a number of times
		final LinnExecutor executor = LinnExecutor.newExecutor().useLinn(linn)
				.onStateChanged(t -> {
					System.out.println("Turtle: " + t.getX() + ", " + t.getY()
							+ ", " + t.getZ());
				}).withAxiom().rewrite("H").done();
		System.out.println(executor.getProductionResult());
		executor.executeAtMost(10, p -> {
			System.out.println(p);
		});
		System.out.println("Iterations: " + executor.getIterationCount());
	}

	@Test
	public void testBuildHistory() {
		// define and print an L-System definition
		final Linn linn = LinnBuilder.newLinn("testBuildSimple")
				.withAuthor("Thomas Trojer")
				// H ---> F [ H H ]
				.withRule("H").andProduction().F().rewrite("H").done().build();
		System.out.println(linn);
		// configure the execution environment and execute a number of times
		final LinnExecutor executor = LinnExecutor.newExecutor().useLinn(linn)
				.traceStates(true).onStateChanged(t -> {
					System.out.println("Turtle: " + t.getX() + ", " + t.getY()
							+ ", " + t.getZ());
					System.out.println(
							"Previous Turtle: " + t.getPreviousState().getX()
									+ ", " + t.getPreviousState().getY() + ", "
									+ t.getPreviousState().getZ());
				}).withAxiom().rewrite("H").done();
		System.out.println(executor.getProductionResult());
		executor.executeAtMost(1, p -> {
			System.out.println(p);
		});
		System.out.println("Iterations: " + executor.getIterationCount());
	}

}
