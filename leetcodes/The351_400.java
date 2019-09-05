package leetcode;

public class The351_400 {
    public boolean isPerfectSquare(int num) {
        for(int i = 1;num > 0;i+=2){
            num -= i;
        }
        return (num == 0);
    }
    private boolean isPerfectSquare2(int num){
        if(num == 1)
            return true;
        long left = 1,right = num -1;
        long mid;
        while(left <= right){
            mid = (left + right)/2;
            if(mid * mid > num){
                right = mid - 1;
            }else if(mid * mid < num){
                left = mid + 1;
            }else{
                return true;
            }
        }
        return false;
    }
}
