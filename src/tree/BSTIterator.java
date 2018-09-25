package tree;

import java.util.*;

public class BSTIterator {
    List list;
    Iterator<Integer> it;

    public BSTIterator(TreeNode root) {
        list = new ArrayList<>();
        addTreeNodeToList(root, list);
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Integer i1 = (Integer) o1;
                Integer i2 = (Integer) o2;
                return i1.compareTo(i2);
            }
        });
        it = list.iterator();
    }

    public void addTreeNodeToList(TreeNode root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        addTreeNodeToList(root.left, list);
        addTreeNodeToList(root.right, list);
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return it.hasNext();
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        return it.next();
    }
}
