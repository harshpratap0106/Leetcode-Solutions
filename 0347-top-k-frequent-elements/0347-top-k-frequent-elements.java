class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        // Store frequency of each number
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Max Heap based on frequency
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        // Add all entries into heap
        maxHeap.addAll(map.entrySet());

        int[] result = new int[k];

        // Extract top k frequent elements
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll().getKey();
        }

        return result;
    }
}