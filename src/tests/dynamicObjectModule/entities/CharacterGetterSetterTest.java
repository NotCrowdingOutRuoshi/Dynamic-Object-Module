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
		assertEquals("gotten x-axis value should be " + initialX, initialX, character.getX());
	}

	@Test
	public void testGetY() {
		assertEquals("gotten y-axis value should be " + initialY, initialY, character.getY());
	}

	@Test
	public void testSetX() {
		int expectedX = 100;

		character.setX(expectedX);

		assertEquals("x-axis value of character should be set to " + expectedX, expectedX, character.getX());
	}

	@Test(expected = AssertionError.class)
	public void testSetXToNegative() {
		int unexpectedX = -1;

		character.setX(unexpectedX);

		fail("x-axis value of character should not be set to a negative integer");
	}

	@Test
	public void testSetY() {
		int expectedY = 100;

		character.setY(expectedY);

		assertEquals("y-axis value of character should be set to " + expectedY, expectedY, character.getY());
	}

	@Test(expected = AssertionError.class)
	public void testSetYToNegative() {
		int unexpectedY = -1;

		character.setY(unexpectedY);

		fail("y-axis value of character should not be set to a negative integer");
	}

	@Test
	public void testGetDirection() {
		assertEquals("direction of character should be" + initialDirection, initialDirection, character.getDirection());
	}

	@Test
	public void testSetDirection() {
		for (DIRECTIONS direction : DIRECTIONS.values()) {
			character = new Character(initialId, initialX, initialY, initialDirection, initialSpeed);
			testSetDirection(direction);
		}
	}

	@Test
	public void testContinuouselySetDirection() {
		for (DIRECTIONS direction : DIRECTIONS.values()) {
			testSetDirection(direction);
		}
	}

	@Test
	private void testSetDirection(DIRECTIONS direction) {
		character.setDirection(direction);
		assertEquals("direction of character should be set to " + direction, direction, character.getDirection());
	}
	
	@Test(expected=AssertionError.class)
	public void testSetDirectionToNull() {
		character.setDirection(null);
		fail("direction of character should not be set to null");
	}

	@Test
	public void testGetId() {
		assertEquals("gotten ID number of character should be " + initialId, initialId, character.getId());
	}

	@Test
	public void testSetId() {
		int expectedId = 100;

		character.setId(expectedId);

		assertEquals("ID number fo character should be set to " + expectedId, expectedId, character.getId());
	}
	
	@Test
	public void testSetIdToNegative() {
		int unexpectedId = -1;
		
		character.setId(unexpectedId);
		
		fail("ID number of character should not be set to a negative value");
	}

	@Test
	public void testGetSpeed() {
		assertEquals("gotten speed of character should be " + initialSpeed, initialSpeed, character.getSpeed());
	}

	@Test
	public void testSetSpeed() {
		int expectedSpeed = 100;

		character.setSpeed(expectedSpeed);

		assertEquals("speed of character should be set to " + expectedSpeed, expectedSpeed, character.getSpeed());
	}

	@Test(expected=AssertionError.class)
	public void testSetSpeedToNegative() {
		int unexpedtedSpeed = -100;
		
		character.setSpeed(unexpedtedSpeed);
		
		fail("speed of character should not be set to a negative value");
	}
}
