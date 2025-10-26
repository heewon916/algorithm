import java.util.*; 
class Solution {
    public static int gcd(int a, int b){
        if(b == 0) return a; 
        return gcd(b, a%b);
    }
    public int solution(int[] arr) {
        Arrays.sort(arr);
        int[] temp = new int[arr.length];
        for(int i=arr.length-1; i>=0; i--){
            temp[arr.length-1-i] = arr[i]; 
        }
    
        /*
        i, i+1을 곱한 거에 두 수의 gcd로 나누고 
        그 값을 갖고 또 진행 
        */
        int num = temp[0]; 
        for(int i=1; i<temp.length; i++){
            num = (num * temp[i]) / gcd(num, temp[i]);
        }
        return num;
    }
}