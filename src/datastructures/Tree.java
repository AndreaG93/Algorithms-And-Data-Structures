package datastructures;

import datastructures.tree.utility.BinaryTreeVisitJob;

/**
 * This class represents a tree data structure.
 *
 * @param <Key>   - It represents an object its class extends {@code Comparable} class.
 * @param <Value> - It represents a generic object.
 * @author Andrea Graziani
 * @version 1.3
 */
public interface Tree<Key extends Comparable<Key>, Value> extends Dictionary<Key, Value> {

    /**
     * Perform a "breadth-first-search" on current tree.
     *
     * @param pVisitJob - Represents a {@code BinaryTreeVisitJob} object.
     */
    void BFSVisit(BinaryTreeVisitJob<Key, Value> pVisitJob);
}