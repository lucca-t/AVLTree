import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinarySearchTree {
    private BinaryNode root;

    public BinarySearchTree()
    {
        root = null;
    }
    public void add(BinaryNode x)
    {
        if(root == null)
        {
            root = x;
            return;
        }
        add(root, x);
    }
    public String toString()
    {
        return toString(root);
    }
    public String toString(BinaryNode root){
        String result = "";
        if(root==null)
            return "";
        result+=root.getValue()+ " ";
        result+=toString(root.left());
        result+=toString(root.right());

        return result;
    }
    private void add(BinaryNode parent, BinaryNode x)
    {
        if(parent == null) return;
        if(x.getValue().compareTo(parent.getValue()) < 0)
            if(parent.left()==null)
                parent.setLeft(x);
            else
                add(parent.left(),x);
        else
        if(parent.right()==null)
            parent.setRight(x);
        else
            add(parent.right(),x);
    }

    public String preOrder()
    {
        return preOrder(root).trim();
    }

    private String preOrder(BinaryNode k)
    {
        String temp = "";
        if(k != null)
        {
            // use value
            temp += k.getValue()+ " ";
            // go left
            temp += preOrder(k.left());
            // go right
            temp += preOrder(k.right());
        }
        return temp;
    }

    public String levelOrder()
    {
        String temp = "";
        Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
        queue.offer(root);
        while(!queue.isEmpty())
        {
            BinaryNode k = queue.poll();
            temp += k.getValue()+" ";
            if(k.left()!=null)
                queue.offer(k.left());
            if(k.right()!=null)
                queue.offer(k.right());
        }
        return temp.trim();
    }

    public String inOrder(){
       return inOrder(root);

    }
    public String inOrder(BinaryNode root){
        String result="";
        if(root==null)
            return result;
        result+=inOrder(root.left());
        result+=root.getValue()+" ";
        result+=inOrder(root.right());
        return result;
    }
    public int getHeight()
    {
        return getHeight(root);
    }
    public String postOrder(BinaryNode root) {
        if (root == null) {
            return "";
        }
        String output = postOrder(root.left());
        output += postOrder(root.right());
        output += root.getValue() + " ";
        return output;
    }
    public String postOrder(){
        return postOrder(root);
    }




    private int getHeight(BinaryNode k)
    {
        if(k == null) return -1;
        return 1 + Math.max(getHeight(k.left()),
                getHeight(k.right()));
    }

    BinaryNode remove(Comparable target)
    {
        if(root == null) return null;
        BinaryNode temp = root;
        BinaryNode inorderSuccessor;

        //check to see if root is to be removed
        if(root.getValue().equals(target))
        {
            //remove root degree 0
            if(root.left() == null && root.right() == null)
            {
                root = null;
                return temp;
            }
            //remove root degree 1 – right child
            else if(root.left() == null)
            {
                root = root.right();
                temp.setRight(null);;
                return temp;
            }
            //remove root degree 1 – left child
            else if(root.right() == null)
            {
                root = root.left();
                temp.setLeft(null);
                return temp;
            }
            //remove root degree 2
            {
                inorderSuccessor = successor(root);
                swap(root,inorderSuccessor);
                if(root.right()==inorderSuccessor)
                {
                    root.setRight(inorderSuccessor.right());
                    inorderSuccessor.setRight(null);
                    return inorderSuccessor;
                }
                return remove(root.right(),target);
            }

        }
        //if root is not removed call remove helper method
        return remove(root,target);
    }



    private BinaryNode successor(BinaryNode k)
    {
        BinaryNode temp = k;
        temp = temp.right();
        while(temp.left() != null)
            temp = temp.left();
        return temp;
    }

    private void swap(BinaryNode x, BinaryNode y)
    {
        Comparable k = x.getValue();
        x.setValue(y.getValue());
        y.setValue(k);
    }

    private BinaryNode search(BinaryNode parent, Comparable target)
    {
        if(parent == null) return null;
        if(parent.left()!=null && parent.left().getValue().equals(target)||
                parent.right()!=null && parent.right().getValue().equals(target))
            return parent;
        else if(target.compareTo(parent.getValue()) < 0)
            return search(parent.left(),target);
        else
            return search(parent.right(),target);
    }
    private BinaryNode remove(BinaryNode startNode, Comparable target)
    {
        BinaryNode nodeToRemove, inorderSuccessor;
        BinaryNode parent = search(startNode,target);

        if(parent == null) return null;

        //decide if it is a left or right child
        boolean isLeft = parent.left()!=null &&
                parent.left().getValue().equals(target);

        nodeToRemove = isLeft ? parent.left() : parent.right();

        //degree 0

        if(nodeToRemove.left() == null && nodeToRemove.right() == null)
        {
            if(isLeft)
                parent.setLeft(null);
            else
                parent.setRight(null);
            return nodeToRemove;
        }

        //degree 1
        else if(nodeToRemove.left() == null)
        {
            if(isLeft)
                parent.setLeft(nodeToRemove.right());
            else
                parent.setRight(nodeToRemove.right());
            nodeToRemove.setRight(null);
            return nodeToRemove;
        }
        else if(nodeToRemove.right() == null)
        {
            if(isLeft)
                parent.setLeft(nodeToRemove.left());
            else
                parent.setRight(nodeToRemove.left());
            nodeToRemove.setLeft(null);
            return nodeToRemove;
        }


        //degree 2
        else
        {
            inorderSuccessor = successor(nodeToRemove);
            swap(inorderSuccessor, nodeToRemove);
            if(nodeToRemove.right()==inorderSuccessor)
            {
                nodeToRemove.setRight(inorderSuccessor.left());
                inorderSuccessor.setRight(null);
                return inorderSuccessor;
            }
            return remove(nodeToRemove.right(), target);
        }

    }

    public static void main(String args[])
    {
        BinarySearchTree tree = new BinarySearchTree();

        String str = "7 27 11 3 14 12 26 41 19 35 4 50";
        String[] list = str.split(" ");

        for(String k:list)
            tree.add(new BinaryNode(Integer.parseInt(k)));

        //System.out.println("in Order "+tree.inOrder());
       // System.out.println("pre Order "+tree.preOrder());
        System.out.println("level Order "+tree.levelOrder());
        //System.out.println("post Order"+tree.postOrder());

        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            tree.remove(in.nextInt());
            System.out.println("level Order " + tree.levelOrder());
        }
    }
    public BinaryNode getNode(){
        return root;
    }


}
