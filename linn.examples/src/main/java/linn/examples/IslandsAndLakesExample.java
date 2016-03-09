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
import linn.core.execute.state.StateChangeType;
import linn.core.lang.LinnBuilder;
import linn.core.math.Bounds;
import processing.core.PApplet;

/**
 * A 2D processing sketch that draws a islands and lakes pattern.
 * This sketch features
 * <ul>
 * <li>new L-system production iteration on mouse click</li>
 * <li>post-production rendering by using the history of state changes (traces)
 * </li>
 * <li>auto focus on draw area by making use of the computed bounds of a
 * {@link LinnTurtle}</li>
 * <li>preset default turtle move length and angles</li>
 * </ul>
 *
 * @author Thomas Trojer <thomas@trojer.net> -- Initial contribution
 */
public class IslandsAndLakesExample extends PApplet {

	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 800;

	private static final double ANGLE = 1 / 2.0 * Math.PI;
	private static final int LEN = 2;

	private LinnExecutor linnExecutor;

	@Override
	public void setup() {
		// the L-system definition
		final Linn linn = LinnBuilder.newLinn("IslandsAndLakesExample").withAuthor("Thomas Trojer")
				// default length and angles (so that rules are less verbose)
				.withDefaultMoveLength(LEN).withDefaultAngles(ANGLE)
				// rule: A ---> A + B - A A + A + A A + A B + A A - B + A A - A - A A - A B - A A A
				.withRule("A").andProduction().F("A").yaw().f("B").negYaw().F("A").F("A").yaw().F("A").yaw().F("A").F("A").yaw().F("A").f("B").yaw().F("A").F("A").negYaw().f("B").yaw().F("A").F("A").negYaw().F("A").negYaw().F("A").F("A").negYaw().F("A").f("B").negYaw().F("A").F("A").F("A").done()
				// rule: B ---> B B B B B B
				.withRule("B").andProduction().f("B").f("B").f("B").f("B").f("B").f("B").done()
				// finalize
				.build();
		// configuring the execution environment
		this.linnExecutor = LinnExecutor.newExecutor().useLinn(linn).traceStates(true)
				// axiom to start the L-System with: A + A + A + A
				.withAxiom().F("A").yaw().F("A").yaw().F("A").yaw().F("A").done();
	}

	@Override
	public void settings() {
		this.size(WINDOW_WIDTH, WINDOW_HEIGHT);
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
		// get post production state (and its trace)
		LinnTurtle state = this.linnExecutor.getState();
		if (state.hasPreviousState() == false) {
			// no state yet
			return;
		}
		LinnTurtle previousState = state.getPreviousState();
		Bounds bounds = state.getBounds();
		// focus on actual drawing area
		this.pushMatrix();
		this.scale(WINDOW_WIDTH / (float) bounds.getExpansionX(), WINDOW_HEIGHT / (float) bounds.getExpansionY());
		this.translate((float) -bounds.getMinX(), (float) -bounds.getMinY());
		// render lines that connect states
		while (previousState != null) {
			if (state.getStateChangeType() != StateChangeType.JUMP) {
				// turtle moved, draw
				this.line((float) state.getX(), (float) state.getY(), (float) previousState.getX(), (float) previousState.getY());
			}
			// update states
			state = previousState;
			previousState = state.getPreviousState();
		}
		this.popMatrix();
	}

	public static void main(String _args[]) {
		PApplet.main(new String[] { linn.examples.IslandsAndLakesExample.class.getName() });
	}
}
