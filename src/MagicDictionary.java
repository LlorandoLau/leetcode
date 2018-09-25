import java.util.ArrayList;
import java.util.List;

public class MagicDictionary {
    List<String> list;

    /**
     * Initialize your data structure here.
     */
    public MagicDictionary() {
        list = new ArrayList<>();
    }

    /**
     * Build a dictionary through a list of words
     */
    public void buildDict(String[] dict) {
        int len = dict.length;
        for (int i = 0; i < len; i++) {
            list.add(dict[i]);
        }
    }

    /**
     * Returns if there is any word in the trie that equals to the given word after modifying exactly one character
     */
    public boolean search(String word) {
        return false;
    }
}
