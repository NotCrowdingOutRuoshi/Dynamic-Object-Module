package tests.dynamicObjectModule;

import static org.junit.Assert.*;

import java.awt.Point;
import java.lang.reflect.Field;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dynamicObjectModule.DynamicObjectModule;
import dynamicObjectModule.entities.Character;
import dynamicObjectModule.entities.Item;
import dynamicObjectModule.entities.Sprite;
import dynamicObjectModule.entities.Character.DIRECTIONS;
import fake.TCPClientModule;

public class DynamicObjectModuleTest {
	private DynamicObjectModule dom;

	private final int _initialNumber = 0;

	private final String _initialItemName = "name";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@Before
	public void setUp() throws Exception {
		dom = new DynamicObjectModule(new TCPClientModule());
	}

	@After
	public void tearDown() throws Exception {
		dom = null;
	}

	@Test
	public void testDynamicObjectModule()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		dom = new DynamicObjectModule(new TCPClientModule());

		Field tcpField = DynamicObjectModule.class.getDeclaredField("_tcpClientModule");
		tcpField.setAccessible(true);

		assertNotNull("injected TCPClientModule should be set", tcpField.get(dom));

		tcpField.setAccessible(false);
	}

	@Test(expected = AssertionError.class)
	public void testDynamicObjectModuleWithNullInjection() {
		dom = new DynamicObjectModule(null);
	}

	@Test
	public void testAddItem() {
		dom.addItem(_initialItemName, _initialNumber, true, Item.DEFAULT_X, Item.DEFAULT_Y);
		Item item = dom.findItem(_initialNumber);

		assertNotNull("item should be added correctly", item);
		assertEquals("item should be added correctly", _initialNumber, item.getIndex());
		assertTrue("item should be added correctly", item.isShared());
		assertEquals("item should be added correctly", _initialItemName, item.getName());
		assertEquals("item should be added correctly", Item.DEFAULT_X, item.getX());
		assertEquals("item should be added correctly", Item.DEFAULT_Y, item.getY());
	}

	@Test(expected = AssertionError.class)
	public void testAddItemWithSameIndex() {
		dom.addItem(null, _initialNumber, true, Item.DEFAULT_X, Item.DEFAULT_Y);
		dom.addItem(null, _initialNumber, true, Item.DEFAULT_X, Item.DEFAULT_Y);
	}

	@Test(expected = AssertionError.class)
	public void testAddItemWithNullName() {
		dom.addItem(null, _initialNumber, true, Item.DEFAULT_X, Item.DEFAULT_Y);
	}

	@Test(expected = AssertionError.class)
	public void testAddItemWithEmptyName() {
		dom.addItem("", _initialNumber, true, Item.DEFAULT_X, Item.DEFAULT_Y);
	}

	@Test(expected = AssertionError.class)
	public void testAddItemWithNegativeIndex() {
		dom.addItem(_initialItemName, -1, true, Item.DEFAULT_X, Item.DEFAULT_Y);
	}

	@Test(expected = AssertionError.class)
	public void testAddItemWithNegativeX() {
		dom.addItem(_initialItemName, _initialNumber, true, -1, Item.DEFAULT_Y);
	}

	@Test(expected = AssertionError.class)
	public void testAddItemWithNegativeY() {
		dom.addItem(_initialItemName, _initialNumber, true, Item.DEFAULT_X, -1);
	}

	@Test
	public void testAddVirtualCharacter() {
		dom.addVirtualCharacter(0);
		assertNotNull("character should be added correctly", dom.findVirtualCharacter(0));
		assertEquals(0, dom.findVirtualCharacter(0).getId());
	}

	@Test(expected = AssertionError.class)
	public void testAddVirtualCharacterWithDuplicateClientNumber() {
		dom.addVirtualCharacter(0);
		dom.addVirtualCharacter(0);
	}

	@Test(expected = AssertionError.class)
	public void testAddVirtualCharacterWithNegativeClientNumber() {
		dom.addVirtualCharacter(-1);
	}

	@Test
	public void testFindItem() {
		int lastItemIndex = _initialNumber + 5;
		for (int i = _initialNumber; i <= lastItemIndex; i++) {
			dom.addItem(_initialItemName, i, true, Item.DEFAULT_X, Item.DEFAULT_Y);
		}

		Item item = dom.findItem(lastItemIndex);
		assertNotNull("item should be found correctly", item);
		assertEquals("item should be found correctly", lastItemIndex, item.getIndex());
		assertTrue("item should be found correctly", item.isShared());
		assertEquals("item should be found correctly", _initialItemName, item.getName());
		assertEquals("item should be found correctly", Item.DEFAULT_X, item.getX());
		assertEquals("item should be found correctly", Item.DEFAULT_Y, item.getY());
	}

	@Test
	public void testFindNonExistItem() {
		assertNull("item should not be found", dom.findItem(_initialNumber));
	}

	@Test
	public void testFindNonExistItem2() {
		dom.addItem(_initialItemName, _initialNumber, true, Item.DEFAULT_X, Item.DEFAULT_Y);
		assertNull("item should not be found", dom.findItem(_initialNumber + 1));
	}

	@Test
	public void testFindNonExistItem3() {
		int lastItemIndex = _initialNumber + 5;

		for (int i = _initialNumber; i <= lastItemIndex; i++) {
			dom.addItem(_initialItemName, i, true, Item.DEFAULT_X, Item.DEFAULT_Y);
		}

		assertNull("item should not be found", dom.findItem(lastItemIndex + 1));
	}

	@Test(expected = AssertionError.class)
	public void testFindItemWithNegativeIndex() {
		dom.findItem(-1);
	}
	
	@Test(expected = AssertionError.class)
	public void testFindItemWithNegativeIndex2() {
		dom.addItem(_initialItemName, _initialNumber, true, Item.DEFAULT_X, Item.DEFAULT_Y);
		dom.findItem(-1);
	}

	@Test
	public void testFindVirtualCharacter() {
		int lastCharacterId = 5;

		for (int i = 0; i <= lastCharacterId; i++) {
			dom.addVirtualCharacter(i);
		}

		Character character = dom.findVirtualCharacter(lastCharacterId);

		assertNotNull("character should be found", character);
		assertEquals("character should be found", lastCharacterId, character.getId());
	}

	@Test
	public void testFindNonExistVirtualCharacter() {
		assertNull("character should not be found", dom.findVirtualCharacter(_initialNumber));
	}

	@Test
	public void testFindNonExistVirtualCharacter2() {
		dom.addVirtualCharacter(_initialNumber);
		assertNull("character should not be found", dom.findVirtualCharacter(_initialNumber + 1));
	}

	@Test
	public void testFindNonExistVirtualCharacter3() {
		int lastCharacterId = _initialNumber + 5;

		for (int i = _initialNumber; i <= lastCharacterId; i++) {
			dom.findVirtualCharacter(i);
		}

		assertNull("character should not be found", dom.findVirtualCharacter(lastCharacterId + 1));
	}

	@Test
	public void testFindVirtualCharacterWithNegativeIndex() {
		assertNull("character with negative index should not be found", dom.findVirtualCharacter(-1));
		dom.addVirtualCharacter(_initialNumber);
		assertNull("character with negative index should not be found", dom.findVirtualCharacter(-1));
	}
	
	@Test
	public void testFindVirtualCharacterWithNegativeIndex2() {
		dom.addVirtualCharacter(_initialNumber);
		assertNull("character with negative index should not be found", dom.findVirtualCharacter(-1));
	}

	@Test
	public void testGetAllDynamicObjects() {
		dom.addVirtualCharacter(0);
		dom.addItem(_initialItemName, _initialNumber, true, Item.DEFAULT_X, Item.DEFAULT_Y);

		Sprite[] allObjects = dom.getAllDynamicObjects();
		Character character = dom.findVirtualCharacter(0);
		Item item = dom.findItem(_initialNumber);

		assertEquals("all gotten dynamic objects should be correct", 2, allObjects.length);
		assertNotNull("all gotten dynamic objects should be correct", character);
		assertEquals("all gotten dynamic objects should be correct", allObjects[0], character);
		assertNotNull("all gotten dynamic objects should be correct", item);
		assertEquals("all gotten dynamic objects should be correct", allObjects[1], item);
	}

	@Test
	public void testGetEmptyDynamicObjects() {
		assertEquals("gotten dynamic object should be empty", 0, dom.getAllDynamicObjects().length);
	}

	@Test
	public void testGetVirtualCharacterPosition() {
		dom.addVirtualCharacter(0);

		Point point = dom.getVirtualCharacterPosition(0);

		assertEquals("gotten character position should be correct", Character.DEFAULT_X, (int) point.getX());
		assertEquals("gotten character position should be correct", Character.DEFAULT_Y, (int) point.getY());
	}

	@Test(expected = AssertionError.class)
	public void testGetNonExistVirtualCharacterPosition() {
		dom.getVirtualCharacterPosition(0);
	}

	@Test(expected = AssertionError.class)
	public void testGetNonExistVirtualCharacterPosition2() {
		dom.addVirtualCharacter(_initialNumber);
		dom.getVirtualCharacterPosition(_initialNumber + 1);
	}

	@Test(expected = AssertionError.class)
	public void testGetVirtualCharacterPositionWithNegativeIndex() {
		dom.getVirtualCharacterPosition(-1);
	}

	@Test
	public void testKeyGETPressed() {
		dom.addVirtualCharacter(0);
		dom.addItem(_initialItemName, 0, true, Character.DEFAULT_X, Character.DEFAULT_Y);

		assertTrue("true should be returned after key GET pressed", dom.keyGETPressed(0));
	}

	@Test
	public void testKeyGETPressedWithEmptyItemSet() {
		dom.addVirtualCharacter(_initialNumber);

		assertFalse("No item should be gotten", dom.keyGETPressed(_initialNumber));
	}

	@Test
	public void testKeyGETPressedWithNoItemGotten() {
		dom.addVirtualCharacter(_initialNumber);
		dom.addItem(_initialItemName, 0, true, 100, 100);

		assertFalse("No item should be gotten", dom.keyGETPressed(_initialNumber));
	}

	@Test(expected = AssertionError.class)
	public void testKeyGETPressedByNonExistVirtualCharacter() {
		dom.addItem(_initialItemName, 0, true, Character.DEFAULT_X, Character.DEFAULT_Y);
		dom.keyGETPressed(0);
	}

	@Test(expected = AssertionError.class)
	public void testKeyGETPressedWithNegativeIndexPassed() {
		dom.keyGETPressed(-1);
	}
	
	@Test
	public void testUpdateItemShared() {
		testUpdateItemShared(true);
		testUpdateItemShared(false);
	}

	private void testUpdateItemShared(boolean isShared) {
		dom = new DynamicObjectModule(new TCPClientModule());
		dom.addItem(_initialItemName, _initialNumber, !isShared, Item.DEFAULT_X, Item.DEFAULT_Y);
		dom.updateItem(_initialNumber, isShared, Item.EMPTY_OWNER, Item.DEFAULT_X, Item.DEFAULT_Y);
		assertEquals("item should be updated", isShared, dom.findItem(_initialNumber).isShared());
	}

	@Test
	public void testUpdateItemOwner() {
		dom = new DynamicObjectModule(new TCPClientModule());
		dom.addItem(_initialItemName, _initialNumber, true, Item.DEFAULT_X, Item.DEFAULT_Y);
		dom.addVirtualCharacter(0);
		dom.updateItem(_initialNumber, true, 0, Item.DEFAULT_X, Item.DEFAULT_Y);
		assertEquals("item should be updated", 0, dom.findItem(_initialNumber).getOwner());
	}

	@Test
	public void testUpdateItemX() {
		dom = new DynamicObjectModule(new TCPClientModule());
		dom.addItem(_initialItemName, _initialNumber, true, Item.DEFAULT_X, Item.DEFAULT_Y);
		dom.updateItem(_initialNumber, true, -1, 100, Item.DEFAULT_Y);
		assertEquals("item should be updated", 100, dom.findItem(_initialNumber).getX());
	}

	@Test
	public void testUpdateItemY() {
		dom = new DynamicObjectModule(new TCPClientModule());
		dom.addItem(_initialItemName, _initialNumber, true, Item.DEFAULT_X, Item.DEFAULT_Y);
		dom.updateItem(_initialNumber, true, -1, Item.DEFAULT_X, 100);
		assertEquals("item should be updated", 100, dom.findItem(_initialNumber).getY());
	}

	@Test
	public void testUpdateOnlyFirstItemInMultipleItems() {
		int expectedX = 100;
		int expectedY = 200;
		boolean expectedShared = false;

		dom.addVirtualCharacter(_initialNumber);
		dom.addItem(_initialItemName, _initialNumber, true, Item.DEFAULT_X, Item.DEFAULT_Y);
		dom.addItem(_initialItemName, _initialNumber + 1, true, Item.DEFAULT_X, Item.DEFAULT_Y);
		dom.updateItem(_initialNumber, expectedShared, _initialNumber, expectedX, expectedY);

		Item firstItem = dom.findItem(_initialNumber);
		Item secondItem = dom.findItem(_initialNumber + 1);

		assertEquals("only first item should be correctly updated", _initialItemName, firstItem.getName());
		assertEquals("only first item should be correctly updated", _initialNumber, firstItem.getIndex());
		assertEquals("only first item should be correctly updated", expectedShared, firstItem.isShared());
		assertEquals("only first item should be correctly updated", _initialNumber, firstItem.getOwner());
		assertEquals("only first item should be correctly updated", expectedX, firstItem.getX());
		assertEquals("only first item should be correctly updated", expectedY, firstItem.getY());

		assertEquals("only first item should be correctly updated", _initialItemName, secondItem.getName());
		assertEquals("only first item should be correctly updated", _initialNumber + 1, secondItem.getIndex());
		assertEquals("only first item should be correctly updated", true, secondItem.isShared());
		assertEquals("only first item should be correctly updated", Item.EMPTY_OWNER, secondItem.getOwner());
		assertEquals("only first item should be correctly updated", Item.DEFAULT_X, secondItem.getX());
		assertEquals("only first item should be correctly updated", Item.DEFAULT_Y, secondItem.getY());
	}

	@Test(expected = AssertionError.class)
	public void testUpdateItemWithEmptyItemSet() {
		dom.updateItem(_initialNumber, true, _initialNumber, Item.DEFAULT_X, Item.DEFAULT_Y);
	}

	@Test(expected = AssertionError.class)
	public void testUpdateNonExistItem() {
		dom.addVirtualCharacter(_initialNumber);
		dom.addItem(_initialItemName, _initialNumber, true, Item.DEFAULT_X, Item.DEFAULT_Y);
		dom.updateItem(_initialNumber + 1, false, _initialNumber, 100, 100);
	}

	@Test(expected = AssertionError.class)
	public void testUpdateItemToNegativeOwnerNumber() {
		dom.addItem(_initialItemName, _initialNumber, true, Item.DEFAULT_X, Item.DEFAULT_Y);
		dom.updateItem(_initialNumber, true, -5, Item.DEFAULT_X, Item.DEFAULT_Y);
	}

	@Test
	public void testUpdateCharacterDirection() {
		for (DIRECTIONS direction : DIRECTIONS.values()) {
			dom = new DynamicObjectModule(new TCPClientModule());
			dom.addVirtualCharacter(0);
			dom.updateVirtualCharacter(0, direction, Character.DEFAULT_SPEED, Character.DEFAULT_X, Character.DEFAULT_Y);
			Character character = dom.findVirtualCharacter(0);
			assertNotNull(character);
			assertEquals(direction, character.getDirection());
		}
	}

	@Test
	public void testUpdateCharacterSpeed() {
		int expectedSpeed = 100;

		dom = new DynamicObjectModule(new TCPClientModule());
		dom.addVirtualCharacter(0);
		dom.updateVirtualCharacter(0, Character.DEFAULT_DIRECTION, expectedSpeed, Character.DEFAULT_X,
				Character.DEFAULT_Y);
		Character character = dom.findVirtualCharacter(0);
		assertNotNull(character);
		assertEquals(expectedSpeed, character.getSpeed());
	}

	@Test
	public void testUpdateCharacterX() {
		int expectedX = 100;

		dom = new DynamicObjectModule(new TCPClientModule());
		dom.addVirtualCharacter(0);
		dom.updateVirtualCharacter(0, Character.DEFAULT_DIRECTION, Character.DEFAULT_SPEED, expectedX,
				Character.DEFAULT_Y);
		Character character = dom.findVirtualCharacter(0);
		assertNotNull(character);
		assertEquals(expectedX, character.getX());
	}

	@Test
	public void testUpdateCharacterY() {
		int expectedY = 100;

		dom = new DynamicObjectModule(new TCPClientModule());
		dom.addVirtualCharacter(0);
		dom.updateVirtualCharacter(0, Character.DEFAULT_DIRECTION, Character.DEFAULT_SPEED, Character.DEFAULT_X,
				expectedY);
		Character character = dom.findVirtualCharacter(0);
		assertNotNull(character);
		assertEquals(expectedY, character.getY());
	}
	
	@Test
	public void testOnlyUpdateFirstCharacterInMultipleCharacters() {
		int expectedSpeed = Character.DEFAULT_SPEED + 100;
		int expectedX = Character.DEFAULT_X + 200;
		int expectedY = Character.DEFAULT_Y + 300;
		DIRECTIONS expectedDirection = DIRECTIONS.UP;
		
		dom.addVirtualCharacter(_initialNumber);
		dom.addVirtualCharacter(_initialNumber + 1);
		dom.updateVirtualCharacter(_initialNumber, expectedDirection, expectedSpeed, expectedX, expectedY);
		
		Character firstCharacter = dom.findVirtualCharacter(_initialNumber);
		Character secondCharacter = dom.findVirtualCharacter(_initialNumber + 1);
		
		assertEquals("only first character should be correctly updated", _initialNumber, firstCharacter.getId());
		assertEquals("only first character should be correctly updated", expectedSpeed, firstCharacter.getSpeed());
		assertEquals("only first character should be correctly updated", expectedDirection, firstCharacter.getDirection());
		assertEquals("only first character should be correctly updated", expectedX, firstCharacter.getX());
		assertEquals("only first character should be correctly updated", expectedY, firstCharacter.getY());
		
		assertEquals("only first character should be correctly updated", _initialNumber + 1, secondCharacter.getId());
		assertEquals("only first character should be correctly updated", Character.DEFAULT_SPEED, secondCharacter.getSpeed());
		assertEquals("only first character should be correctly updated", Character.DEFAULT_DIRECTION, secondCharacter.getDirection());
		assertEquals("only first character should be correctly updated", Character.DEFAULT_X, secondCharacter.getX());
		assertEquals("only first character should be correctly updated", Character.DEFAULT_Y, secondCharacter.getY());
	}
}
