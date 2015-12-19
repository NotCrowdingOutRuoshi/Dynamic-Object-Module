package tests.dynamicObjectModule.entities;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dynamicObjectModule.entities.Character;
import dynamicObjectModule.entities.Character.DIRECTIONS;

public class CharacterGetterSetterTest {
	private Character character;
	private final int initialId = 0;
	private final int initialX = 0;
	private final int initialY = 0;
	private final DIRECTIONS initialDirection = DIRECTIONS.RIGHT;
	private final int initialSpeed = 0;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		character = new Character(initialId, initialX, initialY, initialDirection, initialSpeed);
	}

	@After
	public void tearDown() throws Exception {
		character = null;
	}

	@Test
	public void testGetX() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetY() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetX() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetY() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetDirection() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetDirection() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetId() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetId() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetSpeed() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetSpeed() {
		fail("Not yet implemented"); // TODO
	}

}
