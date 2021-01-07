package traversal;
//https://stackabuse.com/graphs-in-java-depth-first-search-dfs/
//https://algorithms.tutorialhorizon.com/depth-first-search-dfs-in-2d-matrix-2d-array-iterative-solution/
import java.util.HashMap;
import java.util.LinkedList;

public class Graph {

    // Each node maps to a list of all his neighbors
    // We use HashMap to support disconnected graphs
    private HashMap<Node, LinkedList<Node>> adjacencyMap; //If we want natural ordering of children, use PriorityQueue instead
    private boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        adjacencyMap = new HashMap<>();
    }

    public void addEdgeHelper(Node a, Node b) {
        LinkedList<Node> tmp = adjacencyMap.get(a);

        if (tmp != null) {
            tmp.remove(b); //duplicates edges not allowed
        }
        else {
            tmp = new LinkedList<>();
        }
        tmp.add(b);
        adjacencyMap.put(a, tmp);
    }

    public void addEdge(Node source, Node destination) {

        // We make sure that every used node shows up in our .keySet()
        if (!adjacencyMap.keySet().contains(source))
            adjacencyMap.put(source, null);

        if (!adjacencyMap.keySet().contains(destination))
            adjacencyMap.put(destination, null);

        addEdgeHelper(source, destination);

        // If a graph is undirected, we want to add an edge from destination to source as well
        if (!directed) {
            addEdgeHelper(destination, source);
        }
    }

    public void printEdges() {
        for (Node node : adjacencyMap.keySet()) {
            System.out.print("The " + node.getName() + " has an edge towards: ");
            for (Node neighbor : adjacencyMap.get(node)) {
                System.out.print(neighbor.getName() + " ");
            }
            System.out.println();
        }
    }

    public boolean hasEdge(Node source, Node destination) {
        return adjacencyMap.containsKey(source) && adjacencyMap.get(source).contains(destination);
    }

    public void depthFirstSearchModified(Node node) {
        depthFirstSearch(node); // For unconnected graphs

        for (Node n : adjacencyMap.keySet()) {
            if (!n.isVisited()) {
                depthFirstSearch(n);
            }
        }
    }

    public void depthFirstSearch(Node node) {
        node.visit();
        System.out.print(node.getName() + " ");

        LinkedList<Node> allNeighbors = adjacencyMap.get(node);
        if (allNeighbors == null) {
            return;
        }

        for (Node neighbor : allNeighbors) {
            if (!neighbor.isVisited())
                depthFirstSearch(neighbor);
        }
    }

    public void breadthFirstSearch(Node node) {
        if (node == null)
            return;

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);

        while(! queue.isEmpty()) {
            Node currentFirst = queue.removeFirst();
            if (currentFirst.isVisited())
                continue;

            currentFirst.visit();
            System.out.print(currentFirst.getName() + " ");

            LinkedList<Node> allNeighbors = adjacencyMap.get(currentFirst);
            if (allNeighbors == null) {
                continue;
            }

            for (Node neighbor : allNeighbors) {
                // We only add unvisited neighbors
                if (!neighbor.isVisited()) {
                    queue.add(neighbor);
                }
            }
        }
    }

    void breadthFirstSearchModified(Node node) { //to support unconnected graphs
        breadthFirstSearch(node);

        for (Node n : adjacencyMap.keySet()) {
            if (!n.isVisited()) {
                breadthFirstSearch(n);
            }
        }
    }

    public static void main(String [] args) {
        Graph graph = new Graph(true);
        Node zero = new Node(0, "0");
        Node one = new Node(1, "1");
        Node two = new Node(2, "2");
        Node three = new Node(3, "3");
        Node four = new Node(4, "4");
        Node five = new Node(5, "5");
        Node six = new Node(6, "6");
        Node seven = new Node(7, "7");
        Node eight = new Node(8, "8");

        graph.addEdge(zero,one);
        graph.addEdge(zero,two);
        graph.addEdge(zero,three);
        graph.addEdge(one,four);
        graph.addEdge(one,five);
        graph.addEdge(two,six);
        graph.addEdge(two,seven);
        graph.addEdge(seven, eight);

       // graph.depthFirstSearch(zero);
        graph.breadthFirstSearch(zero);
    }
}