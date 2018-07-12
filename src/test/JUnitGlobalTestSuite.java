package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.datastructures.JUnitDictionary;
import test.datastructures.JUnitTreeGlobalTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        JUnitDictionary.class,
        JUnitTreeGlobalTestSuite.class,
})
public class JUnitGlobalTestSuite {
}