package hashtable;

public class HashTableNode<K, V>
{
    private K key;
    private V value;
    private boolean isDeleted;

    public HashTableNode(K key, V value)
    {
        this.key = key;
        this.value = value;
        this.isDeleted = false;
    }

    public K getKey()
    {
        return key;
    }

    public V getValue()
    {
        return value;
    }

    public void delete()
    {
        isDeleted = true;
    }

    public boolean isDeleted()
    {
        return isDeleted;
    }

    public String toString()
    {
        String output = key.toString();
        output += ":";
        output += value.toString();
        return output;
    }
}
