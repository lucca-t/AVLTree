import java.util.LinkedList;
import java.util.Queue;

public class BinaryNode {
    private BinaryNode left, right,root;
    private Comparable myValue;
    public BinaryNode(){
        root=null;
    }
    public BinaryNode(Comparable x){
        myValue=x;
    }






    public void setValue(Comparable value) {
        myValue=value;
    }
    public Comparable getValue() {
        return myValue;
    }
    public BinaryNode left(){
        return left;
    }
    public void setLeft(BinaryNode x){
        left=x;
    }
    public BinaryNode right(){
        return right;
    }
    public void setRight(BinaryNode x){
        right=x;
    }








    //other methods not shown.
}
