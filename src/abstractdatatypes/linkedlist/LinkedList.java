package abstractdatatypes.linkedlist;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T>
{
    private Element<T> front;
    private int length = 0;

    @SafeVarargs
    public LinkedList(T... initialValues)
    {
        for (T value : initialValues)
        {
            append(value);
        }
    }

    public boolean isEmpty()
    {
        return front == null;
    }

    private Element<T> getElementBefore(int pos)
    { //Returns either the element before a position if pos > 0, or the element itself if pos == 0
        int index = 0;
        Element<T> current = front;
        while (index < pos-1)
        {
            current = current.getNext();
            index++;
        }
        return current;
    }

    public void append(T item)
    {
        length++;
        if (isEmpty())
        {
            front = new Element<>(item, null, null);
        }
        else
        {
            Element<T> current = front;
            while (current.getNext() != null)
            {
                current = current.getNext();
            }
            current.setNext(new Element<>(item, current, null));
        }
    }

    public T remove(T item)
    {
        return pop(index(item));
    }

    public int count(T item)
    {
        int count = 0;
        if (!isEmpty())
        {
            Element<T> current = front;
            while (current != null)
            {
                if (current.getData().equals(item))
                {
                    count++;
                }
                current = current.getNext();
            }
        }
        return count;
    }

    public boolean contains(T item)
    {
        return indexNoException(item) != -1;
    }

    public int length()
    {
        return length;
    }

    private int indexNoException(T item) //Returns -1 when not found, rather than throwing exception
    {
        int index = -1;
        if (!isEmpty())
        {
            index = 0;
            Element<T> current = front;
            while (!current.getData().equals(item) && current.getNext() != null)
            {
                current = current.getNext();
                index++;
            }
            if (!current.getData().equals(item))
            {
                index = -1;
            }
        }
        return index;
    }

    public int index(T item)
    {
        int index = indexNoException(item);
        if (index == -1)
        {
            throw new IllegalArgumentException("Item not found in list!");
        }
        return index;
    }

    public void insert(T item, int pos)
    {
        if (pos < 0 || pos > length())
        {
            throw new IndexOutOfBoundsException("Index must be between 0 and "+length()+"!");
        }

        if (pos == length)
        {
            append(item);
        }
        else if (pos == 0)
        {
            length++;
            Element<T> temp = front;
            front = new Element<>(item, null, temp);
            front.getNext().setPrevious(front);
        }
        else
        {
            length++;
            //Gets the element before the position to be inserted into.
            Element<T> current = getElementBefore(pos);
            //Sets the next item to be a new element with the provided data.
            current.setNext(new Element<>(item, current, current.getNext()));
            //Sets the previous of the item after the inserted item to be the inserted item.
            current.getNext().getNext().setPrevious(current.getNext());
        }
    }

    public T pop()
    {
        return pop(0);
    }

    public T pop(int pos)
    {
        if (pos < 0 || pos >= length())
        {
            throw new IndexOutOfBoundsException("Index must be between 0 and "+length()+"!");
        }
        if (isEmpty())
        {
            throw new UnsupportedOperationException("Cannot pop from an empty list!");
        }
        length--;
        if (pos == 0)
        {
            T item = front.getData();
            front = front.getNext();
            if (front != null)
            {
                front.setPrevious(null);
            }
            return item;
        }
        else
        {
            Element<T> current = getElementBefore(pos);
            //Data to be returned
            T item = current.getNext().getData();
            if (current.getNext().getNext() != null)
            {//Checks if item is not last in the list.
                //Links the item after the one removed to the one before.
                current.getNext().getNext().setPrevious(current);
            }
            //Links the item before the one removed to the one after.
            current.setNext(current.getNext().getNext());
            return item;
        }
    }

    public T get(int pos)
    {
        if (pos < 0)
        {
            throw new IllegalArgumentException("Position must be greater than 0!");
        }
        return (pos > 0) ? getElementBefore(pos).getNext().getData() : getElementBefore(pos).getData();
    }

    public boolean search(T item)
    {
        return indexNoException(item) != -1;
    }

    public String toString()
    {
        StringBuilder output = new StringBuilder();
        Element<T> current = front;
        output.append("[");
        while (current != null)
        {
            output.append(current.getData())
                    .append(", ");
//                    .append("(")
//                    .append("previous=")
//                    .append((current.getPrevious() != null)?current.getPrevious().getData():null)
//                    .append(", ")
//                    .append("next=")
//                    .append((current.getNext() != null)?current.getNext().getData():null)
//                    .append("), ");
            current = current.getNext();
        }
        if (front != null)
        {
            output.delete(output.length() - 2, output.length());
        }
        output.append("]");
        return output.toString();
    }

    public T[] asArray()
    {
        T[] array = (T[]) new Object[length()];
        int index = 0;
        Element<T> current = front;
        while (current != null)
        {
            array[index] = current.getData();
            current = current.getNext();
            index++;
        }
        return array;
    }

    public T[] toArray(T[] a)
    {
        T[] resizedArray = Arrays.copyOf(a, length);
        for (int i = 0; i < length; i++)
        {
            resizedArray[i] = get(i);
        }
        return resizedArray;
    }

    @Override
    public Iterator<T> iterator()
    {
        return new LinkedListIterator<>(front);
    }

    private static class LinkedListIterator<T> implements Iterator<T>
    {
        private Element<T> current;

        public LinkedListIterator(Element<T> front)
        {
            current = front;
        }

        public boolean hasNext()
        {
            return current != null;
        }

        public T next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            T result = current.getData();
            current = current.getNext();
            return result;
        }
    }

//    public static void main(String[] args)
//    {
//        LinkedList<Integer> a = new LinkedList<Integer>();
//        a.append(1);
//        a.append(2);
//        for (Integer b : a)
//        {
//            System.out.println(b);
//        }
//    }
}
