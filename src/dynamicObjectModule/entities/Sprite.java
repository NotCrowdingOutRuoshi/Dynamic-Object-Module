package dynamicObjectModule.entities;

public abstract class Sprite {
	public static final int DEFAULT_X = 0;
	public static final int DEFAULT_Y = 0;
	
	protected int _x;
	protected int _y;

	public Sprite(int x, int y) {
		assert (x >= 0);
		assert (y >= 0);
		
		_x = x;
		_y = y;
	}

	public int getX() {
		return _x;
	}

	public void setX(int x) {
		assert x >= 0;
		_x = x;
	}

	public int getY() {
		return _y;
	}

	public void setY(int y) {
		assert y >= 0;
		_y = y;
	}
}
