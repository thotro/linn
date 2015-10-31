package linn.core.lang;

import static org.junit.Assert.*;
import linn.core.Linn;
import linn.core.RuleProductionContainer;
import linn.core.execute.LinnExecutor;

import org.junit.Test;

public class BuilderTest {

	@Test
	public void testBuildSimple() {
		Linn linn = LinnBuilder.newLinn("testLinn").withAuthor("Thomas Trojer")
				.withRule("H").andWeight(1.5f).andProduction().F().rewrite("H").done().build();
		System.out.println(linn);
		
		LinnExecutor executor = LinnExecutor.newExecutor().useLinn(linn).withAxiom().rewrite("H").done();
		System.out.println(executor.getProductionResult());
		executor.execute();
		System.out.println(executor.getProductionResult());
		executor.execute();
		System.out.println(executor.getProductionResult());
	}

}
