package test.dictionary;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.dictionary.tree.JUnitAVLTree;
import test.dictionary.tree.JUnitBinarySearchTree;
import test.dictionary.tree.JUnitRBTree;
import test.dictionary.tree.JUnitSplayTree;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        JUnitBinarySearchTree.class,
        JUnitAVLTree.class,
        JUnitSplayTree.class,
        JUnitRBTree.class
})
public class JUnitTreeGlobalTestSuite {
}
