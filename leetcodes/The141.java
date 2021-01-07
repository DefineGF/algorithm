/**
 * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
 * 内存消耗：38.6 MB, 在所有 Java 提交中击败了72.33% 的用户
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) 
            return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null) {
            if (fast.next == null)
                return false;
            fast = fast.next;
            if (slow == fast) 
                return true;
            slow = slow.next;
            fast = fast.next;
        }
        return false;
    }
}
