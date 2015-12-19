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
	public void testDynamicObjectModuleWithNullInjection()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		dom = new DynamicObjectModule(null);

		Field tcpField = DynamicObjectModule.class.getDeclaredField("_tcpClientModule");
		tcpField.setAccessible(true);

		fail("null injection to constructor should not be allowed");

		tcpField.setAccessible(false);
	}

	@Test
	public void testAddItem() {
		dom.addItem(_initialItemName, _initialNumber, true, Item.DEFAULT_X, Item.DEFAULT_Y);
		Item item = dom.findItem(0);
		assertNotNull("item should be added correctly", item);
		assertEquals("item should be added correctly", _initialNumber, item.getIndex());
		assertTrue("item should be added correctly", item.isShared());
		assertEquals("item should be added correctly", _initialItemName, item.getName());
		assertEquals("item should be added correctly", Item.DEFAULT_X, item.getX());
		assertEquals("item should be added correctly", Item.DEFAULT_Y, item.getY());
	}

	@Test
	public void testAddVirtualCharacter() {
		dom.addVirtualCharacter(0);
		assertNotNull("character should be added correctly", dom.findVirtualCharacter(0));
		assertEquals(0, dom.findVirtualCharacter(0).getId());
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
	public void testGetAllDynamicObjects() {
		dom.addVirtualCharacter(0);
		dom.addItem(_initialItemName, _initialNumber, true, Item.DEFAULT_X, Item.DEFAULT_Y);

		Sprite[] allObjects = dom.getAllDynamicObjects();
		Character character = dom.findVirtualCharacter(0);
		Item item = dom.findItem(_initialNumber);

		assertEquals("all gotten Sprite objects should be correct", 2, allObjects.length);
		assertNotNull("all gotten Sprite objects should be correct", character);
		assertEquals("all gotten Sprite objects should be correct", allObjects[0], character);
		assertNotNull("all gotten Sprite objects should be correct", item);
		assertEquals("all gotten Sprite objects should be correct", allObjects[1], item);
	}

	@Test
	public void testGetVirtualCharacterPosition() {
		dom.addVirtualCharacter(0);

		Point point = dom.getVirtualCharacterPosition(0);

		assertEquals("gotten character position should be correct", Character.DEFAULT_X, (int) point.getX());
		assertEquals("gotten character position should be correct", Character.DEFAULT_Y, (int) point.getY());
	}

	@Test
	public void testKeyGETPressed() {
		dom.addVirtualCharacter(0);
		dom.addItem(_initialItemName, 0, true, Character.DEFAULT_X, Character.DEFAULT_Y);

		assertTrue(dom.keyGETPressed(0));
	}

	@Test
	public void testUpdateItem() {
		testUpdateItemShared(true);
		testUpdateItemShared(false);
		testUpdateItemOwner();
		testUpdateItemX();
		testUpdateItemY();
	}

	private void testUpdateItemShared(boolean isShared) {
		dom = new DynamicObjectModule(new TCPClientModule());
		dom.addItem(_initialItemName, _initialNumber, !isShared, Item.DEFAULT_X, Item.DEFAULT_Y);
		dom.updateItem(_initialNumber, isShared, -1, Item.DEFAULT_X, Item.DEFAULT_Y);
		assertEquals("item should be updated", isShared, dom.findItem(_initialNumber).isShared());
	}

	private void testUpdateItemOwner() {
		dom = new DynamicObjectModule(new TCPClientModule());
		dom.addItem(_initialItemName, _initialNumber, true, Item.DEFAULT_X, Item.DEFAULT_Y);
		dom.addVirtualCharacter(0);
		dom.updateItem(_initialNumber, true, 0, Item.DEFAULT_X, Item.DEFAULT_Y);
		assertEquals("item should be updated", 0, dom.findItem(_initialNumber).getOwner());
	}

	private void testUpdateItemX() {
		dom = new DynamicObjectModule(new TCPClientModule());
		dom.addItem(_initialItemName, _initialNumber, true, Item.DEFAULT_X, Item.DEFAULT_Y);
		dom.updateItem(_initialNumber, true, -1, 100, Item.DEFAULT_Y);
		assertEquals("item should be updated", 100, dom.findItem(_initialNumber).getX());
	}

	private void testUpdateItemY() {
		dom = new DynamicObjectModule(new TCPClientModule());
		dom.addItem(_initialItemName, _initialNumber, true, Item.DEFAULT_X, Item.DEFAULT_Y);
		dom.updateItem(_initialNumber, true, -1, Item.DEFAULT_X, 100);
		assertEquals("item should be updated", 100, dom.findItem(_initialNumber).getY());
	}

	@Test
	public void testUpdateVirtualCharacter() {
		testUpdateCharacterDirection();
		testUpdateCharacterSpeed();
		testUpdateCharacterX();
		testUpdateCharacterY();
	}

	private void testUpdateCharacterDirection() {
		for (DIRECTIONS direction : DIRECTIONS.values()) {
			dom = new DynamicObjectModule(new TCPClientModule());
			dom.addVirtualCharacter(0);
			dom.updateVirtualCharacter(0, direction, Character.DEFAULT_SPEED, Character.DEFAULT_X, Character.DEFAULT_Y);
			Character character = dom.findVirtualCharacter(0);
			assertNotNull(character);
			assertEquals(direction, character.getDirection());
		}
	}

	private void testUpdateCharacterSpeed() {
		int expectedSpeed = 100;

		dom = new DynamicObjectModule(new TCPClientModule());
		dom.addVirtualCharacter(0);
		dom.updateVirtualCharacter(0, Character.DEFAULT_DIRECTION, expectedSpeed, Character.DEFAULT_X,
				Character.DEFAULT_Y);
		Character character = dom.findVirtualCharacter(0);
		assertNotNull(character);
		assertEquals(expectedSpeed, character.getSpeed());
	}

	private void testUpdateCharacterX() {
		int expectedX = 100;

		dom = new DynamicObjectModule(new TCPClientModule());
		dom.addVirtualCharacter(0);
		dom.updateVirtualCharacter(0, Character.DEFAULT_DIRECTION, Character.DEFAULT_SPEED, expectedX,
				Character.DEFAULT_Y);
		Character character = dom.findVirtualCharacter(0);
		assertNotNull(character);
		assertEquals(expectedX, character.getX());
	}

	private void testUpdateCharacterY() {
		int expectedY = 100;

		dom = new DynamicObjectModule(new TCPClientModule());
		dom.addVirtualCharacter(0);
		dom.updateVirtualCharacter(0, Character.DEFAULT_DIRECTION, Character.DEFAULT_SPEED, Character.DEFAULT_X,
				expectedY);
		Character character = dom.findVirtualCharacter(0);
		assertNotNull(character);
		assertEquals(expectedY, character.getY());
	}
}
