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
			checkCollisions();
		}
	}
	
	private void checkCollisions() {
		checkEnd();
		checkWall();
	}
	
	private void checkEnd() {
		if (field.getBall().getY() >= (Breakout.APPLICATION_HEIGHT - (2 * Consts.Ball.BALL_RADIUS))) {
			makeInactive();
		}
	}
	
	private void checkWall() {
		if ((field.getBall().getX() >= (Breakout.APPLICATION_WIDTH - 2 * Consts.Ball.BALL_RADIUS)) || (field.getBall().getX() <= 0)) {
			reverseX();
		} else if (field.getBall().getY() <= 0) {
			reverseY();
		}
	}
	
	public boolean isActive() {
		return active;
	}
	
	private void reverseX() {
		vx = -vx;
	}
	
	private void reverseY() {
		vy = -vy;
	}
	
	private double absY() {
		return Math.abs(vy);
	}
	
	private double vx() {
		return vx;
	}
	
	private double vy() {
		return vy;
	}
	
	private void moveBall(GOval b) {
		b.move(vx, vy);
		pause(30);
	}
	
	private void makeActive() {
		active = true;
	}
	
	private void makeInactive() {
		active = false;
	}
	
	private void pause(int x) {
		try {
			Thread.sleep(x);
			} catch (InterruptedException e) {
			}
	}
	
}