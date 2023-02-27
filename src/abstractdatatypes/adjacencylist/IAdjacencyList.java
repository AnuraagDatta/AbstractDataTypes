package abstractdatatypes.adjacencylist;

import abstractdatatypes.linkedlist.LinkedList;

public interface IAdjacencyList<N>
{
    void add(N source, N destination, Integer weight);

    Integer getConnectionWeight(N origin, N destination);

    LinkedList<N> depthFirstTraversal(N start);

    LinkedList<N> dfs(N start, N target);

    LinkedList<N> breadthFirstTraversal(N start);

    LinkedList<N> bfs(N start, N target);

    String path(N start, N target);

    int pathLength(N start, N target);
}
