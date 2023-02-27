package abstractdatatypes.queues;

public interface IQueue<T>
{
    void add(T item);
    T remove();
    boolean isEmpty();
    boolean isFull();
}
