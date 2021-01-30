#### 二叉树最小深度

##### 题目描述

```
获取根节点到最近叶子节点的最短路径上的节点数量
输入：root = [3,9,20,null,null,15,7]
输出：2
		3
	   / \
 	  9   20
 	     /  \
 	    15   7

```

##### 方法一

```java
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        
        if (root.left == null) return minDepth(root.right) + 1;
        else if (root.right == null) {
            return minDepth(root.left) + 1;
        } else {
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }
    }
}
```

time:7ms

memory : 59.3MB

时间复杂度： O(n)

空间复杂度:	O(h), 其中h为树的高度，平均情况下为 h = lgn



##### 方法二： 使用队列； 时间最佳

```java
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        return helper(q, 1);
    }
    
    private int helper（Queue<TreeNode> q, int depth) {
        for (int size = q.size(); size > 0; size--) { 
            TreeNode node = q.poll(), left = node.left, right = node.right;
            if (left == null && right == null)
                return depth;
            if (left != null) 
                q.offer(left);
            if (right != null) 
                q.offer(right);

        } // 一层遍历结束
        return helper(q, depth + 1);
    }
}
```

time: 0ms;

memory: 58.2MB

算法思想：

- 将每层节点放入队列中；

- 在helper中对每层节点遍历：传入参数：Queue： 保存当前层节点的队列，depth：当前所在层

    - 叶节点: (left == null && right == null)，直接返回结果（因为此时已经得到最小深度，没必要继续计算)
    - 非叶节点，将此层的节点自左往右加入队列；

    最后再调用 helper(queue, depth + 1) 查看下一层之情况；

- 递归终结：叶节点；



##### 方法三：内存最佳

```java
class QueueNode {
    TreeNode node;
    int depth;
    public QueueNode(TreeNode node, int depth) {
        this.node = node;
        this.depth = depth;
    }
}
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<QueueNode> queue = new LinkedList<>();
        queue.offer(new QueueNode(root, 1));
        while (!queue.isEmpty()) {
            QueueNode queueNode = queue.poll();
            
            TreeNode node = queueNode.node;
            int depth = queueNode.depth;
            if (node.left == null && node.right == null) {
                return depth;
            }
            if (node.left != null) {
                queue.offer(new QueueNode(node.left, depth + 1));
            } 
            if (node.right != null) {
                queue.offer(new QueueNode(node.right, depth + 1));
            }
        }
        return 0;
    }
}
```

time:1ms;

memory: 57.9MB

算法思想：与方法二基本类似，但取消了递归，因此需要新的 class 来保存当前节点所在的层；
