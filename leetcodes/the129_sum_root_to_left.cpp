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
    int sumNumbers(TreeNode* root) {
        int sum = 0;
        helper(root,0,sum);
        return sum;
    }
    void helper(TreeNode* root,int s,int &sum){
        if(root == NULL){
            return;
        }
        s = s*10 + root->val;
        if(root->right == NULL && root->left == NULL){
            sum += s;
        }
        helper(root->left,s,sum);
        helper(root->right,s,sum);
    }
};