/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends Program {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;
	
	Field field = null;
	Engine engine = null;
	GCanvas canvas = null;
	
	public Breakout() {
		field = new Field();
		engine = new Engine(field);
	}

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		field.setup();
		engine.startup();
	}
	
	public void init() {
		canvas = field.getGCanvas();
		add(canvas);
	}

}
