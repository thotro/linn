# linn
A small L-System (Lindenmayer System) interpreter written in Java.

![linn](https://github.com/thotro/linn/blob/master/linn.png)
LINN ({L}-System interpreter developed in {INN}sbruck) is a small, configurable L-System interpreter with the following features and design intentions:
 * Types of L-Systems supported:
   * Both context-free and context-sensitive ones
   * Both stochastic and deterministic ones
   * Parameterized ones
 * Written in Java/ANTLR4
 * Core library has no other external dependencies
 * Core library does not include any render capabilities (although test/example projects will)
 * User code injection to accomplish the following:
   * Condition evaluation for selecting production rules
   * Parameter values, default values, variables
   * Production/simulation notification actions (e.g. for actual rendering, logging of results, etc.)
 * Syntax mostly based on what can be found in Prusinkiewicz, Lindenmayer, Hanan, et al., "The Algorithmic Beauty of Plants", freely available [http://algorithmicbotany.org/papers/#abop here]

Project status: 5%

Current milestone: Basic ANTLR grammar and parser infrastructure

That's it for now :)


