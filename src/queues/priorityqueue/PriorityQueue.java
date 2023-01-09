package queues.priorityqueue;

import LinkedList.LinkedList;

public class PriorityQueue<E>
{
    LinkedList<Integer> queue;
    int maxSize;

    public PriorityQueue(int maxSize)
    {
        if (maxSize > 0)
        {
            this.maxSize = maxSize;
            queue = new LinkedList<Integer>();
        }
        else
        {
            throw new IllegalArgumentException("maxSize must be positive!");
        }
    }

    public void add(int item)
    {
        if (!isFull())
        {
            if (isEmpty())
            {
                queue.append(item);
            }
            else
            {
                int i = 0;
                while (i < queue.length() && queue.get(i) <= item)
                {
                    i++;
                }
                if (i < queue.length())
                {
                    queue.insert(item, i);
                }
                else
                {
                    queue.append(i);
                }
            }
        }
        else
        {
            throw new IllegalStateException("The queue is already full!");
        }
    }

    public int remove()
    {
        if (!isEmpty())
        {
            int item = queue.get(0);
            queue.pop(0);
            return item;
        }
        else
        {
            throw new IllegalStateException("The queue is already empty!");
        }
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
