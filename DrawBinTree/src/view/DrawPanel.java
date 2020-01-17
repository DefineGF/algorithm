package view;


import model.BinTree;
import util.Log;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DrawPanel extends JPanel {
    private static final int WIN_WIDTH_DEF  = 1200;
    private static final int WIN_HEIGHT_DEF = 1600;

    static final int CIRCLE_RADIUS   = 45;
    static final int LINE_X_Y_LEN    = CIRCLE_RADIUS * 4;
    static final double WEIGHT_VALUE = 0.8;


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Font font = new Font("宋体",Font.BOLD,24);
        g.setFont(font);
        String []source = {"NaN","A","B","C","D","E","NaN","F","NaN","NaN","G"};


        BinTree tree = new BinTree(source);
        TreeNodeView viewRoot = tree.createTreeView(new Point(WIN_WIDTH_DEF/2,CIRCLE_RADIUS + 10));
        drawTreeE(g,viewRoot);

    }


    private void drawTreeE(Graphics g,TreeNodeView viewRoot){
        if(viewRoot != null){
            drawNode(g,viewRoot);
            drawLines(g,viewRoot);
            drawTreeE(g,viewRoot.getLeftSon());
            drawTreeE(g,viewRoot.getRightSon());
        }
    }
    private void drawNode(Graphics g, TreeNodeView node){
        Point nodePos = node.getNodeCenPos();
        Point fontPos = node.getFontPos();
        g.drawOval(nodePos.getpX() - DrawPanel.CIRCLE_RADIUS,
                   nodePos.getpY() - DrawPanel.CIRCLE_RADIUS,
                    DrawPanel.CIRCLE_RADIUS * 2,DrawPanel.CIRCLE_RADIUS * 2);

        g.drawString(node.getDisplayValue(),fontPos.getpX(),fontPos.getpY());
    }

    private void drawLines(Graphics g, TreeNodeView node){
        if(node.hasSon()){
            TreeLineView treeLineView = new TreeLineView(node);
            List<Line> lines = treeLineView.getLines();
            Log.d("drawLines:   个数：" + lines.size());
            for(Line line:lines){
                g.drawLine(line.getStartP().getpX(),line.getStartP().getpY(),
                        line.getEndP().getpX(),line.getEndP().getpY());
            }
        }
    }

    public static void main(String []args){
        JFrame frame = new JFrame();
        frame.add(new DrawPanel());
        frame.setSize(DrawPanel.WIN_WIDTH_DEF,DrawPanel.WIN_HEIGHT_DEF);
        frame.setVisible(true);
    }

}
