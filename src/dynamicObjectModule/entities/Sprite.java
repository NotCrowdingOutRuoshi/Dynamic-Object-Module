package dynamicObjectModule.entities;

public abstract class Sprite {
	public static final int DEFAULT_X = 0;
	public static final int DEFAULT_Y = 0;

	protected StringBuilder consoleOutput;
	protected int _id;
	protected int _x;
	protected int _y;

	public Sprite(int id, int x, int y) {
		assert (id >= 0);
		assert (x >= 0);
		assert (y >= 0);

		_id = id;
		_x = x;
		_y = y;
	}

	public void draw() {
		flushConsoleOutput();
	}

	public String flushConsoleOutput() {
		String result = consoleOutput.toString();
		consoleOutput = new StringBuilder();

		return result;
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		assert (id >= 0);
		_id = id;
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
