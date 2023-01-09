package queues.priorityqueue;
public class PriorityQueueNode<E>
{
    private int priority;
    private E element;

    public PriorityQueueNode(int priority, E element)
    {
        this.priority = priority;
        this.element = element;
    }

    public int getPriority()
    {
        return priority;
    }

    public E getElement()
    {
        return element;
    }
}
