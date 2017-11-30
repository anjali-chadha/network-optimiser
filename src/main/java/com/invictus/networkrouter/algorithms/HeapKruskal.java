package com.invictus.networkrouter.algorithms;

import com.invictus.networkrouter.graph.Edge;

import java.util.List;

/**
 * Heap for Kruksal Algorithm
 */
//TODO: See how we can merge the two heaps in a single class.
public class HeapKruskal {

    private int size;
    private int m;
    private Edge[] H;

    public void heapSort(List<Edge> edgeList) {
        m = edgeList.size();
        size = m;
        H = edgeList.toArray(new Edge[m]);

        for (int k = m/2; k >= 0; k--)
            sink(k);
    }

    public void deleteMaximum() {
        if(isEmpty()) return;
        H[0] = H[size - 1];
        size--;
        sink(0);
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private void sink(int index) {
        while(index < size) {
            int biggerChild = greaterWeightEdge(leftChild(index),
                    rightChild(index));

            if(biggerChild == -1 || H[index].compareTo(H[biggerChild]) >= 0) {
                break;
            }
            swap(index, biggerChild, H);
            index = biggerChild;
        }
    }

    private void swap(int a, int b, Edge[] arr) {
        Edge temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public Edge maximum() {
        if(isEmpty()) {
            return null;
        } else {
            return H[0];
        }
    }

    private int leftChild(int i) {
        int j = 2 * i + 1;
        return (j < size) ? j: -1;
    }

    private int rightChild(int i) {
        int j = 2 * i + 2;
        return (j < size) ? j : -1;
    }

    private int greaterWeightEdge(int i, int j) {
        if(j == -1 && i == -1) {
            return -1;
        } else if(j == -1) {
            return i;
        }
        return H[i].compareTo(H[j]) >=0 ? i : j;
    }
}


