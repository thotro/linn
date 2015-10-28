package linn.core.lang;

import static org.junit.Assert.*;
import linn.core.Linn;

import org.junit.Test;

public class BuilderTest {

	@Test
	public void testBuildSimple() {
		Linn linn = LinnBuilder.newLinn("testLinn").withAuthor("Thomas Trojer").withRule("H").thatHasWeight(1.5f).andProduction().done().build();
		System.out.println(linn);
	}

}
