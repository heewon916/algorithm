package bj;
import java.io.*;
import java.util.*;
public class Main_bj_21394_숫자카드 {
    static int[] input;
    static int[] answer;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        input = new int[10];
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine(), " ");
            int cnt6 = 0;
            int totalLen = 0;
            for(int i=1; i<10; i++){
                if(i == 6) {
                    cnt6 = Integer.parseInt(st.nextToken()); continue;
                }
                if(i == 9){
                    input[i] = Integer.parseInt(st.nextToken()) + cnt6;
                }else input[i] = Integer.parseInt(st.nextToken());
                totalLen += input[i];
            }

//            System.out.println(Arrays.toString(input));
            answer = new int[totalLen];
            int turn = 0; // 짝수면 앞에, 홀수면 뒤에 넣기
            int front = 0, last = totalLen-1;
            for(int i=9; i>=0; i--){
                for(int j=0; j<input[i]; j++){
                    if(turn%2 == 0) answer[front++] = i;
                    else answer[last--] = i;
                    turn++;
                }
            }

           for(int i=0; i<totalLen; i++){
               sb.append(answer[i] + " ");
           }
           sb.append("\n");
        }
        System.out.println(sb);
    }
}
/*
N장의 카드가 있는데 6은 9로 이해할 수 있다는 거니까 6은 9로 그냥 대체해서 처리해도 될 것 같다
좌측 1개 우측 1개 좌측 1개 우측 1개 이렇게 가져와서 수를 만드는 거네
그럼 그냥 6을 9로 대체한다음에 내림차순 정렬해서 가장 큰 수를 만들고
걔네를 좌-우

 */