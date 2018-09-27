import tree.TreeNode;

//449. 序列化和反序列化二叉搜索树
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        addToString(root, sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        System.out.println(data);
        if (data.length() == 0) return null;
        String[] strs = data.split(" ");
        TreeNode root = null;
        for (String str : strs) {
            int val = Integer.parseInt(str);
            root = add(root, val);
        }
        return root;
    }

    public TreeNode add(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (root.val < val) root.right = add(root.right, val);
        else root.left = add(root.left, val);
        return root;
    }

    public void addToString(TreeNode root, StringBuffer sb) {
        if (root == null) return;
        sb.append(root.val).append(" ");
        addToString(root.left, sb);
        addToString(root.right, sb);

    }
}
