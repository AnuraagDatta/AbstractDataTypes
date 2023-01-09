package hashtable;

public class HashTable<K, V>
{
    private HashTableNode<K, V>[] table;
    private int size;
    private int maxSize;

    public HashTable(int maxSize)
    {
        if (maxSize <= 0)
        {
            throw new IllegalArgumentException("MaxSize must be positive!");
        }
        this.maxSize = maxSize;
        table =  new HashTableNode[maxSize];
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public boolean isFull()
    {
        return size == maxSize;
    }

    public int length()
    {
        return size;
    }

    public int midSquares(int key)
    {
        long square = (long) key * key; //Square key.
        int powerOfTen = (int)Math.ceil((Math.floor(Math.log10(square)) + 1) / 2.0) + 1; //Find the place at which to cut off the square.
        square = square % (long) Math.pow(10, powerOfTen); //Mod by power of 10 to isolate everything after required digits.
        square = square / (long) Math.pow(10, powerOfTen - 2); //Floor div by power of 10 two less than before, to isolate only middle two digits.
        return (int) square % maxSize; //Mod by maxSize to return hashTable location.
    }

    public int generateHash(K key)
    {
        return midSquares(key.toString().hashCode());
    }

    private boolean correctNodeFound(int hash, K key)
    {
        return (!table[hash].isDeleted() && key.equals(table[hash].getKey()));
    }

    public void add(K key, V value)
    {
        if (isFull())
        {
            throw new UnsupportedOperationException("HashMap is full!");
        }
        int hash = generateHash(key);
        if (table[hash] == null || table[hash].isDeleted())
        {//Already empty so can be added straight away.
            table[hash] = new HashTableNode<K, V>(key, value);
            size++;
        }
        else
        {
            int initialHash = hash;
            do
            {//Advance until empty space found or one full loop completed.
                if (table[hash] != null && key.equals(table[hash].getKey()))
                {
                    throw new IllegalArgumentException("Key " + key + " already exists!");
                }
                hash = (hash + 1) % maxSize;
            }
            while (!(hash == initialHash || table[hash] == null || table[hash].isDeleted()));

            if (hash == initialHash)
            {
                throw new UnsupportedOperationException("No empty address!");
            }
            table[hash] = new HashTableNode<K, V>(key, value);
            size++;
        }
    }

    private HashTableNode<K, V> findMatch(K key)
    {
        int hash = generateHash(key);
        HashTableNode<K, V> item = null;
        if (table[hash] != null)
        {//Item at hash is not null so has existed
            if (correctNodeFound(hash, key))
            {
                item = table[hash];
            }
            else
            {
                int initialHash = hash;
                do
                {//Advance until key at given location matches input key, looping around until starting point reached again.
                    hash = (hash + 1) % maxSize;
                } while (!(table[hash] == null || hash == initialHash || correctNodeFound(hash, key)));
                if (table[hash] != null && correctNodeFound(hash, key))
                {//Match found.
                    item = table[hash];
                }
            }
        }
        return item;
    }

    public V item(K key)
    {
        HashTableNode<K, V> item = findMatch(key);
        if (item == null)
        {
            throw new IllegalArgumentException("Key "+key+" not found!");
        }
        return item.getValue();
    }

    public void delete(K key)
    {
        HashTableNode<K, V> item = findMatch(key);
        if (item == null)
        {
            throw new IllegalArgumentException("Key "+key+" not found!");
        }
        item.delete();
        size--;
    }

    public boolean contains(K key)
    {
        return findMatch(key) != null;
    }

    public String toString()
    {
        StringBuilder output = new StringBuilder("{");

        for (HashTableNode<K, V> item : table)
        {
            if (item != null && !item.isDeleted())
            {
                output.append(item);
            }
//            output.append(", ");
        }
        if (!isEmpty())
        {
            output.delete(output.length() - 2, output.length());
        }
        output.append("}");
        return output.toString();
    }

    public V[] asArray()
    {
        V[] array = (V[]) new Object[maxSize];

        for (int i = 0; i < maxSize; i++)
        {
            array[i] = ((table[i] != null && !table[i].isDeleted()) ? table[i].getValue() : null);
        }
        return array;
    }
}
