package adjacencylist;
public class Main
{
    public static void main(String[] args)
    {
        AdjacencyListHashTable<String, Integer> h1 = new AdjacencyListHashTable<>(
                new String[]{"A", "B", "C", "D", "E", "F", "G"},
                false);

        h1.add("A", "B", 5);
        h1.add("A", "D", 8);
        h1.add("A", "E", 4);

        h1.add("B", "C", 4);

        h1.add("C", "D", 5);
        h1.add("C", "G", 2);

        h1.add("D", "E", 7);
        h1.add("D", "F", 6);

        System.out.println(h1);
    }
}