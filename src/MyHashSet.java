import java.util.Arrays;

public class MyHashSet {
    int[] arr;

    public MyHashSet() {
        arr = new int[Integer.MAX_VALUE];
        Arrays.fill(arr, 0);
    }

    public void add(int key) {
        arr[key] = 1;
    }

    public void remove(int key) {
        arr[key]--;
    }

    /**
     * Returns true if this set did not already contain the specified element
     */
    public boolean contains(int key) {
        return arr[key] > 0 ? true : false;
    }
}
