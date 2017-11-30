package com.invictus.networkrouter.graph;

import com.invictus.networkrouter.utils.Constants;

import java.util.*;

public class GraphGenerator {
    private List<Edge>[] adjList;
    private List<Edge> allEdges;
    private Set<Integer> edgesSet;
    private int n;
    private Random random;
    private int edgeWeightUpperLimit;

    //TODO: Check this generic array thing. What should I use- checked or unchecked technique?
    public GraphGenerator(int graphType) {
        n = Constants.VERTICES_COUNT;
        initialization(graphType);
        }

    public GraphGenerator(int graphType, int n) {
        this.n = n;
        initialization(graphType);
    }

    private void initialization(int graphType) {
        random = new Random();
        edgesSet = new HashSet<>();
        adjList = (List<Edge>[]) new ArrayList[n];
        allEdges = new ArrayList<>();
        edgeWeightUpperLimit = random.nextInt(Constants.EDGE_WEIGHTS_BOUND) + 171; //Random Number

        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<Edge>();
        }
        if(graphType == 1) {
            createGraphType1(adjList, n);
        } else {
            createGraphType2(adjList, n);
        }
    }

    /**
     * G1: Sparse Undirected Graph
     * Average vertex degree is 8.
     */
    private void createGraphType1(List<Edge>[] adj, int n) {
        int totalEdges = 4 * n;
        createACycle(adj, n);
        int remainingEdges = totalEdges - n; //After creating a cycle, n edges used already

        while(remainingEdges > 0) {
            int a = random.nextInt(n);
            int b = a;
            while(b == a) {
                b = random.nextInt(n);
            }
            int edgeKey = calculateEdgeKey(a, b);
            if(! edgesSet.contains(edgeKey)) {
                int randWeight = getRandomWeight();
                Edge e = new Edge(a, b, randWeight);
                adjList[a].add(e);
                adjList[b].add(new Edge(b, a, randWeight));
                allEdges.add(e);
                remainingEdges--;
            }
        }
    }

    /**
     * G2: Dense Graph
     * Each vertex adjacent to about 20% of other vertices
     */
    private void createGraphType2(List<Edge>[] adj, int n) {
        createACycle(adj, n);

        //Pick each pair of vertices and connect them with a probablity of 20%
        for(int i = 0; i < n; i++) {
            for(int j = i  + 1; j > i && j < n; j++) {
                int randNum = random.nextInt(100);
                int edgeKey = calculateEdgeKey(i, j);
                if(randNum < 20 && !edgesSet.contains(edgeKey)) {
                    int randWeight = getRandomWeight();
                    Edge e = new Edge(i, j, randWeight);
                    adjList[i].add(e);
                    adjList[j].add(new Edge(j, i, randWeight));
                    allEdges.add(e);
                }
            }
        }
    }

    private boolean doesEdgeExists(int source, int destination, List<Edge>[] adjList) {
        List<Edge> edgeList = adjList[source];
        for(Edge e: edgeList) {
            if(e.getDestinationVertex() == destination) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method will make sure that the graph remains connected
     * by first joining all the vertices in a cycle.
     *
     **/
    private void createACycle(List<Edge>[] adj, int n) {
        for(int i = 0; i < n - 1 ; i ++) {
            int randWeight1 = getRandomWeight();
            Edge e = new Edge(i, i + 1, randWeight1);
            adj[i].add(e);
            adj[i+1].add(new Edge(i+1, i, randWeight1));
            allEdges.add(e);
            addEdgeKeyToSet(i, i + 1);
        }
        int randWeight2 = getRandomWeight();
        Edge e2 = new Edge(n - 1,1, randWeight2);
        adj[n - 1].add(e2);
        adj[1].add(new Edge(1, n - 1, randWeight2));
        allEdges.add(e2);
        addEdgeKeyToSet(n - 1, 1);
    }

    /**
     * Adding 1 to the random weight generator to avoid edges with 0 weight.
     */
    private int getRandomWeight() {
        return random.nextInt(edgeWeightUpperLimit) + 1;
    }

    public void printAllEdges() {
        for(int i = 0; i< n; i++) {
            for(Edge e: adjList[i]) {
                System.out.println(e);
            }
        }
    }

    public List<Edge>[] getAdjList(){
        return adjList;
    }

    public List<Edge> getAllEdges() {
        return allEdges;
    }

    public static void main(String[] args) {
        GraphGenerator o = new GraphGenerator(2, 20);
        o.printAllEdges();
    }

    private int calculateEdgeKey(int s, int t) {
        return (int)Math.pow(10, 8) * s + t;
    }
    private void addEdgeKeyToSet(int s, int t) {
        int key1 = calculateEdgeKey(s, t);
        int key2 = calculateEdgeKey(t, s);
        edgesSet.add(key1);
        edgesSet.add(key2);
    }
}