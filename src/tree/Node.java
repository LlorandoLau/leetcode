package tree;

import java.util.List;

public class Node {
    private static String str = "You can not see me";
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
