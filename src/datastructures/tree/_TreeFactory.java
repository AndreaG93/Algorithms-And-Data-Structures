package datastructures.tree;

import datastructures.Tree;

/**
 * This class is used to retrieve {@code Tree} objects.
 *
 * @author Andrea Graziani
 * @version 1.0
 */
public abstract class _TreeFactory {

    /**
     * This method is used to retrieve wanted {@code Tree} object.
     *
     * If specified name matches an existing and implemented tree then a {@code Tree}
     * object is returned; otherwise {@code null} is returned.
     *
     * @param pTreeName - It represents a {@code String} object.
     * @return A {@code Tree} object or {@code null}.
     */
    public static Tree getTree(String pTreeName){

        // Name processing...
        // =================================================================== //
        String className = String.format("%s.%s", _TreeFactory.class.getPackage().getName(), pTreeName);

        // Allocation concrete requested object...
        // =================================================================== //
        try {
            return (Tree) Class.forName(className).getConstructor().newInstance();
        } catch (Exception e) {

            // Print data about error and exit...
            e.printStackTrace();
            return null;
        }
    }
}
