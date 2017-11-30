package com.invictus.networkrouter.algorithms;

import java.util.*;

/**
 * Performing union by rank and path compression.
 * Will be used by Kruskal to find the cycle existence.
 */
public class UnionFind {

    private int[] parent;
    private int[] rank;

    /**
     * In case, no parent is assigned to a vertex, it will have default value 0.
     * This will work because in our implementation, all vertices have integer values
     * starting from 1.
     */
    void make_set(int n) {
        parent = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    /**
     * Perform path compression on the path where vertex v is present
     * and return the root vertex of the path
     */
    private int find(int v) {
        int w = v;
        Queue<Integer> queue = new LinkedList<>();
        while(w != parent[w]) {
            queue.add(w);
            w = parent[w];
        }

        while(! queue.isEmpty()) {
            int i = queue.remove();
            parent[i] = w;
        }
        return w;
    }

    /**
     * Performing union by rank.
     * If the tree heights is not equal, connect the tree with
     * a lower rank to the tree with a higher rank.
     * Otherwise, if heights are equal, connect any vertex with the other, and
     * increase the rank of the parent vertex, since that results in the increment
     * of the height of the tree.
     */
    public void union(int v1, int v2) {
        int rootA = find(v1);
        int rootB = find(v2);

        int a = rank[rootA];
        int b = rank[rootB];

        if(a > b){
            parent[rootB] = rootA;
        } else if(a < b){
            parent[rootA] = rootB;
        } else {
            //equality case
            parent[rootA] = rootB;
            rank[rootB]++;
        }
    }

    boolean isConnected(int v1, int v2) {
        return find(v1) == find(v2);
    }
}
