import model.BinTree;
import model.BinTreeNode;

public class Test {

    private void testBinTreeCreate(){
        String []source = {"NaN","A","B","C","D","E","NaN","F","NaN","NaN","G"};
        BinTree tree = new BinTree(source);
        BinTreeNode root = tree.getRoot();
        tree.preOrder(root,1);
    }
    public static void main(String []args){
        Test t = new Test();
        t.testBinTreeCreate();

    }

}
