import java.util.*; 
class Solution {
    int[] nums; 
    int[] b; 
    boolean[] v;
    int n; 
    Set<Integer> set = new HashSet<>(); 
    public int solution(String numbers) {
        n = numbers.length(); 
        nums = new int[n];
        
        // 일단 자리별로 쪼개야 되고? 
        for(int i=0; i<n; i++){     
            nums[i] = numbers.charAt(i) - '0'; 
        }
        
        // 1자릿수는 일단 추가해놓고 
        for(int i=0; i<n; i++){
            set.add(nums[i]);
        }
        
        for(int R=2; R<=n; R++){ // 2자릿수부터 추가하자 
            b = new int[R];  // R개 자릿수 구할거임 
            v = new boolean[n]; // 중복하는 건 안되니까 
            perm(0, R);
        }
        
        // 그리고 소수 판별해야됨 
        int answer = 0; 
        for(int a: set){
            if(isPrime(a)) answer++; 
        }
        return answer; 
    }
    public void perm(int cnt, int R){
        if(cnt == R){
            int temp = 0; 
            for(int i=0; i<R; i++){
                temp = temp*10 + b[i]; 
            }
            set.add(temp);
            return; 
        }
        for(int i=0; i<n; i++){
            if(v[i]) continue; 
            v[i] = true; 
            b[cnt] = nums[i];  // b[i] = nums[i]가 아니라 b[cnt] = nums[i] 임 
            perm(cnt+1, R);
            v[i] = false; 
        }
    }
    public boolean isPrime(int n){
        if(n == 0 || n == 1) return false; 
        for(int i=2; i <= (int)Math.sqrt(n); i++){ // <가 아니라 <= 
            if(n % i == 0) return false; 
        }
        return true; 
    }
}
/*
0. numbers에 있는 수들을 쪼개서 배열에 넣어놓고 N개 중에 R개를 선택하고, 그걸 순서를 매겼을 때의 숫자가 소수인지 판별해야 함 
중복 사용이 불가능함 
1. 차라리 만들 수 있는 모든 수를 만들어 넣어놓고 (set에다가)
2. 그 수들을 하나씩 소수 판별하는 게 나을 듯 
*/