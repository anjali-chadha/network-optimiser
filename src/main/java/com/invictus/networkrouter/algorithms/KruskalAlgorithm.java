package com.invictus.networkrouter.algorithms;

import com.invictus.networkrouter.graph.Edge;

import java.util.*;

public class KruskalAlgorithm {
    private HeapKruskal heap;
    private int n;
    private Queue<Edge> maxSpanningTree;
    private int[] bw;
    private int[] dad;
    private boolean[] visited;
    private int destination;

    public KruskalAlgorithm(List<Edge>[] adjList, List<Edge> allEdges, int source, int destination) {
        n = adjList.length;
        heap = new HeapKruskal();
        bw = new int[n];
        dad = new int[n];
        visited = new boolean[n];
        Arrays.fill(dad, -1);
        this.destination = destination;

        maxSpanningTree = new LinkedList<>();
        heap.heapSort(allEdges);

        UnionFind uf = new UnionFind();
        uf.make_set(n);

        int m = allEdges.size();

        while(m != 0) {
            Edge maxEdge = heap.maximum();
            heap.deleteMaximum();

            if(!uf.isConnected(maxEdge.getDestinationVertex(), maxEdge.getSourceVertex())) {
                uf.union(maxEdge.getDestinationVertex(), maxEdge.getSourceVertex());
                maxSpanningTree.add(maxEdge);
            }
            m--;
        }
        List<Edge>[] mstAdjList = createAdjListMST(maxSpanningTree);
        getMaxBW(mstAdjList, source);
        System.out.println("Kruskal: MaxBw - "  + bw[destination]);
        //System.out.println("Bandwidth: " + Arrays.toString(bw));
        //System.out.println("Dad: " + Arrays.toString(dad));
        printPath();
        System.out.println();
    }

    private List<Edge>[] createAdjListMST(Queue<Edge> edgeList) {
        List<Edge> [] mstAdjList = (List<Edge>[]) new ArrayList[n];
        for(int i = 0; i < n; i++) {
            List<Edge> l = new ArrayList<>();
            mstAdjList[i] = l;
        }
        for(Edge e: edgeList) {
            int u = e.getSourceVertex();
            int v = e.getDestinationVertex();

            mstAdjList[u].add(e);
            mstAdjList[v].add(new Edge(v, u, e.getEdgeWeight()));
        }

        return mstAdjList;
    }

    private void getMaxBW(List<Edge>[] adjList, int s) {
        bw[s] = Integer.MAX_VALUE;
        DFS(adjList, s);
    }

    private void DFS(List<Edge>[] adjList, int vertex) {
        visited[vertex] = true;
        if(vertex == destination) return;
        for(Edge e: adjList[vertex]) {
            int w = e.getDestinationVertex();
            int wt = e.getEdgeWeight();
            if(!visited[w]) {
                dad[w] = vertex;
                if(wt < bw[vertex]) {
                    bw[w] = wt;
                } else {
                    bw[w] = bw[vertex];
                }
                DFS(adjList, w);
            }
        }
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
