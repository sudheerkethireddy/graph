import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class encapsulates the graph which is a combination of vertices
 */
public class Graph {


    private boolean isDirected; // flag indicating if the graph is directed or not
    private Map<Vertex, List<Vertex>> adjacentVertices; // graph is represented as a map of verties representing each vertex and associated vertices


    public static void main(String[] args) {
        Graph graph = new Graph(true);
        Vertex a = new Vertex ("A");
        Vertex b = new Vertex ("B");
        Vertex c = new Vertex ("C");
        Vertex d = new Vertex ("D");
        Vertex e = new Vertex ("E");

        graph.addEdge(a,b);
        graph.addEdge(a,e);
        graph.addEdge(b,d);
        graph.addEdge(b,e);
        graph.addEdge(b,e);
        graph.addEdge(c,e);
        graph.addEdge(b,a);
        graph.addEdge(d,e);


        graph.getPath(a,d);
    }


    public Graph(boolean isDirected) {
        this.isDirected = isDirected;
        this.adjacentVertices = new HashMap<>();
    }

    public void addVertex(Vertex vertex) {
        adjacentVertices.putIfAbsent(vertex, new ArrayList<Vertex>());
    }


    /**
     * This method adds the edge between source and destination verties
     * @param source           - the source vertex
     * @param destination      - the destination vertex
     */
    public void addEdge(Vertex source, Vertex destination) {

        addVertex(source);
        addVertex(destination);

        addEdgeHelper(source, destination);

        // adding edge from destination to source, if the graph is un-directed
        if(!this.isDirected) {
            addEdgeHelper(destination, source);
        }
    }


    /**
     * This method gives you the path from source vertex to destination vertex
     * @param source       - the source vertex
     * @param destination  - the destination vertex
     */
    public void getPath(Vertex source, Vertex destination) {

        List<Vertex> beingVisited = new ArrayList<>();
        List<Vertex> currentPath = new ArrayList<>();

        if(hasEdge(source,destination)) {
            System.out.println(Arrays.asList(source.label, destination.label));
        }
        else {
            currentPath.add(source);
            dfs(source, destination, beingVisited, currentPath);
        }
    }


    private void dfs(Vertex source, Vertex destination, List<Vertex> beingvisited, List<Vertex> currentPath) {

        beingvisited.add(source);

        if(source.equals(destination)) {
            System.out.println(currentPath.stream().map(x -> x.label).collect(Collectors.toList()));
        }
        for(Vertex vertex : adjacentVertices.get(source)) {
            if(!beingvisited.contains(vertex)) {
                currentPath.add(vertex);
                dfs(vertex, destination,beingvisited, currentPath);
                currentPath.remove(vertex);
            }
        }
    }


    /**
     * util method that checks if there exists an edge b/w source and vertex
     * @param source       - source vertex
     * @param destination  - destination vertex
     * @return              - true if there exists a direct edge b/w vertices or else false.
     */
    private boolean hasEdge(Vertex source, Vertex destination) {

        if(adjacentVertices.containsKey(source) &&  //  check there exists the source vertex in the graph
                adjacentVertices.get(source) != null &&  // check that source vertex has non-empty adjacent vertices
                adjacentVertices.get(source).contains(destination) // destination vertex is part of list of adjacent vertices
         ) {
            return true;
        }
        else {
            return false;
        }
    }

    private void addEdgeHelper(Vertex source, Vertex destination) {

        List<Vertex> adjacentNodes = adjacentVertices.get(source);
        if(adjacentNodes.size() > 0) {
            adjacentNodes.remove(destination);
        }
        else {
            adjacentNodes = new ArrayList<>();
        }
        adjacentNodes.add(destination);
        adjacentVertices.put(source, adjacentNodes);
    }

    // getters and setters for the graph
    public Map<Vertex, List<Vertex>> getAdjacentVertices() {
        return adjacentVertices;
    }

}
