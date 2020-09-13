class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        StringBuilder ans = new StringBuilder();
        int index = 0;
        for (;index < strs[0].length(); index++) {
            char c = strs[0].charAt(index);
            int i = 1;
            for (; i < strs.length; i++) {
                if (index >= strs[i].length() || c != strs[i].charAt(index)) {
                    break;
                }
            }
            if (i == strs.length) {
                ans.append(c);
            } else {
                return ans.toString();
            }
        }
        return ans.toString();
    }
}
