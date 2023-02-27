/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import abstractdatatypes.hashtable.HashTable;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author dw
 */
public class HashJUnitTest
{
    @Test
    public void test1isEmpty() 
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        Assert.assertTrue("Test 1: isEmpty {,,,,,,,,,,}", h.isEmpty());
    }

    @Test
    public void test2add54Bill()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        Assert.assertArrayEquals("Test 2: add 54:Bill: {,,,,,,,,,,54:Bill}", new String[]{null,null,null,null,null,null,null,null,null,null,"Bill"}, h.arrayOfValues());
    }

    @Test
    public void test3add26Ben()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        Assert.assertArrayEquals("Test 3: add 26:Ben: {,,,,26:Ben,,,,,,54:Bill}", new String[]{null,null,null,null,"Ben",null,null,null,null,null,"Bill"}, h.arrayOfValues());
    }

    @Test
    public void test4add93Bob()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        Assert.assertArrayEquals("Test 4: add 93:Bob: {,,,,26:Ben,93:Bob,,,,,54:Bill}", new String[]{null,null,null,null,"Ben","Bob",null,null,null,null,"Bill"}, h.arrayOfValues());
    }

    @Test
    public void test5add17Benny()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        Assert.assertArrayEquals("Test 5: add 17:Benny: {,,,,26:Ben,93:Bob,17:Benny,,,,54:Bill}", new String[]{null,null,null,null,"Ben","Bob","Benny",null,null,null,"Bill"}, h.arrayOfValues());
    }

    @Test
    public void test6add77Benji()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        Assert.assertArrayEquals("Test 6: add 77:Benji: {77:Benji,,,,26:Ben,93:Bob,17:Benny,,,,54:Bill}", new String[]{"Benji",null,null,null,"Ben","Bob","Benny",null,null,null,"Bill"}, h.arrayOfValues());
    }

    @Test
    public void test7add31Banksy()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        Assert.assertArrayEquals("Test 7: add 31:Banksy: {77:Benji,,,,26:Ben,93:Bob,17:Benny,,,31:Banksy,54:Bill}", new String[]{"Benji",null,null,null,"Ben","Bob","Benny",null,null,"Banksy","Bill"}, h.arrayOfValues());
    }

    @Test
    public void test8add65Bobby()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        Assert.assertArrayEquals("Test 8: add 65:Bobby: {77:Benji,65:Bobby,,,26:Ben,93:Bob,17:Benny,,,31:Banksy,54:Bill}", new String[]{"Benji","Bobby",null,null,"Ben","Bob","Benny",null,null,"Banksy","Bill"}, h.arrayOfValues());
    }

    @Test
    public void test9add11Bernard()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        h.add(11, "Bernard");
        Assert.assertArrayEquals("Test 9: add 11:Bernard: {77:Benji,65:Bobby,11:Bernard,,26:Ben,93:Bob,17:Benny,,,31:Banksy,54:Bill}", new String[]{"Benji","Bobby","Bernard",null,"Ben","Bob","Benny",null,null,"Banksy","Bill"}, h.arrayOfValues());
    }

    @Test
    public void test10add53Billy()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        h.add(11, "Bernard");
        h.add(53, "Billy");
        Assert.assertArrayEquals("Test 10: add 53:Billy: {77:Benji,65:Bobby,11:Bernard,53:Billy,26:Ben,93:Bob,17:Benny,,,31:Banksy,54:Bill}", new String[]{"Benji","Bobby","Bernard","Billy","Ben","Bob","Benny",null,null,"Banksy","Bill"}, h.arrayOfValues());
    }

    @Test
    public void test11checkForNotEmpty()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        h.add(11, "Bernard");
        h.add(53, "Billy");
        Assert.assertFalse("Test 11: Check for not empty: ", h.isEmpty());
    }

    @Test
    public void test12checkLength()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        h.add(11, "Bernard");
        h.add(53, "Billy");
        Assert.assertEquals("Test 12: Check length: ",9, h.length());
    }

    @Test
    public void test13item53()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        h.add(11, "Bernard");
        h.add(53, "Billy");
        Assert.assertEquals("Test 13: Get item with key 53: ", "Billy", h.item(53));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test14item99()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        h.add(11, "Bernard");
        h.add(53, "Billy");
        Assert.assertNull("Test 14: item(99) {77:Benji,65:Bobby,11:Bernard,53:Billy,26:Ben,93:Bob,17:Benny,,,31:Banksy,54:Bill}", h.item(99));
    }

    @Test
    public void test15contains11()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        h.add(11, "Bernard");
        h.add(53, "Billy");
        Assert.assertTrue("Test 15: Check key 11 in hashTable", h.contains(11));
    }

    @Test
    public void test16contains13()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        h.add(11, "Bernard");
        h.add(53, "Billy");
        Assert.assertFalse("Test 16: Check key 13 in hashTable", h.contains(13));
    }

    @Test
    public void test17delete65()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        h.add(11, "Bernard");
        h.add(53, "Billy");
        h.delete(65);
        Assert.assertArrayEquals("Test 17: delete key 65: {77:Benji,,11:Bernard,53:Billy,26:Ben,93:Bob,17:Benny,,,31:Banksy,54:Bill}", new String[]{"Benji",null,"Bernard","Billy","Ben","Bob","Benny",null,null,"Banksy","Bill"}, h.arrayOfValues());
    }

    @Test(expected=IllegalArgumentException.class)
    public void test18delete99()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        h.add(11, "Bernard");
        h.add(53, "Billy");
        h.delete(65);
        h.delete(99);
    }

    @Test
    public void test19checkLength()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        h.add(11, "Bernard");
        h.add(53, "Billy");
        h.delete(65);
        Assert.assertEquals(8, h.length());
    }

    @Test
    public void test20add12Benji()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        h.add(11, "Bernard");
        h.add(53, "Billy");
        h.delete(65);
        h.add(12, "Benji");
        Assert.assertArrayEquals("Test 20: add 12:Benji: {77:Benji,12:Benji,11:Bernard,53:Billy,26:Ben,93:Bob,17:Benny,,,31:Banksy,54:Bill}", new String[]{"Benji","Benji","Bernard","Billy","Ben","Bob","Benny",null,null,"Banksy","Bill"}, h.arrayOfValues());
    }

    @Test
    public void test21add28Butch()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        h.add(11, "Bernard");
        h.add(53, "Billy");
        h.delete(65);
        h.add(12, "Benji");
        h.add(28, "Butch");
        Assert.assertArrayEquals("Test 20: add 28:Butch: {77:Benji,12:Benji,11:Bernard,53:Billy,26:Ben,93:Bob,17:Benny,28:Butch,,31:Banksy,54:Bill}", new String[]{"Benji","Benji","Bernard","Billy","Ben","Bob","Benny","Butch",null,"Banksy","Bill"}, h.arrayOfValues());
    }

    @Test(expected=IllegalArgumentException.class)
    public void test22add77Blobby()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        h.add(11, "Bernard");
        h.add(53, "Billy");
        h.delete(65);
        h.add(12, "Benji");
        h.add(28, "Butch");
        h.add(77, "Blobby");
    }

    @Test
    public void test23delete31()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        h.add(11, "Bernard");
        h.add(53, "Billy");
        h.delete(65);
        h.add(12, "Benji");
        h.add(28, "Butch");
        h.delete(31);
        Assert.assertArrayEquals("Test 20: delete(31): {77:Benji,12:Benji,11:Bernard,53:Billy,26:Ben,93:Bob,17:Benny,28:Butch,,,54:Bill}", new String[]{"Benji","Benji","Bernard","Billy","Ben","Bob","Benny","Butch",null,null,"Bill"}, h.arrayOfValues());
    }

    @Test
    public void test24item53() {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        h.add(11, "Bernard");
        h.add(53, "Billy");
        h.delete(65);
        h.add(12, "Benji");
        h.add(28, "Butch");
        h.delete(31);
        h.item(53);
        System.out.println(h);
        Assert.assertEquals("Test 24: item(53): ", "Billy", h.item(53));
    }

    @Test
    public void test25add42Boris()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        h.add(11, "Bernard");
        h.add(53, "Billy");
        h.delete(65);
        h.add(12, "Benji");
        h.add(28, "Butch");
        h.delete(31);
        h.add(42, "Boris");
        Assert.assertArrayEquals("Test 25: add 42:Boris: {77:Benji,12:Benji,11:Bernard,53:Billy,26:Ben,93:Bob,17:Benny,28:Butch,,42:Boris,54:Bill}", new String[]{"Benji","Benji","Bernard","Billy","Ben","Bob","Benny","Butch",null,"Boris","Bill"}, h.arrayOfValues());
    }

    @Test
    public void test26add41Blinky()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        h.add(11, "Bernard");
        h.add(53, "Billy");
        h.delete(65);
        h.add(12, "Benji");
        h.add(28, "Butch");
        h.delete(31);
        h.add(42, "Boris");
        h.add(41, "Blinky");
        Assert.assertArrayEquals("Test 26: add 41:Blinky: {77:Benji,12:Benji,11:Bernard,53:Billy,26:Ben,93:Bob,17:Benny,28:Butch,41:Blinky, 42:Boris, 54:Bill}", new String[]{"Benji","Benji","Bernard","Billy","Ben","Bob","Benny","Butch","Blinky","Boris","Bill"}, h.arrayOfValues());
    }

    @Test
    public void test27checkFull()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        h.add(11, "Bernard");
        h.add(53, "Billy");
        h.delete(65);
        h.add(12, "Benji");
        h.add(28, "Butch");
        h.delete(31);
        h.add(42, "Boris");
        h.add(41, "Blinky");
        Assert.assertTrue("Test 27: check full hashTable: {77:Benji,12:Benji,11:Bernard,53:Billy,26:Ben,93:Bob,17:Benny,28:Butch,41:Blinky, 42:Boris, 54:Bill}", h.isFull());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test28add11Blanka()
    {
        HashTable<Integer, String> h = new HashTable<>(11);
        h.add(54, "Bill");
        h.add(26, "Ben");
        h.add(93, "Bob");
        h.add(17, "Benny");
        h.add(77, "Benji");
        h.add(31, "Banksy");
        h.add(65, "Bobby");
        h.add(11, "Bernard");
        h.add(53, "Billy");
        h.delete(65);
        h.add(12, "Benji");
        h.add(28, "Butch");
        h.delete(31);
        h.add(42, "Boris");
        h.add(41, "Blinky");
        h.add(11, "Blanka");
    }
}
