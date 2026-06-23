class Solution {
    public boolean hasCycle(ListNode head) {

        // Slow pointer moves 1 step
        ListNode slow = head;

        // Fast pointer moves 2 steps
        ListNode fast = head;

        // Traverse the linked list
        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            // Cycle detected
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}