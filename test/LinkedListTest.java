/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Assert;
import org.junit.Test;
import abstractdatatypes.linkedlist.LinkedList;

import static org.junit.Assert.*;

/**
 *
 * @author water
 */
public class LinkedListTest
{
    @Test
    public void test1isEmpty()
    {
        System.out.println("Test 1: isEmpty(), {}");
        LinkedList<String> ll = new LinkedList<>();
        assertTrue("isEmpty(): True, {}", ll.isEmpty());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test2pop()
    {
        System.out.println("Test 2: pop(), {}");
        LinkedList<String> ll = new LinkedList<>();
        ll.pop();
    }

    @Test
    public void test3length()
    {
        System.out.println("Test 3: length(), {}");
        LinkedList<String> ll = new LinkedList<>();
        assertEquals("Test 3: length(), {}", 0, ll.length());
    }

    @Test
    public void test4append1()
    {
        System.out.println("Test 4: append 1: {1}");
        LinkedList<String> ll = new LinkedList<>();
        ll.append("1");
        Assert.assertArrayEquals("Test 4: append 1: {1}", new String[]{"1"}, ll.asArray());
    }

    @Test
    public void test5length()
    {
        System.out.println("Test 5: length() , {1}");
        LinkedList<String> ll = new LinkedList<>();
        ll.append("1");
        assertEquals("Test 5: length(), {1}", 1, ll.length());
    }

    @Test
    public void test6append4()
    {
        System.out.println("Test 6: append 4: {1, 4}");
        LinkedList<String> ll = new LinkedList<>();
        ll.append("1");
        ll.append("4");
        Assert.assertArrayEquals("Test 6: append 4: {1, 4}", new String[]{"1","4"}, ll.asArray());
    }

    @Test
    public void test7append5()
    {
        System.out.println("Test 7: append 5: {1, 4, 5}");
        LinkedList<String> ll = new LinkedList<>();
        ll.append("1");
        ll.append("4");
        ll.append("5");
        Assert.assertArrayEquals("Test 7: append 5: {1, 4, 5}", new String[]{"1","4", "5"}, ll.asArray());
    }

    @Test
    public void test8index4()
    {
        System.out.println("Test 8: index 4 : {1, 4, 5}");
        LinkedList<String> ll = new LinkedList<>();
        ll.append("1");
        ll.append("4");
        ll.append("5");
        assertEquals("Test 8: index 4: {1, 4, 5}", 1, ll.index("4"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test9index6()
    {
        System.out.println("Test 9: index 6 : {1, 4, 5}");
        LinkedList<String> ll = new LinkedList<>();
        ll.append("1");
        ll.append("4");
        ll.append("5");
        assertTrue("Test 9: index 6: {1, 4, 5}", ll.index("6") >= 0);
    }

    @Test
    public void test10insert31()
    {
        System.out.println("Test 10: insert (3, 1) : {1, 3, 4, 5}");
        LinkedList<String> ll = new LinkedList<>();
        ll.append("1");
        ll.append("4");
        ll.append("5");
        ll.insert("3",1);
        Assert.assertArrayEquals("Test 10: insert (1,3) : {1, 3, 4, 5}", new String[]{"1","3", "4", "5"}, ll.asArray());
    }

    @Test
    public void test11append5()
    {
        System.out.println("Test 11: append 5 : {1, 3, 4, 5, 5}");
        LinkedList<String> ll = new LinkedList<>();
        ll.append("1");
        ll.append("4");
        ll.append("5");
        ll.insert("3",1);
        ll.append("5");
        Assert.assertArrayEquals("Test 11: append 5 : {1, 3, 4, 5, 5}", new String[]{"1","3", "4", "5", "5"}, ll.asArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test12insert35()
    {
        System.out.println("Test 12: insert (3, 5) : {1, 3, 4, 5, 5}");
        LinkedList<String> ll = new LinkedList<>();
        ll.append("1");
        ll.append("4");
        ll.append("5");
        ll.insert("3",1);
        ll.append("5");
        ll.insert("3", 5);
    }

    @Test
    public void test13insert35()
    {
        System.out.println("Test 13: insert (2, 0) : {2, 1, 3, 4, 5, 5}");
        LinkedList<String> ll = new LinkedList<>();
        ll.append("1");
        ll.append("4");
        ll.append("5");
        ll.insert("3",1);
        ll.append("5");
        ll.insert("2", 0);
        Assert.assertArrayEquals("Test 13: insert (2, 0) : {2, 1, 3, 4, 5, 5}", new String[]{"2", "1","3", "4", "5", "5"}, ll.asArray());
    }

    @Test
    public void test14remove5()
    {
        System.out.println("Test 14: remove 5 : {2, 1, 3, 4, 5}");
        LinkedList<String> ll = new LinkedList<>();
        ll.append("1");
        ll.append("4");
        ll.append("5");
        ll.insert("3",1);
        ll.append("5");
        ll.insert("2", 0);
        ll.remove("5");
        Assert.assertArrayEquals("Test 14: remove 5 : {2, 1, 3, 4, 5}", new String[]{"2", "1","3", "4", "5"}, ll.asArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test15remove6()
    {
        System.out.println("Test 15: remove 6 : {2, 1, 3, 4, 5}");
        LinkedList<String> ll = new LinkedList<>();
        ll.append("1");
        ll.append("4");
        ll.append("5");
        ll.insert("3",1);
        ll.append("5");
        ll.insert("2", 0);
        ll.remove("6");
    }

    @Test
    public void test16pop2()
    {
        System.out.println("Test 15: pop 2 : {2, 1, 3, 4, 5}");
        LinkedList<String> ll = new LinkedList<>();
        ll.append("1");
        ll.append("4");
        ll.append("5");
        ll.insert("3",1);
        ll.append("5");
        ll.insert("2", 0);
        ll.remove("5");
        ll.pop(2);
        Assert.assertArrayEquals("Test 15: pop 2 : {2, 1, 3, 4, 5}", new String[]{"2", "1", "4", "5"}, ll.asArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test17pop4()
    {
        System.out.println("Test 17: pop 4 : {2, 1, 4, 5}");
        LinkedList<String> ll = new LinkedList<>();
        ll.append("1");
        ll.append("4");
        ll.append("5");
        ll.insert("3",1);
        ll.append("5");
        ll.insert("2", 0);
        ll.remove("5");
        ll.pop(2);
        ll.pop(4);
        Assert.assertArrayEquals("Test 17: pop 4 : {2, 1, 4, 5}", new String[]{"2", "1", "4", "5"}, ll.asArray());
    }

    @Test
    public void test18isEmpty()
    {
        System.out.println("Test 18: isEmpty : {2, 1, 4, 5}");
        LinkedList<String> ll = new LinkedList<>();
        ll.append("1");
        ll.append("4");
        ll.append("5");
        ll.insert("3",1);
        ll.append("5");
        ll.insert("2", 0);
        ll.remove("5");
        ll.pop(2);
        assertFalse("Test 18: isEmpty : {2, 1, 4, 5}", ll.isEmpty());
    }

    @Test
    public void test19search4()
    {
        System.out.println("Test 19 search 4 : {2, 1, 4, 5}");
        LinkedList<String> ll = new LinkedList<>();
        ll.append("1");
        ll.append("4");
        ll.append("5");
        ll.insert("3",1);
        ll.append("5");
        ll.insert("2", 0);
        ll.remove("5");
        ll.pop(2);
        assertTrue("Test 19: search 4 : {2, 1, 4, 5}", ll.search("4"));
    }

    @Test
    public void test20search3()
    {
        System.out.println("Test 20: search 3 : {2, 1, 4, 5}");
        LinkedList<String> ll = new LinkedList<>();
        ll.append("1");
        ll.append("4");
        ll.append("5");
        ll.insert("3",1);
        ll.append("5");
        ll.insert("2", 0);
        ll.remove("5");
        ll.pop(2);
        assertFalse("Test 20: search 3 : {2, 1, 4, 5}", ll.search("3"));
    }

    @Test
    public void test21pop()
    {
        System.out.println("Test 21: pop : {2, 1, 4, 5}");
        LinkedList<String> ll = new LinkedList<>();
        ll.append("1");
        ll.append("4");
        ll.append("5");
        ll.insert("3",1);
        ll.append("5");
        ll.insert("2", 0);
        ll.remove("5");
        ll.pop(2);
        ll.pop();
        Assert.assertArrayEquals("Test 20: pop : {2, 1, 4, 5}", new String[]{"1", "4", "5"}, ll.asArray());
    }

    @Test
    public void test22length()
    {
        System.out.println("Test 22: length : {1, 4, 5}");
        LinkedList<String> ll = new LinkedList<>();
        ll.append("1");
        ll.append("4");
        ll.append("5");
        ll.insert("3",1);
        ll.append("5");
        ll.insert("2", 0);
        ll.remove("5");
        ll.pop(2);
        ll.pop();
        assertEquals("Test 22: length : {1, 4, 5}", 3, ll.length());
    }
}
