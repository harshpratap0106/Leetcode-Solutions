/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode middleNode(ListNode head) {

        // Slow pointer moves 1 step
        ListNode slow = head;

        // Fast pointer moves 2 steps
        ListNode fast = head;

        // Traverse the linked list
        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }

        // Slow pointer reaches middle node
        return slow;
    }
}