package tests.dynamicObjectModule.entities;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dynamicObjectModule.entities.Item;

public class ItemGetterSetterTest {
	private Item item;
	private String initialItemName = "name";
	private int initialItemIndex = 0;
	private int initialItemX = 0;
	private int initialItemY = 0;
	private boolean initialItemShared = true;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		item = new Item(initialItemName, initialItemIndex, initialItemShared, initialItemX, initialItemY);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetIndex() {
		assertEquals("gotten index should be " + initialItemIndex, initialItemIndex, item.getIndex());
	}

	@Test
	public void testSetIndex() {
		int expectedIndex = 1;
		item.setIndex(expectedIndex);
		assertEquals("index should be set to " + expectedIndex, expectedIndex, item.getIndex());
	}
	
	@Test(expected=AssertionError.class)
	public void testSetIndexWithNegativeValue() {
		int unexpectedIndex = -1;
		item.setIndex(unexpectedIndex);
		fail("index should not be set to negative value");
	}

	@Test
	public void testGetOwner() {
		int expectedOwner = 1;
		item.setOwner(expectedOwner);
		assertEquals("gotten owner should be " + expectedOwner, expectedOwner, item.getOwner());
	}
	
	@Test
	public void testGetInitialOwner() {
		 assertEquals(Item.EMPTY_OWNER, item.getOwner());
	}

	@Test
	public void testPaintComponentGraphics() {
		// Do not test.
	}

	@Test
	public void testSetOwner() {
		int expectedOwner = 1;
		item.setOwner(expectedOwner);
		assertEquals("owner should be set to " + expectedOwner, expectedOwner, item.getOwner());
	}
	
	@Test(expected=AssertionError.class)
	public void testSetOwnerWithNegativeValue() {
		int unexpectedOwner = -1;
		item.setOwner(unexpectedOwner);
		fail("owner should not be set to negative value");
	}

	@Test
	public void testGetName() {
		assertEquals("gotten item name should be " + initialItemName, initialItemName, item.getName());
	}

	@Test
	public void testSetNameString() {
		String expectedName = "My name is Mr.expected!";
		item.setName(expectedName);
		assertEquals("item name should be set to " + expectedName, expectedName, item.getName());
	}
	
	@Test(expected=AssertionError.class)
	public void testSetNameWithEmptyString() {
		String unexpectedName = "";
		item.setName(unexpectedName);
		fail("item name should not be set to empty string");
	}
	
	@Test(expected=AssertionError.class)
	public void testSetNameWithNullString() {
		item.setName(null);
		fail("item name should not be set to null");
	}

	@Test
	public void testIsOwned() {
		assertFalse("initial item should not be owned", item.isOwned());
		item.setOwner(1);
		assertTrue("item should be set to owned", item.isOwned());
	}

	@Test
	public void testIsShared() {
		assertEquals("shared of item should initially be " + initialItemShared, initialItemShared, item.isShared());
	}

	@Test
	public void testSetShared() {
		item.setShared(true);
		assertTrue(item.isShared());
		item.setShared(false);
		assertFalse(item.isShared());
	}
	
	@Test
	public void testGetX() {
		assertEquals("gotten x should be " + initialItemX, initialItemX, item.getX());
	}
	
	@Test
	public void testSetX() {
		int expectedX = 100;
		item.setX(expectedX);
		assertEquals("x-axis value of item should be set to " + expectedX, expectedX, item.getX());
	}
	
	@Test(expected=AssertionError.class)
	public void testSetXWithNegativeValue() {
		int unexpectedY = -1;
		item.setX(unexpectedY);
		fail("y-axis value of item should not be set to negative value");
	}
	
	@Test
	public void testGetY() {
		assertEquals("gotten y should be " + initialItemY, initialItemY, item.getY());
	}
	
	@Test
	public void testSetY() {
		int expectedY = 100;
		item.setY(expectedY);
		assertEquals("y-axis value of item should be set to " + expectedY, expectedY, item.getY());
	}
	
	@Test(expected=AssertionError.class)
	public void testSetYWithNegativeValue() {
		int unexpectedY = -1;
		item.setY(unexpectedY);
		fail("x-axis value of item should not be set to negative value");
	}

}