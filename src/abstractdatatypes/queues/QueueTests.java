package abstractdatatypes.queues;

import abstractdatatypes.queues.circularqueue.CircularQueue;
import abstractdatatypes.queues.linearqueue.LinearQueue;
import abstractdatatypes.queues.priorityqueue.PriorityQueue;

public class QueueTests
{
    public static void main(String[] args)
    {
//        linearQueueTest();
//        circularQueueTest1();
//        circularQueueTest2();
        priorityQueueTest();
    }

    static void linearQueueTest()
    {
        System.out.println("-----Linear abstractdatatypes.queues.Queue Test-----");
        LinearQueue linearQueue = new LinearQueue(4);

        //Test 1: Check for empty queue
        System.out.println("Test 1: isEmpty = "+linearQueue.isEmpty());

        //Test 2: Add element
        linearQueue.add(1);
        System.out.println("Test 2: Added 1 to queue. "+linearQueue);

        //Test 3: Add element
        linearQueue.add(2);
        System.out.println("Test 3: Added 2 to queue. "+linearQueue);

        //Test 4: Check for full queue
        System.out.println("Test 4: isFull = "+linearQueue.isFull());

        //Test 5: Add element
        linearQueue.add(3);
        System.out.println("Test 5: Added 3 to queue. "+linearQueue);

        //Test 6: Remove element
        System.out.println("Test 6: Removed item: "+linearQueue.remove()+". "+linearQueue);

        //Test 7: Add element
        linearQueue.add(4);
        System.out.println("Test 7: Added 4 to queue. "+linearQueue);

        //Test 8: Check for full queue
        System.out.println("Test 8: isFull = "+linearQueue.isFull());

        //Test 9: Try to add element (throws error since queue is already full)
        try
        {
            linearQueue.add(5);
        }
        catch(Exception e)
        {
            System.out.println("Test 9: Throws IllegalArgumentException: "+e.getMessage());
        }
    }

    private static void circularQueueTest1()
    {
        System.out.println("-----Circular abstractdatatypes.queues.Queue Test 1-----");
        CircularQueue<Integer> circularQueue = new CircularQueue<>(4);

        //Test 1: Check for empty queue
        System.out.println("Test 1: isEmpty = "+circularQueue.isEmpty());

        //Test 2: Add element
        circularQueue.add(1);
        System.out.println("Test 2: Added 1 to queue. "+circularQueue);

        //Test 3: Add element
        circularQueue.add(2);
        System.out.println("Test 3: Added 2 to queue. "+circularQueue);

        //Test 4: Check for full queue
        System.out.println("Test 4: isFull = "+circularQueue.isFull());

        //Test 5: Add element
        circularQueue.add(3);
        System.out.println("Test 5: Added 3 to queue. "+circularQueue);

        //Test 6: Remove element
        System.out.println("Test 6: Removed item: "+circularQueue.remove()+". "+circularQueue);

        //Test 7: Add element
        circularQueue.add(4);
        System.out.println("Test 7: Added 4 to queue. "+circularQueue);

        //Test 8: Check for full queue
        System.out.println("Test 8: isFull = "+circularQueue.isFull());

        //Test 9: Add element
        circularQueue.add(5);
        System.out.println("Test 9: Added 5 to queue. "+circularQueue);

        //Check for full queue
        System.out.println("Test 10: isFull = "+circularQueue.isFull());
        System.out.println("\n\n");
    }
    private static void circularQueueTest2()
    {
        System.out.println("-----Circular abstractdatatypes.queues.Queue Test 2-----");
        CircularQueue<Integer> circularQueue = new CircularQueue<>(4);

        //Test 1: Add element
        circularQueue.add(1);
        System.out.println("Test 1: Added element 1 to queue. "+circularQueue);

        //Test 2: Add element
        circularQueue.add(2);
        System.out.println("Test 2: Added element 2 to queue. "+circularQueue);

        //Test 3: Add element
        circularQueue.add(3);
        System.out.println("Test 3: Added element 3 to queue. "+circularQueue);

        //Test 4: Remove element
        System.out.println("Test 4: Removed item: "+circularQueue.remove()+". "+circularQueue);

        //Test 5: Remove element
        System.out.println("Test 5: Removed item: "+circularQueue.remove()+". "+circularQueue);

        //Test 6: Add element
        circularQueue.add(4);
        System.out.println("Test 6: Added element 4 to queue. "+circularQueue);

        //Test 7: Add element
        circularQueue.add(5);
        System.out.println("Test 7: Added element 5 to queue. "+circularQueue);

        //Test 8: Check for full queue
        System.out.println("Test 8: isFull = "+circularQueue.isFull());

        //Test 9: Add element
        circularQueue.add(6);
        System.out.println("Test 9: Added element 6 to queue. "+circularQueue);

        //Test 10: Check for full queue
        System.out.println("Test 10: isFull = "+circularQueue.isFull());

        //Test 11: Remove element
        System.out.println("Test 11: Removed item: "+circularQueue.remove()+". "+circularQueue);

        //Test 12: Remove element
        System.out.println("Test 12: Removed item: "+circularQueue.remove()+". "+circularQueue);

        //Test 13: Remove element
        System.out.println("Test 13: Removed item: "+circularQueue.remove()+". "+circularQueue);

        //Test 14: Remove element
        System.out.println("Test 14: Removed item: "+circularQueue.remove()+". "+circularQueue);

        //Test 15: Check for empty queue
        System.out.printf("Test 15: isEmpty = "+circularQueue.isEmpty());
        System.out.println("\n\n");
    }

    private static void priorityQueueTest()
    {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.add("B", Integer.MAX_VALUE);
        priorityQueue.add("C", Integer.MAX_VALUE);
        priorityQueue.add("D", Integer.MAX_VALUE);
        priorityQueue.add("E", Integer.MAX_VALUE);
        priorityQueue.remove("B");
        priorityQueue.add("B", 7);
        priorityQueue.remove("D");
        priorityQueue.add("D", 3);
        priorityQueue.remove();
        priorityQueue.remove("B");
        priorityQueue.add("B", 5);
        priorityQueue.remove("C");
        priorityQueue.add("C", 7);
        priorityQueue.remove("E");
        priorityQueue.add("E", 10);

        System.out.println(priorityQueue);

    }
}