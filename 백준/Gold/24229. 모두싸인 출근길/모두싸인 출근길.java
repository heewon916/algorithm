
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static ArrayList<int[]> list;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][2];
        list = new ArrayList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            arr[i][0] = L; arr[i][1] = R;
        }

        // 1. 정렬
        Arrays.sort(arr, (o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            else return o1[0] - o2[0];
        });

        // 2. 전처리 - 이어지는 거 묶고
        int start = arr[0][0], end = arr[0][1];
        for(int i=1; i<N; i++){
            int s2 = arr[i][0];
            int e2 = arr[i][1];
            if(s2 <= end){ // 판자가 겹치는 경우
                if(end < e2){
                    // start는 유지
                    end = e2;
                }else{    // end >= e2 인 경우
                    // start, end 모두 유지
                }
            } else { // 점프해야 하는 경우
                // 기존의 start, end add하고
                list.add(new int[]{start, end});
                // 업데이트 필요
                start = s2;
                end = e2;
            }
        }
        list.add(new int[] {start, end});
        

        // 3. 계산
        /**
         * 내 현재 기준으로
         * 다음 판자를 봤을 때
         * 그 판자의 시작점이 내가 갈 수 있는 최대 좌표보다 작으면 난 갈 수 있음
         * 그 판자에 도달했을 때 갈 수 있는 최대 좌표랑 지금의 최대 좌표를 비교했을 때 더 큰 녀석으로 고른다.
         * 그러다가 판자의 시작점이 내가 갈 수 있는 최대 좌표보다 커지면 더 이상 갈 수 없음
         */
        int[] now = list.get(0);
        int ans = now[1]; 
        int lastReachPoint = now[1] + (now[1] - now[0]);
        for(int i=1; i<list.size(); i++){
            int[] next = list.get(i);
            if(next[0] <= lastReachPoint){
                int jumpStrength = next[1] + (next[1] - next[0]);
                lastReachPoint = Math.max(lastReachPoint, jumpStrength);
                ans = next[1]; // 이 판자에는 도달할 수 있다는 뜻  
            }else{
                break;
            }
        }
        System.out.println(ans);
    }
}
