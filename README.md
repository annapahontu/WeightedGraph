# Java Weighted Graph Implementation

This project is a simple, academic implementation of a weighted, directed graph in Java. It uses an adjacency list structure to store the graph, where an array of LinkedLists represents the vertices, and each LinkedList stores the Edge objects for that vertex. This code was developed as an exercise in understanding basic graph data structures.

### How to Compile and Run
To compile and run this project, you must have the Java Development Kit (JDK) installed.
1. Clone this repository to your local machine.
2. Open your terminal or command prompt.
3. Navigate to the root directory of the project (the folder that contains the wgraph directory, not the wgraph directory itself).
4. Compile all the .java files using the package path:
 ```
javac wgraph/*.java
```      
5. Run the Main class using its fully qualified name
6. The output will show the graph being built, printed, modified by vertex/edge removal, and printed again.

### Possible Refactors
A much more efficient and standard implementation would avoid using a base array:
- Using ArrayList: Replacing the LinkedList<Edge>[] with an ArrayList<LinkedList<Edge>> would make addVertex() an amortized $O(1)$ operation, fixing the primary performance bottleneck.
- Using HashMap: Using a HashMap<Integer, LinkedList<Edge>> would be the most robust solution. It would allow for $O(1)$ vertex addition/lookup and would completely eliminate the re-indexing problem, allowing vertices to have non-sequential IDs.
