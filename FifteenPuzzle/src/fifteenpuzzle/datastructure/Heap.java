
package fifteenpuzzle.datastructure;

/**
 * Heap Class.
 * Implementation of minimum heap for nodes which contains information of 
 * certain permutation of puzzle and cost evaluation to solution.
 * Cost evaluation is used to make comparison. 
 *
 * @author Tero Mäntylä
 */
public class Heap {
    private Node[] heap;
    private int MAX;
    private int size;
  
    
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
     * Description of Left.
     *
     * @param index     position of node in the heap
     * @return          position of left child in the heap
     */    
    private int left(int index) {
        return 2 * index;
    }
    

    /**
     * Description of Right.
     *
     * @param index     position of node in the heap
     * @return          position of right child in the heap
     */  
    private int right(int index) {
        return (2 * index) + 1;
    }
    

    /**
     * Description of Parent.
     *
     * @param index     position of node in the heap
     * @return          parents position in the heap
     */   
    private int parent(int index) {
        return index / 2;
    }


    /**
     * Description of swap.
     * Method changes places of two nodes in the heap.
     *
     * @param index1    position of node in the heap
     * @param index2    position of node in the heap
     */   
    private void swap(int index1, int index2) {
        Node tmp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = tmp;
    }

    
    /**
     * Description of insert
     *
     * @param node      node for added to the heap
     */  
    public void insert(Node node) {
        size++;
        int index = size;
        while (index > 1 && heap[parent(index)].getCost() > node.getCost()) {
            heap[index] = heap[parent(index)];
            index = parent(index);
        }
        heap[index] = node;
    }
    

    /**
     * Description of removeMin
     *
     * @return         node with smallest cost.
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
     * @param index     node which position is checked in the heap
     */   
    private void heapify(int index) {
        int left = left(index);
        int right = right(index);
        int smallest;
        
        if (right <= size) {
            if (heap[left].getCost() < heap[right].getCost()) {
                smallest = left;
            } else {
                smallest = right;
            }
            if (heap[index].getCost() > heap[smallest].getCost()) {
                swap(index, smallest);
                heapify(smallest);
            }
        } else if (left == size && heap[index].getCost() > heap[left].getCost()) {
            swap(index, left);
        }
    }
  
}
