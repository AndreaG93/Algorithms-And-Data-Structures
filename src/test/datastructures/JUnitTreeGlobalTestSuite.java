package test.datastructures;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.datastructures.tree.JUnitAVLTree;
import test.datastructures.tree.JUnitBinarySearchTree;
import test.datastructures.tree.JUnitRBTree;
import test.datastructures.tree.JUnitSplayTree;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        JUnitBinarySearchTree.class,
        JUnitAVLTree.class,
        JUnitSplayTree.class,
        JUnitRBTree.class
})
public class JUnitTreeGlobalTestSuite {
}
