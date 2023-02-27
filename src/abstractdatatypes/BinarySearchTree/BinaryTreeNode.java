package abstractdatatypes.BinarySearchTree;

public class BinaryTreeNode
{
    private BinaryTreeNode parent;
    private BinaryTreeNode leftChild;
    private BinaryTreeNode rightChild;
    private String data;

    private int layer = 0;

    public BinaryTreeNode(BinaryTreeNode parent, String data, int layer)
    {
        this.parent = parent;
        this.data = data;
        this.layer = layer;
    }


    public void setLeftChild(BinaryTreeNode child)
    {
        this.leftChild = child;
    }

    public BinaryTreeNode getLeftChild()
    {
        return leftChild;
    }

    public void setRightChild(BinaryTreeNode child)
    {
        this.rightChild = child;
    }

    public BinaryTreeNode getRightChild()
    {
        return rightChild;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public String getData()
    {
        return data;
    }

    public BinaryTreeNode getParent()
    {
        return parent;
    }

    public int getLayer()
    {
        return layer;
    }

    public void setLayer(int layer)
    {
        this.layer = layer;
    }

    public String toString()
    {
//        String output = "Node Data: "+data+"\tParent: ";
//        output += (parent != null) ? parent.getData() : "null";
//        output += "\tLeft child: ";
//        output += (leftChild != null) ? leftChild.getData() : "null";
//        output += "\tRightChild: ";
//        output += (rightChild != null) ? rightChild.getData() : "null";
//        return output;
        return data + "\tLayerNo.:"+layer;
    }
}
