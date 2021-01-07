package traversal;

public class Node {
    private int value;
    private String name;
    private boolean visited; // New attribute

    public Node(int value, String name) {
        this.value = value;
        this.name = name;
        visited = false;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    // Two new methods we'll need in our traversal algorithms
    void visit() {
        visited = true;
    }

    void unvisit() {
        visited = false;
    }

    public boolean isVisited() {
        return visited;
    }
}