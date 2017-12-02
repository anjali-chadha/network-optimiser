package com.invictus.networkrouter.algorithms;

import com.invictus.networkrouter.graph.Edge;
import com.invictus.networkrouter.graph.GraphGenerator;

import java.util.List;

public class RoutingAlgorithm {

    private int startVertex;
    private int endVertex;
    private List<Edge>[] adjList;
    private GraphGenerator g;

    public RoutingAlgorithm(int startVertex, int endVertex, GraphGenerator g, int algorithmType) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.g = g;
        this.adjList = g.getAdjList();
        switch(algorithmType) {
            case 1: maxBandwidth1();
            break;
            case 2: maxBandwidth2();
            break;
            case 3: maxBandwidth3();
            break;
                //TODO: Add default and throw error
        }
    }

    /**
     * Using Dijkstra algorithm without using a heap
     * @return
     */
    private void maxBandwidth1() {
        new DijkstraWithoutHeap(adjList, startVertex, endVertex);
    }

    /**
     * Using Dijkstra algorithm with heap for storing fringes
     * @return
     */
    private void maxBandwidth2() {
        new DijkstraWithHeap(adjList, startVertex, endVertex);
    }

    /**
     * Using Kruskal algorithm, where edges are sorted
     * by HeapSort.
     * Use Union-find to check whether the new vertex forms a cycle or not
     * @return
     */
    private void maxBandwidth3() {
        new KruskalAlgorithm(adjList, g.getAllEdges(), startVertex, endVertex);
    }
}
