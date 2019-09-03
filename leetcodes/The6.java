package leetcode;

public class The6 {
    public String convert(String s, int numRows) {
        StringBuilder []sb = new StringBuilder[numRows];
        for(int i = 0;i<numRows;i++){
            sb[i] = new StringBuilder();
        }
        int dirIndex = -1;
        boolean down = true;
        for(int i = 0;i<s.length();i++){
            if(down && dirIndex < numRows - 1){
                dirIndex ++;
                down = !(dirIndex == numRows - 1);
            }else if(!down && dirIndex > 0){
                dirIndex --;
                down = (dirIndex == 0);
            }
            sb[dirIndex].append(s.charAt(i));
        }
        StringBuilder res = new StringBuilder();
        for(int i = 0;i < numRows;i++){
            res.append(sb[i]);
        }
        return res.toString();
    }
    public static void main(String []args){
        The6 the6 = new The6();
        String str = "LEETCODEISHIRING";
        int n = 3;
        System.out.println(the6.convert(str,n));//正确答案:LCIRETOESIIGEDHN
        String str2= "LEETCODEISHIRING";
        int n2 = 4;
        System.out.println(the6.convert(str2,n2));//正确答案:LDREOEIIECIHNTSG
    }
}
