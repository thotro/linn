# linn
A small L-System (Lindenmayer System) interpreter written in Java.

![linn](https://github.com/thotro/linn/blob/master/linn.png)
LINN ({L}-System interpreter developed in {INN}sbruck) is a small, configurable L-System interpreter with the following features and design intentions:
 * Types of L-Systems supported:
   * Both context-free and context-sensitive ones
   * Both stochastic and deterministic ones
   * Parameterized ones
 * Written in Java 8 with optional ANTLR4 grammar
 * Core library has no noteworthy external dependencies (just Google Guava)
 * Core library does not include any render capabilities (although examples do)
 * User code injection to accomplish the following:
   * Condition evaluation for selecting production rules
   * Parameter values, default values and variables
   * Notification actions on productions and entire interations (e.g. useful for rendering, logging of results, etc.)
 * Syntax mostly based on what can be found in Prusinkiewicz, Lindenmayer, Hanan, et al., "The Algorithmic Beauty of Plants", freely available [here](http://algorithmicbotany.org/papers/#abop)
 * Examples will show both API usage, actual rendering in Processing/OpenGL and export of production results as meshes/point clouds

Status
------

Project status: 40%

What works so far: 
 * Fluent API for defining L-systems and executing them
 * State change and post execution notifications to trigger e.g. rendering
 * Deterministic, context-free L-systems
 * Stochastic L-systems
 * Basic examples (2D) rendered as [Processing](https://processing.org/) sketches

Current milestone: further productions, parameterized rules, examples

Contents
--------

 * [Project structure](../../wiki/Project-structure)
 * [Example outputs](../../wiki/Example-outputs)

That's it for now :)




