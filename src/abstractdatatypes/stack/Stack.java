package abstractdatatypes.stack;

public class Stack<T>
{
    public static class Node<T>
    {
        private T data;

        public Node(T data)
        {
            this.data = data;
        }

        public T getData()
        {
            return data;
        }
    }

    Node<T>[] stack;
    int top = -1;
    int maxSize;

    public Stack(int maxSize) throws IllegalArgumentException
    {
        if (maxSize > 0)
        {
            this.maxSize = maxSize;
            stack = new Node[maxSize];
        }
        else
        {
            throw new IllegalArgumentException("MaxSize must be positive!");
        }
    }

    public void push(T item) throws IllegalStateException
    {
        if (!isFull())
        {
            top++;
            stack[top] = new Node<>(item);
        }
        else
        {
            throw new IllegalStateException("The abstractdatatypes.stack is already full!");
        }
    }

    public T pop() throws IllegalStateException
    {
        if (!isEmpty())
        {
            top--;
            return stack[top + 1].getData();
        }
        else
        {
            throw new IllegalStateException("abstractdatatypes.stack.Stack is already empty!");
        }
    }

    public T peek() throws IllegalStateException
    {
        if (!isEmpty())
        {
            return stack[top].getData();
        }
        else
        {
            throw new IllegalStateException("Cannot peek into an empty abstractdatatypes.stack!");
        }
    }

    public boolean isEmpty()
    {
        return top == -1;
    }

    public boolean isFull()
    {
        return top + 1 == maxSize;
    }

    public String toString()
    {
        StringBuilder output = new StringBuilder();
        output.append("[");
        if (!isEmpty())
        {
            for (int i = 0; i <= top; i++)
            {
                output.append(stack[i].getData()).append(", ");
            }
            output.delete(output.length() - 2, output.length());
        }
        output.append("]");
        return output.toString();
    }

    public static void main(String[] args)
    {
        textbookStackTest();
        worksheetStackTest();
    }

    static void textbookStackTest()
    {
        Stack<String> stack = new Stack<>(3);

        System.out.println(stack.isEmpty());
        System.out.println(stack);

        stack.push("Blue");
        System.out.println(stack);

        stack.push("Red");
        System.out.println(stack);

        stack.push("Green");
        System.out.println(stack);

        System.out.println(stack.isEmpty());

        System.out.println(stack.peek());

        System.out.println(stack.pop());
        System.out.println(stack);
    }

    static void worksheetStackTest()
    {
        System.out.println("\n------------------------------\n");
        Stack<Integer> stack = new Stack<>(3);

        System.out.println(stack.isEmpty());

        stack.push(1);
        System.out.println(stack);

        stack.push(2);
        System.out.println(stack);

        System.out.println(stack.peek());

        stack.push(3);
        System.out.println(stack);

        System.out.println(stack.isFull());

        System.out.println(stack.pop());
        System.out.println(stack);
    }
}
