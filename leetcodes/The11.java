class Solution {
    public int maxArea(int[] height) {
        int start = 0;
        int tail  = height.length - 1;
        int maxArea = 0;
        while (start <= tail) {
            if (height[start] > height[tail]) {
                maxArea = Math.max(maxArea, height[tail] * (tail - start));
                tail--;
            } else if (height[start] < height[tail]) {
                maxArea = Math.max(maxArea, height[start] * (tail - start));
                start++;
            } else {
                maxArea = Math.max(maxArea, height[start] * (tail - start));
                start++;
                tail--;
            }
        }
        return maxArea;
    }
}
