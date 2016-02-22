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
import linn.core.math.Bounds;
import processing.core.PApplet;

/**
 * A 2D processing sketch that draws a continuous line version of the Sierpinski
 * triangle. This sketch features
 * <ul>
 * <li>new L-system production iteration on mouse click</li>
 * <li>post-production rendering by using the history of state changes (traces)
 * </li>
 * <li>auto focus on draw area by making use of the computed bounds of a
 * {@link LinnTurtle}</li>
 * </ul>
 *
 * @author Thomas Trojer <thomas@trojer.net> -- Initial contribution
 */
public class SierpinskiExample extends PApplet {

	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 800;

	private static final double ANGLE = 1 / 3.0 * Math.PI;
	private static final int LEN = 2;

	private LinnExecutor linnExecutor;

	@Override
	public void setup() {
		// the L-system definition
		final Linn linn = LinnBuilder.newLinn("SierpinskiExample").withAuthor("Thomas Trojer")
				// rule: A ---> B - A - B
				.withRule("A").andProduction().F(LEN, "B").yaw(-ANGLE).F(LEN, "A").yaw(-ANGLE).F(LEN, "B").done()
				// rule: B ---> A + B + A
				.withRule("B").andProduction().F(LEN, "A").yaw(ANGLE).F(LEN, "B").yaw(ANGLE).F(LEN, "A").done()
				// finalize
				.build();
		// configuring the execution environment
		this.linnExecutor = LinnExecutor.newExecutor().useLinn(linn).traceStates(true)
				// axiom to start the L-System with: H
				.withAxiom().F(LEN, "A").done();
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
		LinnTurtle previousState = state.getPreviousState();
		if (previousState == null) {
			return;
		}
		Bounds bounds = state.getBounds();
		// focus on actual drawing area
		this.pushMatrix();
		this.scale(WINDOW_WIDTH / (float) bounds.getExpansionX(), WINDOW_HEIGHT / (float) bounds.getExpansionY());
		this.translate((float) -bounds.getMinX(), (float) -bounds.getMinY());
		// render lines that connect states
		while (previousState != null) {
			this.line((float) state.getX(), (float) state.getY(), (float) previousState.getX(), (float) previousState.getY());
			// update states
			state = previousState;
			previousState = state.getPreviousState();
		}
		this.popMatrix();
	}

	public static void main(String _args[]) {
		PApplet.main(new String[] { linn.examples.SierpinskiExample.class.getName() });
	}
}
