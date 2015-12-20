package dynamicObjectModule.entities;

import java.awt.Graphics;

public class Item extends Sprite {
	public final static int EMPTY_OWNER = -1;

	private int _index;
	private int _owner;
	private String _name;
	private boolean _shared;

	public Item(String name, int index, boolean shared, int x, int y) {
		super(x, y);

		assert (name != null && !name.isEmpty());
		assert (index >= 0);
		
		_name = name;
		_owner = EMPTY_OWNER;
		_index = index;
		_shared = shared;
	}

	public int getIndex() {
		return _index;
	}

	public void setIndex(int index) {
		assert (index >= 0);
		_index = index;
	}

	public int getOwner() {
		return _owner;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawRect(_x, _y, 50, 50);
	}

	public void setOwner(int owner) {
		assert (owner >= 0 || owner == EMPTY_OWNER);
		_owner = owner;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		assert (name != null && !name.isEmpty());
		_name = name;
	}

	public boolean isOwned() {
		return _owner != EMPTY_OWNER;
	}

	public boolean isShared() {
		return _shared;
	}

	public void setShared(boolean isShared) {
		_shared = isShared;
	}
}
