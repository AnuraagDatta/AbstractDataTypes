package queues.circularqueue;

public class CircularQueueNode<T>
{
    private T item;

    public CircularQueueNode(T item)
    {
        this.item = item;
    }

    public T getItem()
    {
        return item;
    }
}
