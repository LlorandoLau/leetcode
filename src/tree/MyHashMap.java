package tree;

import java.util.Arrays;

public class MyHashMap {
    int[] arr;

    public MyHashMap() {
        arr = new int[100000];
        Arrays.fill(arr, Integer.MIN_VALUE);
    }

    /**
     * value will always be positive.
     */
    public void put(int key, int value) {
        arr[key] = value;
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        return arr[key];
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        arr[key] = Integer.MIN_VALUE;
    }
}
