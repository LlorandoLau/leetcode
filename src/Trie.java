import java.util.ArrayList;
import java.util.List;

class Trie {
    List<String> list;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        list = new ArrayList<>();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        list.add(word);
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        for (String s : list) {
            if (s.equals(word)) return true;
        }
        return false;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        for (String s : list) {
            if (s.indexOf(prefix) == 0) return true;
        }
        return false;
    }
}
