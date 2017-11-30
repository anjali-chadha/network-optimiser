package com.invictus.networkrouter.algorithms;

import com.invictus.networkrouter.graph.Edge;
import com.invictus.networkrouter.graph.NodeStatus;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DijkstraWithoutHeap {

    private NodeStatus[] status;
    private int[] dad;
    private int[] bw;
    private int n;
    private List<Integer> fringeList = new LinkedList<>();
    private int destination;

    private void initialisation(List<Edge>[] adjList, int source) {
        n = adjList.length;
        status = new NodeStatus[n];
        dad = new int[n];
        bw = new int[n];

        Arrays.fill(dad, -1); //-1 represent no parent.
        Arrays.fill(status, NodeStatus.unseen);
        bw[source] = Integer.MAX_VALUE;

        status[source] = NodeStatus.intree;

        for(Edge edge: adjList[source]) {
            int v = edge.getDestinationVertex();
            int wt = edge.getEdgeWeight();
            status[v] = NodeStatus.fringe;
            dad[v] = source;
            bw[v] = wt;
            fringeList.add(v);
        }
    }

    public DijkstraWithoutHeap(List<Edge>[] adjList, int source, int destination) {
        initialisation(adjList, source);
        this.destination = destination;

        while (status[destination] != NodeStatus.intree) {
            int maxBWNode = fringeList.get(0);
            for(Integer node: fringeList) {
                if(bw[node] > bw[maxBWNode]) {
                    maxBWNode = node;
                }
            }

            fringeList.remove((Integer)maxBWNode);
            status[maxBWNode] = NodeStatus.intree;

            for(Edge edge: adjList[maxBWNode]) {
                int w = edge.getDestinationVertex();
                int wt = edge.getEdgeWeight();
                if(status[w] == NodeStatus.unseen) {
                    status[w] = NodeStatus.fringe;
                    dad[w] = maxBWNode;
                    bw[w] = Math.min(bw[maxBWNode], wt);
                    fringeList.add(w);
                } else if(status[w] == NodeStatus.fringe && bw[w] < Math.min(bw[maxBWNode], wt)) {
                    bw[w] = Math.min(bw[maxBWNode], wt);
                    dad[w] = maxBWNode;
                }
            }
        }

        System.out.println("Dijkstra without heap: MaxBW- " + bw[destination]);
        //System.out.println("Bandwidth: " + Arrays.toString(bw));
       // System.out.println("Dad: " + Arrays.toString(dad));
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
