package dynamicObjectModule.entities;

import javax.swing.JLabel;

public abstract class Sprite extends JLabel {
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

	@Override
	public int getX() {
		return _x;
	}

	public void setX(int x) {
		_x = x;
	}

	@Override
	public int getY() {
		return _y;
	}

	public void setY(int y) {
		_y = y;
	}
}
