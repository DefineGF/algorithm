package view;

import sun.font.FontDesignMetrics;

import java.awt.*;


/**
 * nodeCenterPos:节点中心坐标 & 节点值(计算偏移使位于圆点中心) -> fontPos:显示字符坐标
 *
 */
public class TreeNodeView {
    private Font font;
    private Point nodeCenPos = null;//节点圆心坐标
    private Point fontPos;
    private String value = "null";//显示值
    private int layer;//层数
    private TreeNodeView leftSon = null;
    private TreeNodeView rightSon = null;
    private boolean hasSon = false;
    private int fontWidth  = 0;
    private int fontHeight = 0;


    public TreeNodeView(){
        font = new Font("宋体",Font.BOLD,20);
    }


    /**
     *
     * @param layer 所在层数
     * @param value 节点显示
     */
    public TreeNodeView(int layer, String value){
        this.layer = layer;
        font = new Font("宋体",Font.BOLD,20);
        setValue(value);

    }


    //设置字体样式和显示值
    public void setFont(Font font){
        this.font = font;
    }


    //设置默认字体样式，设置显示值
    public void setValue(String value){
        this.value = value;
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        for(int i = 0;i < value.length();i++){
            fontWidth += metrics.charWidth(value.charAt(i));
        }
        fontHeight = metrics.getHeight();
        if(nodeCenPos != null){//重新修改显示值
            updateFontPos();
        }
    }


    public void setNodeCenPos(Point nodeCenPos) {
        this.nodeCenPos = nodeCenPos;
        updateFontPos();//添加节点坐标时，字体坐标也就确定了
    }

    //根据节点坐标设置显示字体坐标
    private void updateFontPos(){
        fontPos = new Point(nodeCenPos.getpX() - fontWidth / 2,nodeCenPos.getpY() + fontHeight/2 );
    }


    //获取节点坐标
    public Point getNodeCenPos() {
        return nodeCenPos;
    }

    //获取字体坐标
    public Point getFontPos(){
        return fontPos;
    }

    //获取字体格式
    public Font getFont(){
        return this.font;
    }

    public String getDisplayValue() {
        return value;
    }


    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public TreeNodeView getLeftSon() {
        return leftSon;
    }
    public TreeNodeView getRightSon(){
        return rightSon;
    }

    public boolean hasSon(){
        return hasSon;
    }

    /**
     *
     * @param sonNode 添加节点
     * @param type 节点类型： 左、右
     * 根据被添加节点即父节点 以及 为左|右孩子节点，设置孩子节点坐标
     */
    public void addSon(TreeNodeView sonNode, TreeNodeView.SON_TYPE type){
        if(!hasSon){
            hasSon = true;
        }
        int xT = (int)(DrawPanel.LINE_X_Y_LEN * Math.pow(DrawPanel.WEIGHT_VALUE,layer - 1));
        if(type == SON_TYPE.LEFT_SON){//左孩子
            Point sonCenPos = new Point(nodeCenPos.getpX() - xT, nodeCenPos.getpY() + DrawPanel.LINE_X_Y_LEN);
            sonNode.setNodeCenPos(sonCenPos);
            leftSon = sonNode;
        }else if(type == SON_TYPE.RIGHT_SON){//右孩子
            Point sonCenPos = new Point(nodeCenPos.getpX() + xT,nodeCenPos.getpY() + DrawPanel.LINE_X_Y_LEN);
            sonNode.setNodeCenPos(sonCenPos);
            rightSon = sonNode;
        }else{
            System.err.println("0: 左孩子; 1: 右孩子.");
        }
    }

    public enum SON_TYPE{
        LEFT_SON,RIGHT_SON
    }
}
