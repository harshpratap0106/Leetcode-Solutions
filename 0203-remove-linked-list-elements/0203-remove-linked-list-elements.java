class Solution {
    public ListNode removeElements(ListNode head, int val) {

        // Dummy node to handle head deletion cases
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // Pointer for traversal
        ListNode current = dummy;

        // Traverse linked list
        while (current.next != null) {

            if (current.next.val == val) {     // Remove node if value matches
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return dummy.next;
    }
}