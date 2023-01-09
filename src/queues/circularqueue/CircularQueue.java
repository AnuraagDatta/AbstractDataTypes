package queues.circularqueue;

import queues.IQueue;

public class
CircularQueue<T> implements IQueue<T>
{

    private CircularQueueNode<T>[] queue;
    private int maxSize;
    //Front and back set to -1 when queue is empty.
    private int front = -1;
    private int back = -1;

    public CircularQueue(int maxSize)
    {
        if (maxSize <= 0)
        {
            throw new IllegalArgumentException("maxSize must be positive!");
        }
        this.maxSize = maxSize;
        queue = new CircularQueueNode[maxSize];
    }

    @Override
    public void add(T item)
    {
        if (isFull())
        {
            throw new IllegalStateException("Queue is already full!");
        }
        back = (back + 1) % maxSize;
        front = (front == -1) ? 0 : front; //Updates front to 0 so the program knows the queue is not empty.
        queue[back] = new CircularQueueNode<>(item);
    }

    @Override
    public T remove()
    {
        if (isEmpty())
        {
            throw new IllegalStateException("queues.Queue is already empty!");
        }
        T item = (T)queue[front].getItem();
        if (front == back)
        {
            //At this point the queue will be empty after the item is removed, so the pointers are set to reflect that
            front = -1;
            back = -1;
        }
        else
        {
            front = (front + 1) % maxSize;
        }
        return item;
    }

    @Override
    public boolean isEmpty()
    {
        return (front == -1) && (back == -1); //Checks for dummy values.
    }

    @Override
    public boolean isFull()
    {
        return (back + 1 % maxSize) == front; //Checks if the queue will overlap if another item is added.
    }

    public String toString() //Prints items in queue, as well as some key variables.
    {
        StringBuilder output = new StringBuilder();
        output.append("Queue: [");
        if (!isEmpty())
        {
            int i = front;
            output.append(queue[i].getItem()).append(", ");
            while (i != back)
            {
                i = (i + 1) % maxSize;
                output.append(queue[i].getItem()).append(", ");
            }
            output.delete(output.length() - 2, output.length());
        }
        output.append("]");
//        output.append("], maxSize=")
//                .append(maxSize)
//                .append(", front=")
//                .append(front)
//                .append(", back=")
//                .append(back);
        return output.toString();
    }
}
