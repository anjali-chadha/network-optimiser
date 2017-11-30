package com.invictus.networkrouter;

import com.invictus.networkrouter.graph.GraphGenerator;

public class Test1 {
   // @Test
    public void graph1_3vertex() {
        GraphGenerator o = new GraphGenerator(1, 3);
        o.printAllEdges();
    }

}
