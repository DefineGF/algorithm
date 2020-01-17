package model;


import util.Log;
import view.Point;
import view.TreeNodeView;

public class BinTree {
    private BinTreeNode root;
    private String[] source;

    public BinTree(String []source){
        this.source = source;
        if(source != null && source.length > 1){
            root = new BinTreeNode();
            createTree(root,1);
        }

    }

    //通过数组创建二叉树
    private void createTree(BinTreeNode node, int index){
        if(index < source.length  && !source[index].equals("NaN")){
            node.setValue(source[index]);
            if(index * 2 < source.length && !source[index * 2].equals("NaN")){
                node.setLeftSon(new BinTreeNode());
                createTree(node.getLeftSon(),index * 2);
            }
            if(index * 2 + 1 < source.length && !source[index * 2 + 1].equals("NaN")){
                node.setRightSon(new BinTreeNode());
                createTree(node.getRightSon(),index * 2 + 1);
            }
        }
    }

    /**
     *
     * @param pos 根节点初始坐标
     */
    public TreeNodeView createTreeView(Point pos){
        TreeNodeView viewRoot = null;
        if(root != null){
            viewRoot = new TreeNodeView();
            viewRoot.setNodeCenPos(pos);
            treeViewHelper(viewRoot,root,1);
        }
        return viewRoot;
    }


    //根据BinTreeNode 来创建树的视图 TreeNodeView
    private void treeViewHelper(TreeNodeView viewNode, BinTreeNode node, int layer){
        if(viewNode == null)
            return;
        if(node != null) {
            viewNode.setLayer(layer);
            viewNode.setValue(node.getValue());

            if(node.getLeftSon() != null){//判断，尽量减少对象创建以免浪费
                viewNode.addSon(new TreeNodeView(), TreeNodeView.SON_TYPE.LEFT_SON);
            }
            if(node.getRightSon() != null){
                viewNode.addSon(new TreeNodeView(), TreeNodeView.SON_TYPE.RIGHT_SON);
            }
            treeViewHelper(viewNode.getLeftSon(), node.getLeftSon(), layer + 1);
            treeViewHelper(viewNode.getRightSon(), node.getRightSon(), layer + 1);
        }
    }

    public BinTreeNode getRoot(){
        return root;
    }


    public void preOrder(BinTreeNode node,int layer){
        if(node != null){
            Log.d("layer = " + layer + " value = " + node.getValue());
            preOrder(node.getLeftSon(),layer+1);
            preOrder(node.getRightSon(),layer+1);
        }
    }

}
