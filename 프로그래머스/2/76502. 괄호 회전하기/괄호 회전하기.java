import java.util.*; 
class Solution {
    public int solution(String s) {
        int answer = 0;
        ArrayDeque<Character> q = new ArrayDeque<>(); 
        for(char c: s.toCharArray()){
            q.add(c);
        }
        int size = q.size(); 
        
        // 왼쪽 방향 괄호일 때만 
        // 왼쪽 방향으로 회전시키고 나서 올바른 괄호 문자열인지 검사 
       
        for(int i=0; i<size; i++){
            ArrayDeque<Character> stack = new ArrayDeque<>();
            // 이제 올바른 문자열인지 검사 
            // q에 들어있는 문자를 순서대로 보고 
            // 왼쪽 괄호면 stack에 넣고
            // 오른쪽 괄호면 stack에서 뽑는다. 
            // 이때 stack이 비어있거나, 짝이 안 맞으면 그 문자열은 그른 것임 
            boolean able = true; 
            for(char a: q){
                if(a == '(' || a == '{' || a == '[') {
                    stack.offer(a);
                }else{
                    if(stack.isEmpty()) {
                        able = false; 
                        break; 
                    }
                    
                    char b = stack.pollLast(); // 맨 뒤에 있는 거 뽑 
                    if(a == ')' && b == '(') continue;
                    else if(a == ']' && b == '[') continue;
                    else if(a == '}' && b == '{') continue;
                    else {
                        // 짝이 안 맞는 거니까 
                        able = false;
                        break; 
                    }   
                }
            }
            if(able && stack.isEmpty()) answer++; 
            q.offer(q.pollFirst());
        }
        return answer;
    }
}