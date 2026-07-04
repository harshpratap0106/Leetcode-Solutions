import java.util.*;

class Solution {

    public int findKthLargest(int[] nums, int k) {

        // Min Heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {

            minHeap.add(num);

            // Keep only k largest elements
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Root of heap is kth largest
        return minHeap.peek();
    }
}