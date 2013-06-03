
package fifteenpuzzle;

/**
 * Heap Class
 *
 * @author Tero Mäntylä
 */

public class Heap {
    private Node[] heap;
    private int MAX;
    private int size;
    private int index;

    
    /**
     * Constructor
     *
     * @param maxSize  the maximum size of the heap.
     */
    
    public Heap(int maxSize) {
        MAX = maxSize;
        heap = new Node[MAX];
        size = 0;
    }
    

    /**
     * Description of Left
     *
     * @param index 
     * @return 
     */
    
    private int left(int index) {
        return 2 * index;
    }
    

    /**
     * Description of Right
     *
     * @param index 
     * @return 
     */
    
    private int right(int index) {
        return (2 * index) + 1;
    }
    

    /**
     * Description of Parent
     *
     * @param index 
     * @return
     */
    
    private int parent(int index) {
        return index / 2;
    }


    /**
     * Description of swap
     *
     * @param index1 
     * @param index2 
     */
    
    private void swap(int index1, int index2) {
        Node tmp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = tmp;
    }

    
    /**
     * Description of insert
     *
     * @param node
     */
    
    public void insert(Node node) {
        size++;
        int index = size;
        while (index > 1 && heap[parent(index)].getManDist() > node.getManDist()) {
            index = parent(index);
        }
        heap[index] = node;
    }
    

    /**
     * Description of removeMin
     *
     * @return
     */
    
    public Node removeMin() {
        Node min = heap[1];     
        heap[1] = heap[size];
        size--;
        if (size != 0) {
            heapify(1);
        }
        return min;
    }
    

    /**
     * Description of heapify
     *
     * @param index
     */
    
    private void heapify(int index) {
        int left = left(index);
        int right = right(index);
        int largest;
        
        if (right <= size) {
            if (heap[left].getManDist() > heap[right].getManDist()) {
                largest = left;
            } else {
                largest = right;
            }
            if (heap[left].getManDist() < heap[largest].getManDist()) {
                swap(index, largest);
                heapify(largest);
            }
        } else if (left == size && heap[index].getManDist() < heap[left].getManDist()) {
            swap(index, left);
        }
    }
  
}
