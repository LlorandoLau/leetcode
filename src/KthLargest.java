import java.util.*;

public class KthLargest {
    PriorityQueue<Integer> pq;
    int k;

    public KthLargest(int k, int[] nums) {
        pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        this.k = k;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] arr = new int[2];
        Arrays.fill(arr, -1);
        int len = nums.length;
        boolean inZone = false;
        for (int i = 0; i < len; i++) {
            if (nums[i] == target) {
                if (arr[0] == -1) {
                    arr[0] = i;
                    inZone = true;
                }
            }
            if (nums[i] != target && inZone) {
                arr[1] = i;
            }
        }
        return arr;
    }

    public int add(int val) {
        pq.add(val);
        List list = new ArrayList();
        int count = 0;
        while (count < k) list.add(pq.poll());
        Integer li = pq.peek();
        pq.addAll(list);
        return li;
    }

}
