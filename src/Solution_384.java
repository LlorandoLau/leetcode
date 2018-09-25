import java.util.Random;

public class Solution_384 {
    int[] nums;
    int[] ori;
    int[] reset;
    int len;

    public Solution_384(int[] nums) {
        this.nums = new int[nums.length];
        this.ori = new int[nums.length];
        this.reset = new int[nums.length];
        System.arraycopy(nums, 0, reset, 0, nums.length);
        System.arraycopy(nums, 0, ori, 0, nums.length);
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 6};
        Solution_384 s = new Solution_384(nums);
        s.reset();
        nums = s.shuffle();
        for (int i : nums)
            System.out.println(i);
    }

    public int[] reset() {
        return reset;
    }

    public int[] shuffle() {
        Random r = new Random();
        int i = 0;
        int times = nums.length;
        int index = r.nextInt(times);
        while (i < times) {
            System.out.println(i + " " + times);
            if (ori[index] != 0) {
                nums[i] = ori[index];
                i++;
                ori[index] = 0;
            }
            index = r.nextInt(times);
        }
        return nums;
    }
}
