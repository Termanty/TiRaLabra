/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fifteenpuzzle.datastructure;

/**
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
    
    

}
