package dictionary.tree.binarytree.node;

import java.util.concurrent.Callable;

public interface NodeVisitFunction<T> extends Callable<T> {

    public void setNodeToVisit();

}
