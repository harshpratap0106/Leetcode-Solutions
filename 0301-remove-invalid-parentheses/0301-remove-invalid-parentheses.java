class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        q.offer(s);
        visited.add(s);

        boolean found = false;

        while (!q.isEmpty() && !found) {
            int size = q.size();

            while (size-- > 0) {
                String cur = q.poll();

                if (isValid(cur)) {
                    ans.add(cur);
                    found = true;
                    continue;
                }

                for (int i = 0; i < cur.length(); i++) {
                    if (cur.charAt(i) != '(' && cur.charAt(i) != ')')
                        continue;

                    String next = cur.substring(0, i) + cur.substring(i + 1);

                    if (visited.add(next))
                        q.offer(next);
                }
            }
        }

        return ans;
    }

    private boolean isValid(String s) {
        int balance = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                if (--balance < 0)
                    return false;
            }
        }
        return balance == 0;
    }
}