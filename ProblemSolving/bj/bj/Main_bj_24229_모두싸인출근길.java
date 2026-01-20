
package bj;
import java.io.*;
import java.util.*;

public class Main_bj_24229_모두싸인출근길 {
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
        for(int[] a: list){
            System.out.println(Arrays.toString(a));
        }

        // 3. 계산
        // TODO: lastPoint랑 prevDiff만 처리하면 될 듯하다
        int lastPoint = 0;
        for(int i=1; i<list.size(); i++){

            int[] prev = list.get(i-1);
            int[] now = list.get(i);
            int prevDiff = prev[1] - prev[0];

            System.out.println("prev: " + Arrays.toString(prev));
            System.out.println("now: " + Arrays.toString(now));
            System.out.println("lastPoint: " + lastPoint);
            System.out.println("prevDiff: " + prevDiff);

            if(prevDiff + lastPoint > now[1]) {
                System.out.println("==continue==");
                i += 1;
                continue;
            }
            if(prevDiff + prev[1] >= now[0]){
                 // 건너갈 수 있음
                 lastPoint = now[1];
                 System.out.println("건너갈 수 있음: " + lastPoint);
             }else{
                 // 이제 못 건너감
                 System.out.println("종료: -- 건너갈 수 없음: " + lastPoint);
                 break;
             }
            System.out.println("-------");
        }
        System.out.println(lastPoint);
    }
}
