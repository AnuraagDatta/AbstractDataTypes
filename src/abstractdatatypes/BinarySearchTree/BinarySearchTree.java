package abstractdatatypes.BinarySearchTree;

import abstractdatatypes.linkedlist.LinkedList;

public class BinarySearchTree
{
    private BinaryTreeNode root;
    private int maxNodeLength = 0;

    public void add(String item)
    {
        root = addRecursive(null, root, item);
    }

    public BinaryTreeNode addRecursive(BinaryTreeNode parent, BinaryTreeNode root, String item)
    {
        if (root == null)
        {
            if (item.length() > maxNodeLength)
            {
                maxNodeLength = item.length();
            }
            return new BinaryTreeNode(parent, item, layerNumber(parent));
        }
        if (item.compareTo(root.getData()) < 0)
        {
            root.setLeftChild(addRecursive(root, root.getLeftChild(), item));
        }
        else
        {
            root.setRightChild(addRecursive(root, root.getRightChild(), item));
        }
        return root;
    }

    public BinaryTreeNode find(String key)
    {
        return findRecursive(root, key);
    }

    private BinaryTreeNode findRecursive(BinaryTreeNode root, String key)
    {
        if (root == null)
        {
            throw new IllegalArgumentException("Key not found!");
        }
        if (key.compareTo(root.getData()) < 0)
        {
            return findRecursive(root.getLeftChild(), key);
        }
        else if (key.compareTo(root.getData()) > 0)
        {
            return findRecursive(root.getRightChild(), key);
        }
        else
        {
            return root;
        }
    }

    public int layerNumber(BinaryTreeNode root)
    {
        int layer = 0;
        while (root != null)
        {
            root = root.getParent();
            layer++;
        }
        return layer;
    }

    public int getMaxNodeLength()
    {
        return maxNodeLength;
    }

    public LinkedList<BinaryTreeNode> getLayer(int layerNumber)
    {
        return getLayer(root, new LinkedList<>(), layerNumber);
    }

    private LinkedList<BinaryTreeNode> getLayer(BinaryTreeNode node, LinkedList<BinaryTreeNode> list, int layerNumber)
    {
        if (node != null)
        {
            getLayer(node.getLeftChild(), list, layerNumber);
            if (node.getLayer() == layerNumber)
            {
                list.append(node);
            }
            getLayer(node.getRightChild(), list, layerNumber);
        }
        return list;
    }
//
//    public boolean isEmpty()
//    {
//        return size == 0;
//    }
//
//    public int nodeCount()
//    {
//        return size;
//    }

//    public void delete(int key)
//    {
//        root = deleteRec(root, key);
//    }
//
//    private BinaryTreeNode deleteRec(BinaryTreeNode root, int key)
//    {
//        if (root == null)
//        {
//            return root;
//        }
//        if (key < root.getData())
//        {
//            root.setLeftChild(deleteRec(root.getLeftChild(), key));
//        }
//        else if (key > root.getData())
//        {
//            root.setRightChild(deleteRec(root.getRightChild(), key));
//        }
//        else
//        {
//            if (root.getLeftChild() == null)
//            {
//                return root.getRightChild();
//            }
//            else if (root.getRightChild() == null)
//            {
//                return root.getLeftChild();
//            }
//
//            root.setData(findMinimum(root.getRightChild()).getData());
//            root.setRightChild(deleteRec(root.getRightChild(), root.getData()));
//        }
//        return root;
//    }

    public void printTraversals()
    {
        System.out.println("Pre-order traversal: ");
        displayPreOrder(root);
        System.out.println("\nIn-order traversal: ");
        displayInOrder(root);
        System.out.println("\nPost-order traversal: ");
        displayPostOrder(root);
    }

    public void displayPreOrder(BinaryTreeNode node)
    {
        if (node != null)
        {
            System.out.println(node);
            displayPreOrder(node.getLeftChild());
            displayPreOrder(node.getRightChild());
        }
    }

    public void displayInOrder(BinaryTreeNode node)
    {
        if (node != null)
        {
            displayInOrder(node.getLeftChild());
            System.out.println(node);
            displayInOrder(node.getRightChild());
        }
    }

    public void displayPostOrder(BinaryTreeNode node)
    {
        if (node != null)
        {
            displayPostOrder(node.getLeftChild());
            displayPostOrder(node.getRightChild());
            System.out.println(node);
        }
    }

    public int getHeight(BinaryTreeNode root)
    {
        if (root == null)
        {
            return 0;
        }
        int leftTreeHeight = getHeight(root.getLeftChild());
        int rightTreeHeight = getHeight(root.getRightChild());
        return max(leftTreeHeight, rightTreeHeight) + 1;
    }

    private int max(int a, int b)
    {
        return (a > b) ? a : b;
    }

    public BinaryTreeNode getRoot()
    {
        return root;
    }

//    public BinaryTreeNode findMinimum(BinaryTreeNode root)
//    {
//        return (root.getLeftChild() == null) ?
//                root :
//                findMinimum(root.getLeftChild());
//    }
//
//    public BinaryTreeNode inOrderSuccessor(BinaryTreeNode root, BinaryTreeNode successor, String key)
//    {
//        if (root == null)
//        {
//            return root;
//        }
//        if (root.getData().equals(key))
//        {
//            if (root.getRightChild() != null)
//            {
//                return findMinimum(root.getRightChild());
//            }
//        }
//        else if (key.compareTo(root.getData()) < 0)
//        {
//            return inOrderSuccessor(root.getLeftChild(), root, key);
//        }
//        else
//        {
//            return inOrderSuccessor(root.getRightChild(), successor, key);
//        }
//        return successor;
//    }

    public String toString()
    {
        int layerCount = getHeight(root);
        for (int i = 0; i < layerCount; i++)
        {
            System.out.println(getLayer(i));
        }
        int gapWidth = maxNodeLength;
        return "";
    }

    public static void main(String[] args)
    {
        BinarySearchTree b = new BinarySearchTree();
        b.add("Jim");
        b.add("Kevin");
        b.add("Alice");
        b.add("Belinda");
        b.add("Charles");
        b.add("David");
        b.add("Aaron");
        b.printTraversals();
        System.out.println(b.getMaxNodeLength());
//        System.out.println(b.layerNumber(b.find("Alice")));
        System.out.println(b);
    }
}
