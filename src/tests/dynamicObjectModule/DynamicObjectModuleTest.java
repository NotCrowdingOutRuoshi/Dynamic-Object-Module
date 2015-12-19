package tests.dynamicObjectModule;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dynamicObjectModule.DynamicObjectModule;
import dynamicObjectModule.entities.Item;
import fake.TCPClientModule;

public class DynamicObjectModuleTest {
	private DynamicObjectModule dom;
	
	private final String validItemName = "name";
	private final int validItemIndex = 0;
	private final int validItemX = 0;
	private final int validItemY = 0;

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
	public void testDynamicObjectModule() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		dom = new DynamicObjectModule(new TCPClientModule());
		
		Field tcpField = DynamicObjectModule.class.getDeclaredField("_tcpClientModule");
		tcpField.setAccessible(true);
		
		assertNotNull("injected TCPClientModule should be set", tcpField.get(dom));
		
		tcpField.setAccessible(false);
	}
	
	@Test(expected=AssertionError.class)
	public void testDynamicObjectModuleWithNullInjection() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		dom = new DynamicObjectModule(null);
		
		Field tcpField = DynamicObjectModule.class.getDeclaredField("_tcpClientModule");
		tcpField.setAccessible(true);
		
		fail("null injection to constructor should not be allowed");
		
		tcpField.setAccessible(false);
	}

	@Test
	public void testAddItem() {
		dom.addItem(validItemName, validItemIndex, true, validItemX, validItemY);
		Item item = dom.findItem(0);
		assertNotNull("item should be added correctly", item);
		assertEquals("item should be added correctly", validItemIndex, item.getIndex());
		assertTrue("item should be added correctly", item.isShared());
		assertEquals("item should be added correctly", validItemName, item.getName());
		assertEquals("item should be added correctly", validItemX, item.getX());
		assertEquals("item should be added correctly", validItemY, item.getY());
	}

	@Test
	public void testAddVirtualCharacter() {
		dom.addVirtualCharacter(0);
		assertNotNull("character should be added correctly", dom.findVirtualCharacter(0));
		assertEquals(0, dom.findVirtualCharacter(0).getId());
	}

	@Test
	public void testFindItem() {
		int lastItemIndex = validItemIndex + 5;
		for (int i = validItemIndex; i <= lastItemIndex; i++) {
			dom.addItem(validItemName, i, true, validItemX, validItemY);
		}
		
		Item item = dom.findItem(lastItemIndex);
		assertNotNull("item should be found correctly", item);
		assertEquals("item should be found correctly", lastItemIndex, item.getIndex());
		assertTrue("item should be found correctly", item.isShared());
		assertEquals("item should be found correctly", validItemName, item.getName());
		assertEquals("item should be found correctly", validItemX, item.getX());
		assertEquals("item should be found correctly", validItemY, item.getY());
	}

	@Test
	public void testFindVirtualCharacter() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetAllDynamicObjects() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetVirtualCharacterPosition() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testKeyGETPressed() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testUpdateItem() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testUpdateVirtualCharacter() {
		fail("Not yet implemented"); // TODO
	}

}
