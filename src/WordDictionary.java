import java.util.HashSet;
import java.util.Set;

class WordDictionary {
    Set<String> list;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        list = new HashSet<>();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        list.add(word);
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        if (list.contains(word)) return true;
        for (String s : list) {
            if (s.matches(word)) return true;
        }
        return false;
    }
}
