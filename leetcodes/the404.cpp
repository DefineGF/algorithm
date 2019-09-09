/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    int sumOfLeftLeaves(TreeNode* root) {
        int sum = 0;
        helper(root,1,sum);//!0表示不记根节点
        return sum;
    }
    void helper(TreeNode* root,int tag,int &sum){//tag == 0?表示当前为左孩子:当前非左孩子;
        if(root == NULL)
            return;
        if(tag == 0 && root->left == NULL && root->right == NULL){//当前为左叶子
            sum += root->val;
        }
        helper(root->left,0,sum);
        helper(root->right,1,sum);
    }
};