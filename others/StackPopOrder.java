import java.util.Stack;

/** 
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等
 * 算法思想：通过栈实现压栈出栈操作；
 * 将压栈数组中元素依次加入栈，同时对比出栈数组元素：相同则出栈；不同则继续压栈
 * 最后如果栈中无元素，说明出栈和入栈顺序匹配；反之类之
 */

public class StackPopOrder {
    private boolean IsPopOrder(int [] pushA,int [] popA) {
        if(pushA == null || pushA.length == 0)
            return false;
        Stack<Integer> stack = new Stack<>();
        for(int i =0,j=0;i < pushA.length;){
            stack.push(pushA[i++]);
            while(!stack.isEmpty() && stack.peek() == popA[j]){
                stack.pop();
                j++;
            }
        }
        return stack.empty();
    }
}

/*
*
*/
