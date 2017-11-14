public class MaxHeap2 {

    private Edge[] heap;
    private int n;
    private int size;

    MaxHeap2(int n) {
        heap = (Edge[]) new Object[n + 1];
        this.n = n;
        size = 0;
    }

    private int getParentIndex(int i) {
        int index = i/2;
        return (index != 0)? index: -1;
    }

    private int getLeftChidIndex(int i) {
        int index = 2 * i;
        return (index <= size) ? index: -1;
    }

    public Edge maximum() {
        return heap[1];
    }

    /**
     * This function will return true if the insert operation is successful
     * otherwise returns false
     */
    public boolean insert(Edge edge) {
        size++;
        if(!(size <= n)) return false;

        heap[size] = edge;
        return true;
    }

    public boolean delete(Edge edge) {
        return false;
    }

    private void sink() {

    }

    private void swim() {

    }

    void heapSort() {

    }

    public int size() {
        return size;
    }
}


