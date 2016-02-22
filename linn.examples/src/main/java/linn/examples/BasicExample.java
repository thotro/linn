/**
 * Copyright (c) 2016 by Thomas Trojer <thomas@trojer.net>
 * LINN - A small L-System interpreter.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package linn.examples;

import linn.core.Linn;
import linn.core.execute.LinnExecutor;
import linn.core.execute.state.LinnTurtle;
import linn.core.lang.LinnBuilder;
import processing.core.PApplet;

/**
 * A 2D processing sketch that draws a quite boring circular pattern. This
 * sketch features
 * <ul>
 * <li>new L-system production iteration every 250ms</li>
 * <li>state-change handler based rendering</li>
 * {@link LinnTurtle}</li>
 * </ul>
 *
 * @author Thomas Trojer <thomas@trojer.net> -- Initial contribution
 */
public class BasicExample extends PApplet {

	private LinnExecutor linnExecutor;

	@Override
	public void setup() {
		final Linn linn = LinnBuilder.newLinn("BasicExample").withAuthor("Thomas Trojer")
				// rule: H ---> ROTATE MOVE-100 H
				.withRule("H").andProduction().yaw(1.0f).F(200).rewrite("H").done()
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
			this.line(200 + (float) tp.getX(), 400 + (float) tp.getY(), 200 + (float) t.getX(), 400 + (float) t.getY());
		})
				// axiom to start the L-System with: H
				.withAxiom().rewrite("H").done();

		// set ultra-slow frame rate
		this.frameRate(4);
	}

	@Override
	public void settings() {
		this.size(800, 600);
		this.loop();
	}

	@Override
	public void draw() {
		this.background(255);
		this.linnExecutor.executeOnce();
	}

	public static void main(String _args[]) {
		PApplet.main(new String[] { linn.examples.BasicExample.class.getName() });
	}
}
