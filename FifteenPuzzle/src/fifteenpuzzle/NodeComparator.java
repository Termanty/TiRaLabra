
package fifteenpuzzle;

import java.util.Comparator;

/**
 * NodeComparator class
 *
 * @author termanty
 */

public class NodeComparator implements Comparator<Node> {
   
    /**
     * Description of compare
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
