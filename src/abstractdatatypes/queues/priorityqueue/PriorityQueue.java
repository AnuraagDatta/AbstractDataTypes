package abstractdatatypes.queues.priorityqueue;

import abstractdatatypes.linkedlist.LinkedList;
import abstractdatatypes.hashtable.KeyValuePair;

public class PriorityQueue<E>
{
    LinkedList<KeyValuePair<E, Integer>> queue;
    int maxSize = Integer.MAX_VALUE;

    public PriorityQueue(int maxSize)
    {
        this();
        if (maxSize <= 0)
        {
            throw new IllegalArgumentException("maxSize must be positive!");
        }
        this.maxSize = maxSize;
    }

    public PriorityQueue()
    {
        queue = new LinkedList<>();
    }

    public void add(E element, int priority)
    {
        if (isFull())
        {
            throw new IllegalStateException("The queue is already full!");
        }
        KeyValuePair<E, Integer> itemToAdd = new KeyValuePair<>(element, priority);
        if (isEmpty())
        {
            queue.append(itemToAdd);
        }
        else
        {
            int i = 0;
            while (i < queue.length() && queue.get(i).getValue() <= priority)
            {
                i++;
            }
            queue.insert(itemToAdd, i);
        }
    }

    public KeyValuePair<E, Integer> remove()
    {
        if (isEmpty())
        {
            throw new IllegalStateException("The queue is already empty!");
        }
        return queue.pop();
    }

    public KeyValuePair<E, Integer> remove(E element)
    {
        if (isEmpty())
        {
            throw new IllegalStateException("The queue is already empty!");
        }
        for (int i = 0; i < queue.length(); i++)
        {
            KeyValuePair<E, Integer> item = queue.get(i);
            if (item.getKey().equals(element))
            {
                return queue.remove(item);
            }
        }
        throw new IllegalArgumentException("Element not found in queue!");
    }

    public Integer getPriority(E element)
    {
        for (int i = 0; i < queue.length(); i++)
        {
            KeyValuePair<E, Integer> item = queue.get(i);
            if (item.getKey().equals(element))
            {
                return item.getValue();
            }
        }
        throw new IllegalArgumentException("Element not found in queue!");
    }

    public void updatePriority(E element, Integer newPriority)
    {
        remove(element);
        add(element, newPriority);
    }

    public boolean isFull()
    {
        return queue.length() == maxSize;
    }

    public boolean isEmpty()
    {
        return queue.length() == 0;
    }

    public String toString()
    {
        return queue.toString();
    }
}
