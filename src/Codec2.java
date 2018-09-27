import tree.TreeNode;

//297. 二叉树的序列化与反序列化
public class Codec2 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuffer sb = new StringBuffer();
        transStr(root, sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] str = data.split(" ");
        int[] index = new int[1];
        return deserialize(str, index);
    }

    //IMPORTANT,从先序序列重组二叉树
    public TreeNode deserialize(String[] data, int[] index) {
        TreeNode root = null;
        if (index[0] < data.length) {
            int val = 0;
            if (data[index[0]].equals("#")) return null;
            else {
                val = Integer.parseInt(data[index[0]]);
                root = new TreeNode(val);
                index[0]++;
                root.left = deserialize(data, index);
                index[0]++;
                root.right = deserialize(data, index);
            }
        }
        return root;
    }

    public void transStr(TreeNode root, StringBuffer sb) {
        if (root == null) {
            sb.append("# ");
            return;
        }
        sb.append(root.val).append(" ");
        transStr(root.left, sb);
        transStr(root.right, sb);
    }
}
