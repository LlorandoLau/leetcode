package tree;

import java.lang.reflect.Field;
import java.util.*;

public class test {
    int max = 0;

    public static void main(String[] args) {
        Field[] fields = Node.class.getDeclaredFields();
        for (Field f : fields)
            System.out.println(f);

    }

    public int swim(TreeNode node, int sum) {
        if (node == null) return sum;
        if (node.left != null) sum = swim(node.left, sum);
        if (node.right != null) sum = swim(node.right, sum);
        return sum;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    public void connect(TreeLinkNode root) {
        TreeLinkNode temp = new TreeLinkNode(0);
        TreeLinkNode left = temp;
        while (root != null) {
            if (root.left != null) {
                left.next = root.left;
                left = root.left;
            }
            if (root.right != null) {
                left.next = root.right;
                left = root.right;
            }
            root = root.next;
            if (root != null) {
                left = temp;
                root = temp.next;
                temp.next = null;
            }
        }
    }

    public void levelTrans(TreeNode root, List list) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }

    public List<Integer> rightSideView(TreeLinkNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) rightMost(root, 0, list);
        return list;
    }

    public void rightMost(TreeLinkNode root, int depth, List list) {
        if (depth == list.size()) list.add(root.val);
        if (root.right != null) rightMost(root.right, depth + 1, list);
        if (root.left != null) rightMost(root.left, depth + 1, list);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        boolean same = false;
        if (left == null && right == null) same = true;
        else if (left != null && right != null) {
            if (left.val == right.val) same = true;
            if (same) same = isSymmetric(left.left, right.right);
            if (same) same = isSymmetric(left.right, right.left);
        }
        return same;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public boolean checkPerfectNumber(int num) {
        List<Integer> list = factors(num);
        int sum = 0;
        for (Integer i : list) {
            sum += i;
        }
        return sum == num;
    }

    public List<Integer> factors(int num) {
        List<Integer> list = new ArrayList<>();
        int extract = (int) Math.pow(num, 0.5);
        for (int i = 2; i < extract; i++) {
            if (num % i == 0) {
                list.add(i);
                list.add(num / i);
            }
        }
        return list;
    }

    public int compress(char[] chars) {
        int len = chars.length;
        if (len == 0) return 0;
        StringBuffer sb = new StringBuffer();
        char ch = chars[0];
        int count = 1;
        for (int i = 1; i < len; i++) {
            if (chars[i] == ch) count++;
            else {
                sb.append(ch);
                if (count != 1) sb.append(count);
                ch = chars[i];
                count = 1;
            }
        }
        sb.append(ch);
        if (count != 1) sb.append(count);
        System.arraycopy(sb.toString().toCharArray(), 0, chars, 0, sb.length());
        return sb.length();
    }

    public TreeNode sortedArrayToBST(int[] nums, int begin, int end) {
        if (begin > end) return null;
        int mid = (begin + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, begin, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, end);
        return root;
    }

    public TreeNode add(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        else if (val < root.val) root.left = add(root.left, val);
        else root.right = add(root.right, val);
        return root;
    }

    int maxDepth(TreeNode node) {
        if (node == null) return 0;
        int left = maxDepth(node.left);
        int right = maxDepth(node.right);
        return Math.max(left, right) + 1;
    }

    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode node = null;
        if (root == null) return node;
        if (root.val == val) return root;
        node = searchBST(root.left, val);
        if (node != null) return node;
        node = searchBST(root.right, val);
        return node;
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (val > root.val) root.right = insertIntoBST(root.right, val);
        else if (val <= root.val) root.left = insertIntoBST(root.left, val);
        else if (root == null) return new TreeNode(val);
        return root;
    }

    public int minDepth(TreeNode root) {
        return depth(root, 0, Integer.MAX_VALUE);
    }

    public int depth(TreeNode node, int depth, int min) {
        if (node == null) {
            if (min > depth)
                min = depth;
            return min;
        }
        depth++;
        min = depth(node.left, depth, min);
        min = depth(node.right, depth, min);
        return min;
    }

    public int findBottomLeftValue(TreeNode root) {
        return findBottomLeftValue(root, depth(root, 0, Integer.MAX_VALUE), 0);
    }

    public int findBottomLeftValue(TreeNode node, int depth, int count) {
        if (node == null) return -1;
        int val;
        if (count == depth) {
            return node.val;
        }
        val = findBottomLeftValue(node.left, depth, count++);
        if (val != -1) return val;
        val = findBottomLeftValue(node.left, depth, count++);
        return val == -1 ? -1 : val;
    }

    public int minDiffInBST(TreeNode root) {
        List list = new ArrayList();
        addToList(root, list);
        Arrays.sort(list.toArray());
        return 0;
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums, int begin, int end) {
        if (begin <= end) return null;
        int mid = max(nums, begin, end);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = constructMaximumBinaryTree(nums, 0, mid - 1);
        root.right = constructMaximumBinaryTree(nums, mid + 1, nums.length);
        return root;
    }

    public int widthOfBinaryTree(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        //levelTrans(root,lists,-1);
        int max = 0;
        for (List list : lists) {
            max = Integer.max(max, list.size());
        }
        return max;
    }

    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        trans(root, map);
        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> e = it.next();
            if (e.getValue() == max) list.add(e.getKey());
        }
        int[] temp = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            temp[i] = list.get(i);
        }
        return temp;
    }

    public void trans(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) return;
        Integer temp = map.get(root.val);
        if (temp == null) map.put(root.val, 1);
        else map.put(root.val, ++temp);
        max = Integer.max(max, temp);
        trans(root.left, map);
        trans(root.right, map);
    }

    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        addToList(root, list);
        int len = list.size();
        for (int i = 0; i < len; i++) {
            if (!isValid(list, i)) return false;
        }
        return true;
    }

    public boolean isValid(List<Integer> list, int index) {
        int len = list.size();
        for (int i = 0; i < index; i++) {
            if (list.get(i) > list.get(index)) return false;
        }
        for (int i = index + 1; i < len; i++) {
            if (list.get(i) < list.get(index)) return false;
        }
        return true;
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int glen = g.length;
        int slen = s.length;
        int bisket = 0;
        int children = 0;
        if (slen == 0 || glen == 0) return 0;
        for (int i = 0; i < glen; i++) {
            while (bisket < slen && s[bisket] < g[i]) bisket++;
            if (bisket == s.length) break;
            bisket++;
            children++;
        }
        return children;
    }

    public int[] shortestToChar(String S, char C) {
        int fir = findchar(S, C, 0);
        int sec = findchar(S, C, fir + 1);
        int len = S.length();
        int[] temp = new int[len];
        for (int i = 0; i < len; i++) {
            if (i == fir) {
                temp[i] = 0;
            } else if (i == sec) {
                temp[i] = 0;
                fir = sec;
                sec = findchar(S, C, fir + 1);
            } else if (i < fir) {
                temp[i] = fir - i;
            } else if (i > fir && i < sec) {
                temp[i] = i - fir < sec - i ? i - fir : sec - i;
            } else if (i > sec) {
                temp[i] = sec - i;
            }
        }
        return temp;
    }

    public boolean increasingTriplet(int[] nums) {
        int len = nums.length;
        int begin = 0;
        int end = 0;
        for (int i = 1; i < len; i++) {
            if (nums[i - 1] < nums[i]) {
                end++;
            } else {
                if (end - begin + 1 >= 3) return true;
                begin = i;
                end = i;
            }
        }
        return false;
    }

    public int[] singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            Integer temp = map.get(nums[i]);
            if (temp == null) {
                count++;
                map.put(nums[i], 1);
            } else {
                count--;
                map.put(nums[i], ++temp);
            }
        }
        int[] arr = new int[count];
        int index = 0;
        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> e = it.next();
            if (e.getValue() == 1) arr[index++] = e.getKey();
        }
        return arr;
    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int j = 0; j < col; j++) {
            int i = 0;
            int temp = j;
            while (j < col) {
                if (matrix[i][temp] != matrix[0][j]) return false;
                i++;
                temp++;
            }
        }
        for (int i = 0; i < row; i++) {
            int temp = i;
            int j = 0;
            while (i < row) {
                if (matrix[temp][j] != matrix[i][0]) return true;
                i++;
                temp++;
            }
        }
        return true;
    }

    public int binaryGap(int N) {
        char[] arr = Integer.toBinaryString(N).toCharArray();
        int begin = -1;
        int end = -1;
        int len = arr.length;
        int max = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] == '1') {
                if (begin == -1) begin = i;
                else if (begin != -1 && end == -1) end = i;
                else if (begin != -1 && end != -1) {
                    max = Integer.max(end - begin, max);
                    begin = end;
                    end = i;
                }
            }
        }
        return Integer.max(max, end - begin);
    }

    public int distributeCandies(int[] candies) {
        int len = candies.length;
        Map<Integer, Integer> map = new HashMap<>();
        addInMap(candies, map);
        int size = map.size();
        if (size == len / 2) return size;
        else if (size > len / 2) return len / 2;
        else return size;
    }

    public void addInMap(int[] nums, Map<Integer, Integer> map) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            Integer temp = map.get(nums[i]);
            if (temp == null) temp = 0;
            map.put(nums[i], ++temp);
        }
    }

    public int integerReplacement(int n) {
        int count = 0;
        while (n != 1) {
            if (n % 2 == 0) n /= 2;
            else {
                n = n - 1;
            }
            count++;
        }
        return count;
    }

    public int findchar(String s, char c, int begin) {
        int len = s.length();
        for (int i = begin + 1; i < len; i++) {
            if (s.charAt(i) == c) return i;
        }
        return begin;
    }

    public int[][] flipAndInvertImage(int[][] A) {
        int row = A.length;
        if (row == 0) return null;
        int col = A[0].length;
        int[][] temp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                temp[i][col - 1 - j] = A[i][j] == 0 ? 1 : 0;
            }
        }
        return temp;
    }

    public int convertBST(TreeNode parent, TreeNode child) {
        if (child == null) return 0;
        child.val += convertBST(child, child.right);
        if (child == parent.left) child.val += parent.val;
        return child.val;
    }

    public int islandPerimeter(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int sum = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sum += perimeter(grid, row, col, i, j);
            }
        }
        return sum;
    }

    public int perimeter(int[][] grid, int row, int col, int i, int j) {
        int sum = 4;
        if (0 < i && i < row) {
            if (grid[i - 1][j] == 1) sum--;
            if (grid[i + 1][j] == 1) sum--;
        }
        if (0 < j && j < col) {
            if (grid[i][j - 1] == 1) sum--;
            if (grid[i][j + 1] == 1) sum--;
        }
        return sum;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) return false;
        if (Math.abs(maxDepth(root.left) - maxDepth(root.right)) > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public int pick(int[] nums, int target) {
        int len = nums.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (nums[i] == target) list.add(i);
        }
        Random r = new Random();
        int index = r.nextInt(list.size());
        return list.get(index);
    }

    public void levelTrans(TreeNode root, List<List<TreeNode>> lists, int depth) {
        if (root == null) return;
        depth++;
        if (lists.size() == depth) {
            lists.add(new ArrayList<>());
        }
        lists.get(depth).add(root);
        levelTrans(root.left, lists, depth);
        levelTrans(root.right, lists, depth);
    }

    public TreeNode pruneTree(TreeNode root) {
        prune(root);
        return root;
    }

    public boolean prune(TreeNode root) {
        if (root == null) return true;
        if (root.val == 1) return true;
        boolean left = prune(root.left);
        boolean right = prune(root.right);
        if (!left) root.left = null;
        if (!right) root.right = null;
        return left || right;
    }

    public int findTilt(TreeNode root) {
        if (root == null) return 0;
        return Math.abs(sum(root.left, 0) - sum(root.right, 0));
    }

    public int sum(TreeNode root, int sum) {
        if (root == null) return sum;
        sum += root.val;
        sum += sum(root.left, sum) + sum(root.right, sum);
        return sum;
    }

    public int max(int[] nums, int begin, int end) {
        int max = 0;
        for (int i = begin; i < end; i++) {
            if (nums[i] > nums[max]) max = i;
        }
        return max;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left == null ? right : left;
    }

    public String tree2str(TreeNode t) {
        StringBuffer sb = new StringBuffer();
        tree2str(t, sb);
        return sb.toString();
    }

    public void tree2str(TreeNode t, StringBuffer sb) {
        if (t == null) return;
        sb.append(t.val);
        if (t.left == null && t.right == null) return;
        sb.append("(");
        tree2str(t.left, sb);
        sb.append(")");
        if (t.right != null) {
            sb.append("(");
            tree2str(t.right, sb);
            sb.append(")");
        }
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        if (root == null) return list;
        find(root, new HashMap<String, Integer>(), list);
        return list;
    }

    public String find(TreeNode root, Map<String, Integer> map, List<TreeNode> list) {
        if (root == null) return "#";
        String str = root.val + "," + find(root.left, map, list) + "," + find(root.right, map, list);
        if (map.getOrDefault(str, 0) == 1) list.add(root);
        map.put(str, map.getOrDefault(str, 0) + 1);
        return str;
    }

    public List<Integer> largestValues(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        //levelTrans(root, lists, -1);
        for (List<Integer> list : lists) {
            Collections.sort(list);
            temp.add(list.get(list.size() - 1));
        }
        return temp;
    }

    public String licenseKeyFormatting(String S, int K) {
        int len = S.length();
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for (int i = len - 1; i >= 0; i++) {
            if (S.charAt(i) != '-') {
                if (count == K) {
                    sb.append("-");
                    count = 1;
                }
                sb.append(S.charAt(i));
                count++;
            }
        }
        return sb.reverse().toString().toUpperCase();
    }

    public int trailingZeroes(int n) {
        int count = 0;
        while (n >= 1) {
            count += (n /= 5);
        }
        return count;
    }

    public void addToList(TreeNode node, List list) {
        if (node == null) return;
        addToList(node.left, list);
        addToList(node.right, list);
        list.add(node);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public int maxDepth(Node node) {
        int left = 0;
        int right = 0;
        if (node == null) return 0;
        List<Node> list = node.children;
        int len = list.size();
        int max = 0;
        int val;
        for (int i = 0; i < len; i++) {
            val = maxDepth(list.get(i));
            if (val > max) max = val;
        }
        return max;
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return false;
        return isSameTree(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    public int sum_of_tree(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) return 0;
        root.val += sum_of_tree(root.left, map) + sum_of_tree(root.right, map);
        int val = root.val;
        Integer i = map.get(val);
        if (i == null) map.put(val, 1);
        else map.put(val, ++i);
        return val;
    }

    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> map = new HashMap();
        sum_of_tree(root, map);
        List<Integer> list = new ArrayList<>();
        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> entry = it.next();
            Integer val = entry.getValue();
            //if(val.equals(max)) list.add(entry.getKey());
        }
        int[] temp = new int[list.size()];
        for (int i = 0; i < temp.length; i++)
            temp[i] = list.get(i);
        return null;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        else if (p == null && q != null) return false;
        else if (p != null && q == null) return true;
        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    //865. 具有所有最深结点的最小子树
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        List<List<TreeNode>> lists = new ArrayList<>();
        levelTrans(root, lists, -1);
        List<TreeNode> list = lists.get(lists.size() - 1);
        return lowestCommonAncestor(root, list);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> list) {
        if (root == null) return null;
        for (TreeNode node : list) {
            if (node.equals(root)) return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, list);
        TreeNode right = lowestCommonAncestor(root.right, list);
        if (left != null && right != null) return root;    //p，q各分布在左右子树
        return left == null ? right : left;   //都在左子树，或者右子树
    }

}
