import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphGenerator {
    private List<Edge>[] adjList;
    private int n;
    private Random random;

    //TODO: Check this generic array thing. What should I use- checked or unchecked technique?
    public GraphGenerator(int graphType) {
        n = Constants.VERTICES_COUNT;
        adjList = (List<Edge>[]) new Object[n+1];

        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<Edge>();
        }
        if(graphType == 1) {
            createGraphType1(adjList, n);
        } else {
            createGraphType2(adjList, n);
        }
    }

    /**
     * G1: average vertex degree is 8.
     */
    private void createGraphType1(List<Edge>[] adj, int n) {

    }

    /**
     * G2: Each vertex adjacent to about 20% of other vertices
     */
    private void createGraphType2(List<Edge>[] adj, int n) {

    }

    /**
     * This method will make sure that the graph remains connected
     * by first joining all the vertices in a cycle.
     **/
    private void createACycle(List<Edge>[] adj, int m) {
        for(int i = 1; i < m ; i ++) {
            adj[i].add(new Edge(i, i + 1,
                    random.nextInt(Constants.EDGE_WEIGHTS_BOUND)));

        }
        adj[m].add(new Edge(m,1,
                random.nextInt(Constants.EDGE_WEIGHTS_BOUND)));
    }
}