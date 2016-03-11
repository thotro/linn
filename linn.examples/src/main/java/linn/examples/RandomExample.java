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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import linn.core.Linn;
import linn.core.execute.LinnExecutor;
import linn.core.execute.state.LinnTurtle;
import linn.core.execute.state.StateChangeType;
import linn.core.lang.LinnBuilder;
import linn.core.lang.ProductionRuleProductionBuilder;
import linn.core.math.Bounds;
import processing.core.PApplet;

/**
 * NOTE: This examples generates random L-Systems. This is fun to play around
 * with but is not considered stable by any means. It will e.g. not work all the
 * time as
 * different random numbers lead to varying results (or none at all).
 * <p>
 *
 * It prints the
 * seed and the L-System definition for later reuse.
 * <p>
 *
 * Please also note the comments within the source code and scan through all
 * uses of <code>this.rand.next...()</code> to change randomness behavior as you
 * think feels right. ;-)
 *
 * @author Thomas Trojer <thomas@trojer.net> -- Initial contribution
 *
 */
public class RandomExample extends PApplet {

	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 800;

	private Random rand;

	private LinnExecutor linnExecutor;

	@Override
	public void setup() {
		long seed = System.currentTimeMillis();
		System.out.println("Seed: " + seed);
		this.rand = new Random(seed);
		// the L-system definition
		LinnBuilder builder = LinnBuilder.newLinn("RandomExample").withAuthor("Thomas Trojer");
		// how many productions
		int numberOfProductions = this.rand.nextInt(10) + 1;
		int numberOfDifferentLabels = this.rand.nextInt(numberOfProductions + 1) + 1;
		// the rule names
		char[] ruleNames = this.computeProductionRuleNames(numberOfProductions, numberOfDifferentLabels);
		System.out.println(Arrays.toString(ruleNames));
		for (int i = 0; i < ruleNames.length; i++) {
			// number of rules and rewrites
			int numberOfRules = this.rand.nextInt(5) + 1;
			int numberOfRewriteRules = this.rand.nextInt(numberOfRules + 1) + 1;
			// compute productions
			char ruleName = ruleNames[i];
			ProductionRuleProductionBuilder<LinnBuilder> ruleBuilder = builder.withRule(ruleName + "").andProduction();
			this.computeProduction(ruleBuilder, ruleNames, numberOfRules, numberOfRewriteRules);
		}
		Linn linn = builder.build();
		System.out.println(linn);

		// axiom
		int numberOfAxiomRules = this.rand.nextInt(5);
		// configuring the execution environment
		ProductionRuleProductionBuilder<LinnExecutor> axiomBuilder = LinnExecutor.newExecutor().useLinn(linn).traceStates(true).withAxiom();
		this.computeProduction(axiomBuilder, ruleNames, numberOfAxiomRules, numberOfAxiomRules);
		this.linnExecutor = axiomBuilder.done();
	}

	private void computeMoveProduction(ProductionRuleProductionBuilder<?> builder) {
		double length = this.rand.nextInt(3) + 1;
		boolean isJump = false; //this.rand.nextBoolean();
		if (isJump) {
			builder.f(length);
		} else {
			builder.F(length);
		}
	}

	private void computeRotateProduction(ProductionRuleProductionBuilder<?> builder) {
		int deg45 = this.rand.nextInt(8);
		double yaw = Math.PI * 0.25 * deg45;
		//int deg90 = this.rand.nextInt(4);
		//double yaw = Math.PI * 0.5 * deg90;
		builder.yaw(yaw);
	}

	private void computeRewriteProduction(ProductionRuleProductionBuilder<?> builder, char[] ruleNames) {
		int ruleIdx = this.rand.nextInt(ruleNames.length);
		char ruleName = ruleNames[ruleIdx];
		boolean isFRewrite = this.rand.nextBoolean();
		if (isFRewrite) {
			double length = this.rand.nextInt(3) + 1; //this.rand.nextDouble();
			boolean isJump = false; //this.rand.nextBoolean();
			if (isJump) {
				builder.f(length, ruleName + "");
			} else {
				builder.F(length, ruleName + "");
			}
		} else {
			builder.rewrite(ruleName + "");
		}
	}

	private void computeProduction(ProductionRuleProductionBuilder<?> ruleBuilder, char[] ruleNames, int numberOfProductions, int numberOfRewrites) {
		List<Boolean> rewriteIdxs = Lists.newArrayList();
		for (int i = 0; i < numberOfProductions; i++) {
			rewriteIdxs.add(i < numberOfRewrites);
		}
		Collections.shuffle(rewriteIdxs, this.rand);
		for (int i = 0; i < numberOfProductions; i++) {
			if (rewriteIdxs.get(i) == true) {
				this.computeRewriteProduction(ruleBuilder, ruleNames);
			} else {
				boolean isMove = this.rand.nextBoolean();
				if (isMove) {
					this.computeMoveProduction(ruleBuilder);
				} else {
					this.computeRotateProduction(ruleBuilder);
				}
			}
		}
		ruleBuilder.done();
	}

	private char[] computeProductionRuleNames(int numberOfRulesLabels, int numberOfDifferentRuleLabels) {
		// define random production rule names
		char[] ruleLabels = new char[numberOfDifferentRuleLabels];
		for (int i = 0; i < numberOfDifferentRuleLabels; i++) {
			char letter = (char) (this.rand.nextInt(91 - 65) + 65);
			ruleLabels[i] = letter;
		}
		// pick from rule labels and assign to actual rules
		char[] productionRuleNames = new char[numberOfRulesLabels];
		for (int i = 0; i < numberOfRulesLabels; i++) {
			int labelIdx = this.rand.nextInt(ruleLabels.length);
			char letter = ruleLabels[labelIdx];
			productionRuleNames[i] = letter;
		}
		return productionRuleNames;
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
		PApplet.main(new String[] { linn.examples.RandomExample.class.getName() });
	}
}
