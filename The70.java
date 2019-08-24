package leetcode;

import util.P;

public class The70 {
    public int climbStairs(int n) {
        if(n<0){
            return -1;
        }else if(n<=2){
            return n;
        }
        return climbStairs(n-1)+climbStairs(n-2);
    }
    public int climbStairs2(int n){
        if(n<=2)
            return n;
        int[] temp=new int[n];
        temp[0]=1;
        temp[1]=2;
        for(int i=2;i<n;i++){
            temp[i]=temp[i-1]+temp[i-2];
        }
        return temp[n-1];
    }
    public static void main(String []args){
        int res=new The70().climbStairs(10);
        int res2=new The70().climbStairs2(10);
        System.out.println(res);
        P.print(res2);
    }
}
