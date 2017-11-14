import java.util.LinkedList;
import java.util.Queue;

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
    }

    /**
     * Perform path compression on the path where vertex v is present
     * and return the root vertex of the path
     */
    private int find(int v) {
        int w = v;
        Queue<Integer> queue = new LinkedList<>();
        while(w != 0) {
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
    private void union(int v1, int v2) {
        int a = rank[v1];
        int b = rank[v2];

        if(a > b){
            parent[v2] = v1;
        } else if(a < b){
            parent[v1] = v2;
        } else {
            //equality case
            parent[v1] = v2;
            rank[v2]++;
        }
    }

    boolean isConnected(int v1, int v2) {
        return (parent[v1] != 0) && (parent[v1] == parent[v2]);
    }
}
