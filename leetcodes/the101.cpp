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
    bool isSymmetric(TreeNode* root) {
        return helper(root,root);
    }
    bool helper(TreeNode* r1,TreeNode* r2){
        if(r1 == NULL && r2 == NULL)
            return true;
        if(r1 == NULL || r2 == NULL)
            return false;
		//此时左右子树均不为空
        return (r1->val == r2->val) && helper(r1->left,r2->right) && helper(r1->right,r2->left);
    }
};