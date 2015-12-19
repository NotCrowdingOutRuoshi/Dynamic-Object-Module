package dynamicObjectModule;

import java.awt.Point;
import java.util.ArrayList;

import dynamicObjectModule.entities.Sprite;
import dynamicObjectModule.entities.Item;
import dynamicObjectModule.entities.Character;
import dynamicObjectModule.entities.Character.DIRECTIONS;
import fake.MoveCodes;
import fake.TCPClientModule;

public class DynamicObjectModule {
	private ArrayList<Item> _items;
	private ArrayList<Character> _sprites;
	private TCPClientModule _tcpClientModule;

	public DynamicObjectModule(TCPClientModule tcpClientModule) {
		assert (tcpClientModule != null);

		_items = new ArrayList<Item>();
		_sprites = new ArrayList<Character>();
		_tcpClientModule = tcpClientModule;
	}

	public void addItem(String name, int index, boolean shared, int x, int y) {
		assert (name != null && !name.isEmpty());
		assert (index >= 0);

		for (Item item : _items) {
			assert (index != item.getIndex());
		}

		Item item = new Item(name, index, shared, x, y);
		_items.add(item);
	}

	public void addVirtualCharacter(int clientNumber) {
		assert (clientNumber >= 0);
		assert (findVirtualCharacter(clientNumber) == null);

		for (Character character : _sprites) {
			assert (clientNumber != character.getId());
		}

		Character character = new Character(clientNumber, 0, 0, Character.DIRECTIONS.RIGHT, 0);
		_sprites.add(character);
	}

	public Item findItem(int index) {
		assert (index >= 0);

		for (Item item : _items) {
			if (item.getIndex() == index) {
				return item;
			}
		}

		return null;
	}

	public Character findVirtualCharacter(int id) {
		assert (id >= 0);
		for (Character character : _sprites) {
			if (character.getId() == id) {
				return character;
			}
		}

		return null;
	}

	public Sprite[] getAllDynamicObjects() {
		Character[] sprites = new Character[_sprites.size()];
		_sprites.toArray(sprites);

		Item[] items = new Item[_items.size()];
		_items.toArray(items);

		ArrayList<Sprite> result = new ArrayList<Sprite>();
		result.addAll(_sprites);
		result.addAll(_items);

		return result.toArray(null);
	}

	public Point getVirtualCharacterPosition(int clientNumber) {
		Character character = findVirtualCharacter(clientNumber);

		assert (character != null);

		return new Point(character.getX(), character.getY());
	}

	public void keyGETPressed(int id) {
		Character character = findVirtualCharacter(id);
		
		assert (character != null);
		
		for (Item item : _items) {
			if (item.getX() == character.getX() && item.getY() == character.getY()) {
				_tcpClientModule.inputMoves(MoveCodes.GET);
				break;
			}
		}
	}

	public void updateItem(int index, boolean shared, int owner, int x, int y) {
		assert (findVirtualCharacter(owner) != null);

		Item item = findItem(index);

		assert (item != null);

		item.setShared(shared);
		item.setOwner(owner);
		item.setX(x);
		item.setY(y);
	}

	public void updateVirtualCharacter(int clientNumber, DIRECTIONS direction, int speed, int x, int y) {
		Character character = findVirtualCharacter(clientNumber);

		assert (character != null);

		character.setDirection(direction);
		character.setSpeed(speed);
		character.setX(x);
		character.setY(y);
	}
}
