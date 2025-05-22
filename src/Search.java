import java.util.List;

public interface Search<V> {
    boolean hasPathTo(Vertex<V> destination);
    List<Vertex<V>> getPath(Vertex<V> destination);
}