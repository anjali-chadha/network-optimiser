import java.util.List;

public class RoutingAlgorithm {

    private int startVertex;
    private int endVertex;
    private List<Edge>[] adjList;
    private int n;

    RoutingAlgorithm(int startVertex, int endVertex, List<Edge>[] adjList, int algorithmType) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.adjList = adjList;
        this.n = adjList.length - 1;
        switch(algorithmType) {
            case 1: maxBandwidth1();
            break;
            case 2: maxBandwidth2();
            break;
            case 3: maxBandwidth3();
            break;
        }
    }

    /**
     * Using Dijkstra algorithm without using a heap
     * @return
     */
    private int maxBandwidth1() {
        return 0;
    }

    /**d
     * Using Dijkstra algorithm with heap for storing fringes
     * @return
     */
    private int maxBandwidth2() {
        return 0;
    }

    /**
     * Using Kruskal algorithm, where edges are sorted
     * by HeapSort.
     * Use Union-find to check whether the new vertex forms a cycle or not
     * @return
     */
    private int maxBandwidth3() {
        //TODO: See how to instantiate heap.
        MaxHeap2 heap = new MaxHeap2(n);
        int m = heap.size();

        UnionFind uf = new UnionFind();
        uf.make_set(n + 1);

        while(m != 0) {
            //int minimum
            Edge maxEdge = heap.maximum();
            heap.delete(maxEdge);
            if(uf.isConnected(maxEdge.))
            m--;
        }

        return 0;
    }


}
