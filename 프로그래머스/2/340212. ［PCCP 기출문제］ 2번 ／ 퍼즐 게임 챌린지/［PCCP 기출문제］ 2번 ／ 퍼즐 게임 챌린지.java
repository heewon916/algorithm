import java.util.*; 
class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        int min = 1; 
        int max = Arrays.stream(diffs).max().getAsInt(); 
        
        while(min <= max){
            int mid = (min+max)/2; 
            
            if(check(mid, diffs, times) <= limit){
                // mid 숙련도를 가졌을 때 나오는 전체 소요시간 <= limit이면 너무 적게 걸리니, 숙련도를 낮춰도 됨
                answer = mid;
                max = mid - 1; 
            }else{ // mid 숙련도를 가졌을 때 나오는 전체 소요시간 > limit이면 너무 오래 걸리니, 숙련도가 올라가야함
                min = mid + 1; 
            }
        }
        
        return answer;
    }
    public long check(long level, int[] diffs, int[] times){
        long total = times[0]; 
        
        for(int i=1; i<diffs.length; i++){
            if(level >= diffs[i]) {
                total += times[i]; 
            }else{
                total = total + (diffs[i] - level) * (times[i] + times[i-1]) + times[i]; 
            }
        }
        // System.out.println("checkTime(): " + total); 
        return total; 
    }
}

/**
여기서 중요한 건 수식은 알아내도
이분탐색으로 결국 완탐으로 가야 한다는 거? 

수식 그 자체로도 풀어보고 싶은데 개복잡해질 듯
*/
