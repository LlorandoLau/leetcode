public class NumArray {
    int[] nums;

    public NumArray(int[] nums) {
        this.nums = new int[nums.length];
        System.arraycopy(nums, 0, this.nums, 0, nums.length);
    }

    public void update(int i, int val) {
        nums[i] = val;
    }

    public int sumRange(int i, int j) {
        int sum = 0;
        for (int begin = i; begin <= j; begin++)
            sum += this.nums[begin];
        return sum;
    }
}
