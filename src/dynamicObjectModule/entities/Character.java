package dynamicObjectModule.entities;

import java.awt.Graphics;

public class Character extends Sprite {
	public enum DIRECTIONS {
		UP, DOWN, LEFT, RIGHT
	};

	private int _id;
	private int _speed;
	private int _x;
	private int _y;
	private DIRECTIONS _direction;

	public Character(int id, int x, int y, DIRECTIONS direction, int speed) {
		super(x, y);
		assert (id >= 0);
		assert (direction == DIRECTIONS.UP);
		assert (direction == DIRECTIONS.DOWN);
		assert (direction == DIRECTIONS.LEFT);
		assert (direction == DIRECTIONS.RIGHT);
		assert (speed >= 0);

		_id = id;
		_direction = direction;
		_speed = speed;
	}

	public DIRECTIONS getDirection() {
		return _direction;
	}

	public void setDirection(DIRECTIONS direction) {
		assert (direction == DIRECTIONS.UP);
		assert (direction == DIRECTIONS.DOWN);
		assert (direction == DIRECTIONS.LEFT);
		assert (direction == DIRECTIONS.RIGHT);

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

	public int getX() {
		return _x;
	}

	public void setX(int x) {
		_x = x;
	}

	public int getY() {
		return _y;
	}

	public void setY(int y) {
		_y = y;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawRect(_x, _y, 100, 100);
	}
}
