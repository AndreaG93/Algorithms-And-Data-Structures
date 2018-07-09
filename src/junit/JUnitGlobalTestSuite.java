package junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import junit.test.JUnitDictionary;
import junit.test.tree.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        JUnitDictionary.class,
        JUnitTreeTestSuite.class,
})
public class JUnitGlobalTestSuite {
}