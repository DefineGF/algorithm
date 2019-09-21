/**
第一种方法   执行用时 :352 ms, 在所有 Java 提交中击败了21.92%的用户
             内存消耗 :38.3 MB, 在所有 Java 提交中击败了93.33%的用户
*/
class Solution {
    public int minimumLengthEncoding(String[] words) {
        StringBuilder res = new StringBuilder();
        boolean []visited = new boolean[words.length];
        for(int i = 0;i < words.length;i++){
            if(visited[i]) continue; 
            String temp = words[i];
            for(int j = i + 1;j < words.length;j++){
                if(visited[j]) continue;
                //字符串匹配
                short h = helper(temp,words[j]);
                if(h == -1){
                    temp = words[j];
                    visited[j] = true;
                }else if(h == 1){
                    visited[j] = true;
                }
            }
            res.append(temp).append("#");
        }
        return res.length();
    }
    /**
    返回值0 两者不匹配; 1 取左边; -1 取右边
    */
    private short helper(String S,String C){
        if(S.length() >= C.length()){
            int t = S.length() - C.length();
            for(int i = C.length() - 1;i >= 0;i--){
                if(S.charAt(i + t) != C.charAt(i)){
                    return 0;
                }
            }
            return 1;
        }else{
            int t = C.length() - S.length();
            for(int i = S.length() - 1;i >= 0;i--){
                if(S.charAt(i) != C.charAt(i + t)){
                    return 0;
                }
            }
            return -1;
        }
    }
}

/**
官方答案
*/
class Solution {
    public int minimumLengthEncoding(String[] words) {
        Set<String> ws = new HashSet(Arrays.asList(words));
        for(String W:words){
            for(int i = 1;i< W.length();i++){
                ws.remove(W.substring(i));
            }
        }
        int res = 0;
        for(String w:ws){
            res += (w.length() + 1);
        }
        return res;
    }
}
