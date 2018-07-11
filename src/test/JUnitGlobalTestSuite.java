package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.dictionary.JUnitDictionary;
import test.dictionary.JUnitTreeGlobalTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        JUnitDictionary.class,
        JUnitTreeGlobalTestSuite.class,
})
public class JUnitGlobalTestSuite {
}