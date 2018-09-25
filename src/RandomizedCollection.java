import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomizedCollection {
    Set<Integer> set;

    /**
     * Initialize your data structure here.
     */
    public RandomizedCollection() {
        set = new HashSet<>();
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        return set.add(val);
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        return set.remove((Integer) val);
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        int len = set.size();
        return (Integer) set.toArray()[new Random().nextInt(len)];
    }
}
