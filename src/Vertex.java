import java.util.HashMap;
import java.util.Map;

public class Vertex<V> {
    private V data;
    private Map<Vertex<V>, Double> adjacentVertices = new HashMap<>();

    public Vertex(V data) {
        this.data = data;
    }

    public V getData() {
        return data;
    }

    public void addAdjacentVertex(Vertex<V> destination, double weight) {
        adjacentVertices.put(destination, weight);
    }

    public Map<Vertex<V>, Double> getAdjacentVertices() {
        return adjacentVertices;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}