package com.invictus.networkrouter;

import com.invictus.networkrouter.graph.GraphGenerator;
import org.junit.jupiter.api.Test;

public class Test1 {
    @Test
    public void graph1_3vertex() {
        GraphGenerator o = new GraphGenerator(1, 3);
        o.printAllEdges();
    }

}
