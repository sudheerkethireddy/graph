import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GraphTest {

    Graph directedGraph = new Graph(true);
    Graph undirectedGraph = new Graph(false);
    Vertex a = new Vertex("A");
    Vertex b = new Vertex("B");
    Vertex c = new Vertex("C");
    Vertex d = new Vertex("D");
    Vertex e = new Vertex("E");

    @Test
    public void testAddEdge_directedGraph() {
        directedGraph.addEdge(a,b);
        assertEquals(directedGraph.getAdjacentVertices().get(a).size(), 1);
        directedGraph.addEdge(b,c);
        assertEquals(directedGraph.getAdjacentVertices().get(b).size(), 1);
    }

    @Test
    public void testAddEdge_undirectedGraph() {
        undirectedGraph.addEdge(a,b);
        assertEquals(undirectedGraph.getAdjacentVertices().get(a).size(), 1);
        undirectedGraph.addEdge(b,c);
        assertEquals(undirectedGraph.getAdjacentVertices().get(b).size(), 2);
    }

    @Test
    public void testAddVertex() {
        Graph graph = new Graph(false);
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        assertEquals(graph.getAdjacentVertices().get(a).size(),0);
    }

    @Test
    public void testPath_directedGraph_withDirectEdge() {
        Graph graph = new Graph(true);
        graph.addEdge(a,b);
        graph.addEdge(a,e);
        graph.addEdge(b,d);
        graph.addEdge(b,e);
        graph.addEdge(b,e);
        graph.addEdge(a,d);
        graph.addEdge(c,e);
        graph.addEdge(b,a);
        graph.addEdge(d,e);

        graph.getPath(a,e);
    }

    @Test
    public void testPath_directedGraph_witNohDirectEdge() {
        Graph graph = new Graph(true);
        graph.addEdge(a,b);
        graph.addEdge(b,d);
        graph.addEdge(d,e);

        graph.getPath(a,e);
    }

}
