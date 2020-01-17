package model;

public class BinTreeNode {
    private String value;
    private BinTreeNode leftSon = null;
    private BinTreeNode rightSon = null;

    public BinTreeNode(){}

    public BinTreeNode(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setRightSon(BinTreeNode rightSon) {
        this.rightSon = rightSon;
    }

    public void setLeftSon(BinTreeNode leftSon) {
        this.leftSon = leftSon;
    }


    public BinTreeNode getLeftSon() {
        return leftSon;
    }

    public BinTreeNode getRightSon() {
        return rightSon;
    }

}
