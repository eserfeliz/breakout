public final class Consts {
	
	public final class Field {

	/** Dimensions of game board (usually the same) */
	public static final int WIDTH = Breakout.APPLICATION_WIDTH;
	public static final int HEIGHT = Breakout.APPLICATION_HEIGHT;

    private Field() {}
  }

  public final class Paddle {
	  
	  public final class Spacing {
		  /** Offset of the paddle up from the bottom */
		  public static final int PADDLE_Y_OFFSET = 30;
		  
		  private Spacing() {}
	  }
	  
	  public final class Dimensions {
		  /** Dimensions of the paddle */
		  public static final int PADDLE_WIDTH = 60;
		  public static final int PADDLE_HEIGHT = 10;
		  
		  private Dimensions() {}
	  }

	  private Paddle() {}
  }

  public final class Brick {
	  
	  public final class Number {
		  /** Number of bricks per row */
		  public static final int NBRICKS_PER_ROW = 10;

		  /** Number of rows of bricks */
		  public static final int NBRICK_ROWS = 10;
		  
		  private Number() {}
	  }
	  
	  public final class Spacing {
		  /** Separation between bricks */
		  public static final int BRICK_SEP = 4;
		  
		  /** Offset of the top brick row from the top */
		  public static final int BRICK_Y_OFFSET = 70;
		  
		  private Spacing() {}
	  }
	
	  public final class Dimensions {
		  /** Width of a brick */
		  public static final int BRICK_WIDTH =
			  (Field.WIDTH - (Brick.Number.NBRICKS_PER_ROW - 1) * Brick.Spacing.BRICK_SEP) / Brick.Number.NBRICKS_PER_ROW;

		  /** Height of a brick */
		  public static final int BRICK_HEIGHT = 8;

		  private Dimensions() {}
	  }
	  
	  private Brick() {}
  }
  
  public final class Ball {
	  
	  /** Radius of the ball in pixels */
	  public static final int BALL_RADIUS = 6;

	  private Ball() {}
  }
  
  public final class Turns {
	  
	  /** Number of turns */
	  public static final int NTURNS = 3;
	  
	  private Turns() {}
  }
  
  private Consts() {}
}