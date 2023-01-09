package adjacencylist;

import hashtable.HashTable;

public class AdjacencyListHashTable<N, W>
{
    private HashTable<N, HashTable<N, W>> adjacencyList;
    private boolean isDirected;
    private N[] nodes;

    public AdjacencyListHashTable(N[] nodes, boolean isDirected)
    {
        if (nodes.length <= 0)
        {
            throw new IllegalArgumentException("MaxSize must be positive!");
        }
        this.nodes = nodes;
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

    public String toString()
    {
        StringBuilder output = new StringBuilder("GRAPH = {");
        for (int i = 0; i < adjacencyList.length(); i++)
        {
            output.append("\n\t")
                    .append(nodes[i]).append(":")
                    .append(adjacencyList.item(nodes[i])).append(",");
        }
        return output.deleteCharAt(output.length() - 1).append("\n}").toString();
    }
}
