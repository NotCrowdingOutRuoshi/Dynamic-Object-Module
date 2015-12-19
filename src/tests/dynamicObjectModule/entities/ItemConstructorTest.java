package tests.dynamicObjectModule.entities;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dynamicObjectModule.entities.Item;

public class ItemConstructorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNameInitializedSuccessfully() {
		String itemName = "New name";
		Item item = new Item(itemName, 0, true, 0, 0);
		assertEquals("Item name should be initialized", itemName, item.getName());
	}
	
	@Test(expected=AssertionError.class)
	public void testInitialNameShouldNotBeNull() {
		String itemName = "";
		Item item = new Item(itemName, 0, true, 0, 0);
		assertTrue("Item name should not be initialized to null", false);
	}

	@Test
	public void testIndexInitializedSuccessfully() throws IllegalArgumentException, IllegalAccessException {
		for (int i = 0; i < 1000; i++) {
			Item item = new Item("name", i, true, 0, 0);
			assertEquals("Item index should be initialized", i, item.getIndex());
		}
	}
	
	@Test(expected=AssertionError.class)
	public void testInitialIndexShouldNotBeNegative() {
		Item item = new Item("name", -1, true, 0, 0);
		assertTrue("Index should not be initialized with negative value", false);
	}
	
	@Test
	public void testSharedInitializedSuccessfully() {
		Item item = new Item("name", 0, true, 0, 0);
		assertTrue("Item shared should be initialized to true", item.isShared());
		
		item = new Item("name", 0, false, 0, 0);
		assertFalse("Item shared should be initialized false", item.isShared());
	}
	
	@Test
	public void testLocationInitializedSuccessfully() {
		for (int i = 0; i < 1000; i++) {
			Item item = new Item("name", 0, true, i, i);
			assertEquals("Item x-axis value should be initialized to" + i, i, item.getX());
			assertEquals("Item y-axis value should be initialized to" + i, i, item.getY());
		}
	}
	
	@Test(expected=AssertionError.class)
	public void testInitialLocationShouldNotBeNegative() {
		for (int i = -1; i > -1000; i--) {
			Item item = new Item("name", 0, true, i, i);
		}
		
		assertTrue("Item location should not be initialized with negative value", false);
	}
}
