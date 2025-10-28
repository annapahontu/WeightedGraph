package wgraph;

public class Edge {
    private int source;      // The starting vertex
    private int destination; // The ending vertex
    private int weight;      // The weight of the edge
    
    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    // Getter for the source vertex
    public int getSource() {
        return source;
    }

    // Getter for the destination vertex
    public int getDestination() {
        return destination;
    }

    // Getter for the weight
    public int getWeight() {
        return weight;
    }

    // Setter for the source vertex
    public void setSource(int source) {
        this.source = source;
    }

    // Setter for the destination vertex
    public void setDestination(int destination) {
        this.destination = destination;
    }

    // Setter for the weight
    public void setWeight(int weight) {
        this.weight = weight;
    }
}