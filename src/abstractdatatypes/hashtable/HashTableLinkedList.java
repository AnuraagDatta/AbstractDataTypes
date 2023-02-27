package abstractdatatypes.hashtable;

import abstractdatatypes.linkedlist.LinkedList;

public class HashTableLinkedList<K, V> implements IHashTable<K, V>
{
    private LinkedList<KeyValuePair<K, V>>[] table;
    private int size;
    private int itemCount = 0;

    public HashTableLinkedList(int size)
    {
        this.size = size;
        table = new LinkedList[size];
    }

    @Override
    public boolean isEmpty()
    {
        return itemCount == 0;
    }

    @Override
    public int length()
    {
        return itemCount;
    }

    @Override
    public int generateHash(K key)
    {
        return midSquares(key.hashCode(), size);
    }

    @Override
    public void add(K key, V value)
    {
        int hash = generateHash(key);
        if (table[hash] == null)
        {
            table[hash] = new LinkedList<>();
        }
        table[hash].append(new KeyValuePair<>(key, value));
        itemCount++;
    }

    private KeyValuePair<K, V> findMatch(K key)
    {
        int hash = generateHash(key);
        KeyValuePair<K, V> match = null;
        LinkedList<KeyValuePair<K, V>> pairsWithSameHash = table[hash];
        if (pairsWithSameHash != null)
        {
            for (KeyValuePair<K, V> keyValuePair : pairsWithSameHash)
            {
                if (keyValuePair.getKey().equals(key))
                {
                    match = keyValuePair;
                }
            }
        }
        return match;
    }

    @Override
    public V item(K key)
    {
        KeyValuePair<K, V> item = findMatch(key);
        if (item == null)
        {
            throw new IllegalArgumentException("Key " + key + " not found!");
        }
        return item.getValue();
    }

    @Override
    public void delete(K key)
    {
        int hash = generateHash(key);
        LinkedList<KeyValuePair<K, V>> pairsWithSameHash = table[hash];
        boolean deleted = false;
        int i = 0;
        while (!deleted && i < pairsWithSameHash.length())
        {
            KeyValuePair<K, V> keyValuePair = pairsWithSameHash.get(i);
            if (keyValuePair.getKey().equals(key))
            {
                pairsWithSameHash.remove(keyValuePair);
                deleted = true;
                itemCount--;
            }
            i++;
        }
        if (!deleted)
        {
            throw new IllegalArgumentException("Key " + key + " not found!");
        }
    }

    @Override
    public boolean contains(K key)
    {
        return findMatch(key) != null;
    }

    public void incrementMaxSize()
    {
        size++;
        //TODO: finish implementing increment max size method.
    }

    @Override
    public void changeValue(K key, V newValue)
    {
        delete(key);
        add(key, newValue);
    }

    public KeyValuePair<K, V>[] toArray()
    {
        KeyValuePair<K, V>[] array = new KeyValuePair[itemCount];
        int index = 0;
        for (LinkedList<KeyValuePair<K, V>> location : table)
        {
            if (location != null)
            {
                for (KeyValuePair<K, V> pair : location)
                {
                    array[index] = pair;
                    index++;
                }
            }
        }
        return array;
    }

    @Override
    public V[] arrayOfValues()
    {
        KeyValuePair<K, V>[] pairArray = toArray();
        V[] array = (V[]) new Object[pairArray.length];
        for (int i = 0; i < pairArray.length; i++)
        {
            array[i] = pairArray[i].getValue();
        }
        return array;
    }

    @Override
    public K[] arrayOfKeys()
    {
        KeyValuePair<K, V>[] pairArray = toArray();
        K[] array = (K[]) new Object[pairArray.length];
        for (int i = 0; i < pairArray.length; i++)
        {
            array[i] = pairArray[i].getKey();
        }
        return array;
    }

    public KeyValuePair<K, V> getPairAtIndex(int targetIndex)
    {
        int currentIndex = 0;
        for (LinkedList<KeyValuePair<K, V>> location : table)
        {
            if (location != null)
            {
                if (currentIndex + location.length() > targetIndex)
                {
                    for (KeyValuePair<K, V> pair : location)
                    {
                        if (currentIndex == targetIndex)
                        {
                            return pair;
                        }
                        currentIndex++;
                    }
                }
                currentIndex += location.length();
            }
        }
        throw new IllegalArgumentException("Not found!");
    }

    public K getKeyAtIndex(int targetIndex)
    {
        return getPairAtIndex(targetIndex).getKey();
    }

    public V getValueAtIndex(int targetIndex)
    {
        return getPairAtIndex(targetIndex).getValue();
    }

    public String toString()
    {
        StringBuilder output = new StringBuilder("{");
        for (LinkedList<KeyValuePair<K, V>> location : table)
        {
            if (location != null)
            {
                for (KeyValuePair<K, V> pair : location)
                {
                    output.append(pair).append(", ");
                }
            }
        }
        if (!isEmpty())
        {
            output.delete(output.length() - 2, output.length());
        }
        output.append("}");
        return output.toString();
    }
}
