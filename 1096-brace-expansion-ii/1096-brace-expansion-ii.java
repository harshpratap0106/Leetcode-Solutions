class Solution {
    int pos = 0;

    public List<String> braceExpansionII(String expression) {
        return new ArrayList<>(parse(expression));
    }

    Set<String> parse(String s) {
        Set<String> res = new TreeSet<>();

        while (pos<s.length() && s.charAt(pos)!='}') {
            Set<String> cur;

            if (s.charAt(pos) == '{') {
                pos++;
                cur = parse(s);
                pos++;
            } else {
                cur = new TreeSet<>();
                cur.add(String.valueOf(s.charAt(pos++)));
            }

            if (res.isEmpty()) {
                res.addAll(cur);
            } else {
                Set<String> temp = new TreeSet<>();

                for (String a : res)
                    for (String b : cur)
                        temp.add(a+b);

                res = temp;
            }

            if (pos<s.length() && s.charAt(pos)==',') {
                pos++;
                Set<String> next = parse(s);

                res.addAll(next);
                return res;
            }
        }
        return res;
    }
}