class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack == null || haystack.length() == 0) {
            return -1;
        }
        int len1 = haystack.length();
        int len2 = needle.length();
        for (int i = 0;i < len1 - len2; i++) {
            int flag = i;
            int j = 0;
            for (;j < len2; j++) {
                if (haystack.charAt(flag) == needle.charAt(j)) {
                    flag++;
                } else {
                    break;
                }
            }
            if (j == len2) {
                return i;
            }
        }
        return -1;
    }
}
