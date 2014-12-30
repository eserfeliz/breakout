import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Field extends GraphicsProgram implements MouseMotionListener {
	
	GCanvas canvas = null;
	
	GRect brick = null;
	GRect paddle = null;
	GOval ball = null;
	
	private int bricksRemaining = 0;
	private int xPos, yPos = 0;
	
	public Field() {
		canvas = getGCanvas();
		yPos = Consts.Brick.Spacing.BRICK_Y_OFFSET;
		xPos = Consts.Brick.Spacing.BRICK_SEP / 2;
		canvas.addMouseMotionListener(this);
	}

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
	}

	public void init() {
	}
	
	public void setup() {
		setupBricks();
		setupPaddle();
		setupBall();
	}
	
	private void setupBricks() {
		for (int i = 1; i <= Consts.Brick.Number.NBRICK_ROWS; i++) {
			for (int j = 1; j <= Consts.Brick.Number.NBRICKS_PER_ROW; j++) {
				bricksRemaining += 1;
				switch (i) {
				case 1:
				case 2:
					drawBrick(Color.RED);
					break;
				case 3:
				case 4:
					drawBrick(Color.ORANGE);
					break;
				case 5:
				case 6:
					drawBrick(Color.YELLOW);
					break;
				case 7:
				case 8:
					drawBrick(Color.GREEN);
					break;
				case 9:
				case 10:
					drawBrick(Color.CYAN);
					break;
				default:
					break;
				}
				xPos = xPos + Consts.Brick.Spacing.BRICK_SEP + Consts.Brick.Dimensions.BRICK_WIDTH;
			}
			xPos = Consts.Brick.Spacing.BRICK_SEP / 2;
			yPos += Consts.Brick.Dimensions.BRICK_HEIGHT + Consts.Brick.Spacing.BRICK_SEP;
		}
	}
	
	private void drawBrick(Color color) {
		GRect brick = new GRect(xPos, yPos, Consts.Brick.Dimensions.BRICK_WIDTH, Consts.Brick.Dimensions.BRICK_HEIGHT);
		brick.setColor(color);
		brick.setFilled(true);
		canvas.add(brick);
	}
	
	private void setupPaddle() {
		drawPaddle();
	}
	
	private void drawPaddle() {
		paddle = new GRect(Breakout.APPLICATION_WIDTH / 2 - 30.0, Consts.Field.HEIGHT - (Consts.Paddle.Spacing.PADDLE_Y_OFFSET + Consts.Paddle.Dimensions.PADDLE_HEIGHT), Consts.Paddle.Dimensions.PADDLE_WIDTH, Consts.Paddle.Dimensions.PADDLE_HEIGHT);
		paddle.setColor(Color.BLACK);
		paddle.setFilled(true);
		canvas.add(paddle);
	}
	
	private void setupBall() {
		drawBall();
	}
	
	public GOval getBall() {
		return ball;
	}
	
	private void drawBall() {
		ball = new GOval(Breakout.APPLICATION_WIDTH / 2 - 2 * Consts.Ball.BALL_RADIUS, Consts.Field.HEIGHT - (Consts.Paddle.Spacing.PADDLE_Y_OFFSET + Consts.Paddle.Dimensions.PADDLE_HEIGHT + 2 * Consts.Ball.BALL_RADIUS), 2 * Consts.Ball.BALL_RADIUS, 2 * Consts.Ball.BALL_RADIUS);
		ball.setColor(Color.BLACK);
		ball.setFilled(true);
		ball.sendToBack();
		canvas.add(ball);
	}
	
	public void mouseMoved(MouseEvent e) {
		Point movePos = e.getPoint();
		if (movePos.x <= 3) {
			paddle.setLocation(0, Consts.Field.HEIGHT - (Consts.Paddle.Spacing.PADDLE_Y_OFFSET + Consts.Paddle.Dimensions.PADDLE_HEIGHT));
		} else if (movePos.x >= (Breakout.APPLICATION_WIDTH - Consts.Paddle.Dimensions.PADDLE_WIDTH)) {
			paddle.setLocation(Breakout.APPLICATION_WIDTH - Consts.Paddle.Dimensions.PADDLE_WIDTH, Consts.Field.HEIGHT - (Consts.Paddle.Spacing.PADDLE_Y_OFFSET + Consts.Paddle.Dimensions.PADDLE_HEIGHT));
		} else {
			paddle.setLocation(movePos.x, Consts.Field.HEIGHT - (Consts.Paddle.Spacing.PADDLE_Y_OFFSET + Consts.Paddle.Dimensions.PADDLE_HEIGHT));
		}
	    canvas.repaint();
	    saySomething("Mouse moved", e);
	    e.consume();
	}
	
	void saySomething(String eventDescription, MouseEvent e) {
        System.out.println(eventDescription 
                        + " (" + e.getX() + "," + e.getY() + ")"
                        + " detected on "
                        + e.getComponent().getClass().getName()
                        );
	}
	
	private void pause(int x) {
		try {
			Thread.sleep(x);
			} catch (InterruptedException e) {
			}
	}
	
	public GCanvas getCanvas() {
		return canvas;
	}
}