package adjacencylist;

import LinkedList.LinkedList;
import hashtable.HashTable;
import hashtable.KeyValuePair;
import queues.circularqueue.CircularQueue;
import queues.priorityqueue.PriorityQueue;

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
            throw new IllegalArgumentException("Node "+source+" not found!");
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

    public W connectionWeight(N origin, N destination)
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
        LinkedList<N> visited =new LinkedList<N>();
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

    public LinkedList<N> dijkstra(N start, N target)
    {
        LinkedList<N> nodesVisited = new LinkedList<N>();
        N[] keys = adjacencyList.arrayOfKeys();
        PriorityQueue<N> pq = new PriorityQueue<>();
        for (N key : keys)
        {//Initialises priority queue with infinity for all distances except start node.
            if (!key.equals(start))
            {
                pq.add(key, Integer.MAX_VALUE);
            }
            else
            {
                pq.add(key, 0);
            }
        }
        int totalDistance = 0;
        System.out.println(pq);
        while (!pq.isEmpty())
        {
            KeyValuePair<N, Integer> nextItem = pq.remove();
            N currentNode = nextItem.getKey();
            nodesVisited.append(currentNode);
            int currentDistance = nextItem.getValue();
            totalDistance = currentDistance;
            N[] neighbours = getConnectedNodes(currentNode);
            for (N neighbour : neighbours)
            {
                if (!nodesVisited.contains(neighbour))
                {
                    W connectionWeight = connectionWeight(currentNode, neighbour);
                    if (!(connectionWeight instanceof Integer))
                    {
                        throw new IllegalArgumentException("Dijkstra only works if weight type is Integer!");
                    }
                    int newDistance = currentDistance + (Integer) connectionWeight;
                    if (newDistance < pq.getPriority(neighbour))
                    {
                        pq.remove(neighbour);
                        pq.add(neighbour, newDistance);
                    }
                }
            }
        }
        System.out.println(totalDistance);
        return nodesVisited;
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
        AdjacencyListHashTable<String, Integer> h1 = new AdjacencyListHashTable<>(
                new String[]{"A", "B", "C", "D", "E"},
                false);

        h1.add("A", "B", 7);
        h1.add("A", "D", 3);

        h1.add("B", "C", 3);
        h1.add("B", "D", 2);

        h1.add("C", "D", 4);
        h1.add("C", "E", 1);

        h1.add("D", "E", 7);


        System.out.println(h1);
        System.out.println(h1.dfs("A"));
        System.out.println(h1.bfs("A"));
        System.out.println(h1.findDFS("A", "F"));
        System.out.println(h1.findBFS("A", "F"));
        System.out.println(h1.dijkstra("A", "E"));
    }
}
