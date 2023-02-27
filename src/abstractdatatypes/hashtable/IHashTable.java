package abstractdatatypes.hashtable;

public interface IHashTable<K, V>
{
    boolean isEmpty();

    int length();

    default int midSquares(int key, int maxSize)
    {
        long square = (long) key * key; //Square key.
        int powerOfTen = (int) Math.ceil((Math.floor(Math.log10(square)) + 1) / 2.0) + 1; //Find the place at which to cut off the square.
        square = square % (long) Math.pow(10, powerOfTen); //Mod by power of 10 to isolate everything after required digits.
        square = square / (long) Math.pow(10, powerOfTen - 2); //Floor div by power of 10 two less than before, to isolate only middle two digits.
        return (int) square % maxSize; //Mod by maxSize to return hashTable location.
    }

    int generateHash(K key);

    void add(K key, V value);

    V item(K key);

    void delete(K key);

    boolean contains(K key);
    
    void changeValue(K key, V newValue);

    String toString();

    V[] arrayOfValues();

    K[] arrayOfKeys();
}
