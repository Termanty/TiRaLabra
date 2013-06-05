/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fifteenpuzzle.datastructure;

import java.util.Arrays;

/**
 *
 * @author termanty
 */
public class MyHashSet {
    private final int SIZE = 50021;
    private MyLinkedList[] setTable = new MyLinkedList[SIZE];
    
    
    public void insert(Node node) {
        int key = Math.abs(Arrays.deepHashCode(node.getPuzzle().getPuzzle()) % SIZE);
        if (setTable[key] == null) {
            setTable[key] = new MyLinkedList(node, null);
        } else {
            MyLinkedList tmp = setTable[key];
            setTable[key] = new MyLinkedList(node, tmp);
        }
    }
    
    public boolean contains(Node node) {
        int key = Math.abs(Arrays.deepHashCode(node.getPuzzle().getPuzzle()) % SIZE);
        if (setTable[key] == null) {
            return false;
        }
        MyLinkedList a = setTable[key];
        while (a != null) {
            if (node.equals(a.getNode())) {
                return true;
            }
        }
        return false;
    }
    
   
}
