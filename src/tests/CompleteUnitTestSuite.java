package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tests.dynamicObjectModule.DynamicObjectModuleTest;
import tests.dynamicObjectModule.entities.CharacterGetterSetterTest;
import tests.dynamicObjectModule.entities.ItemConstructorTest;
import tests.dynamicObjectModule.entities.ItemGetterSetterTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ DynamicObjectModuleTest.class, CharacterGetterSetterTest.class, ItemConstructorTest.class,
		ItemGetterSetterTest.class })
public class CompleteUnitTestSuite {
	//
}
