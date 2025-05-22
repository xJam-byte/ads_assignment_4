import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private Map<Vertex<V>, Vertex<V>> edgeTo = new HashMap<>();
    private Set<Vertex<V>> visited = new HashSet<>();
    private final Vertex<V> start;

    public BreadthFirstSearch(WeightedGraph<V> graph, Vertex<V> start) {
        this.start = start;
        bfs(graph, start);
    }

    private void bfs(WeightedGraph<V> graph, Vertex<V> current) {
        Queue<Vertex<V>> queue = new LinkedList<>();
        visited.add(current);
        queue.add(current);

        while (!queue.isEmpty()) {
            Vertex<V> vertex = queue.poll();
            for (Vertex<V> neighbor : vertex.getAdjacentVertices().keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    edgeTo.put(neighbor, vertex);
                    queue.add(neighbor);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(Vertex<V> destination) {
        return visited.contains(destination);
    }

    @Override
    public List<Vertex<V>> getPath(Vertex<V> destination) {
        if (!hasPathTo(destination)) return null;

        List<Vertex<V>> path = new LinkedList<>();
        for (Vertex<V> x = destination; x != start; x = edgeTo.get(x)) {
            path.add(0, x);
        }
        path.add(0, start);
        return path;
    }
}