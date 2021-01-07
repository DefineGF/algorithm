/**
 * 执行用时：1 ms, 在所有 Java 提交中击败了99.98%的用户
 * 内存消耗：41.3 MB, 在所有 Java 提交中击败了43.55%的用户
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int len1 = 0, len2 = 0;
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1.next != null) {
            len1++;
            p1 = p1.next;
        }
        while (p2.next != null) {
            len2++;
            p2 = p2.next;
        }

        int t = 0;
        if (len1 > len2) { // A > B
           t = len1 - len2;
           p1 = headA;
           p2 = headB;
        } else {
            t = len2 - len1;
            p1 = headB;
            p2 = headA;
        }

        for (int i = 0; i < t; i++) {
            p1 = p1.next;
        }
        while (p1 != null && p2 != null) {
            if (p1 == p2)
                return p1;
            p1 = p1.next;
            p2 = p2.next;
        }
        return null;
    }
}
