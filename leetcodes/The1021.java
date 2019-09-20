class Solution {
    public String removeOuterParentheses(String S) {
        int L = 1,R = 0;//左右括号的数量
        StringBuilder res = new StringBuilder();
        for(int i = 1;i<S.length();i++){
            char c = S.charAt(i);
            if(c == '(')
                L++;
            else
                R++;
            if(L != R){
                res.append(c);
            }else{//左右括号数量相等时，一原语结束
                i++;
                L = 1;
                R = 0;
            }
        }
        return res.toString();
    }
}
