import java.util.*; 
class Solution {
    public int[] solution(int[] answers) {
        int[] a1 = {1, 2, 3, 4, 5};
        int[] a2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] a3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int s1=0, s2=0, s3=0; 
        for(int i=0; i<answers.length ; i++){
            if(answers[i] == a1[i % a1.length]) s1++; 
            if(answers[i] == a2[i % a2.length]) s2++; 
            if(answers[i] == a3[i % a3.length]) s3++; 
        }
        int maxscore = Math.max(s1, Math.max(s2, s3));
        ArrayList<Integer> ans = new ArrayList<>();
        if(s1 == maxscore) ans.add(1);
        if(s2 == maxscore) ans.add(2); 
        if(s3 == maxscore) ans.add(3); 
        int[] answer = new int[ans.size()];
        for(int i=0; i<ans.size(); i++){
            answer[i] = ans.get(i);  
        }
        return answer;
    }
}