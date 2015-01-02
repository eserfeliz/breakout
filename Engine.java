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
	GObject object = null;
	int turns = 0;
	
	public Engine(Field f) {
		field = f;
		ball = field.getBall();
		vx = rgen.nextDouble(1, 9);
		vy = -5;
		active = false;
		turns = Consts.Turns.NTURNS;
	}
	
	public void startup() {
		if (!isActive()) {
			makeActive();
			turns--;
		}
		run();
	}
	
	public void run() {
		while (isActive()) {
			checkCollisions();
			moveBall(field.getBall());
		}
	}
	
	public void init() {
	}
	
	private void checkCollisions() {
		checkForEnd();
		checkForWall();
		GObject gobj = checkForFieldObjs();
	}
	
	private void checkForEnd() {
		if (field.getBall().getY() >= (Breakout.APPLICATION_HEIGHT - (2 * Consts.Ball.BALL_RADIUS))) {
			if (turns > 0) {
				turns--;
				field.getBall().setLocation(Breakout.APPLICATION_WIDTH / 2 - 2 * Consts.Ball.BALL_RADIUS, Consts.Field.HEIGHT - (Consts.Paddle.Spacing.PADDLE_Y_OFFSET + Consts.Paddle.Dimensions.PADDLE_HEIGHT + 2 * Consts.Ball.BALL_RADIUS));
				absY();
				reverseY();
				absX();
				//field.repaint();
				pause(5000);
			} else {
				makeInactive();
			}
		}
	}
	
	private void checkForWall() {
		System.out.println(isActive());
		if (field.getBall().getX() <= 0) {
			absX();
		} else if (field.getBall().getX() >= (Breakout.APPLICATION_WIDTH - 2 * Consts.Ball.BALL_RADIUS)) {
			absX();
			reverseX();
		} else if (field.getBall().getY() <= 0) {
			absY();
		}
	}
	
	private GObject checkForFieldObjs() {
		object = checkPoints(field.getBall().getX(), field.getBall().getY());
		if (object != null) {
			if (!(object.getColor() == Color.BLACK)) {
				field.remove(object);
			}
			return object;
		}
		return null;
	}
	
	private GObject checkPoints(double x, double y) {
		object = field.getElementAt((x + Consts.Ball.BALL_RADIUS), y);
		if (object != null && object != field.getBall()) {
			absY();
			if ((field.getElementAt(x, (y + Consts.Ball.BALL_RADIUS)) != null) && (field.getElementAt(x, (y + Consts.Ball.BALL_RADIUS)) != field.getBall())) {
				reverseX();
				return object;
			} else if ((field.getElementAt((x + (2 * Consts.Ball.BALL_RADIUS)), (y + Consts.Ball.BALL_RADIUS)) != null) && (field.getElementAt((x + (2 * Consts.Ball.BALL_RADIUS)), (y + Consts.Ball.BALL_RADIUS)) != field.getBall())) {
				reverseX();
				return object;
			} else {
				return object;
			}
		}
		object = field.getElementAt((x + Consts.Ball.BALL_RADIUS),(y + (2 * Consts.Ball.BALL_RADIUS)));
		if (object != null && object != field.getBall()) {
			absY();
			reverseY();
			if ((field.getElementAt(x, (y + Consts.Ball.BALL_RADIUS)) != null) && (field.getElementAt(x, (y + Consts.Ball.BALL_RADIUS)) != field.getBall())) {
				reverseX();
				return object;
			} else if ((field.getElementAt((x + (2 * Consts.Ball.BALL_RADIUS)), (y + Consts.Ball.BALL_RADIUS)) != null) && (field.getElementAt((x + (2 * Consts.Ball.BALL_RADIUS)), (y + Consts.Ball.BALL_RADIUS)) != field.getBall())) {
				reverseX();
				return object;
			} else {
				return object;
			}
		}
		object = field.getElementAt(x, (y + Consts.Ball.BALL_RADIUS));
		if (object != null && object != field.getBall()) {
			absX();
			if ((field.getElementAt((x + Consts.Ball.BALL_RADIUS), y) != null) && (field.getElementAt((x + Consts.Ball.BALL_RADIUS), y) != field.getBall())) {
				absY();
				return object;
			} else if ((field.getElementAt((x + Consts.Ball.BALL_RADIUS), (y + (2 * Consts.Ball.BALL_RADIUS))) != null) && (field.getElementAt((x + Consts.Ball.BALL_RADIUS), (y + (2 * Consts.Ball.BALL_RADIUS))) != field.getBall())) {
				absY();
				reverseY();
				return object;
			} else {
				return object;
			}
		}
		object = field.getElementAt((x + (2 * Consts.Ball.BALL_RADIUS)), (y + Consts.Ball.BALL_RADIUS));
		if (object != null && object != field.getBall()) {
			absX();
			reverseX();
			if ((field.getElementAt((x + Consts.Ball.BALL_RADIUS), y) != null) && (field.getElementAt((x + Consts.Ball.BALL_RADIUS), y) != field.getBall())) {
				absY();
				return object;
			} else if ((field.getElementAt((x + Consts.Ball.BALL_RADIUS), (y + (2 * Consts.Ball.BALL_RADIUS))) != null) && (field.getElementAt((x + Consts.Ball.BALL_RADIUS), (y + (2 * Consts.Ball.BALL_RADIUS))) != field.getBall())) {
				absY();
				reverseY();
				return object;
			} else {
				return object;
			}
		}
		return null;
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
	
	private void absX() {
		vx = Math.abs(vx);
	}
	
	private void absY() {
		vy= Math.abs(vy);
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
	
	private void resetBall(GOval b) {
		b.move(Breakout.APPLICATION_WIDTH / 2 - 2 * Consts.Ball.BALL_RADIUS, Consts.Field.HEIGHT - (Consts.Paddle.Spacing.PADDLE_Y_OFFSET + Consts.Paddle.Dimensions.PADDLE_HEIGHT + 2 * Consts.Ball.BALL_RADIUS));
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