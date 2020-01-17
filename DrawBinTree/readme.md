![运行截图](https://github.com/DefineGF/algorithm_rep/blob/master/DrawBinTree/bin_tree.PNG)
#### 基本类 (model package)
+ BinTreeNode.java: 二叉树节点类
+ BinTree.java:含二叉树根节点，主要功能：通过数组创建二叉树；递归将二叉树映射成二叉树视图（见下）以便显示；前序遍历二叉树（测试用）.

#### 视图类（view package）
+ 基本类
  - Line：直线类
  - Point：点类
+ 复合类（我瞎起的名字）
  - TreeNodeView.java：二叉树节点映射的视图，逻辑与二叉树节点类似，但是含有节点中心坐标位置、显示的字符位置（以便居中）、层数（便于根据层数调整叶子节点之间的距离），主要功能：添加子节点，并根据被添加也就是父节点的坐标来设置子节点坐标；含列表字段保存孩子节点；
  - TreeLineView.java：将父节点与子节点之间的线映射为视图；通过列表包含的孩子信息，设置线的长度及竖直偏角；
  - DrawPanel.java：画板类；含有font，以及与节点大小，线段长度等基本默认信息；功能：根据TreeNodeView，TreeLineView来绘图，总方法为：drawTreeE(Graphics g,TreeNodeView viewRoot),传入二叉树视图根节点。
