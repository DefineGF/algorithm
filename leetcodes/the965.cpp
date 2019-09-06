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
    bool helper(TreeNode *root,int val){
        if(root != NULL){
            if(root->val != val){
                return false;
            }
            return helper(root->left,val) && helper(root->right,val);
        }
        return true;
    }
    bool isUnivalTree(TreeNode* root) {
        if(root == NULL){
            return true;
        }else{
            int val = root->val;
            return helper(root->left,val) && helper(root->right,val);
        }
    }
};