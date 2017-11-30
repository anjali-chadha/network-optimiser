package com.invictus.networkrouter.algorithms;

/**
 * Max Heap that Dijkstra's algorithm wants.
 */
public class HeapDijkstra {

    //H[i] gives the name of the vertex of the graph.
    private int[] H;
    private int[] reverseH; //stores the heap position of every element in the heap.

    //D stores the vertex values in the heap.
    private int[] D;
    private int size;
    private int n; //Represents the capacity of the heap

    public HeapDijkstra(int n) {
        H = new int[n];
        D = new int[n];
        reverseH = new int[n];
        this.n = n;
    }

    /**
     * Returns the vertex with the maximum edge weight.
     * @return
     */
    public int maximum() {
        if(isEmpty()) {
            return -1;
        } else {
            return H[0];
        }
    }

    public void insert(int vertex, int weight) {
        if(size == n - 1) return;

        size++;
        H[size - 1] = vertex;
        reverseH[vertex] = size - 1;
        D[vertex] = weight;
        swim(size - 1, weight);
    }

    public void deleteMaximum() {
        if(isEmpty()) return;
        H[0] = H[size - 1];
        reverseH[H[0]] = 0;
        size--;
        sink(0);
    }

    public void delete(int vertex) {
        int index = reverseH[vertex];
        if(index == -1) return;
        H[index] = H[size - 1];
        reverseH[H[index]] = index;
        size --;
        sink(index);
        swim(index, D[H[index]]);
    }

    private void sink(int index) {
        while(index < size) {
            int biggerChild = greaterValueVertex(leftChild(index),
                    rightChild(index));

            if(biggerChild == -1 || D[H[index]] >= D[H[biggerChild]]) {
                break;
            }
            swap(index, biggerChild, H);
            reverseH[H[index]] = index;
            reverseH[H[biggerChild]] = biggerChild;
            index = biggerChild;
        }
    }

    private int greaterValueVertex(int i, int j) {
        if(j == -1 && i == -1) {
            return -1;
        } else if(j == -1) {
            return i;
        }
        return D[H[i]] >= D[H[j]]? i : j;
    }

    private void swim(int index, int weight) {
        while (index > 0) {
            int parentVertex = H[parent(index)];
            int parentValue = D[parentVertex];
            if(parentValue >= weight) {
                break;
            } else {
                swap(index, parent(index), H);
                reverseH[H[index]] = index;
                reverseH[H[parent(index)]] = parent(index);
            }
            index = parent(index);
        }
    }

    private void swap(int a, int b, int[] arr) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private int parent(int i) {
        if(i == 0) return -1;
        if((i - 1)/2 >= 0) {
            return (i - 1)/2;
        }
        return -1;
    }

    private int leftChild(int i) {
       int j = 2 * i + 1;
       return (j < size) ? j: -1;
    }

    private int rightChild(int i) {
        int j = 2 * i + 2;
        return (j < size) ? j : -1;
    }

    private boolean isEmpty() {
        return size == 0;
    }
}

