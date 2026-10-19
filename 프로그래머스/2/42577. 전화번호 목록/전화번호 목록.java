import java.util.*; 
import java.io.*; 
class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        if(phone_book.length == 1){
            return true; 
        }
        // 1. 숫자 값 기준으로 오름차순 정렬
        Arrays.sort(phone_book); 
        // 2. 이웃한 애들끼리 prefix인지 확인하기 
        String prev = phone_book[0]; 
        for(int i=1; i<phone_book.length; i++){
            if(phone_book[i].startsWith(prev)){
                return false; 
            }
            prev = phone_book[i]; 
        }
        
        return true;
    }
}