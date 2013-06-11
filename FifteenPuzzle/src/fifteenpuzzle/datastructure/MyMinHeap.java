
package fifteenpuzzle.datastructure;

/**
 * MyMinHeap Class.
 * Implementation of minimum heap for nodes which contains information of 
 * certain permutation of puzzle and cost evaluation to solution.
 * Cost evaluation is used to make comparison.
 * This class is used in A*-algorith to select least cost node to continue search.
 * Heap size has to be big enough to get correct answer. 1.000.000 works fine.
 *
 * @author Tero Mäntylä
 */
public class MyMinHeap {
    
    private Node[] heap;
    private int MAX;
    private int size;
  
    
    /**
     * Description of Constructor
     * This constructor sets the maximum size for the heap and creates
     * Node[]-array where to put the inserted values.
     *
     * @param maxSize  the maximum size of the heap.
     */   
    public MyMinHeap(int maxSize) {
        MAX = maxSize;
        heap = new Node[MAX];
        size = 0;
    }
   
    
    /**
     * Description of left(int index).
     * This method return left child of the given node (index).
     *
     * @param index     position of node in the heap
     * @return          position of left child in the heap
     */    
    private int left(int index) {
        return 2 * index;
    }
    

    /**
     * Description of right(int index).
     * This method return left child of the given node (index).
     * 
     * @param index     position of node in the heap
     * @return          position of right child in the heap
     */  
    private int right(int index) {
        return (2 * index) + 1;
    }
    

    /**
     * Description of parent(int index).
     * This method return parent of the given node (index).
     *
     * @param index     position of node in the heap
     * @return          parents position in the heap
     */   
    private int parent(int index) {
        return index / 2;
    }


    /**
     * Description of swap(int index1, int index2).
     * This method changes places of two nodes in the heap.
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
     * Description of insert(Node node).
     * This method adds given node to heap. If heap is full, one hundred parth of
     * the end of the heap is trashed by moving size-variable. This is little bit
     * risky bisness but with big enough heap it works.
     *
     * @param node      node for added to the heap
     */  
    public void insert(Node node) {
        size++;
        if (size == MAX) {
            size -= MAX/100;
        }
        int index = size;
        while (index > 1 && heap[parent(index)].getCost() > node.getCost()) {
            heap[index] = heap[parent(index)];
            index = parent(index);
        }
        heap[index] = node;
    }
    

    /**
     * Description of removeMin().
     * This method return head of the heap. That is node which has lowest cost value.
     *
     * @return         node with smallest cost.
     */
    public Node removeMin() {
        if (size == 0) {
            return null;
        }
        Node min = heap[1];     
        heap[1] = heap[size];
        size--;
            heapify(1);
        return min;
    }
    

    /**
     * Description of heapify(int index).
     * This method makes sure that heap stays as a heap after 
     * removal the head of the heap and moving last to the first.
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
