package abstractdatatypes.queues.linearqueue;

public class LinearQueue
{
    int[] queue;
    int maxSize;
    int size = 0;
    int front = 0;
    int back = -1;

    public LinearQueue(int maxSize)
    {
        if (maxSize > 0)
        {
            this.maxSize = maxSize;
            queue = new int[maxSize];
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
            back++;
            queue[back] = item;
            size++;
        }
        else
        {
            throw new IllegalArgumentException("The queue is full!");
        }
    }

    public int remove()
    {
        if (!isEmpty())
        {
            front++;
            size--;
            return queue[front-1];
        }
        else
        {
            throw new IllegalArgumentException("The queue is empty!");
        }
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public boolean isFull()
    {
        return back + 1 == maxSize;
    }

    public String toString()
    {
        StringBuilder output = new StringBuilder();
        output.append("abstractdatatypes.queues.Queue: [");
        int i = front;
        output.append(queue[i]+", ");
        while (i != back)
        {
            i = (i + 1) % maxSize;
            output.append(queue[i]+", ");
        }
        if (!isEmpty())
        {
            output.delete(output.length()-2, output.length());
        }
//        output.append("]");
        output.append("], size="+size+", maxSize="+maxSize+", front="+front+", back="+back);
        return output.toString();
    }
}
