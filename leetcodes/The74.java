class Solution {
    /**
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0)
            return false;
        int i = 0,j = matrix[0].length - 1;
        while(i < matrix.length && j >= 0){
            if(target == matrix[i][j]){
                return true;
            }else if(target > matrix[i][j]){
                i++;
            }else{
                j--;
            }
        }
        return false;
    }
    
    */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0)
            return false;
        int i = 0,j = matrix[0].length - 1;
        boolean left = false;//添加方向
        while(i < matrix.length && j >= 0){
            if(target == matrix[i][j]){
                return true;
            }else if(target > matrix[i][j]){
                if(left){//左移时发现小于目标值 直接返回false
                    return false;
                }    
                i++;
            }else{
                j--;
                left = true;
            }
        }
        return false;
    }
}
