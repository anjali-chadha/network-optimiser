package com.invictus.networkrouter.graph;

public class Edge implements Comparable<Edge>{
    private int sourceVertex;
    private int destinationVertex;
    private int edgeWeight;

    //TODO: Since this is for undirected graph. Do I really need to maintain
    // source and destination vertices? Shall I treat them differently?
    public Edge(int sourceVertex, int destinationVertex, int edgeWeight) {
        this.sourceVertex = sourceVertex;
        this.destinationVertex = destinationVertex;
        this.edgeWeight = edgeWeight;
    }

    public int getDestinationVertex() {
        return destinationVertex;
    }

    public int getSourceVertex() {
        return sourceVertex;
    }

    public int getEdgeWeight() {
        return edgeWeight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.edgeWeight - o.edgeWeight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "sourceVertex=" + sourceVertex +
                ", destinationVertex=" + destinationVertex +
                ", edgeWeight=" + edgeWeight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (sourceVertex != edge.sourceVertex && sourceVertex != edge.destinationVertex) return false;
        if (destinationVertex != edge.destinationVertex && destinationVertex != edge.sourceVertex) return false;
        return edgeWeight == edge.edgeWeight;
    }

    @Override
    public int hashCode() {
        int result = sourceVertex + destinationVertex;
        result = 31 * result + edgeWeight;
        return result;
    }
}