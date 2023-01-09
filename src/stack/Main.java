package stack;

public class Main
{
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