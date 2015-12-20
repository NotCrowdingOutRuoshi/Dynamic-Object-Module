package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tests.dynamicObjectModule.entities.CharacterGetterSetterTest;
import tests.dynamicObjectModule.entities.ItemConstructorTest;
import tests.dynamicObjectModule.entities.ItemGetterSetterTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ CharacterGetterSetterTest.class, ItemConstructorTest.class, ItemGetterSetterTest.class })
public class EntityTestSuite {

}
