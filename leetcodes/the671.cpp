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
    int findSecondMinimumValue(TreeNode* root) {
        unsigned int min = UINT_MAX;
        unsigned int secMin = UINT_MAX;
        helper(root,min,secMin);
        return (secMin == UINT_MAX)?-1:secMin;
        
    }
    void helper(TreeNode* root,unsigned int &m,unsigned int &s){
        if(root == NULL)
            return;
        if(root->val < m){
            s = m;
            m = root->val;
        }else if((root->val > m) && (root->val < s)){//if root -> val == m then error
            s = root->val;
        }
        helper(root->left,m,s);
        helper(root->right,m,s);
    }
};