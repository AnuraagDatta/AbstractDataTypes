package adjacencylist;

import LinkedList.LinkedList;
import hashtable.HashTable;
import hashtable.KeyValuePair;
import queues.circularqueue.CircularQueue;
import queues.priorityqueue.PriorityQueue;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class AdjacencyListHashTable<N, W>
{
    private HashTable<N, HashTable<N, W>> adjacencyList;
    private boolean isDirected;

    public AdjacencyListHashTable(N[] nodes, boolean isDirected)
    {
        if (nodes.length == 0)
        {
            throw new IllegalArgumentException("MaxSize must be positive!");
        }
        adjacencyList = new HashTable<>(nodes.length);
        for (N node : nodes)
        {
            adjacencyList.add(node, new HashTable<>(nodes.length));
        }
        this.isDirected = isDirected;
    }

    private void addConnection(N source, N destination, W weight)
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

    public void add(N source, N destination, W weight)
    {
        addConnection(source, destination, weight);
        if (!isDirected)
        {
            addConnection(destination, source, weight);
        }
    }

    public W getConnectionWeight(N origin, N destination)
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

    public LinkedList<N> dfs(N start)
    {
        return dfs(start, new LinkedList<N>());
    }

    private LinkedList<N> dfs(N currentVertex, LinkedList<N> visited)
    {
        visited.append(currentVertex);
        N[] connections = getConnectedNodes(currentVertex);
        for (N connection : connections)
        {
            if (!visited.contains(connection))
            {
                dfs(connection, visited);
            }
        }
        return visited;
    }

    public LinkedList<N> findDFS(N start, N target)
    {
        LinkedList<N> traversal = dfs(start, new LinkedList<N>());
        return sublistUntilFound(traversal, target);
    }

    public LinkedList<N> bfs(N start)
    {
        LinkedList<N> visited = new LinkedList<N>();
        CircularQueue<N> nodeQueue = new CircularQueue<>(adjacencyList.getMaxSize());
        nodeQueue.add(start);
        visited.append(start);
        while (!nodeQueue.isEmpty())
        {
            N currentNode = nodeQueue.remove();
            for (N connection : getConnectedNodes(currentNode))
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

    public LinkedList<N> findBFS(N start, N target)
    {
        LinkedList<N> traversal = bfs(start);
        return sublistUntilFound(traversal, target);
    }

    private LinkedList<N> sublistUntilFound(LinkedList<N> traversal, N target)
    {
        LinkedList<N> finalList = new LinkedList<N>();
        for (int i = 0; i < traversal.length(); i++)
        {
            N item = traversal.get(i);
            finalList.append(item);
            if (item.equals(target))
            {
                return finalList;
            }
        }
        return null;
    }

    public String path(N start, N target)
    {
        return dijkstra(start, target).getKey();
    }

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
        LinkedList<N> nodesVisited = new LinkedList<N>();
        N[] nodes = adjacencyList.arrayOfKeys();
        PriorityQueue<N> pq = new PriorityQueue<>();
        for (N node : nodes)
        {//Initialises priority queue with infinity for all distances except start node.
            if (!node.equals(start))
            {
                pq.add(node, Integer.MAX_VALUE);
            }
            else
            {
                pq.add(node, 0);
            }
        }
        HashTable<N, N> nodesAndParents = new HashTable<>(nodes.length);
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
                while (nodesAndParents.contains(currentNode))
                {
                    currentNode = nodesAndParents.item(currentNode);
                    path = currentNode.toString() + ", " + path;
                }
                return new KeyValuePair<>(path, minDistanceUntilNode);
            }
            for (N connection : getConnectedNodes(currentNode))
            {//Iterates through connections of current node
                if (!nodesVisited.contains(connection))
                {//Connection not visited yet.
                    W connectionWeight = getConnectionWeight(currentNode, connection); //Weight of edge between the current node and the current connection being tested
                    if (!(connectionWeight instanceof Integer))
                    {//Can only add to int if connection type is also int.
                        throw new IllegalArgumentException("This implementation of dijkstra only works if weight type is Integer!");
                    }
                    int newDistance = minDistanceUntilNode + (Integer) connectionWeight;
                    if (newDistance < pq.getPriority(connection))
                    {//New minimum distance found, so value updated in priority queue.
                        if (nodesAndParents.contains(connection))
                        {
                            nodesAndParents.changeValue(connection, currentNode);
                        }
                        else
                        {
                            nodesAndParents.add(connection, currentNode);
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
//        AdjacencyListHashTable<String, Integer> h1 = new AdjacencyListHashTable<>(
//                new String[]{"A", "B", "C", "D", "E"},
//                false);
//
//        h1.add("A", "B", 7);
//        h1.add("A", "D", 3);
//
//        h1.add("B", "C", 3);
//        h1.add("B", "D", 2);
//
//        h1.add("C", "D", 4);
//        h1.add("C", "E", 1);
//
//        h1.add("D", "E", 7);
//
//
//        System.out.println(h1);
//        System.out.println(h1.dfs("A"));
//        System.out.println(h1.bfs("A"));
//        System.out.println(h1.findDFS("A", "F"));
//        System.out.println(h1.findBFS("A", "F"));
//        System.out.println(h1.dijkstraWithPath("A", "B"));

//        AdjacencyListHashTable<String, Integer> h = new AdjacencyListHashTable<>(
//                new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"},
//                false);

//        h.add("A", "B", 4);
//        h.add("A", "C", 5);

//        h.add("B", "D", 3);
//        h.add("B", "E", 12);

//        h.add("C", "D", 1);
//        h.add("C", "F", 11);

//        h.add("D", "E", 9);
//        h.add("D", "F", 4);
//        h.add("D", "G", 10);


//        h.add("E", "G", 6);
//        h.add("E", "H", 10);

//        h.add("F", "G", 5);
//        h.add("F", "I", 11);

//        h.add("G", "H", 3);
//        h.add("G", "I", 5);
//        h.add("G", "J", 15);

//        h.add("H", "J", 14);

//        h.add("I", "J", 8);

//        System.out.println(h.path("A", "D")+"\t"+h.pathLength("A", "D"));
        AdjacencyListHashTable<String, Integer> test = parseGraphFromFile("/media/anuraag/Anuraag 12Z/A-Level (Year 12-Year 13)/Computer Science/Mr Waterman/Data Structures/Projects/AbstractDataTypes/src/adjacencylist/GraphInput.txt");
        System.out.println(test.path("A", "G"));
        System.out.println(test.pathLength("A", "G"));
    }

    public static AdjacencyListHashTable<String, Integer> parseGraphFromFile(String path)
    {
        try
        {
            Scanner input = new Scanner(new File(path));
            String[] nodes = input.nextLine().split(" ");
            boolean isDirected = input.nextBoolean();
            AdjacencyListHashTable<String, Integer> graph = new AdjacencyListHashTable<>(nodes, isDirected);
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
