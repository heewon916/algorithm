import java.util.*;

class Solution {
    public int solution(int[] nums) {
        Set<Integer> types = new HashSet<>();

        for (int num : nums) {
            types.add(num);
        }

        return Math.min(nums.length / 2, types.size());
    }
}