class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        // Dummy node to simplify list creation
        ListNode dummy = new ListNode(-1);
        //
        ListNode current = dummy;

        // Compare nodes from both lists
        while (list1 != null && list2 != null) {

            // Attach smaller node
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }

            current = current.next;
        }

        // Attach remaining nodes from list1
        if (list1 != null) {
            current.next = list1;
        }

        // Attach remaining nodes from list2
        if (list2 != null) {
            current.next = list2;
        }

        return dummy.next;
    }
}