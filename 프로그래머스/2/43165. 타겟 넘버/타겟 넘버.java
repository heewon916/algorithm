import java.util.*; 
class Solution {
    int answer = 0;
    public int solution(int[] numbers, int target) {
        answer = 0;
        dfs(0, 0, numbers, target);
        return answer;
    }
    public void dfs(int i, int sum, int[] numbers, int target){
        if(i == numbers.length){
            if(sum == target){
                answer++; 
            }   
            return; 
        }
        // 문제의 코드: 성공/실패 여부와 관계없이, 인덱스가 끝까지 갔으면 탐색 종료해야 되는데 못하니까 IndexBoundOutOutOfException이 뜨겠지 
        // if(sum == target && i == numbers.length){
        //     answer++; 
        //     return; 
        // }
        
        // 지금 숫자를 더할 경우 
        dfs(i+1, sum + numbers[i], numbers, target);
            
        // 지금 숫자 뺄 경우 
        dfs(i+1, sum - numbers[i], numbers, target);
    }
}
/*
N 20 
2^20 = 10^6 
*/