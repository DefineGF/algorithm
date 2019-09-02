package leetcode;

public class The263 {
    private boolean isUgly(int num) {//递归
        boolean two = false,three = false,five = false;
        if(num == 0){
            return false;
        }else if(num == 1){
            return true;
        }
        if(num % 2 == 0){
            two = isUgly(num/2);
        }else if(num % 3 == 0){
            three = isUgly(num/3);
        }else if(num % 5 == 0){
            five = isUgly(num/5);
        }else{
            return false;
        }
        return two || three || five;
    }

    private boolean isUgly2(int num){//非递归
        int []p = {2,3,5};
        if(num == 0) return false;
        for(int i = 0;i < 3;i++){
            while(num % p[i] == 0){
                num = num/p[i];
            }
        }
        return num == 1;

    }

    public static void main(String []args){
        The263 the263 = new The263();
        for(int i = 1;i<100;i++){
            System.out.println("the " + i + " is ugly:"+the263.isUgly(i));
            System.out.println("the " + i + " is ugly:"+the263.isUgly2(i));
        }
    }
}
