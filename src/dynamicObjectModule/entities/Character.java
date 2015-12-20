package dynamicObjectModule.entities;

import java.awt.Graphics;

public class Character extends Sprite {
	public enum DIRECTIONS {
		UP, DOWN, LEFT, RIGHT
	};

	public static final int DEFAULT_SPEED = 0;
	public static final DIRECTIONS DEFAULT_DIRECTION = DIRECTIONS.RIGHT; 

	private int _id;
	private int _speed;
	private DIRECTIONS _direction;

	public Character(int id, int x, int y, DIRECTIONS direction, int speed) {
		super(x, y);
		
		assert (id >= 0);
		assert (isDirectionValid(direction));
		assert (speed >= 0);

		_id = id;
		_direction = direction;
		_speed = speed;
	}

	public DIRECTIONS getDirection() {
		return _direction;
	}

	public void setDirection(DIRECTIONS direction) {
		assert (isDirectionValid(direction));

		_direction = direction;
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		assert (id >= 0);
		_id = id;
	}

	public int getSpeed() {
		return _speed;
	}

	public void setSpeed(int speed) {
		assert (speed >= 0);
		_speed = speed;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawRect(_x, _y, 100, 100);
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
