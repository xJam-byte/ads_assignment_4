import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private Map<Vertex<V>, Double> distance = new HashMap<>();
    private Map<Vertex<V>, Vertex<V>> edgeTo = new HashMap<>();
    private final Vertex<V> start;

    public DijkstraSearch(WeightedGraph<V> graph, Vertex<V> start) {
        this.start = start;
        for (Vertex<V> v : graph.getVertices()) {
            distance.put(v, Double.POSITIVE_INFINITY);
        }
        distance.put(start, 0.0);

        PriorityQueue<Vertex<V>> queue = new PriorityQueue<>(Comparator.comparingDouble(distance::get));
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();

            for (Map.Entry<Vertex<V>, Double> neighborEntry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = neighborEntry.getKey();
                double weight = neighborEntry.getValue();

                double newDist = distance.get(current) + weight;
                if (newDist < distance.get(neighbor)) {
                    distance.put(neighbor, newDist);
                    edgeTo.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(Vertex<V> destination) {
        return distance.containsKey(destination) && distance.get(destination) != Double.POSITIVE_INFINITY;
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