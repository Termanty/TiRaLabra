
package fifteenpuzzle.datastructure;

import fifteenpuzzle.datastructure.Node;
import java.util.Comparator;

/**
 * NodeComparator class.
 * This class is for test purposes. So that Java's PriorityQueue class works
 * as minimun heap. In test document is done comparison of efficiences of 
 * Java's PriorityQueue class and MyMinHeap class. More you can read from
 * test document.
 *
 * @author termanty
 */
public class NodeComparator implements Comparator<Node> {
   
    /**
     * Description of compare.
     * This method compares two nodes or actualy their costs by subtracting
     * second nodes cost value from first nodes cost value.
     *
     * @param o1    node 
     * @param o2    node
     * @return      0 if nodes have equal manhattan distance, positive if
     *               node o1 is bigger and negative if it's smaller. 
     */
    @Override
    public int compare(Node o1, Node o2) {
        return o1.getCost() - o2.getCost();
    } 
}
