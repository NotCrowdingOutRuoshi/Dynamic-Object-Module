package dynamicObjectModule.entities;

public class Character extends Sprite {
	public enum DIRECTIONS {
		UP, DOWN, LEFT, RIGHT
	};

	public static final int DEFAULT_SPEED = 0;
	public static final DIRECTIONS DEFAULT_DIRECTION = DIRECTIONS.RIGHT;

	private int _speed;
	private DIRECTIONS _direction;

	public Character(int id, int x, int y, DIRECTIONS direction, int speed) {
		super(id, x, y);

		assert (isDirectionValid(direction));
		assert (speed >= 0);

		_direction = direction;
		_speed = speed;
	}

	@Override
	public void draw() {
		super.draw();

		consoleOutput.append("Character drawed\n");
		consoleOutput.append("ID: " + _id + "\n");
		consoleOutput.append("x: " + _x + "\n");
		consoleOutput.append("y: " + _y + "\n");
		consoleOutput.append("direction: " + _direction + "\n");
		consoleOutput.append("speed: " + _speed + "\n");

		System.out.println(consoleOutput);
	}

	public DIRECTIONS getDirection() {
		return _direction;
	}

	public void setDirection(DIRECTIONS direction) {
		assert (isDirectionValid(direction));

		_direction = direction;
	}

	public int getSpeed() {
		return _speed;
	}

	public void setSpeed(int speed) {
		assert (speed >= 0);
		_speed = speed;
	}

	private boolean isDirectionValid(DIRECTIONS direction) {
		for (DIRECTIONS dir : DIRECTIONS.values()) {
			if (direction == dir) {
				return true;
			}
		}

		return false;
	}
}
