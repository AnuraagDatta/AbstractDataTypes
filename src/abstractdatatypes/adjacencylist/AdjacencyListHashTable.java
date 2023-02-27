package abstractdatatypes.adjacencylist;

import abstractdatatypes.hashtable.HashTableLinkedList;
import abstractdatatypes.linkedlist.LinkedList;
import abstractdatatypes.hashtable.HashTable;
import abstractdatatypes.hashtable.KeyValuePair;
import abstractdatatypes.queues.circularqueue.CircularQueue;
import abstractdatatypes.queues.priorityqueue.PriorityQueue;

import java.io.File;
import java.util.Scanner;

public class AdjacencyListHashTable<N> implements IAdjacencyList<N>
{
    private HashTableLinkedList<N, HashTableLinkedList<N, Integer>> adjacencyList;
    private boolean isDirected;

    @SafeVarargs
    public AdjacencyListHashTable(boolean isDirected, N... nodes)
    {
        if (nodes.length == 0)
        {
            throw new IllegalArgumentException("MaxSize must be positive!");
        }
        adjacencyList = new HashTableLinkedList<>(nodes.length);
        for (N node : nodes)
        {
            adjacencyList.add(node, new HashTableLinkedList<>(nodes.length));
        }
        this.isDirected = isDirected;
    }

//    public void addNode(N newNode)
//    {
//        adjacencyList.incrementMaxSize();
//    }

    private void addConnection(N source, N destination, Integer weight)
    {
        if (!adjacencyList.contains(source))
        {
            throw new IllegalArgumentException("Node " + source + " not found!");
        }
        if (source.equals(destination))
        {
            throw new IllegalArgumentException("Node cannot connect to itself!");
        }
        if (adjacencyList.item(source).contains(destination))
        {
            throw new IllegalArgumentException("A connection between these nodes already exists!");
        }
        adjacencyList.item(source).add(destination, weight);
    }

    @Override
    public void add(N source, N destination, Integer weight)
    {
        addConnection(source, destination, weight);
        if (!isDirected)
        {
            addConnection(destination, source, weight);
        }
    }

    @Override
    public Integer getConnectionWeight(N origin, N destination)
    {
        try
        {
            return adjacencyList.item(origin).item(destination);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Connection not found!");
        }
    }

    public N[] getConnectedNodes(N node)
    {
        return adjacencyList.item(node).arrayOfKeys();
    }

    @Override
    public LinkedList<N> depthFirstTraversal(N start)
    {
        return depthFirstTraversal(start, new LinkedList<>());
    }

    private LinkedList<N> depthFirstTraversal(N currentVertex, LinkedList<N> visited)
    {
        visited.append(currentVertex);
        N[] connections = getConnectedNodes(currentVertex);
        for (N connection : connections)
        {
            if (!visited.contains(connection))
            {
                depthFirstTraversal(connection, visited);
            }
        }
        return visited;
    }

    @Override
    public LinkedList<N> dfs(N start, N target)
    {
        return sublistUntilFound(depthFirstTraversal(start, new LinkedList<>()), target);
    }

    @Override
    public LinkedList<N> breadthFirstTraversal(N start)
    {
        LinkedList<N> visited = new LinkedList<>();
        CircularQueue<N> nodeQueue = new CircularQueue<>(adjacencyList.length());
        nodeQueue.add(start);
        visited.append(start);
        while (!nodeQueue.isEmpty())
        {
            N currentNode = nodeQueue.remove();
            N[] connections = getConnectedNodes(currentNode);
            for (N connection : connections)
            {
                if (!visited.contains(connection))
                {
                    nodeQueue.add(connection);
                    visited.append(connection);
                }
            }
        }
        return visited;
    }

    @Override
    public LinkedList<N> bfs(N start, N target)
    {
        return sublistUntilFound(breadthFirstTraversal(start), target);
    }

    private LinkedList<N> sublistUntilFound(LinkedList<N> traversal, N target)
    {
        LinkedList<N> finalList = new LinkedList<>();
        for (N item : traversal)
        {
            finalList.append(item);
            if (item.equals(target))
            {
                return finalList;
            }
        }
        throw new IllegalArgumentException("Target not found!");
    }

    @Override
    public String path(N start, N target)
    {
        return dijkstra(start, target).getKey();
    }

    @Override
    public int pathLength(N start, N target)
    {
        return dijkstra(start, target).getValue();
    }

    private KeyValuePair<String, Integer> dijkstra(N start, N target)
    {
        if (!(adjacencyList.contains(start) && adjacencyList.contains(target)))
        {
            throw new IllegalArgumentException("Either the start node or the end node does not exist!");
        }
        LinkedList<N> nodesVisited = new LinkedList<>();
        N[] nodes = adjacencyList.arrayOfKeys();
        PriorityQueue<N> pq = new PriorityQueue<>();
        for (N node : nodes)
        {//Initialises priority queue with infinity for all distances except start node.
            if (node.equals(start))
            {
                pq.add(node, 0);
            }
            else
            {
                pq.add(node, Integer.MAX_VALUE);
            }
        }
        HashTable<N, N> parentInShortestPath = new HashTable<>(nodes.length);
        int minDistanceUntilNode;
        while (!pq.isEmpty())
        {
            KeyValuePair<N, Integer> currentNodeAndDistance = pq.remove(); //Stored as key-value pair, with node name and distance.
            N currentNode = currentNodeAndDistance.getKey();
            nodesVisited.append(currentNode);
            //Assume distance at any given item removed from the priority queue is the optimal distance to that node.
            minDistanceUntilNode = currentNodeAndDistance.getValue();
            if (currentNode.equals(target))
            {//Has reached target, so can return distance to current node, and trace back through the path.
                String path = "";
                path = currentNode + path;
                while (parentInShortestPath.contains(currentNode))
                {
                    currentNode = parentInShortestPath.item(currentNode);
                    path = currentNode.toString() + ", " + path;
                }
                return new KeyValuePair<>(path, minDistanceUntilNode);
            }
            for (N connection : getConnectedNodes(currentNode))
            {//Iterates through connections of current node
                if (!nodesVisited.contains(connection))
                {//Connection not visited yet.
                    Integer connectionWeight = getConnectionWeight(currentNode, connection); //Weight of edge between the current node and the connection being tested.
                    int newDistance = minDistanceUntilNode + connectionWeight;
                    if (newDistance < pq.getPriority(connection))
                    {//New minimum distance found, so value updated in priority queue, and node and parent logged in abstractdatatypes.hashtable.
                        if (parentInShortestPath.contains(connection))
                        {
                            parentInShortestPath.changeValue(connection, currentNode);
                        }
                        else
                        {
                            parentInShortestPath.add(connection, currentNode);
                        }
                        pq.updatePriority(connection, newDistance);
                    }
                }
            }
        }
        //If this point is reached, the target has not been found.
        throw new IllegalArgumentException("Target not found!");
    }

    public String toString()
    {
        StringBuilder output = new StringBuilder("GRAPH = {");
        for (int i = 0; i < adjacencyList.length(); i++)
        {
            output.append("\n\t")
                    .append(adjacencyList.getKeyAtIndex(i)).append(":")
                    .append(adjacencyList.getValueAtIndex(i)).append(",");
        }
        return output.deleteCharAt(output.length() - 1).append("\n}").toString();
    }

    public static void main(String[] args)
    {
        AdjacencyListHashTable<String> h1 = new AdjacencyListHashTable<>(
                false, "A", "B", "C", "D", "E"
        );

        h1.add("A", "B", 7);
        h1.add("A", "D", 3);

        h1.add("B", "C", 3);
        h1.add("B", "D", 2);

        h1.add("C", "D", 4);
        h1.add("C", "E", 1);

        h1.add("D", "E", 7);


        System.out.println(h1);
        System.out.println(h1.depthFirstTraversal("A"));
        System.out.println(h1.breadthFirstTraversal("A"));
        System.out.println(h1.bfs("A", "E"));
        System.out.println(h1.dfs("A", "E"));
        System.out.println(h1.path("A", "E"));

        AdjacencyListHashTable<String> h = new AdjacencyListHashTable<>(
                false, "A", "B", "C", "D", "E", "F", "G", "H", "I", "J");

        h.add("A", "B", 4);
        h.add("A", "C", 5);

        h.add("B", "D", 3);
        h.add("B", "E", 12);

        h.add("C", "D", 1);
        h.add("C", "F", 11);

        h.add("D", "E", 9);
        h.add("D", "F", 4);
        h.add("D", "G", 10);


        h.add("E", "G", 6);
        h.add("E", "H", 10);

        h.add("F", "G", 5);
        h.add("F", "I", 11);

        h.add("G", "H", 3);
        h.add("G", "I", 5);
        h.add("G", "J", 15);

        h.add("H", "J", 14);

        h.add("I", "J", 8);

        System.out.println(h.path("A", "J")+"\t"+h.pathLength("A", "J"));
        AdjacencyListHashTable<String> test = parseGraphFromFile("/media/anuraag/Anuraag 12Z/A-Level (Year 12-Year 13)/Computer Science/Mr Waterman/Data Structures/Projects/AbstractDataTypes/src/abstractdatatypes/adjacencylist/GraphInput.txt");
        System.out.println(test);
        System.out.println(test.path("A", "G"));
        System.out.println(test.pathLength("A", "G"));
    }

    public static AdjacencyListHashTable<String> parseGraphFromFile(String path)
    {
        try
        {
            Scanner input = new Scanner(new File(path));
            String[] nodes = input.nextLine().split(" ");
            boolean isDirected = input.nextBoolean();
            AdjacencyListHashTable<String> graph = new AdjacencyListHashTable<>(isDirected, nodes);
            while (input.hasNextLine())
            {
                String source = input.next();
                String destination = input.next();
                int weight = Integer.parseInt(input.next());
                graph.add(source, destination, weight);
            }
            return graph;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }
}
