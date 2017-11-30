package com.invictus.networkrouter.algorithms;

import com.invictus.networkrouter.graph.Edge;
import com.invictus.networkrouter.graph.NodeStatus;

import java.util.Arrays;
import java.util.List;

public class DijkstraWithHeap {
    private int[] dad;
    private NodeStatus[] status;
    private int[] bw;
    private int n;
    private HeapDijkstra heap;
    private int destination;

    private void initialisation(List<Edge>[] adjList, int source) {
        n = adjList.length;
        status = new NodeStatus[n];
        dad = new int[n];
        bw = new int[n];
        heap = new HeapDijkstra(n);

        Arrays.fill(dad, -1); //-1 represent no parent.
        Arrays.fill(status, NodeStatus.unseen);
        status[source] = NodeStatus.intree;

        bw[source] = Integer.MAX_VALUE;

        for(Edge edge: adjList[source]) {
            int v = edge.getDestinationVertex();
            int wt = edge.getEdgeWeight();
            status[v] = NodeStatus.fringe;
            dad[v] = source;
            bw[v] = wt;
            heap.insert(v, wt);
        }
    }

    public DijkstraWithHeap(List<Edge>[] adjList, int source, int destination) {
        this.destination = destination;

        initialisation(adjList, source);

        while(status[destination] != NodeStatus.intree) {
            //Can combine following two steps in one method.
            int maxBWNode = heap.maximum();
            heap.deleteMaximum();

            status[maxBWNode] = NodeStatus.intree;
            for(Edge edge: adjList[maxBWNode]) {
                int w = edge.getDestinationVertex();
                int wt = edge.getEdgeWeight();
                if(status[w] == NodeStatus.unseen) {
                    status[w] = NodeStatus.fringe;
                    dad[w] = maxBWNode;
                    bw[w] = Math.min(bw[maxBWNode], wt);
                    heap.insert(w, bw[w]);
                } else if(status[w] == NodeStatus.fringe && bw[w] < Math.min(bw[maxBWNode], wt)) {
                    heap.delete(w);
                    bw[w] = Math.min(bw[maxBWNode], wt);
                    dad[w] = maxBWNode;
                    heap.insert(w, bw[w]);
                }
            }
        }
        System.out.println("Dijkstra with heap: MaxBW- "  + bw[destination]);
        //System.out.println("Bandwidth: " + Arrays.toString(bw));
        //System.out.println("Dad: " + Arrays.toString(dad));
        printPath();
        System.out.println();
    }

    private void printPath() {
        int v = destination;
        StringBuilder path = new StringBuilder();
        while(v != -1) {
            path.append(v + ", ");
            v = dad[v];
        }
        System.out.println(path.toString());
    }
}
