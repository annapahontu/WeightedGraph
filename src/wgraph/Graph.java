package wgraph;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
    private int vertexCount; // Number of vertices
    private LinkedList<Edge>[] adjacencyList; // Adjacency list array

    public Graph() {
        this.vertexCount = 0;
        adjacencyList = new LinkedList[0];
    }

    public boolean isEmpty() {
        return vertexCount == 0;
    }

    public void addVertex() {
        vertexCount++;
        // Create a new, larger array
        LinkedList<Edge>[] newAdj = new LinkedList[vertexCount];
        // Copy the old list content
        for (int i = 0; i < adjacencyList.length; i++) {
            newAdj[i] = adjacencyList[i];
        }
        // Initialize the list for the new vertex
        newAdj[vertexCount - 1] = new LinkedList<>();
        adjacencyList = newAdj;
    }

    public void removeVertex(int vertex) {
        if (vertex < 0 || vertex >= vertexCount) {
            System.out.println("Vertex does not exist.");
            return;
        }
        // Clear all outgoing edges from the removed vertex
        if (adjacencyList[vertex] != null) {
            adjacencyList[vertex].clear();
        }

        // Loop through all vertices to remove incoming edges
        for (int i = 0; i < vertexCount; i++) {
            if (adjacencyList[i] != null) {
                LinkedList<Edge> list = adjacencyList[i];
                LinkedList<Edge> newList = new LinkedList<>();

                for (int j = 0; j < list.size(); j++) {
                    Edge edge = list.get(j);
                    if (edge.getDestination() != vertex) {
                        newList.add(edge);
                    }
                }
                adjacencyList[i] = newList;
            }
        }

        // Create a new, smaller adjacency list
        LinkedList<Edge>[] newAdj = new LinkedList[vertexCount - 1];
        int index = 0;
        for (int i = 0; i < vertexCount; i++) {
            // Copy all lists *except* the removed vertex's list
            if (i != vertex) {
                newAdj[index] = adjacencyList[i];
                index++;
            }
        }
        
        // Re-index all edges
        for (int i = 0; i < newAdj.length; i++) {
            LinkedList<Edge> list = newAdj[i];
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getDestination() > vertex) {
                    list.get(j).setDestination(list.get(j).getDestination() - 1);
                }
            }
        }
        adjacencyList = newAdj;
        vertexCount--;
    }

    public void addEdge(int source, int destination, int weight) {
        // Use new variable names for the constructor
        Edge edge = new Edge(source, destination, weight);
        adjacencyList[source].addFirst(edge);
        
        // For an undirected graph
        // Edge edge2 = new Edge(destination, source, weight);
        // adjacencyList[destination].addFirst(edge2);
    }

    public void removeEdge(int source, int destination) {
        Iterator<Edge> iterator = adjacencyList[source].iterator();
        while (iterator.hasNext()) {
            Edge edge = iterator.next();
            if (edge.getDestination() == destination) {
                iterator.remove();
            }
        }
        // Check if the vertices are now isolated
        checkIfIsolated(source);
        checkIfIsolated(destination);
    }

    private void checkIfIsolated(int vertex) {
        // Check for any incoming edges
        boolean hasIncomingEdge = false;
        for (int i = 0; i < vertexCount; i++) {
            if (adjacencyList[i] != null) {
                int j = 0;
                while (j < adjacencyList[i].size() && !hasIncomingEdge) {
                    Edge edge = adjacencyList[i].get(j);
                    if (edge.getDestination() == vertex) {
                        hasIncomingEdge = true;
                    }
                    j++;
                }
            }
        }

        // If no outgoing edges (isEmpty) and no incoming edges, it's isolated.
        if (adjacencyList[vertex].isEmpty() && !hasIncomingEdge) {
            System.out.println("Vertex " + vertex + " is now isolated and will be removed.");
            adjacencyList[vertex].clear();
        }
    }

    public void printGraph() {
        for (int i = 0; i < vertexCount; i++) {
            LinkedList<Edge> list = adjacencyList[i];

            for (int j = 0; j < list.size(); j++) {
                System.out.println("Vertex " + i + " is connected to " + list.get(j).getDestination()
                        + " with weight " + list.get(j).getWeight());
            }
        }
    }
}