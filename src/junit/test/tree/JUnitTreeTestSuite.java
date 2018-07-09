package junit.test.tree;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        JUnitBinarySearchTree.class,
        JUnitBinarySearchTreeAVL.class,
        JUnitBinarySearchTreeSplay.class,
        JUnitBinarySearchTreeRB.class
})
public class JUnitTreeTestSuite {
}
