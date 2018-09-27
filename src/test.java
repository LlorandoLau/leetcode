import tree.TreeNode;

import java.util.*;

public class test {

    private int n;

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
    }

    public static void newANode(ListNode node) {
        node = new ListNode(0);
    }

    public static String mostCommonWord(String paragraph, String[] banned) {
        int len = banned.length;
        paragraph = paragraph.toLowerCase();
        for (int i = 0; i < len; i++) {
            paragraph = paragraph.replaceAll(banned[i], " ");
        }
        paragraph = paragraph.replaceAll("[^A-Za-z]", " ");

        String[] strs = paragraph.split("\\s+");

        Arrays.sort(strs);
        for (String s : strs) {
            System.out.println(s);
        }
        int times = 1;
        int max = 1;
        String max_str = strs[0];
        String present = strs[0];
        System.out.println(strs.length);
        len = strs.length;
        for (int i = 1; i < len; i++) {
            if (strs[i].equals(present)) {
                times++;
            } else {
                if (max < times) {
                    max = times;
                    max_str = present;
                }
                times = 1;
                present = strs[i];
            }
        }
        return max_str;
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode node = new TreeNode();
        return merge(node, t1, t2);
    }

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        Map<Integer, List<int[]>> map = new TreeMap<>();
        int l1 = nums1.length;
        int l2 = nums2.length;
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                int[] temp = new int[2];
                int sum = nums1[i] + nums2[j];
                temp[0] = nums1[i];
                temp[1] = nums2[j];
                List<int[]> l = map.get(sum);
                if (l == null) map.put(sum, l);
                else {
                    l.add(temp);
                    map.put(sum, l);
                }
            }
        }
        List<int[]> list = new ArrayList<>();
        int count = 0;
        Iterator<Map.Entry<Integer, List<int[]>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, List<int[]>> e = it.next();
            for (int[] l : e.getValue()) {
                list.add(l);
                count++;
                if (count == k) return list;
            }

        }
        return list;
    }

    public int[] constructRectangle(int area) {
        int length = (int) Math.ceil(Math.pow(area, 0.5));
        while (area % length != 0) {
            length++;
        }
        int[] temp = new int[2];
        temp[0] = length;
        temp[1] = area / length;
        return temp;
    }

    public int kthSmallest(int[][] matrix, int k) {
        int len = matrix.length;
        List<Integer> list = new ArrayList();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                list.add(matrix[i][j]);
            }
        }
        Collections.sort(list);
        return list.get(k);
    }

    public char nextGreatestLetter(char[] letters, char target) {
        int len = letters.length;
        if (target > letters[len - 1]) return letters[0];
        for (int i = 0; i < len; i++) {
            if (letters[i] > target) return letters[i];
        }
        return ' ';
    }

    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] temp = new int[len];
        for (int i = 0; i < len; i++) {
            int j;
            for (j = (i + 1) % len; j != i; j = (j + 1) % len) {
                if (j == len) j = 0;
                if (nums[j] > nums[i]) {
                    temp[i] = nums[j];
                    break;
                }
            }
            if (j == i) temp[i] = -1;
        }
        return temp;
    }

    public int[] shuffle(int[] nums) {
        int len = 0;

        int[] temp = new int[len];
        int[] random = new int[len];
        Random r = new Random(len - 1);
        int index = r.nextInt();
        System.out.println(random.length + " " + index);

        for (int i = 0; i < len; i++) {
            while (random[index] != 1) {
                System.out.println(index);
                index = r.nextInt();
            }
            temp[i] = nums[index];
            random[index] = 1;
        }
        return temp;
    }

    public int getImportance(List<Employee> employees, int id) {
        Employee e = null;
        for (Employee em : employees) {
            if (em.id == id) {
                e = em;
                break;
            }
        }
        int temp = e.importance;
        for (Integer i : e.subordinates) {
            for (Employee emp : employees) {
                if (emp.id == i) {
                    temp += getImportance(employees, i);
                    break;
                }
            }
        }
        return temp;
    }

    public ListNode reverseList(ListNode head) {
        List<ListNode> listNodes = new ArrayList<>();
        while (head != null) {
            listNodes.add(head);
            head = head.next;
        }
        int len = listNodes.size();
        head = listNodes.get(0);
        for (int i = 1; i < len; i++) {
            ListNode node = listNodes.get(i);
            node.next = head;
            head = node;
        }
        return head;


    }

    public TreeNode merge(TreeNode total, TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        else if (t1 != null && t2 != null) {
            total.val = t1.val + t2.val;
            merge(total.left, t1.left, t2.left);
            merge(total.right, t1.right, t2.right);
        } else if (t1 == null && t2 != null) {
            total.val = t2.val;
            merge(total.left, null, t2.left);
            merge(total.right, null, t2.right);
        } else {
            total.val = t1.val;
            merge(total.left, t1.left, null);
            merge(total.right, t1.right, null);
        }

        return total;
    }

    public int totalHammingDistance(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                sum += hammingDistance(nums[i], nums[j]);
            }
        }
        return sum;
    }

    public int hammingDistance(int x, int y) {
        String fir = Integer.toBinaryString(x);
        String sec = Integer.toBinaryString(y);
        System.out.println(fir + " " + sec);
        int firlen = fir.length();
        int seclen = sec.length();
        int sum = Math.abs(firlen - seclen);
        String s = "";
        for (int i = 0; i < sum; i++)
            s += "0";
        if (firlen < seclen)
            fir = s + fir;
        else
            sec = s + sec;
        int len = sec.length();
        for (int i = 0; i < len; i++) {
            if (fir.charAt(i) != sec.charAt(i)) sum++;
        }
        return sum;
    }

    public int[] plusOne(int[] digits) {
        int len = digits.length;
        if (len == 1 && digits[0] == 0) {
            digits[0]++;
            return digits;
        }
        isTen(len - 1, 1, digits);
        return digits;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < m; i++) {
            if (nums1[i] > nums2[i]) {
                swap(nums1, i, nums2, i);
            }
        }
        Arrays.sort(nums2);
        System.arraycopy(nums2, 0, nums1, m, nums2.length);
    }

    public int[] shortestToChar(String S, char C) {
        return null;
    }

    public int countSegments(String s) {
        String[] strs = s.split("[^A-Za-z]");
        return strs.length;
    }

    public String toGoatLatin(String S) {
        String[] strs = S.split(" ");
        StringBuffer sb;
        char begin;
        int len = strs.length;
        int subLen;
        for (int i = 0; i < strs.length; i++) {
            begin = strs[i].charAt(0);
            subLen = strs[i].length();
            if (isVowet(begin)) {
                sb = new StringBuffer(strs[i]);
                sb.append("ma");
            } else {
                sb = new StringBuffer(strs[i].substring(1));
                sb.append(begin + "ma");
            }
            for (int j = 0; j < i + 1; j++)
                sb.append("a");
            strs[i] = sb.toString();
        }
        String newone = "";
        for (int i = 0; i < len; i++) {
            newone += strs[i];
            if (i == len - 1) continue;
            newone += " ";
        }
        return newone;
    }

    public boolean isVowet(char ch) {
        switch (ch) {
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
            default:
                return false;

        }
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        if (head.next == null && head.val == val) return null;
        ListNode pre = head;
        ListNode next = head;
        while (next != null) {
            if (next.val == val) {
                if (next == head) {
                    head = next.next;
                    next = null;
                }
                pre.next = next.next;
            }
            pre = next;
            next = next.next;
        }
        return head;
    }

    public ListNode middleNode(ListNode head) {
        if (head.next == null) return head;
        ListNode fir = head;
        ListNode sec = head;
        while (sec != null) {
            fir = fir.next;
            sec = sec.next.next;
        }
        return fir;
    }

    public String reverseString(String s) {
        char[] arr = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        char temp;
        while (i < j) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return new String(arr);
    }

    public void swap(int[] nums1, int index1, int[] nums2, int index2) {
        int temp = nums1[index1];
        nums1[index1] = nums2[index2];
        nums2[index2] = temp;
    }

    public void isTen(int index, int plus, int[] array) {
        array[index] = array[index] + plus;
        if (array[index] == 10) {
            array[index] = 0;
            if (index == 0) return;
            isTen(index - 1, 1, array);
        }
    }

    public int countPrimes(int n) {
        int times = 0;
        int[] arr = new int[n + 1];
        Arrays.fill(arr, 0);
        for (int i = 2; i < n; i++) {
            if (arr[i] == 0) times++;
            for (int j = 1; i * j <= n; j++) {
                arr[i * j]++;
            }
        }
        return times;
    }

    public int nthUglyNumber(int n) {
        int times = 1;
        int i = 0;
        for (i = 1; times != n; i++) {
            if (isUgly(i)) times++;
        }
        return --i;
    }

    public boolean isHappy(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = sum_of_power(n);
        while (sum != 1) {
            if (map.get(n) == null)
                map.put(n, sum);
            else
                return false;
            n = sum;
            sum = sum_of_power(n);
        }
        return true;
    }

    public boolean isBadVersion(int i) {
        return false;
    }

    public int guess(int num) {
        return 0;
    }

    public int guessNumber(int n) {
        int begin = 1;
        int end = n;
        int mid;
        int val;
        while (begin < end) {
            mid = begin / 2 + end / 2;
            val = guess(mid);
            if (val == 0) return mid;
            else if (val == -1) begin = mid = 1;
            else end = mid - 1;
        }
        return -1;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int l2 = nums2.length;
        for (int i = 0; i < l2; i++) {
            map.put(nums2[i], i);
        }
        int l1 = nums1.length;
        int val;
        int mid = Integer.MAX_VALUE;
        int[] arr = new int[l1];
        for (int j = 0; j < l1; j++) {
            val = nums1[j];
            for (int h = map.get(val); h < l2; h++) {
                if (nums2[h] > val && nums2[h] < mid) {
                    mid = nums2[h];

                }
            }
            if (mid == Integer.MAX_VALUE) arr[j] = -1;
            else arr[j] = mid;
            mid = Integer.MAX_VALUE;
        }
        return arr;
    }

    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<String>();
        for (int i = 1; i <= n; i++) {
            if (n % 15 == 0) list.add("FizzBuzz");
            else if (n % 3 == 0) list.add("Fizz");
            else if (n % 5 == 0) list.add("Buzz");
            else list.add(i + "");
        }
        return list;
    }

    public int reverseBits(int n) {
        int newone = n & 1;
        while (n != 0) {
            n = n >> 1;
            newone = newone << 1;
            newone = newone | (n & 1);
        }
        return newone;
    }

    public int hammingWeight(int n) {
        char[] arr = Integer.toBinaryString(n).toCharArray();
        int len = arr.length;
        int times = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] == '1') times++;
        }
        return times;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        Map<Integer, Integer> m1 = new HashMap<>();
        Map<Integer, Integer> m2 = new HashMap<>();
        for (int i = 0; i < l1; i++) {
            m1.put(nums1[i], i);
        }
        for (int j = 0; j < l2; j++) {
            m2.put(nums2[j], j);
        }
        Map<Integer, Integer> small;
        Map<Integer, Integer> big;
        int size;
        if (l1 < l2) {
            small = m1;
            big = m2;
            size = l1;
        } else {
            small = m2;
            big = m1;
            size = l2;
        }
        List<Integer> list = new ArrayList<>();
        Object o;
        int sum = 0;
        for (Object i : small.keySet()) {
            o = big.get(i);
            if (o != null) {
                list.add((Integer) o);
                sum++;
            }
        }
        int[] both = new int[sum];
        for (int i = 0; i < sum; i++) {
            both[i] = list.get(i);
        }
        return both;
    }

    public boolean isPowerOfFour(int n) {
        double tem = Math.log10(n) / Math.log10(4);
        return (tem - (int) (tem)) == 0 ? true : false;
    }

    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        while (n != 1) {
            if (n % 2 != 0) return false;
            n = n / 2;
        }

        return true;
    }

    public int sum_of_power(int n) {
        int sum = 0;
        while (n != 0) {
            sum += Math.pow(n % 10, 2);
            n = n / 10;
        }
        return sum;
    }

    public boolean isUgly(int num) {
        while (num != 1) {
            if (num % 2 == 0) {
                num /= 2;
            } else if (num % 3 == 0) {
                num /= 3;
            } else if (num % 5 == 0) {
                num /= 5;
            } else {
                return false;
            }
        }
        return true;
    }

    public int strStr(String haystack, String needle) {
        if (needle.equals("") || needle == null) return 0;
        int hLen = haystack.length();
        int nLen = needle.length();
        for (int i = 0; i < hLen - nLen; i++) {
            if (haystack.substring(i, i + nLen).equals(needle))
                return i;
        }
        return -1;
    }

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int col = grid.length;
        int row = grid[0].length;
        int[] col_max = new int[row];
        int[] row_max = new int[col];
        int temp;
        for (int i = 0; i < row; i++) {
            col_max[i] = grid[0][i];
        }
        for (int i = 0; i < col; i++) {
            row_max[i] = grid[i][0];
        }
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                temp = grid[i][j];
                col_max[j] = col_max[j] > temp ? col_max[j] : temp;
                row_max[i] = row_max[i] > temp ? row_max[i] : temp;
            }
        }

        int sum = 0;
        int max = 0;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                max = col_max[j] < row_max[i] ? col_max[j] : row_max[i];
                sum += (max - grid[i][j]);
            }
        }
        return sum;
    }

    public int findUnsortedSubarray(int[] nums) {
        int begin = -1;
        int end = 0;
        int len = nums.length;
        int[] temp = new int[len];
        System.arraycopy(nums, 0, temp, 0, len);
        Arrays.sort(temp);
        for (int i = 0; i < len; i++) {
            if (temp[i] != nums[i]) {
                if (begin == -1) begin = i;
                else end = i;
            }
        }
        return end - begin + 1;
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = Integer.MAX_VALUE;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    int newsum = nums[i] + nums[j] + nums[k];
                    if (newsum == 0) return 0;
                    if (Math.abs(newsum - target) < Math.abs(sum - target)) sum = newsum;
                }
            }
        }
        return sum;
    }

    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        if (buckets == 1) return 0;
        int base = minutesToTest / minutesToDie + 1;
        int r = 1;
        for (int i = 1; true; i++) {
            r *= base;
            if (r >= buckets) return i;
        }
    }

    public int countBinarySubstrings(String s) {
        char[] arr = s.toCharArray();
        int len = arr.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (countZero(arr, i, j)) count++;
            }
        }
        return count;
    }

    public void recoverTree(TreeNode root) {
        List<TreeNode> listNodes = new ArrayList<>();
        addToList(root, listNodes);
        int len = listNodes.size();
        int fir = -1;
        int sec = -1;
        for (int i = 1; i < len; i++) {
            if (listNodes.get(i).val < listNodes.get(i - 1).val) {
                if (fir == -1) fir = i;
                else sec = i;
                if (sec != -1) break;
                ;
            }
        }
        swap(listNodes.get(fir), listNodes.get(sec));
    }

    public void swap(TreeNode p, TreeNode q) {
        int temp = p.val;
        p.val = q.val;
        q.val = temp;
    }

    public boolean countZero(char[] arr, int begin, int end) {
        int one = 0;
        int zero = 0;
        for (int i = begin; i <= end; i++) {
            if (arr[i] == '1') one++;
            else zero++;
        }
        return one == zero;
    }

    public int[] sortArrayByParity(int[] A) {
        int len = A.length;
        int[] temp = new int[len];
        int index = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (A[i] % 2 == 0) temp[index++] = A[i];
            else list.add(A[i]);
        }
        for (Integer in : list) {
            temp[index++] = in;
        }
        return temp;
    }

    public int nthMagicalNumber(int N, int A, int B) {
        int count = 0;
        long num = A > B ? B : A;
        while (true) {
            if (num % A == 0 || num % B == 0) count++;
            if (count == N) break;
        }
        return (int) (num % (Math.pow(10, 9) + 7));
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public boolean checkPossibility(int[] nums) {
        int sum = 0;
        int len = nums.length;
        int max = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i - 1] > nums[i]) {
                sum++;
            }
        }
        return sum <= 1;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<Integer>();
        int slen = s.length();
        int plen = p.length();
        String newone = null;
        char[] arr;
        for (int i = 0; i < slen - plen + 1; i++) {
            newone = s.substring(i, i + plen);
            System.out.println(newone);
            if (isAnagram(newone, p))
                list.add(i);
        }
        return list;
    }

    public int uniqueMorseRepresentations(String[] words) {
        int len = words.length;
        int arrlen;
        char[] arr;
        String s = "";
        Set<String> set = new HashSet<>();
        String[] code = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        for (int i = 0; i < len; i++) {
            arr = words[i].toCharArray();
            arrlen = arr.length;
            for (int j = 0; j < arrlen; j++) {
                s += code[arr[j] - 97];
            }
            set.add(s);
        }
        return set.size();
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode nodes = head;
        ListNode[] sec = {head.next, head.next};
        while (nodes.next != null && sec[0].next != null) {
            nodes.next = sec[0].next;
            nodes = nodes.next;
            if (nodes.next == null) break;
            sec[0].next = nodes.next;
            sec[0] = sec[0].next;
        }
        nodes.next = sec[1];
        return head;
    }

    public boolean detectCapitalUse(String word) {
        char[] arr = word.toCharArray();
        int sum = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            if (Character.isUpperCase(arr[i]))
                sum++;
        }
        if (Character.isUpperCase(arr[0]) && sum == 1) return true;
        if (sum == len) return true;
        if (sum == 0) return true;
        return false;
    }

    public char findTheDifference(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        char ch = ' ';
        char[] sArr = new char[26];
        int val;
        for (int i = 0; i < sLen; i++)
            sArr[s.charAt(i)]++;
        for (int j = 0; j < tLen; j++) {
            val = --sArr[t.charAt(j)];
            if (val < 0) {
                ch = t.charAt(j);
                break;
            }
        }
        return ch;
    }

    public boolean hasAlternatingBits(int n) {
        int expect;
        if ((n & 1) == 0) expect = 1;
        else expect = 0;
        while (n != 0) {
            n = n >> 1;
            if ((n & 1) != expect) return false;
            expect = expect == 1 ? 0 : 1;
        }
        return true;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List> map = new HashMap<>();
        int len = strs.length;
        List list;
        String sort;
        for (int i = 0; i < len; i++) {
            sort = sortstr(strs[i]);
            list = map.get(sort);
            if (list == null) {
                list = new ArrayList();
                list.add(strs[i]);
                map.put(sort, list);
            } else {
                list.add(strs[i]);
            }
        }
        List toreturn = new ArrayList();
        for (List l1 : map.values())
            toreturn.add(l1);
        return toreturn;
    }

    public String sortstr(String str) {
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    public int minDiffInBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Integer i1 = (Integer) o1;
                Integer i2 = (Integer) o2;
                return i1.compareTo(i2);
            }
        });
        int sub = Integer.MAX_VALUE;
        int val;
        int len = list.size();
        for (int i = 1; i < len; i++) {
            val = Math.abs(list.get(i) - list.get(i - 1));
            if (sub > val) sub = val;
            if (sub == 0) break;
        }
        return sub;

    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        int bool = isSameNode(p, q);
        if (bool == 0) return true;
        if (bool == 2) return false;
        bool = isSameNode(p.left, q.left);
        if (bool == 2) return false;
        bool = isSameNode(p.right, q.right);
        if (bool == 2) return false;
        return true;
    }

    public int kIsNotZero(int[] nums, int k) {
        Set<Integer> set = new HashSet();
        int len = nums.length;
        for (int i = 0; i < len; i++)
            set.add(nums[i]);
        int sum = 0;
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            Integer in = it.next();
            if (set.contains(in + k)) sum++;
        }
        return sum;
    }

    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        List<List> list = new ArrayList();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    List ex = new ArrayList();
                    ex.add(i);
                    ex.add(j);
                    list.add(ex);
                }
            }
        }
        for (List<Integer> l1 : list) {
            setRowToZero(matrix, l1.get(0));
            setColToZero(matrix, l1.get(1));
        }
    }

    public void setRowToZero(int[][] matrix, int row) {
        int len = matrix[0].length;
        for (int j = 0; j < len; j++)
            matrix[row][j] = 0;
    }

    public void setColToZero(int[][] matrix, int col) {
        int len = matrix.length;
        for (int i = 0; i < len; i++)
            matrix[i][col] = 0;
    }

    public int kIsZero(int[] nums) {
        int len = nums.length;
        int sum = 0;
        boolean guard = false;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; nums[j] != 0 && j < len; j++) {
                if (nums[i] == nums[j]) {
                    if (!guard) {
                        guard = true;
                        sum++;
                    }
                    nums[j] = 0;
                }
            }
            guard = false;
        }
        return sum;
    }

    public int findPairs(int[] nums, int k) {
        if (k == 0) return kIsZero(nums);
        else return kIsNotZero(nums, k);
    }
//    public void addTreeNodeToList(Node root, List<Integer> list)
//    {
//        if(root==null)  return;
//        //List<Node> l1=root.children;
//        list.add(root.val);
//        for(Node temp:l1) {
//            addTreeNodeToList(temp,list);
//        }
//        list.add(root.val);
//    }

    public boolean hasSameChar(String s1, String s2) {
        int len;
        String check = "";
        String forCheck = "";
        if (s1.length() > s2.length()) {
            len = s2.length();
            check = s2;
            forCheck = s1;
        } else {
            len = s1.length();
            check = s1;
            forCheck = s2;
        }
        for (int i = 0; i < len; i++) {
            if (forCheck.indexOf(check.charAt(i)) >= 0) return false;
        }
        return true;
    }

    public int search(int[] nums, int target) {
        int begin = 0;
        int end = nums.length - 1;
        int mid = (begin + end) / 2;
        while (begin < end) {
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) {
                begin = mid + 1;
                mid = (begin + end) / 2;
            } else {
                end = mid - 1;
                mid = (begin + end) / 2;
            }
        }
        return -1;
    }

    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList();
//        addTreeNodeToList(root,list);
        int len = list.size();
        for (int i = 0; i < len; i++) {
            List list1 = new ArrayList();
            list1.add(list.get(i));
            list.add(list.get(i));
            if (list.contains(k - list.get(i))) {
                if (list.get(i) * 2 == k && !list.containsAll(list1)) continue;
                return true;
            }
        }
        return false;
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public int singleNonDuplicate(int[] nums) {
        int pre = nums[0];
        int len = nums.length;
        int count = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] == pre) {
                count++;
            } else if (nums[i] != pre) {
                if (count != 2) {
                    return pre;
                }
                pre = nums[i];
                count = 1;
            }
        }
        return -1;
    }

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return root;
        if (root.val < L || root.val > R) {
            TreeNode a = trimBST(root.left, L, R);
            TreeNode b = trimBST(root.right, L, R);
            return a == null ? b : a;
        }
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int len = nums.length;
        int[] right = new int[len];
        int val;
        for (int i = 0; i < len; i++) {
            val = ++right[nums[i]];
            if (val == 2) list.add(nums[i]);
        }
        return list;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int len = nums.length;
        int[] arr = new int[len + 1];
        List<Integer> list = new ArrayList<>(len);
        System.arraycopy(nums, 0, list, 0, len);
        for (int i = 0; i < len; i++) {
            list.remove(nums[i]);
        }
        ((ArrayList<Integer>) list).trimToSize();
        return list;

    }

//    public List<Integer> postorder(Node root) {
//        List<Integer> l=new ArrayList<>();
//        addTreeNodeToList(root,l);
//        return l;
//    }

    public boolean contains(int[] arr, int val) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            if (arr[i] == val) return true;
        }
        return false;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List list = new ArrayList();
//        addTreeNodeToList(root,list);
        return list;
    }

    public int isSameNode(TreeNode p, TreeNode q) {
        if (p == null && q == null) return 0;
        if (p != null && q != null && p.val == q.val) return 1;
        return 2;
    }

    public int findContentChildren(int[] g, int[] s) {
        Map<Integer, Integer> map = new TreeMap<>();
        int sLen = s.length;
        int max = s[sLen - 1];
        for (int i = 0; i < sLen; i++) {
            int sVal = s[i];
            Integer temp = map.get(sVal);
            if (temp == null) map.put(sVal, 1);
            else map.put(sVal, ++temp);
        }
        int count = 0;
        int gLen = g.length;
        for (int i = 0; i < gLen; i++) {
            int temp = g[i];
            Integer j = map.get(temp);
            while (j != null && temp <= max) {
                temp++;
                j = map.get(temp);
            }
            if (j != null) {
                if (j == 1) map.put(temp, null);
                else map.put(temp, --j);
                count++;
            } else if (temp > max) {
                break;
            }
        }
        return count;
    }

    public boolean isValidSerialization(String preorder) {
        String[] arr = preorder.split(",");
        int count = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            if (!arr[i].equalsIgnoreCase("#")) {
                if (count != 1 || count % 2 != 0) return false;
                else count = 0;
            }
        }
        if (count != 1 || count % 2 != 0) return false;
        return true;
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] temp = new int[len];
        for (int i = 0; i < len; i++) {
            int j = i + 1;
            for (; j < len; j++) {
                if (temperatures[j] > temperatures[i]) break;
            }
            if (j != len) temp[i] = j;
            else temp[i] = 0;
        }
        return temp;
    }

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int row = nums.length;
        if (row == 0) return nums;
        int col = nums[0].length;
        int[][] matrix = new int[r][c];
        if (row * col < r * c) return nums;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                queue.offer(nums[i][j]);
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrix[i][j] = queue.poll();
            }
        }
        return matrix;

    }

    public int maxArea(int[] height) {
        int len = height.length;
        int max = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                max = Integer.max(max, area(height, i, j));
            }
        }
        return max;
    }

    public int findNthDigit(int n) {
        StringBuffer sb = new StringBuffer();
        int index = 0;
        while (true) {
            index++;
            sb.append(index);
            if (sb.length() == n) break;
        }
        return Integer.parseInt(String.valueOf(sb.charAt(n - 1)));
    }

    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int len = arr.length;
        int begin = 0;
        int end = len - 1;
        while (begin < end) {
            while (begin < end && !isVowels(arr[begin])) {
                begin++;
            }
            while (end > begin && !isVowels(arr[end])) {
                end--;
            }
            exchange(begin, end, arr);
        }
        return new String(arr);
    }

    public void exchange(int i, int j, char[] arr) {
        char ch = arr[i];
        arr[i] = arr[j];
        arr[j] = ch;
    }

    public boolean isVowels(char ch) {
        switch (ch) {
            //大写
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
            default:
                return false;
        }
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {

        }
        return false;
    }

    public int[] asteroidCollision(int[] asteroids) {
        return null;
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int count = 0;
        for (int i = 1; i < len - 1; i++) {
            if (i == 0 && flowerbed[i] == 0 && flowerbed[i + 1] == 0) count++;
            else if (i == len - 1 && flowerbed[i - 1] == 0 && flowerbed[i] == 0) count++;
            else if (flowerbed[i - 1] == 0 && flowerbed[i] == 0 && flowerbed[i + 1] == 0) count++;
        }
        return count == n;
    }

    public void flatten(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        addToList(root, list);
        int len = list.size();
        root = new TreeNode(list.get(0));
        for (int i = 1; i < len; i++) {
            root.right = new TreeNode(list.get(i));
            root = root.right;
        }
    }

    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            Integer temp = map.get(nums[i]);
            if (temp == 0) map.put(nums[i], i);
            else {
                if (i - temp <= k) return true;
                else map.put(nums[i], i);
            }
        }
        return false;
    }

    public int longestMountain(int[] a) {
        int len = a.length;
        if (len < 3) return 0;
        int begin = -1;
        int mid = -1;
        int end = -1;
        if (a[1] > a[0]) begin = 0;
        int pre = a[1];
        int max = 0;
        for (int i = 2; i < len; i++) {
            if (a[i] > pre) {
                if (begin == -1) begin = i - 1;
                else {
                    max = Integer.max(max, i - 1 - begin);
                    begin = -1;
                    mid = -1;
                    end = -1;
                }
            } else if (a[i] < pre) {
                if (begin != 1 && mid == -1) mid = i - 1;
            }
        }
        if (begin != -1 && mid != -1) max = Integer.max(max, len - begin);
        return max;
    }

    public int lengthOfLastWord(String s) {
        String[] strs = s.split(" ");
        if (strs.length == 0) return 0;
        else return strs[strs.length - 1].length();
    }

    public boolean judgeSquareSum(int c) {
        int extract = (int) Math.ceil(Math.pow(c, 0.5));
        System.out.println(extract);
        for (int i = 0; i <= extract; i++) {
            for (int j = extract; j >= 0; j--) {
                int temp = sum(i, j);
                if (temp == c) return true;
                else if (temp < c) break;
            }
        }
        return false;
    }

    public List<List<Integer>> generate(int numRows) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i <= numRows; i++) {
            list.add(1);
            lists.add(new ArrayList<>(list));
            for (int j = i - 1; j >= 1; j--) {
                list.set(j, list.get(j) + list.get(j - 1));
            }
        }
        return lists;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //even：整除和前一个数
        //odd：整除结果
        int l1 = nums1.length;
        int l2 = nums2.length;
        boolean odd = true;
        int mid = (l1 + l2) / 2;
        int pre = -1;
        if ((l1 + l2) % 2 == 0) {
            odd = false;
            pre = mid - 1;
        }
        int count = 0;
        int[] num = new int[l1 + l2];
        int index1 = 0;
        int index2 = 0;
        while (index1 < l1 && index2 < l2) {
            if (count == mid) return odd ? num[count - 1] : (num[count - 1] + num[count - 2]) / 2;
            if (nums1[index1] < nums2[index2]) {
                num[count++] = nums1[index1++];
            } else if (nums1[index1] > nums2[index2]) {
                num[count++] = nums2[index2];
            } else {
                num[count++] = nums1[index1++];
                num[count++] = nums2[index2];
            }
        }
        while (index1 < l1) {
            if (count == mid) return odd ? num[count - 1] : (num[count - 1] + num[count - 2]) / 2;
            num[count++] = nums1[index1++];
        }
        while (index2 < l2) {
            if (count == mid) return odd ? num[count - 1] : (num[count - 1] + num[count - 2]) / 2;
            num[count++] = nums2[index2++];
        }
        return -1;
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        addToList(root1, l1);
        addToList(root2, l2);
        if (l1.size() != l2.size()) return false;
        int len = l1.size();
        for (int i = 0; i < len; i++) {
            if (!l1.get(i).equals(l2.get(i))) return false;
        }
        return true;
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            list.add(1);
            for (int j = i - 1; j >= 1; j--) {
                list.set(j, list.get(j) + list.get(j - 1));
            }
        }
        return list;
    }

    public int findComplement(int num) {
        int i = 0;
        int j = 0;
        while (i < num) {
            i += Math.pow(2, j);
            j++;
        }
        return i - num;
    }

    public int calPoints(String[] ops) {
        int len = ops.length;
        Stack<Integer> stack = new Stack<>();
        for (String op : ops) {
            if (ops.equals("C")) stack.pop();
            else if (ops.equals("D")) stack.push(stack.peek() * 2);
            else if (ops.equals("+")) stack.push(stack.get(stack.size() - 1) + stack.get(stack.size() - 1));
            else stack.push(Integer.parseInt(op));
        }
        System.out.println(stack);
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }

    public int sum(int i, int j) {
        return (int) Math.pow(i + j, 2) - 2 * i * j;
    }

    public int area(int[] height, int i, int j) {
        return Integer.min(height[i], height[j] * (j - i));
    }

    public String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> set = new HashSet<>();
        String longest = "";
        for (String word : words) {
            if (word.length() == 1 || set.contains(word.substring(0, word.length() - 1))) {
                longest = word.length() > longest.length() ? word : longest;
                set.add(word);
            }
        }
        return longest;
    }

    public String largestNumber(int[] nums) {
        int len = nums.length;
        String[] strs = new String[len];
        for (int i = 0; i < len; i++) {
            strs[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) return o2.compareTo(o1);
                return (o2 + o1).compareTo(o1 + o2);
            }
        });
        StringBuffer sb = new StringBuffer();
        for (String str : strs) {
            sb.append(str);
        }
        if (sb.charAt(0) == '0') return "0";
        return sb.toString();
    }

    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return isPalindrome(list);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> listNodes = new ArrayList<>();
        ListNode temp = head;
        while (head != null) {
            listNodes.add(head);
            head = head.next;
        }
        int len = listNodes.size();
        if (len == n) {
            temp = temp.next;
        } else {
            ListNode pre = listNodes.get(listNodes.size() - n - 1);
            pre.next = pre.next == null ? null : pre.next.next;
        }
        return temp;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = i + 2; k < len; k++) {
                    for (int h = i + 3; h < len; h++) {
                        if (nums[i] + nums[j] + nums[h] + nums[k] == target) {
                            List<Integer> list = new ArrayList<>();
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(nums[h]);
                            list.add(nums[k]);
                            Collections.sort(list);
                            if (!lists.contains(list)) lists.add(list);
                        }
                    }
                }
            }
        }
        return lists;
    }

    public boolean isPalindrome(List<Integer> list) {
        int len = list.size();
        int mid = len / 2;
        int begin = (len % 2 == 0) ? mid : mid + 1;
        int end = mid - 1;
        while (begin < len && end >= 0) {
            System.out.println(list.get(begin) + " " + list.get(end));
            if (list.get(begin) != list.get(end)) return false;
            begin++;
            end--;
        }
        return true;
    }

    public boolean isPalindrome(String s) {
        s = s.replaceAll("[^A-Za-z0-9]", "");
        s = s.toLowerCase();
        int len = s.length();
        int mid = len / 2;
        int begin = (len % 2 == 0) ? mid : mid + 1;
        int end = mid - 1;
        while (begin < len && end >= 0) {
            if (s.charAt(begin) != s.charAt(end)) return false;
            begin++;
            end--;
        }
        return true;
    }

    public boolean validPalindrome(String s) {
        int len = s.length();
        int begin = 0;
        int end = len - 1;
        int count = 0;
        while (begin < end) {
            char c1 = s.charAt(begin);
            char c2 = s.charAt(end);
            if (c1 != c2) {
                if (count != 0) return false;
                count++;
                if (begin < end - 1 && s.charAt(begin + 1) == c2) {
                    begin++;
                } else if (end > begin + 1 && s.charAt(end - 1) == c1) {
                    end--;
                }
            }
        }
        return true;
    }

    public boolean judge(String s, int begin, int end) {
        while (begin < end) {
            if (s.charAt(begin) != s.charAt(end)) return false;
            begin++;
            end--;
        }
        return true;
    }

    public String[] findWords(String[] words) {
        String[] strs = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        List<List<Character>> lists = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            char[] arr = strs[i].toCharArray();
            List<Character> list = new ArrayList<>();
            for (char c : arr) {
                list.add(c);
            }
            lists.add(list);
        }
        String s = "";
        for (String word : words) {
            for (List<Character> list : lists) {
                if (isSubSeq(list, word.toLowerCase())) {
                    s += word + " ";
                    break;
                }
            }
        }
        return s.split(" ");
    }

    public boolean isSubSeq(List<Character> list, String s) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (!list.contains(s.charAt(i))) return false;
        }
        return true;
    }

    public boolean repeatedSubstringPattern(String s) {
        int[] arr = new int[26];
        char[] sarr = s.toCharArray();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            arr[sarr[i] - 'a']++;
        }
        int sub = arr[0];
        for (int i : arr) {
            if (i != sub) return false;
        }
        return true;
    }

    public int evalRPN(String[] tokens) {
        Stack<String> strs = new Stack<>();
        int len = tokens.length;
        for (int i = 0; i < len; i++) {
            String temp = tokens[i];
            if (temp.equals("+")) {
                int sec = Integer.parseInt(strs.pop());
                int fir = Integer.parseInt(strs.pop());
                strs.push(Integer.toString(fir + sec));
            } else if (temp.equals("-")) {
                int sec = Integer.parseInt(strs.pop());
                int fir = Integer.parseInt(strs.pop());
                strs.push(Integer.toString(fir - sec));
            } else if (temp.equals("*")) {
                int sec = Integer.parseInt(strs.pop());
                int fir = Integer.parseInt(strs.pop());
                strs.push(Integer.toString(fir * sec));
            } else if (temp.equals("/")) {
                int sec = Integer.parseInt(strs.pop());
                int fir = Integer.parseInt(strs.pop());
                strs.push(Integer.toString(fir / sec));
            } else {
                strs.push(tokens[i]);
            }
        }
        return Integer.parseInt(strs.pop());
    }

    public double findMaxAverage(int[] nums, int k) {
        int len = nums.length;
        double max_aver = Double.MIN_VALUE;
        int sum = 0;
        int i = 0;
        for (; i < len; i++) {
            sum += nums[i];
            if (i >= k) {
                sum -= nums[i - k];
                max_aver = Double.max(max_aver, sum * 1.0 / k);
            }
            System.out.println(sum + " " + max_aver);
        }
        return Double.max(max_aver, sum * 1.0 / k);
    }

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        String s = Integer.toString(x);
        return isPalindrome(s);
    }

    public String optimalDivision(int[] nums) {
        int len = nums.length;
        if (len == 1) return "" + nums[0];
        else if (len == 2) return nums[0] + "/" + nums[1];
        else {
            StringBuffer sb = new StringBuffer();
            sb.append(nums[0] + "/(");
            for (int i = 1; i < len; i++) {
                sb.append(nums[i] + "/");
            }
            sb.append(")");
            return sb.toString();
        }
    }

    public int pivotIndex(int[] nums) {
        int len = nums.length;
        if (len == 0) return -1;
        int[] left_sum = new int[len];
        int[] right_sum = new int[len];
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            left_sum[i] = sum;
        }
        sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            sum += nums[i];
            right_sum[i] = sum;
        }
        for (int i = 0; i < len - 2; i++) {
            if (left_sum[i] == right_sum[i + 2]) return nums[i];
        }
        return -1;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        levelOrder(root, -1, lists);
        return lists;
    }

    public int findNumberOfLIS(int[] nums) {
        int len = nums.length;
        int pre = nums[0];
        int count = 1;
        int max = 0;
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 1; i < len; i++) {
            if (nums[i] >= pre) count++;
            else {
                Integer temp = map.get(count);
                if (temp == null) temp = 0;
                map.put(count, ++temp);
                count = 1;
                pre = nums[i];
            }
        }
        Integer temp = map.get(count);
        if (temp == null) temp = 0;
        map.put(count, ++temp);
        return map.get(((TreeMap<Integer, Integer>) map).firstKey());
    }

    public int findLengthOfLCIS(int[] nums) {
        int len = nums.length;
        int pre = nums[0];
        int count = 1;
        int max = 0;
        for (int i = 1; i < len; i++) {
            if (nums[i] >= pre) count++;
            else {
                max = Integer.max(count, max);
                count = 1;
                pre = nums[i];
            }
        }
        return Integer.max(count, max);
    }

    public void levelOrder(TreeNode root, int depth, List<List<Integer>> lists) {
        if (root == null) return;
        depth++;
        if (lists.size() < depth)
            lists.add(new ArrayList<>());
        lists.get(depth - 1).add(root.val);
        levelOrder(root.right, depth, lists);
        levelOrder(root.left, depth, lists);
    }

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if (rec1[0] >= rec2[2] || rec1[2] <= rec2[0] || rec1[1] >= rec2[3] || rec1[3] <= rec2[1])
            return false;
        else return true;
    }

    public int findLHS(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        Arrays.sort(nums);
        int max = 0;
        int present_ele = nums[0];
        int present_count = 0;
        int count = 1;
        int sum = 0;
        for (int i = 1; i < len; i++) {
            if (present_ele != nums[i]) {
                present_ele = nums[i];
                present_count = count;
                count = 1;
                if (sum == 0) sum = present_count;
                else {
                    max = Integer.max(max, sum + present_count);
                    sum = present_count;
                }
            } else {
                count++;
            }
        }
        max = Integer.max(max, count + sum);
        return max;
    }

    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int begin = -1;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int max_negative = max;
        boolean always_negative = true;
        for (int i = 0; i < len; i++) {
            int temp = nums[i];
            if (temp < 0) {
                if (sum + temp < 0) {
                    begin = -1;
                    sum = 0;
                } else {
                    sum += temp;
                }
            } else {
                always_negative = false;
                if (begin == -1) begin = i;
                sum += temp;
            }
            max = Integer.max(sum, max);
            max_negative = Integer.max(max_negative, temp);
        }

        return always_negative ? max_negative : max;
    }

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        if (A <= E && C >= G && B <= F && D >= H) {
            return Math.abs((F - H) * (E - G));
        } else if (A >= E && C >= G && B >= F && D >= H) {
            return Math.abs((A - C) * (B - D));
        } else if (C <= E || A >= G) {
            return Math.abs((F - H) * (E - G)) + Math.abs((A - C) * (B - D));
        }
        int[] xIndex = {A, E, C, G};
        int[] yIndex = {B, D, F, H};
        Arrays.sort(xIndex);
        Arrays.sort(yIndex);
        int cover = Math.abs((xIndex[1] - xIndex[2]) * (yIndex[1] - yIndex[2]));
        int fir_area = (C - A) * (D - B);
        int sec_area = (G - E) * (H - F);
        return fir_area + sec_area - cover;
    }

    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int end = nums[len - 1];
        int forth = Integer.max(nums[0] * nums[1], nums[len - 2] * nums[len - 1]);
        return end * forth;
    }

    public int rob(int[] nums) {
        int len = nums.length;
        int max = -1;
        for (int i = 0; i < 2; i++) {
            int sum = nums[i];
            for (int j = i + 2; j < len; j += 2) {
                sum += nums[j];
            }
            max = Integer.max(sum, max);
        }
        return max;
    }

    public int maxProduct(int[] nums) {
        int sum = 0;
        int presum = 1;
        int negative = 0;
        int max = Integer.MIN_VALUE;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int temp = nums[i];
            if (temp == 0) {
                presum = 1;
                negative = 0;
                sum = 0;
            } else if (temp > 0) {
                if (sum <= 0) {
                    sum = temp;
                } else {
                    sum *= temp;
                }
            } else if (temp < 0) {
                if (negative == 0) {
                    negative = temp;
                    presum = sum == 0 ? 1 : sum;
                    sum = negative;
                } else {
                    sum = negative == sum ? presum * sum * temp : negative * presum * sum * temp;
                }
            }
            System.out.println(negative + " " + presum + " " + temp + " " + sum);
            max = Integer.max(max, sum);
        }
        return max;
    }

    public int findShortestSubArray(int[] nums) {
        int len = nums.length;
        if (len == 0) return -1;
        Map<Integer, int[]> map = new HashMap<>();
        int frequence = 0;
        for (int i = 0; i < len; i++) {
            int[] temp = map.get(nums[i]);
            if (temp == null) temp = new int[]{i, i, 1, 0};
            else {
                temp[1] = i;
                temp[2]++;
                temp[3] = i - temp[0];
            }
            if (temp[2] > frequence) frequence = temp[2];
            map.put(nums[i], temp);
        }
        int distance = Integer.MAX_VALUE;
        int ele = -1;
        Iterator<Map.Entry<Integer, int[]>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, int[]> e = it.next();
            System.out.println(e);
            int[] arr = e.getValue();
            if (arr[2] == frequence) {
                if (arr[3] < distance) {
                    distance = arr[3];
                }
            }
        }
        return distance;

    }

    public String addStrings(String num1, String num2) {
        char[] arr1 = num1.toCharArray();
        char[] arr2 = num2.toCharArray();
        int l1 = num1.length() - 1;
        int l2 = num2.length() - 1;
        int len = l1 >= l2 ? l1 : l2;
        StringBuffer s = new StringBuffer();
        int temp = len;
        int[] newone = new int[len];
        int carry = 0;
        while (l1 >= 0 && l2 >= 0) {
            newone[len] = (arr1[l1] - '0') + (arr2[l2] - '0') + carry;
            carry = largeThanTen(newone, len, carry);
            len--;
            l1--;
            l2--;
        }
        while (l1 >= 0) {
            newone[len] = (arr1[l1] - '0') + carry;
            carry = largeThanTen(newone, len, carry);
            len--;
            l1--;
        }
        while (l2 >= 0) {
            newone[len] = (arr2[l2] - '0') + carry;
            carry = largeThanTen(newone, len, carry);
            len--;
            l2--;
        }
        StringBuffer sb = new StringBuffer();
        if (carry == 1) s.append(1);
        for (int i = 0; i <= len; i++) s.append(newone[i]);
        return s.toString();
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        boolean[] bool = {false};
        hasPathSum(root, sum, bool, 0);
        return bool[0];
    }

    public void hasPathSum(TreeNode root, int sum, boolean[] bool, int subsum) {
        if (root == null) {
            if (sum == subsum) bool[0] = true;
        }
        subsum += root.val;
        hasPathSum(root.left, sum, bool, subsum);
        hasPathSum(root.right, sum, bool, subsum);
    }

    public int sumNumbers(TreeNode root) {
        List<Integer> list = new ArrayList();
        sumNumbers(root, 0, list);
        int sum = 0;
        for (Integer i : list)
            sum += i;
        return sum;
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List list = new ArrayList();
        List subList = new ArrayList();
        pathSum(root, 0, sum, subList, list);
        return list;
    }

    public void pathSum(TreeNode root, int subsum, int sum, List subList, List list) {
        if (root == null) return;
        subsum += root.val;

        if (root.left == null && root.right == null) {
            if (sum == subsum) {
                subList.add(root.val);
                list.add(new ArrayList(subList));
            }
            return;
        }

        subList.add(root.val);
        List ex = new ArrayList(subList);
        pathSum(root.left, subsum, sum, subList, list);
        pathSum(root.right, subsum, sum, ex, list);
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List list = new ArrayList();
        binaryTreePaths(root, new StringBuffer(), list);
        return list;
    }

    public void binaryTreePaths(TreeNode root, StringBuffer subList, List list) {
        if (root == null) return;
        if (subList.length() == 0) subList.append(root.val);
        else subList.append("->" + root.val);
        if (root.left == null && root.right == null) {
            list.add(subList.toString());
            return;
        }
        StringBuffer sb = new StringBuffer(subList);
        binaryTreePaths(root.left, subList, list);
        binaryTreePaths(root.right, sb, list);
    }

    //stack overflow
    public void pathSum(TreeNode root, int subsum, int sum, int[] arr) {
        if (root == null) return;
        subsum += root.val;
        pathSum(root, 0, sum, arr);
        if (subsum > sum) return;
        if (subsum == sum) {
            arr[0]++;
            return;
        }
        pathSum(root.left, subsum, sum, arr);
        pathSum(root.right, subsum, sum, arr);
    }

    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0) return -1;
        if (target < nums[0]) return 0;
        if (target > nums[nums.length - 1]) return nums.length;
        int len = nums.length;
        int i;
        int val;
        for (i = 0; i < len; i++) {
            val = nums[i];
            if (val == target) return i;
            if (val > target) return i + 1;
        }
        return -1;
    }

    public void sumNumbers(TreeNode root, int sum, List list) {
        if (root == null) list.add(sum);
        sum = sum * 10 + root.val;
        sumNumbers(root.left, sum, list);
        sumNumbers(root.left, sum, list);
    }

    public int largeThanTen(int[] arr, int index, int carry) {
        if (arr[index] >= 10) {
            arr[index] -= 10;
            carry = 1;
        } else {
            carry = 0;
        }
        return carry;
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        int count = 0;
        int i = 1;
        for (; count != n; i++) {
            if (isSuperUglyNumber(i, primes)) count++;
        }
        return i;
    }

    public int findSecondMinimumValue(TreeNode root) {
        int[] min = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        findSecondMinimumValue(root, min);
        return min[1];
    }

    public void findSecondMinimumValue(TreeNode root, int[] min) {
        if (root == null) return;
        if (root.val < min[0]) {
            min[1] = min[0];
            min[0] = root.val;
        } else if (root.val > min[0] && root.val < min[1]) {
            min[1] = root.val;
        }
        findSecondMinimumValue(root.left, min);
        findSecondMinimumValue(root.right, min);
    }

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList();
        addToList(root, list);
        return list.get(k - 1);
    }

    public int[] fairCandySwap(int[] A, int[] B) {
        int[] result = new int[2];
        int asum = sum(A);
        int bsum = sum(B);
        for (int i = 0; i < A.length; i++) {
            int a = A[i];
            for (int j = 0; j < B.length; j++) {
                int b = B[j];
                int tempA = asum - a + b;
                int tempB = bsum - b + a;
                if (tempA == tempB) {
                    result[0] = a;
                    result[1] = b;
                    break;
                }
            }
        }
        return result;
    }

    public int sum(int[] arr) {
        int sum = 0;
        for (int val : arr) sum += val;
        return sum;
    }

    public int maxChunksToSorted(int[] arr) {
        int len = arr.length;
        int count = 1;
        int leftMax = arr[0];
        int[] rightMin = new int[len];
        rightMin[len - 1] = arr[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            rightMin[i] = Math.min(arr[i], rightMin[i + 1]);
        }
        for (int i = 1; i < len; i++) {
            if (rightMin[i] >= leftMax) {
                count++;
                leftMax = arr[i];
            } else {
                leftMax = Math.max(arr[i], leftMax);
            }
        }
        return count;
    }

    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        if (row == 0) return 0;
        int col = matrix[0].length;
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int level = 0;
                while (squareOne(matrix, i, j, row, col, level)) level++;
                System.out.println(level);
                count = Math.max(count, level);
            }
        }
        return count * count;
    }

    public int findLength(int[] A, int[] B) {
        Map<Integer, List<Integer>> map = indexMap(A, B);
        int alen = A.length;
        int blen = B.length;
        int max = 0;
        for (int i = 0; i < alen; i++) {
            List<Integer> indexs = map.get(A[i]);
            for (int index : indexs) {
                int length = 1;
                while (sameEle(A, B, alen, blen, i, index, length)) length++;
                max = Math.max(max, length);
            }
        }
        return max;
    }


    public boolean sameEle(int[] A, int[] B, int alen, int blen, int i, int j, int length) {
        i = i + length;
        j = j + length;
        if (i >= alen || j >= blen) return false;
        return A[i] == B[j];
    }

    public Map<Integer, List<Integer>> indexMap(int[] A, int[] B) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int len = A.length;
        for (int i = 0; i < len; i++) {
            int tempa = A[i];
            List<Integer> list = map.get(tempa);
            if (list == null) list = new ArrayList<>();
            for (int j = 0; j < B.length; j++) {
                if (B[j] == tempa && !list.contains(j)) list.add(j);
            }
        }
        return map;
    }

    public boolean squareOne(char[][] matrix, int i, int j, int row, int col, int level) {
        if (level == 0) return matrix[i][j] == '1';
        int row_end = level + i;
        int col_end = level + j;
        System.out.println(row + " " + col + " " + row_end + " " + col_end);
        if (row_end >= row || col_end >= col) return false;
        for (int h = i; h <= row_end; h++) {
            for (int k = j; k <= col_end; k++) {
                if (matrix[h][k] == '0') return false;
            }
        }
        return true;
    }

    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(matrix[i], 1);
        }
        for (int i = 0; i < mines.length; i++) {
            matrix[mines[i][0]][mines[i][1]] = 0;
        }
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int level = 0;
                while (aroundOne(matrix, N, N, i, j, level)) level++;
                count = Math.max(count, level);
            }
        }
        return count;
    }

    public boolean aroundOne(int[][] matrix, int row, int col, int i, int j, int level) {
        return isOne(matrix, row, col, i - level, j) && isOne(matrix, row, col, i + level, j) && isOne(matrix, row, col, i, j - level) && isOne(matrix, row, col, i, j + level);
    }

    public boolean isOne(int[][] matrix, int row, int col, int i, int j) {
        if (i < 0 || i >= row) return false;
        if (j < 0 || j >= col) return false;
        return matrix[i][j] == 1;
    }

    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return 1;
        int begin = -1;
        int end = -1;
        int length = Integer.MIN_VALUE;
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (!list.contains(num)) list.add(num);
        }
        len = list.size();
        for (int i = 1; i < len; i++) {
            if (list.get(i) - list.get(i - 1) == 1) {
                if (begin == -1) begin = i - 1;
                end = i;
            } else {
                if (begin != -1) {
                    length = Math.max(length, end - begin + 1);
                    begin = -1;
                    end = -1;
                }
            }
        }
        if (begin != -1) length = Math.max(length, end - begin + 1);
        return length == Integer.MIN_VALUE ? 1 : length;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if (row == 0) return false;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == target) return true;
            }
        }
        return false;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        List<Integer> list = new ArrayList<>();
        for (ListNode listNode : lists) {
            while (listNode != null) {
                list.add(listNode.val);
                listNode = listNode.next;
            }
        }
        if (list.size() == 0) return null;
        Collections.sort(list);
        ListNode head = new ListNode(list.get(0));
        ListNode temp = head;
        int len = list.size();
        for (int i = 1; i < len; i++) {
            ListNode newone = new ListNode(list.get(i));
            temp.next = newone;
            temp = newone;
        }
        return head;
    }

    public int triangleNumber(int[] nums) {
        int len = nums.length;
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (nums[i] + nums[j] > nums[k]) count++;
                }
            }
        }
        return count;
    }

    public void addToList(TreeNode root, List list) {
        if (root == null) return;
        addToList(root.left, list);
        list.add(root.val);
        addToList(root.right, list);
    }

    public Node flatten(Node head) {
        Node temp = head;
//      gettail(head);
        return temp;
    }

    public int countPrimeSetBits(int L, int R) {
        int count = 0;
        for (int i = L; i < R; i++) {
            if (isPrimeSetBits(i)) count++;
        }
        return count;
    }

    public boolean isPrimeSetBits(int n) {
        char[] arr = Integer.toBinaryString(n).toCharArray();
        int count = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            if (arr[i] == '1') count++;
        }
        return isPrime(count);
    }

    public boolean isPrime(int n) {
        int half = (int) Math.pow(n, 0.5);
        for (int i = 2; i <= half; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int val = image[sr][sc];
        int row = image.length;
        int col = image[0].length;
        List<int[]> lists = new ArrayList<>();
        int temp = sr + 1;
        while (temp < row) {
            if (image[temp][sc] != val) break;
            lists.add(new int[]{temp, sc});
            image[temp][sc] = newColor;
            temp++;
        }
        temp = sr - 1;
        while (temp >= 0) {
            if (image[temp][sc] != val) break;
            lists.add(new int[]{temp, sc});
            image[temp][sc] = newColor;
            temp--;
        }
        temp = sc + 1;
        while (temp < col) {
            if (image[sr][temp] != val) break;
            lists.add(new int[]{sr, temp});
            image[sr][temp] = newColor;
            temp++;
        }
        temp = sc - 1;
        while (temp >= col) {
            if (image[sr][temp] != val) break;
            lists.add(new int[]{sr, temp});
            image[sr][temp] = newColor;
            temp--;
        }
        for (int[] arr : lists) {
            changeColor(image, arr[0], arr[1], row, col, val, newColor);
        }
        image[sr][sc] = newColor;
        return image;
    }

    public boolean isMonotonic(int[] A) {
        boolean increase = true;
        int index = 1;
        while (A[index] == A[index - 1]) {
            index++;
        }
        if (A[index - 1] > A[index]) increase = false;
        int len = A.length;
        if (index == len - 1) return false;
        for (int i = index; i < len; i++) {
            if (increase && A[i] < A[i - 1]) return false;
            else if (!increase && A[i] > A[i - 1]) return false;
        }
        return true;
    }

    public boolean backspaceCompare(String S, String T) {
        return rebuild(S).equals(rebuild(T));
    }

    public String rebuild(String str) {
        List<Character> list = new ArrayList<>();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) == '#') {
                if (list.size() != 0) list.remove(list.size() - 1);
            } else list.add(str.charAt(i));
        }
        StringBuffer sb = new StringBuffer();
        for (Character ch : list) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] arr = new int[26];
        int len = magazine.length();
        for (int i = 0; i < len; i++) {
            arr[magazine.charAt(i) - 'a']++;
        }
        len = ransomNote.length();
        for (int i = 0; i < len; i++) {
            if (--arr[ransomNote.charAt(i)] < 0) return false;
        }
        return true;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        List<ListNode> Alist = new ArrayList<>();
        List<ListNode> Blist = new ArrayList<>();
        while (headA != null) {
            Alist.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            Blist.add(headB);
            headB = headB.next;
        }
        int aIndex = Alist.size() - 1;
        int bIndex = Blist.size() - 1;
        while (aIndex >= 0 && bIndex >= 0 && Alist.get(aIndex).equals(Blist.get(bIndex))) {
            aIndex--;
            bIndex--;
        }
        if (aIndex < 0 || bIndex < 0) return null;
        return Alist.get(aIndex + 1);
    }

    public String convertToTitle(int n) {
        StringBuffer sb = new StringBuffer();
        while (n > 0) {
            char s = (char) ((n - 1) % 26 + 'A');
            sb.append(s);
            n = (n - 1) / 26;
        }
        return sb.reverse().toString();
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int num : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> temp = new ArrayList<>(res.get(i));
                temp.add(num);
                res.add(temp);
            }
        }
        return res;
    }

    public int[] countBits(int num) {
        int[] arr = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            arr[i] = sum_of_one(i);
        }
        return arr;
    }

    public int sum_of_one(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) count++;
            n = n >> 1;
        }
        return count;
    }

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        Map<Character, Character> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        char[] ori = pattern.toCharArray();
        for (String s : words) {
            char[] temp = s.toCharArray();
            if (temp.length != ori.length) continue;
            boolean match = true;
            for (int i = 0; match && i < temp.length; i++) {
                Character ch = map.get(ori[i]);
                if (ch == null) {
                    if (map.containsValue(temp[i])) match = false;
                    else map.put(ori[i], temp[i]);
                } else if (ch != temp[i]) match = false;
            }
            if (match) list.add(s);
            map.clear();
        }
        return list;
    }

    public int numberOfArithmeticSlices(int[] A) {
        int len = A.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 3; j < len; j++) {
                if (isArithmeticSlices(A, i, j)) count++;
                else break;
            }
        }
        return count;
    }

    public boolean isArithmeticSlices(int[] A, int begin, int end) {
        boolean arithmetic = true;
        Integer temp = null;
        for (int i = begin + 1; i < end; i++) {
            if (temp == null) temp = A[i] - A[i - 1];
            else if (A[i] - A[i - 1] == temp) {
                arithmetic = false;
                break;
            }
        }
        return arithmetic;
    }

    public int maxProduct(String[] words) {
        int max = 0;
        int len = words.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (!containsSameChar(words[i], words[j]))
                    max = Integer.max(max, words[i].length() * words[j].length());
            }
        }
        return max;
    }

    public String longestPalindrome(String s) {
        char[] arr = s.toCharArray();
        int len = arr.length;
        String str = null;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (isPalindrome(arr, i, j)) {
                    if (str == null || i - j + 1 > str.length()) str = s.substring(i, j);
                }
            }
        }
        return str;
    }

    public int countSubstrings(String s) {
        char[] arr = s.toCharArray();
        int len = arr.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (isPalindrome(arr, i, j)) count++;
            }
        }
        return count;
    }

    public boolean isPalindrome(char[] arr, int begin, int end) {
        while (begin < end) {
            if (arr[begin] != arr[end]) return false;
            begin++;
            end--;
        }
        return true;
    }

    public String replaceWords(List<String> dict, String sentence) {
        String[] words = sentence.split(" ");
        int len = words.length;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            String temp = words[i];
            for (String s : dict) {
                if (temp.indexOf(s) == 0) {
                    words[i] = s;
                    break;
                }
            }
            if (i == len - 1) sb.append(words[i]);
            else sb.append(words[i] + " ");
        }
        return sb.toString();
    }

    public boolean isSubsequence(String s, String t) {
        int pre = 0;
        int slen = s.length();
        int tlen = t.length();
        for (int i = 0; i < slen; i++) {
            int index = t.substring(pre, tlen).indexOf(s.charAt(i));
            if (index == -1) return false;
            pre = pre + index + 1;    //eeeeeee，不+1，会始终停留在第一个e
        }
        return true;
    }

    public List<Integer> partitionLabels(String S) {
        Map<Character, List<Integer>> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        addToMap(map, S);
        int len = S.length();
        char[] arr = S.toCharArray();
        int fir = -1;
        int sec = -1;
        for (int i = 0; i < len; i++) {
            List<Integer> indexs = map.get(arr[i]);
            if (i == sec) {
                list.add(sec - fir + 1);
                fir = -1;
                sec = -1;
                set.clear();
            }
            if (!set.contains(arr[i])) {
                set.add(arr[i]);
                int begin = indexs.get(0);
                int end = indexs.get(indexs.size() - 1);
                if (fir == -1) {
                    fir = begin;
                    sec = end;
                } else if (fir < begin && begin < end) {
                    if (sec < end) sec = end;
                }
            }
        }
        return list;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        if (!lists.contains(list)) lists.add(list);
                    }
                }
            }
        }
        return lists;
    }

    public int largestPalindrome(int n) {
        int top = (int) Math.pow(10, n) - 1;
        int bottom = top / 10;
        for (int i = top; i >= bottom; i--) {
            String s = top + "";
            long temp = Long.parseLong(s + new StringBuffer(s).reverse().toString());
            for (int j = bottom; j <= top; j++) {
                if (temp % j == 0) return (int) (temp % 1337);
            }
        }
        return 9;
    }

    public List<Integer> transList(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i : nums) {
            list.add(i);
        }
        return list;
    }

    public int coinChange(int[] coins, int amount) {
        int count = 0;
        for (int coin : coins) {
            while (amount > coin) {
                amount -= coin;
                count++;
            }
        }
        return amount == 0 ? count : -1;
    }

    public int numMatchingSubseq(String S, String[] words) {
        int count = 0;
        Map<Character, List<Integer>> map = new HashMap<>();
        addToMap(map, S);
        for (String word : words) {
            if (isSubSeq(map, word)) count++;
        }
        return count;
    }

    public ListNode partition(ListNode head, int x) {
        ListNode small = null;
        ListNode temp = null;
        ListNode temp_big = null;
        ListNode big = null;
        while (head != null) {
            if (head.val < x) {
                if (small == null) {
                    small = new ListNode(head.val);
                    temp = small;
                } else {
                    small.next = new ListNode(head.val);
                    small = small.next;
                }
            } else if (head.val >= x) {
                if (big == null) {
                    big = new ListNode(head.val);
                    temp_big = big;
                } else {
                    big.next = new ListNode(head.val);
                    big = big.next;
                }
            }
            head = head.next;
        }
        if (small != null) {
            small.next = temp_big;
        }
        if (small == null && big != null) return temp_big;
        return temp;
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] cost = new int[n];
        int len = logs.size();
        for (int i = 1; i < len; i++) {
            String[] fir = logs.get(i - 1).split(":");
            String[] latter = logs.get(i).split(":");
            int[] id = {Integer.parseInt(fir[0]), Integer.parseInt(latter[0])};
            int[] time = {Integer.parseInt(fir[2]), Integer.parseInt(latter[2])};
            if (fir[1].equals("start")) {
                if (latter[1].equals("start")) cost[id[0]] += (time[1] - time[0]);
                else cost[id[1]] += (time[1] - time[0] + 1);
            } else {
                if (id[0] == id[1]) {
                    cost[id[0]] += (time[1] - time[0]);
                } else {
                    cost[id[1]] += (time[1] - time[0]);
                }
            }
        }
        return cost;
    }

    public int maxProfit(int[] prices) {
        int len = prices.length;
        int sum = 0;
        int fir = -1;
        int sec = -1;
        for (int i = 1; i < len; i++) {
            if (prices[i] > prices[i - 1]) {
                if (fir == -1) {
                    fir = i - 1;
                    sec = i;
                } else {
                    sec = i;
                }
            } else {
                sum += (prices[sec] - prices[fir]);
                fir = -1;
                sec = -1;
            }
        }
        if (fir != -1) sum += (prices[sec] - prices[fir]);
        return sum;
    }

    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        List<Integer> sum = new ArrayList<>();
        int fir = -1;
        int sec = -1;
        for (int i = 1; i < len; i++) {
            if (prices[i] > prices[i - 1]) {
                if (fir == -1) {
                    fir = i - 1;
                    sec = i;
                } else {
                    sec = i;
                }
            } else {
                sum.add(prices[sec] - prices[fir]);
                fir = -1;
                sec = -1;
            }
        }
        if (fir != -1) sum.add(prices[sec] - prices[fir]);
        Collections.sort(sum);
        int temp = 0;
        for (int i = len - 1; i < len - k; i++) {
            temp += sum.get(i);
        }
        return temp;
    }

    public void fillArr(List<Integer> allfive, List<Integer> one_ten) {
        allfive.add(5);
        allfive.add(5);
        one_ten.add(10);
        allfive.add(5);
        one_ten.add(5);
    }

    public boolean lemonadeChange(int[] bills) {
        int len = bills.length;
        int[] count = {0, 0, 0};
        for (int bill : bills) {
            if (bill == 5) count[0]++;
            else if (bill == 10) {
                if (count[0] < 1) return false;
                count[0]--;
                count[1]++;
            } else if (bill == 20) {
                if (count[1] > 0 && count[0] > 0) {
                    count[1]--;
                    count[0]--;
                } else if (count[0] > 2) {
                    count[0] -= 3;
                } else return false;
                count[2]++;
            }
        }
        return true;
    }

    public String findLongestWord(String s, List<String> d) {
        Map<Character, List<Integer>> map = new HashMap<>();
        addToMap(map, s);
        String longest = "";
        for (String str : d) {
            if (isSubSeq(map, str)) {
                if (str.length() > longest.length() || ((str.length() == longest.length() && str.compareTo(longest) < 0)))
                    longest = str;
            }
        }
        return longest;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode fir = null;
        ListNode sec = null;
        ListNode pre = null;
        ListNode latter = null;
        ListNode temp = head;
        int index = 0;
        while (head != null) {
            if (index == m - 1) {
                pre = head;
            } else if (index == n + 1) {
                latter = head;
                break;
            }
            if (index == m) {
                sec = new ListNode(head.val);
                fir = sec;
            } else if (m < index && index <= n) {
//                ListNode temp=new ListNode(head.val);
                temp.next = fir;
                fir = temp;
            }
            head = head.next;
            index++;
        }
        pre.next = fir;
        sec.next = latter;
        return temp;
    }

    public List<Interval> merge(List<Interval> intervals) {
        int len = intervals.size();
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        for (int i = 0; i < len; i++) {
            Interval fir = intervals.get(i);
            if (fir == null) continue;
            for (int j = i + 1; j < len; j++) {
                Interval sec = intervals.get(j);
                if (sec == null) continue;
                if (fir.start <= sec.start && sec.start <= fir.end) {
                    intervals.set(i, new Interval(fir.start, sec.end));
                    intervals.set(j, null);
                }
            }
        }
        intervals.removeAll(Collections.singleton(null));
        return intervals;
    }

    public void addToMap(Map<Character, List<Integer>> map, String s) {
        int len = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < len; i++) {
            List<Integer> list = map.get(arr[i]);
            if (list == null) list = new ArrayList<>();
            list.add(i);
            map.put(arr[i], list);
        }
    }

    public boolean isSubSeq(Map<Character, List<Integer>> map, String word) {
        int pre = -1;
        char[] arr = word.toCharArray();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            List<Integer> list = map.get(arr[i]);
            if (list == null) return false;
            int temp = pre;
            for (Integer in : list) {
                if (pre < in) {
                    pre = in;
                    break;
                }
            }
            if (temp == pre) return false;
        }
        return true;
    }

    public boolean containsSameChar(String s1, String s2) {
        Map<Character, Integer> map = new HashMap<>();
        int l1 = s1.length();
        for (int i = 0; i < l1; i++) {
            map.putIfAbsent(s1.charAt(i), 1);
        }
        int l2 = s2.length();
        for (int j = 0; j < l2; j++) {
            if (map.get(s2.charAt(j)) != null) return true;
        }
        return false;
    }

    public boolean checkRecord(String s) {
        char[] arr = s.toCharArray();
        int len = arr.length;
        int acount = 0;
        int lcount = 0;
        boolean appreciate = true;
        for (int i = 0; i < len; i++) {
            if (arr[i] == 'A') acount++;
            else if (i < len && arr[i] == 'L') {
                while (arr[i] == 'L') {
                    lcount++;
                    i++;
                }
            }
            if (acount > 1 || lcount > 2) {
                appreciate = false;
                break;
            }
        }
        return appreciate;
    }

    public int titleToNumber(String s) {
        int len = s.length();
        int sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            sum += Math.pow(s.charAt(i) - 'A', len - i);
        }
        return sum;
    }

    public int dominantIndex(int[] nums) {
        int len = nums.length;
        int pre_biggest = -1;
        int biggest = -1;
        for (int i = 0; i < len; i++) {
            //pre_biggest,biggest为赋值时的逻辑
            if (pre_biggest == -1)
                pre_biggest = i;
            else if (pre_biggest != -1 && biggest == -1) {
                if (nums[pre_biggest] > nums[i]) {
                    biggest = pre_biggest;
                    pre_biggest = i;
                } else if (nums[pre_biggest] < nums[i]) {
                    biggest = i;
                }
            } else {//主逻辑，最大值
                if (nums[i] > nums[biggest]) {
                    pre_biggest = biggest;
                    biggest = i;
                } else if (nums[i] < nums[biggest] && nums[i] > nums[pre_biggest])    //第二大值
                {
                    pre_biggest = i;
                }
                //等于的情况不赋值
            }
        }
        if (biggest == -1) return pre_biggest;
        return nums[biggest] >= nums[pre_biggest] * 2 ? biggest : -1;
    }

    public String convertToBase7(int num) {
        boolean negative = false;
        if (num == 0) return "0";
        if (num < 0) negative = true;
        num = Math.abs(num);
        StringBuffer sb = new StringBuffer();
        while (num != 0) {
            sb.append(num % 7);
            num /= 7;
        }
        if (negative) sb.append('-');
        return sb.reverse().toString();
    }

    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        int sum = 0;
        int begin = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            if (begin == -1) begin = i;
            if (nums[i] >= s) {
                return 1;
            } else {
                sum += nums[i];
                if (sum > s) {
                    while (begin < i && sum >= s) {
                        sum -= nums[begin];
                        begin++;
                    }
                    min = Integer.min(min, i - (++begin) + 1);
                } else if (sum == s) min = Integer.min(min, i - begin + 1);
                System.out.println(sum + " " + min);
            }
        }
        if (sum > s) {
            while (begin < len && sum >= s) {
                sum -= nums[begin];
                begin++;
            }
            min = Integer.min(min, len - (++begin) + 1);
        } else if (sum == s) min = Integer.min(min, len - begin + 1);
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int mid = len / 2;
        int count = 0;
        for (int i = mid + 1; i < len; i++) {
            count += (nums[i] - nums[mid]);
        }
        for (int i = mid - 1; i >= 0; i++) {
            count += (nums[mid] - nums[i]);
        }
        return count;
    }

    public int minMoves(int[] nums) {
        int count = 0;
        int min_index = 0;
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (nums[i] < nums[min_index]) min_index = i;
        }
        for (int i = 0; i < len; i++) {
            count += (nums[i] - nums[min_index]);
        }
        return count;
    }

    public void changeColor(int[][] image, int sr, int sc, int row, int col, int oldColor, int newColor) {
        int temp = sr + 1;
        while (temp < row) {
            if (image[temp][sc] != oldColor) break;
            image[temp][sc] = newColor;
            temp++;
        }
        temp = sr - 1;
        while (temp >= 0) {
            if (image[temp][sc] != oldColor) break;
            image[temp][sc] = newColor;
            temp--;
        }
        temp = sc + 1;
        while (temp < col) {
            if (image[sr][temp] != oldColor) break;
            image[sr][temp] = newColor;
            temp++;
        }
        temp = sc - 1;
        while (temp >= col) {
            if (image[sr][temp] != oldColor) break;
            image[sr][temp] = newColor;
            temp--;
        }
    }

    public int sumOfLeftLeaves(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        sumOfLeftLeaves(root, list);
        int sum = 0;
        for (Integer i : list)
            sum += i;
        return sum;
    }

    public void sumOfLeftLeaves(TreeNode root, List list) {
        if (root == null) return;
        if (root.left != null) list.add(root.left.val);
        sumOfLeftLeaves(root.left, list);
        sumOfLeftLeaves(root.right, list);
    }

    public int maxCount(int m, int n, int[][] ops) {
        if (ops.length == 0) return m * n;
        int rowmin = ops[0][0];
        int colmin = ops[0][1];
        int len = ops.length;
        for (int i = 1; i < len; i++) {
            if (rowmin > ops[i][0]) rowmin = ops[i][0];
            if (colmin > ops[i][1]) colmin = ops[i][1];
        }
        return rowmin * colmin;
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> m1 = new HashMap<>();
        Map<String, Integer> m2 = new HashMap<>();
        int l1 = list1.length;
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < l1; i++) {
            m1.put(list1[i], i);
        }
        int min = Integer.MAX_VALUE;
        int l2 = list2.length;
        for (int j = 0; j < l2; j++) {
            Integer index = m1.get(list2[j]);
            if (index == null) m2.put(list2[j], -1);
            else {
                int val = index + j;
                m2.put(list2[j], val);
                min = Integer.min(min, val);
            }
        }
        for (int j = 0; j < l2; j++) {
            if (m2.get(list2[j]) == min) list.add(list2[j]);
        }
        return list.toArray(new String[list.size()]);

    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode temp = head;
        int now = head.val;
        int count = 1;
        ListNode newList = null;
        ListNode sub = null;
        while (head.next != null) {
            if (head.next.val != now && count <= 1) {
                if (newList == null) {
                    newList = new ListNode(now);
                    sub = newList;
                    now = head.next.val;
                    count = 1;
                } else {
                    ListNode node = new ListNode(now);
                    newList.next = node;
                    newList = node;
                }
            } else if (head.next.val == now) {
                count++;
            }
            head = head.next;
        }
        return sub;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode temp = head;
        while (temp != null && temp.next != null) {
            if (temp.val == temp.next.val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return head;
    }

    public boolean isSuperUglyNumber(int n, int[] primes) {
        if (n == 1) return true;
        int len = primes.length;
        boolean exist = false;
        while (n != 1) {
            for (int i = 0; i < len; i++) {
                if (n % primes[i] == 0) {
                    exist = true;
                    n /= primes[i];
                }
            }
            if (!exist) return false;
        }
        return true;
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l1.next;
        }
        int carry = 0;
        ListNode head = new ListNode(s1.pop() + s2.pop());
        carry = largeThanTen(head, carry);
        while (!s1.isEmpty() && !s2.isEmpty()) {
            ListNode node = new ListNode(s1.pop() + s2.pop() + carry);
            carry = largeThanTen(node, carry);
            node.next = head;
            head = node;
        }
        while (!s1.isEmpty()) {
            ListNode node = new ListNode(s1.pop());
            carry = largeThanTen(node, carry);
            node.next = head;
            head = node;
        }
        while (!s2.isEmpty()) {
            ListNode node = new ListNode(s2.pop());
            carry = largeThanTen(node, carry);
            node.next = head;
            head = node;
        }
        return head;
    }

    public int largeThanTen(ListNode node, int carry) {
        if (node.val >= 10) {
            node.val -= 10;
            carry = 1;
        } else {
            carry = 0;
        }
        return carry;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(l1.val + l2.val);
        ListNode temp = head;
        int carry = 0;
        carry = largeThanTen(head, carry);
        while (l1.next != null && l2.next != null) {
            l1 = l1.next;
            l2 = l2.next;
            ListNode node = new ListNode(l1.val + l2.val + carry);
            carry = largeThanTen(node, carry);
            head.next = node;
            head = head.next;
        }
        ListNode notnull = l1.next == null ? l2.next : l1.next;
        while (notnull != null) {
            ListNode node = new ListNode(notnull.val + carry);
            carry = largeThanTen(node, carry);
            head.next = node;
            head = head.next;
            notnull = notnull.next;
        }
        if (carry == 1) {
            ListNode node = new ListNode(1);
            head.next = node;
            head = head.next;
        }
        return temp;
    }

    public int[] findErrorNums(int[] nums) {
        int len = nums.length;
        int[] arr = new int[len + 1];
        int[] temp = new int[2];
        for (int i = 0; i < len; i++) {
            arr[nums[i]]++;
        }
        for (int i = 1; i < len; i++) {
            if (arr[i] == 0) temp[1] = i;
            if (arr[i] == 2) temp[0] = i;
        }
        return temp;
    }

    public int findDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (map.get(nums[i]) != null) {
                return nums[i];
            }
            map.put(nums[i], 0);
        }
        return -1;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode store = null;
        ListNode newone = null;
        int val;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                val = l1.val;
                l1 = l1.next;
            } else {
                val = l2.val;
                l2 = l2.next;
            }
            newone = new ListNode(val);
            if (head == null) {
                head = newone;
                store = head;
            } else {
                head.next = newone;
                head = head.next;
            }
        }
        while (l1 != null) {
            newone = new ListNode(l1.val);
            head.next = newone;
            head = head.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            newone = new ListNode(l2.val);
            head.next = newone;
            head = head.next;
            l2 = l2.next;
        }
        return store;
    }

    public String[] uncommonFromSentences(String A, String B) {
        String newone = A + " " + B;
        String[] arr = newone.split(" ");
        Map<String, Integer> map = new HashMap<>();
        Integer in;
        List<String> list = new ArrayList<String>();
        int len = arr.length;
        int sum = len;
        for (int i = 0; i < len; i++) {
            if (map.get(arr[i]) == null) map.put(arr[i], 1);
            else {
                in = map.get(arr[i]);
                map.put(arr[i], in++);
            }
        }
        for (int i = 0; i < len; i++) {
            if (map.get(arr[i]) == 1)
                list.add(arr[i]);
        }
        return (String[]) list.toArray();

    }

    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int len = numbers.length;
        int val;
        int[] arr = new int[2];
        for (int i = 0; i < len; i++) {
            map.put(numbers[i], i);
        }
        for (int i = 0; i < len; i++) {
            val = target - map.get(numbers[i]);
            if (map.get(val) == null) continue;
            arr[0] = i;
            arr[1] = map.get(val);
            break;
        }
        return arr;
    }

    public List<List<Integer>> largeGroupPositions(String s) {
        char begin = s.charAt(0);
        int fir = 0;
        int len = s.length();
        int size = 0;
        List<List<Integer>> total = new ArrayList<>();
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) != begin) {
                size = i - fir;
                if (size >= 3) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(fir);
                    list.add(i - 1);
                }
                fir = i;
            }
        }
        return total;
    }

    public int maxDistToClosest(int[] seats) {
        int one = -1;
        int two = -1;
        int len = seats.length;
        int i = 0;
        int max = 0;
        for (i = 0; i < len; i++) {
            if (seats[i] == 1) {
                one = i;
                break;
            }
        }
        if (i == len - 1) return len - 1;
        int dis;
        for (; i < len; i++) {
            if (seats[i] == 1) {
                dis = (i - one) / 2;
                if (dis > max)
                    max = dis;
                one = i;
            }
        }
        return max;
    }

    public int thirdMax(int[] nums) {
        if (nums.length < 3) {
            Arrays.sort(nums);
            return nums[nums.length - 1];
        }
        long min = Long.MIN_VALUE;
        long one = min;
        long two = min;
        long three = min;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] > one) {
                three = two;
                two = one;
                one = nums[i];
            } else if (nums[i] > two && nums[i] < one) {
                three = two;
                two = nums[i];
            } else if (nums[i] > three && nums[i] < two) {
                three = nums[i];
            }
        }
        if (three == min) return (int) one;
        else return (int) three;


    }

//    public int searchInsert(int[] nums, int target) {
//        int begin = 0;
//        int end = nums.length;
//        int mid = (end + begin) / 2;
//        if (target < nums[begin]) return 0;
//        else if (target > nums[end - 1]) return end;
//        int val = 0;
//        while (begin < end) {
//            val = nums[mid];
//            if (val == target)
//                return mid;
//            else if (val < target) {
//                begin = mid + 1;
//            } else if (val > target) {
//                end = mid - 1;
//            }
//            mid = (end + begin) / 2;
//        }
//        System.out.println(begin + " " + end + " " + mid);
//        return target > nums[mid] ? mid + 1 : mid;
//    }

    public double myPow(double x, int n) {
        int m = Math.abs(n);
        double sum = 1;
        for (int i = 0; i < m; i++)
            sum *= x;
        if (n < 0) return 1 / sum;
        return sum;
    }

    public boolean isPerfectSquare(int num) {
        int square = 0;
        for (int i = 0; i < num / 2 && square < num; i++) {
            square = i * i;
            if (square == num) return true;
        }
        return false;
    }

    public boolean isIsomorphic(String s, String t) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        if (sArr.length != tArr.length) return false;
        Map<Character, Character> map = new HashMap<>();
        int len = sArr.length;
        char ch;
        Object object;
        for (int i = 0; i < len; i++) {
            ch = sArr[i];
            object = map.get(ch);
            if (object == null) {
                if (map.containsValue(tArr[i]))
                    return false;
                else
                    map.put(ch, tArr[i]);
            } else if (!object.equals(tArr[i])) return false;
        }
        return true;
    }

    public boolean wordPattern(String pattern, String str) {
        String[] strs = str.split(" ");
        Map<Character, String> map = new HashMap<Character, String>();
        int len = pattern.length();
        if (len != strs.length) return false;
        char ch;
        Object object;
        for (int i = 0; i < len; i++) {
            ch = pattern.charAt(i);
            object = map.get(ch);
            if (object == null) {
                if (map.containsValue(strs[i]))
                    return false;
                else
                    map.put(ch, strs[i]);
            } else if (!object.equals(strs[i])) return false;
        }
        return true;
    }

    public int rotatedDigits(int N) {
        int val;
        int sum = 0;
        boolean same = true;
        int valid = 0;
        int present;
        for (int i = 1; i <= N; i++) {
            present = i;
            same = true;
            while (present != 0) {
                val = present % 10;
                valid = isValid(val);
                if (valid == 0) break;
                else if (valid == 2) same = false;
                present = present / 10;
            }
            if (!same && valid != 0) sum++;
        }
        return sum;
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int len = s.length();
        int[] ch = new int[26];
        Arrays.fill(ch, 0);
        for (int i = 0; i < len; i++) {
            ch[s.charAt(i) - 97]++;
        }

        for (int i = 0; i < len; i++) {
            if ((--ch[t.charAt(i) - 97]) < 0)
                return false;
        }
        return true;
    }

    int reverse(int x) {
        String s = Integer.toString(x);
        StringBuffer sb = new StringBuffer();
        int len = s.length();
        int begin = 0;
        if (s.charAt(0) == '-') {
            sb.append('-');
            begin = 1;
        }
        for (int i = len - 1; i >= begin; i++) {
            sb.append(s.charAt(i));

        }
        return Integer.parseInt(sb.toString());
    }

    public int isValid(int val) {
        switch (val) {
            case 1:
            case 0:
            case 8:
                return 1;
            case 2:
            case 5:
            case 6:
            case 9:
                return 2;
            default:
                return 0;
        }
    }

    public boolean rotateString(String A, String B) {
        String newone = A + B;
        if (newone.contains(A)) return true;
        return false;
    }

    public int[] numberOfLines(int[] widths, String S) {
        int line_sum = 0;
        int[] val = {0, 1};
        int len = widths.length;
        int alphb;
        for (int i = 0; i < len; i++) {
            alphb = widths[i];
            if (100 - line_sum < alphb) {
                line_sum = alphb;
                val[0]++;
            }
            line_sum += alphb;
        }
        val[1] = line_sum;
        return val;
    }

    public boolean isOneBitCharacter(int[] bits) {
        int len = bits.length;
        if (bits[len - 1] != 0) return false;
        int ones = 0;
        for (int i = len - 2; i >= 0; i++) {
            if (bits[i] != 1) break;
            ones++;
        }
        if (ones % 2 == 0) return false;
        return true;
    }

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] temp = new int[len];
        Arrays.fill(temp, 1);
        int right = 1;
        int left = 1;
        for (int i = 0; i < len; i++) {
            temp[i] = right * nums[i];
            right = temp[i];
        }
        for (int i = len - 1; i > 0; i++) {
            nums[i] = left * temp[i - 1];
            left = left * nums[i];
        }
        nums[0] = left;
        return nums;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int consecutive = 0;
        int times = nums[0] == 1 ? 1 : 0;
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (nums[i] == 0) {
                if (times > consecutive)
                    consecutive = times;
                times = 0;
            } else if (nums[i] == 1) {
                times++;
            }
        }
        return consecutive;
    }

    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        if (len == 1) return nums[0];
        int times = 1;
        for (int i = 0; i < len; i++) {
            if (i < len - 1 && nums[i + 1] != nums[i]) {
                if (times == 1)
                    return nums[i];
            }
        }
        return -1;
    }

    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int val = nums[0];
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (val + 1 != nums[1])
                return val + 1;
        }
        return -1;
    }

    public int majorityElement(int[] nums) {

        Arrays.sort(nums);
        int times = 1;
        int percent = 0;
        int num = nums[0];
        int major = num;
        int len = nums.length;
        int val;
        for (int i = 1; i < len; i++) {
            val = nums[i];
            if (val == num) {
                times++;
            }
            if (val != num) {
                if (times > percent) {
                    percent = times;
                    major = num;
                    num = val;
                }
                num = val;
                times = 1;
            }
        }

        if (times > percent) major = num;
        return major;
    }

    public int removeDuplicates_80(int[] nums) {
        int tail = 0;
        int len = nums.length;
        int times = 1;
        for (int i = 1; i < len; i++) {
            if (nums[tail] == nums[i] && times < 2) {
                tail++;
                times++;
                nums[tail] = nums[i];
            } else if (nums[i] > nums[tail]) {
                tail++;
                times = 1;
                if (tail == i) continue;
                nums[tail] = nums[i];
            } else if (nums[tail] > nums[i]) {

            }
        }
        return tail + 1;
    }

    public void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int removeDuplicates(int[] nums) {
        /*
        int tail=0;
       int len=nums.length;
       for(int i=1;i<len;i++)
       {
           if(nums[i]>nums[tail])
           {
               tail=tail+1;
               if(tail==i)    continue;
               nums[tail]=nums[i];

           }
       }
        return tail+1;
         */
        int tail = 0;
        int len = nums.length;
        int times = 1;
        for (int i = 1; i < len; i++) {
            if (nums[tail] == nums[i] && tail + 1 == i && times <= 2) {
                tail++;
            }
            if (nums[i] > nums[tail]) {
                tail = tail + 1;
                times = 0;
                if (tail == i) continue;
                nums[tail] = nums[i];
            }
        }
        return tail + 1;
    }

    public void moveZeroes(int[] nums) {
        int len = nums.length;
        int index = -1;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                index = i;
                break;
            }
        }
        if (index == -1) return;
        for (int i = index + 1; i < len; i++) {
            if (nums[i] == 0) continue;
            else {
                swap(index, i, nums);
                index++;
            }
        }

    }

    public int arrayPairSum(int[] nums) {
        if (nums == null)
            return 0;
        Arrays.sort(nums);
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i += 2)
            sum += nums[i];
        return sum;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length < k)
            return false;
        int len = nums.length;
        for (int i = 2; i < len; i++) {
            if (nums[i] == nums[i - 2])
                return true;
        }
        return false;
    }

    public void frequency(int[] arr, Map map) {
        int len = arr.length;
        Integer val;
        for (int i = 0; i < len; i++) {
            val = (Integer) map.get(arr[i]);
            if (val == null) map.put(arr[i], 1);
            else map.put(arr[i], ++val);
        }
    }

    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k + 1];
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap();
        frequency(nums, m);
        Map<Integer, Integer> map = new TreeMap<>();
        Set<Map.Entry<Integer, Integer>> s1 = m.entrySet();
        for (Map.Entry<Integer, Integer> e : s1) {
            map.put(e.getKey(), e.getValue());
        }
        List<Integer> list = new ArrayList();
        int size = map.size();
        int count = 0;
        while (count < k) {
            Map.Entry<Integer, Integer> entry = ((TreeMap<Integer, Integer>) map).pollLastEntry();
            list.add(entry.getValue());
            count++;
        }
        return list;
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums)
            set.add(i);
        if (nums.length != set.size())
            return true;
        return false;
    }

    public int peakIndexInMountainArray(int[] A) {
        int begin = 0;
        int end = A.length - 1;
        while (begin + 1 < end && A[begin] < A[begin])
            begin++;
        return begin;

    }

    public int[][] transpose(int[][] A) {
        int row = A.length;
        int col = A[0].length;
        int[][] a = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                a[i][j] = A[j][i];
            }
        }
        return a;
    }

    public String reverse(String s) {
        StringBuffer sb = new StringBuffer();
        int len = s.length();
        for (int i = len - 1; i >= 0; i++) {
            sb.append(s.charAt(i));
        }
        return sb.reverse().toString();
    }

    public List<Integer> temp(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (getSelfDivision(i))
                list.add(i);
        }
        return list;
    }

    public void frequency(String s, int[] arr) {
        int len = s.length();
        Integer val;
        for (int i = 0; i < len; i++) {
            arr[s.charAt(i) - 'a']++;
        }
    }

    public String frequencySort(String s) {
        int[] arr = new int[26];
        frequency(s, arr);
        Map<Integer, List> map = new HashMap<>();
        List list;
        for (int i = 0; i < 26; i++) {
            if (arr[i] == 0) continue;
            list = map.get(arr[i]);
            if (list == null) list = new ArrayList();
            list.add((char) i);
            map.put(arr[i], list);
        }
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<Integer, List>> set = map.entrySet();
        for (Map.Entry e : set) {
            int count = (Integer) e.getKey();
            List l = (List) e.getValue();
            int len = l.size();
            for (int i = 0; i < len; i++) {
                char ch = (Character) l.get(i);
                for (int j = 0; j < count; j++) sb.append(ch);
            }
        }
        return sb.toString();
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int len;
        int[] main, assist;
        if (l1 > l2) {
            len = l1;
            main = nums1;
            assist = nums2;
        } else {
            len = l2;
            main = nums2;
            assist = nums1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        Integer val;
        addMap(assist, map);
        List<Integer> list = new ArrayList();
        for (int i = 0; i < len; i++) {
            val = map.get(main[i]);
            if (val > 0) {
                list.add(val);
                map.put(main[i], --val);
            }
        }

        int[] temp = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            temp[i] = list.get(i);
        }
        return temp;
    }

    public int firstUniqChar(String s) {
        char[] arr = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int len = arr.length;
        Integer val;
        for (int i = 0; i < len; i++) {
            val = map.get(arr[i]);
            if (val == null) map.put(arr[i], i);
            else map.put(arr[i], -1);
        }
        int size = map.size();

        for (Integer i : map.values()) {
            if (i != -1) return i;
        }
        return -1;
    }

    public void addMap(int[] nums, Map<Integer, Integer> map) {
        Integer val;
        int l = nums.length;
        for (int i = 0; i < l; i++) {
            val = map.get(nums[i]);
            if (val == null) map.put(nums[i], val);
            else map.put(nums[i], ++val);
        }
    }

    public boolean getSelfDivision(int num) {
        String s = String.valueOf(num);
        int len = s.length();
        int div;
        for (int i = 0; i < len; i++) {
            div = Integer.parseInt(String.valueOf(s.charAt(i)));
            if (num % div != 0)
                return false;
        }
        return true;
    }

    public Map<Character, Character> init() {
        Map<Character, Character> map = new HashMap<>();
        map.put('}', '{');
        map.put(')', '(');
        map.put(']', '[');
        return map;
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        char[] arr = s.toCharArray();
        Map<Character, Character> map = init();
        for (int i = 0; i < len; i++) {
            char fir = arr[i];
            if (fir == '(' || fir == '{' || fir == '[') stack.push(fir);
            else if (!map.get(fir).equals(stack.peek())) return false;
            else stack.pop();
        }
        return stack.isEmpty();
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) sum += nums[0];
        return sum % 2 == 0;
    }

    public int minSteps(int n) {
        int count = 1;
        int sum = 1;
        while (Math.pow(sum, 2) < n) {
            sum = (int) Math.pow(sum, 2);
            count++;
        }
        return count + n - sum;
    }

    //692
    public List<String> topKFrequent(String[] words, int k) {
        List<String> ans = new ArrayList<String>();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String word : words) {
            if (!map.containsKey(word)) {
                map.put(word, 1);
            } else {
                map.put(word, map.get(word) + 1);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            list.add(entry);//排序
        }
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue().compareTo(o2.getValue()) == 0) return o1.getKey().compareTo(o2.getKey());
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for (int i = 0; i < k; i++) {
            ans.add(list.get(i).getKey());
        }
        return ans;
    }

    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int index = 0;
        int len = nums.length;
        if (len == 0) return 1;
        while (index < len && nums[index] <= 0) index++;
        for (int i = index; i < len; i++) {
            if (i == index && nums[i] != 1) return 1;
            else if (i > index && nums[i] > nums[i - 1] && nums[i] - nums[i - 1] != 1) return nums[i - 1] + 1;
        }
        return nums[len - 1] + 1;
    }

    //846
    public boolean isNStraightHand(int[] hand, int W) {
        Arrays.sort(hand);
        int len = hand.length;
        if (len % W != 0) return false;
        List<Integer> list = new ArrayList<>();
        for (int num : hand) list.add(num);
        while (!list.isEmpty()) {
            int val = list.get(0);
            for (int i = 1; i < W; i++) {
                int temp = val + i;
                if (!list.contains(temp)) return false;
                list.remove((Integer) temp);
            }
            list.remove((Integer) val);
            list.removeAll(Collections.singleton(null));
        }
        return true;
    }//450

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        //递归查找对应的节点
        if (root.val > key) root.left = deleteNode(root.left, key);
        else if (root.val < key) root.right = deleteNode(root.right, key);
        else {   //找到节点
            if (root.left == null | root.right == null) root = root.left != null ? root.left : root.right;  //1
            else {
                TreeNode cur = root.right;
                while (cur.left != null) cur = cur.left;   //右子树的最小节点作为根节点
                root.val = cur.val;   //上移的节点在1步删除
                root.right = deleteNode(root.right, cur.val);  //2，查找冗余节点并删除
            }
        }
        return root;
    }

    //560. 和为K的子数组
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for (int j = i; j < len; j++) {
                sum += nums[j];
                if (sum == k) count++;
            }
        }
        return count;
    }

    //523. 连续的子数组和
    public boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for (int j = i; j < len; j++) {
                sum += nums[j];
                if (j - i + 1 >= 2) {
                    if (sum == 0 && k == 0) return true;    //[0,0,0,0] 0
                    else if (k == 0) return false;       //放在中间,避免整除0的错误[23,3,2,4,56,3]   0
                    else if (sum % k == 0) return true;    //[2,3,5,1,65,3]    1
                }
            }
        }
        return false;
    }

    //441. 排列硬币
    public int arrangeCoins(int n) {
        return (int) ((Math.sqrt(8 * (long) n + 1) - 1) / 2);
    }

    //222. 完全二叉树的节点个数
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = depth(root, true);
        int rightDepth = depth(root, false);
        if (leftDepth == rightDepth) return (1 << leftDepth) - 1;    //主要是这一步节省了时间
        else return 1 + countNodes(root.right) + countNodes(root.left);
    }

    public int depth(TreeNode root, boolean left) {
        int depth = 0;
        while (root != null) {
            root = left ? root.left : root.right; //三元表示式合并两个函数
            depth++;
        }
        return depth;
    }

    //537. 复数乘法
    public String complexNumberMultiply(String a, String b) {
        int[] fir = getarr(a);
        int[] sec = getarr(b);
        StringBuffer sb = new StringBuffer();
        sb.append(fir[0] * sec[0] - fir[1] * sec[1]).append('+').append(fir[0] * sec[1] + fir[1] * sec[0]).append('i');
        return sb.toString();
    }

    public int[] getarr(String s) {
        int index = s.indexOf('+');
        int fir = Integer.parseInt(s.substring(0, index));
        int sec = Integer.parseInt(s.substring(index + 1, s.length() - 1));
        return new int[]{fir, sec};
    }

    //811. 子域名访问计数
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (String cpdomain : cpdomains) {
            int time = Integer.parseInt(cpdomain.substring(0, cpdomain.indexOf(" ")));
            cpdomain = cpdomain.substring(cpdomain.indexOf(" ") + 1);
            String[] temp;
            if (cpdomain.indexOf('.') != cpdomain.lastIndexOf('.')) {
                temp = new String[3];
                temp[0] = cpdomain;
                temp[1] = cpdomain.substring(cpdomain.indexOf('.') + 1);
                temp[2] = cpdomain.substring(cpdomain.lastIndexOf('.') + 1);
            } else {
                temp = new String[2];
                temp[0] = cpdomain;
                temp[1] = cpdomain.substring(cpdomain.indexOf('.') + 1);
            }
            for (String s : temp) {
                Integer i = map.get(s);
                if (i == null) i = 0;
                map.put(s, time + i);
            }
        }
        List<String> list = new ArrayList<>();
        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> e = it.next();
            list.add(e.getValue() + " " + e.getKey());
        }
        return list;
    }

    //777. 在LR字符串中交换相邻字符
    public boolean canTransform(String start, String end) {
        int fir = 0;
        int sec = 0;
        int slen = start.length();
        int elen = end.length();
        while (fir < slen && sec < elen) {
            while (fir < slen && start.charAt(fir) == 'X') fir++;
            while (sec < elen && end.charAt(sec) == 'X') sec++;
            if (fir == slen && sec == elen) return true;
            if (fir == slen || sec == elen) return false;
            if (start.charAt(fir) != end.charAt(sec)) return false;
            if (start.charAt(fir) == 'L' && fir > sec) return false;
            if (start.charAt(fir) == 'R' && fir < sec) return false;
            fir++;
            sec++;
        }
        return false;
    }

    public boolean canMeasureWater(int x, int y, int z) {
        if (z == 0) return true;
        if (x == 0 && y == 0 && z != 0) return false;
        return (x + y) % z == 0;
    }

    public boolean isMatch(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        int fir = 0;
        int sec = 0;
        while (fir < slen && sec < plen) {
            if (s.charAt(fir) != p.charAt(sec)) return false;
            if (p.charAt(sec) == '*') {
                sec++;
                if (sec == plen) return true;
                while (fir < slen && s.charAt(fir) != p.charAt(sec)) fir++;
            }
            if (p.charAt(sec) == '.') {
                sec++;
                fir++;
            }
        }
        return fir == slen && sec == plen;
    }

    //767. 重构字符串
    public String reorganizeString(String S) {
        int[] times = new int[26];
        int len = S.length();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            times[S.charAt(i) - 'a']++;
        }
        PriorityQueue<Pair> q = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o2.times - o1.times;
            }
        });
        for (int i = 0; i < 26; i++) {
            if (times[i] > (S.length() + 1) / 2) return "";
            if (times[i] != 0) q.add(new Pair((char) (i + 'a'), times[i]));
        }
        while (q.size() >= 2) {
            Pair p1 = q.poll();
            Pair p2 = q.poll();
            sb.append(p1.c).append(p2.c);
            if (--p1.times > 0) q.add(p1);
            if (--p2.times > 0) q.add(p2);
        }
        if (q.size() > 0) sb.append(q.poll().c);
        ;
        return sb.toString();
    }

    //241. 为运算表达式设计优先级
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> list = new ArrayList<>();
        int len = input.length();
        for (int i = 0; i < len; i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> l1 = diffWaysToCompute(input.substring(0, i));
                List<Integer> l2 = diffWaysToCompute(input.substring(i + 1));
                for (int r : l1) {
                    for (int r2 : l2) {
                        if (c == '+') list.add(r + r2);
                        if (c == '-') list.add(r - r2);
                        if (c == '*') list.add(r * r2);
                    }
                }
            }
        }
        if (list.isEmpty()) list.add(Integer.parseInt(input));
        return list;
    }

    //95. 不同的二叉搜索树 II
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> subTree = new ArrayList<>();
        if (start <= end) {
            for (int i = start; i <= end; i++) {
                List<TreeNode> leftSubTree = generateTrees(start, i - 1);
                List<TreeNode> rightSubTree = generateTrees(i + 1, end);
                for (int j = 0; j < leftSubTree.size(); j++) {
                    for (int k = 0; k < rightSubTree.size(); k++) {
                        TreeNode node = new TreeNode(i);
                        node.left = leftSubTree.get(j);
                        node.right = rightSubTree.get(k);
                        subTree.add(node);
                    }
                }
            }
        } else subTree.add(null);
        return subTree;
    }

    public int numTrees(int sum) {
        int[] arr = new int[sum + 1];
        arr[0] = 1;
        arr[1] = 1;
        for (int n = 2; n <= sum; n++) {
            for (int i = 0; i < n; i++) {
                arr[n] += arr[i] * arr[n - i - 1];
            }
        }
        return arr[sum];
    }

    //22. 括号生成
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        generateParenthesis(list, n, n, "");
        return list;
    }

    public void generateParenthesis(List<String> list, int left, int right, String path) {
        if (left == 0 && right == 0) {
            list.add(path);
            return;
        }
        if (left != 0) generateParenthesis(list, left - 1, right, path + "(");
        if (right != 0 && right > left) generateParenthesis(list, left, right - 1, path + ")");
    }

    class Pair {
        char c;
        int times;

        public Pair(char c, int times) {
            this.c = c;
            this.times = times;
        }

        @Override
        public String toString() {
            return c + " " + times;
        }
    }
}