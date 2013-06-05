
package fifteenpuzzle.datastructure;

/**
 * MyLinkedList Class
 * 
 * @author termanty
 */
public class MyLinkedList {
    private Node node;
    private MyLinkedList next;

    public MyLinkedList(Node node, MyLinkedList next) {
        this.node = node;
        this.next = next;
    }

    public Node getNode() {
        return node;
    }

    public MyLinkedList getNext() {
        return next;
    }


}
