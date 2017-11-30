package com.invictus.networkrouter.client;

import com.invictus.networkrouter.algorithms.RoutingAlgorithm;
import com.invictus.networkrouter.utils.Constants;
import com.invictus.networkrouter.graph.GraphGenerator;

import java.util.Random;

public class Driver {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        GraphGenerator sparseGraph = new GraphGenerator(1);
        long endTime = System.currentTimeMillis();
        System.out.println("Sparse Graph created in "+ (endTime - startTime)/1000.0 + " sec");
        System.out.println();
        //System.out.println("Starting to create a Dense Graph");
        startTime = System.currentTimeMillis();
        GraphGenerator denseGraph = new GraphGenerator(2);
        endTime = System.currentTimeMillis();
        System.out.println("Dense Graph created in "+ (endTime - startTime)/1000.0 + " sec");
        System.out.println("");
        Random r = new Random();
        Driver o = new Driver();
        for(int i = 0; i < 1; i++) {
            System.out.println("=========================================");
            System.out.println("Sparse Graph: ");
            o.findMaxBWPath(r, sparseGraph);

        }
        System.out.println("=========================================");
        System.out.println("Dense Graph: ");
        o.findMaxBWPath(r, denseGraph);
    }

    private void findMaxBWPath(Random r, GraphGenerator graph) {
        int s = r.nextInt(Constants.VERTICES_COUNT);
        int t = r.nextInt(Constants.VERTICES_COUNT);
        System.out.println("Source, Destination " + s + ", " + t );

        long startTime = System.currentTimeMillis();
        RoutingAlgorithm algorithm = new RoutingAlgorithm(s, t, graph, 1);
        long endTime = System.currentTimeMillis();
        System.out.println("Dijkstra Algorithm Without Heap completed in  "+ (endTime - startTime)/1000.0 + " sec ");

        startTime = System.currentTimeMillis();
        RoutingAlgorithm algorithm2 = new RoutingAlgorithm(s, t, graph, 2);
        endTime = System.currentTimeMillis();
        System.out.println("Dijkstra Algorithm Using Heap completed in "+ (endTime - startTime)/1000.0 + " sec ");

        startTime = System.currentTimeMillis();
        RoutingAlgorithm algorithm3 = new RoutingAlgorithm(s, t, graph, 3);
        endTime = System.currentTimeMillis();
        System.out.println("Kruskal Algorithm completed in "+ (endTime - startTime)/1000.0 + " sec");
    }
}
