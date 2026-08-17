import java.util.*;
import java.io.*; 
class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        num = num-1; 
        n = n-1; 
        
        // 1. num의 위치를 구한다.
        int r = num / w; 
        int c = num % w; 
        
        // if(r%2 == 0){
        //     // 짝수면 [r][c-1]에 위치한다. 
        //     if(c > 0) c = c-1; 
        // } else 
        if(r%2 != 0) {
            // 홀수면 [r][w-c]에 위치한다.
            c = (w-c-1 >= 0) ? w-c-1 : 0; 
        }
        // System.out.println("num의 위치: " + r + ", " + c); 
        
        // 2. 최고 상단 행에서, 어디까지 숫자가 있는지 없는지를 알아 낸다. 
        int lastR = n / w; 
        int lastC = n % w; 
        boolean isLtoR = true; // 맨 윗 줄이 왼 -> 오 방향 
        
        // if(lastR % 2 == 0){
        //     if(lastC > 0) lastC = lastC - 1; 
        // } else 
        if(lastR % 2 != 0){ 
            lastC = (w-lastC-1 >= 0) ? w-lastC-1 : 0; 
            isLtoR = false; 
        }
        
        // System.out.println("n의 위치: " + lastR + ", " + lastC); 
        // System.out.println("맨 윗줄 방향: " + isLtoR); 
        
        // 3. 있으면 -> 그 행부터 세고, 아니면 그 아래 행부터 꺼낼 상자 개수를 센다.
        answer = lastR - r; // 맨 윗 행 일단 포함 안됨 
        if(isLtoR){
            if(c <= lastC){
                answer++; 
            }
        }else{
            if(c >= lastC){
                answer++; 
            }        
        }
    
        
        return answer;
    }
}
/*
번호는 안 궁금. 꺼내야 하는 택배 상자 개수가 궁금 

전체 택배 개수 n
가로 길이 w 
알고 싶은 번호의 위치: num 

num의 위치를 알아야 됨. -> 몇 행 몇 열 

홀수 행: 
*/