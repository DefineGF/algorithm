package view;

import util.Log;

import java.util.LinkedList;
import java.util.List;

public class TreeLineView {
    private static final String TAG = "TreeLineView";
    private TreeNodeView father;
    private List<Line> lines;

    public TreeLineView(TreeNodeView father){
        this.father = father;
        setLines();
    }

    private void setLines(){
        if(father.hasSon()){
            lines = new LinkedList<>();
            int startX = father.getNodeCenPos().getpX();
            int startY = father.getNodeCenPos().getpY() + DrawPanel.CIRCLE_RADIUS;
            Point startP = new Point(startX,startY);
            Point endP;

            if(father.getLeftSon() != null && father.getLeftSon().getDisplayValue() != null){
                int endX = father.getLeftSon().getNodeCenPos().getpX();
                int endY = father.getLeftSon().getNodeCenPos().getpY() - DrawPanel.CIRCLE_RADIUS;
                endP = new Point(endX,endY);
                lines.add(new Line(startP,endP));
            }
            if(father.getRightSon() != null && father.getRightSon().getDisplayValue() != null){
                int endX = father.getRightSon().getNodeCenPos().getpX();
                int endY = father.getRightSon().getNodeCenPos().getpY() - DrawPanel.CIRCLE_RADIUS;
                endP = new Point(endX,endY);
                lines.add(new Line(startP,endP));
            }
        }else{
            Log.e(father.getDisplayValue() + "无子节点");
        }
    }


    public List<Line> getLines(){
        return lines;
    }


}
