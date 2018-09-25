public class longestPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return null;
        int len = strs[0].length();
        int arrLen = strs.length;
        for (int i = 0; i < len; i++) {
            for (int j = 1; j < arrLen; j++) {
                if (i >= strs[j].length() || strs[0].charAt(i) != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

}
