import java.util.*; 
class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] nums = new String[numbers.length];
        for(int i=0; i<numbers.length; i++){
            nums[i] = String.valueOf(numbers[i]); 
        }
        
        Arrays.sort(nums, (o1, o2) -> (o2+o1).compareTo(o1+o2));
        // System.out.println(Arrays.toString(nums));
        
        for(int i=0; i<nums.length; i++){
            answer += nums[i]; 
        }
        
        if(nums[0].equals("0")) return "0";
        
        return answer;
    }
}
/*
"3" < "30" 

그래서 내림차순 정렬하면 303이 되어버림 


*/