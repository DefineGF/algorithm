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
    bool hasPathSum(TreeNode* root, int sum) {
        return helper(root,0,sum);
    }
    bool helper(TreeNode* root,int s,int target){
        if(root == NULL)
            return false;
        if(root->left == NULL && root->right == NULL && (s + root->val == target)){//叶子节点
            return true;
        }
        s += root->val;
        return helper(root->left,s,target) || helper(root->right,s,target);
    }
};