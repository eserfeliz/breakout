import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Engine extends Program {
	
	Field field;
	GOval ball = null;
	private double vx, vy;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private boolean active;
	
	public Engine(Field f) {
		field = f;
		ball = field.getBall();
		vx = rgen.nextDouble(1, 9);
		vy = -5;
		active = false;
	}
	
	public void startup() {
		if (!isActive()) {
			makeActive();
		}
		run();
	}
	
	public void run() {
		while (isActive()) {
			moveBall(field.getBall());
			if ((field.getBall().getX() >= (Breakout.APPLICATION_WIDTH - 2 * Consts.Ball.BALL_RADIUS)) || (field.getBall().getX() <= 0)) {
				reverseX();
			} else if (field.getBall().getY() <= 0) {
				reverseY();
			}
		}
	}
	
	public void reverseX() {
		vx = -vx;
	}
	
	public void reverseY() {
		vy = -vy;
	}
	
	public double absY() {
		return Math.abs(vy);
	}
	
	public double vx() {
		return vx;
	}
	
	public double vy() {
		return vy;
	}
	
	public void moveBall(GOval b) {
		b.move(vx, vy);
		pause(30);
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void makeActive() {
		active = true;
	}
	
	public void makeInactive() {
		active = false;
	}
	
	private void pause(int x) {
		try {
			Thread.sleep(x);
			} catch (InterruptedException e) {
			}
	}
	
}