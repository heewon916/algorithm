package bj;
import java.io.*;
import java.util.*;
public class Main_bj_14718_용감한용사진수 {
    static int N, K;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][3];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }
        long ans = Long.MAX_VALUE;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                for(int k=0; k<N; k++){
                    int count = 0;
                    for(int l=0; l<N; l++){
                        if(arr[i][0] >= arr[l][0] && arr[j][1] >= arr[l][1] && arr[k][2] >= arr[l][2]){
                            count++;
                        }
                    }

                    if(count >= K){
                        ans = Math.min(arr[i][0] + arr[j][1] + arr[k][2], ans);
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
/*
N명
힘, 민첩, 지능 <= 진수
최대한 많은 적
적어도 K명 이김

한 명 이기려면 힘 + 민첩 + 지능 <= 이어야 함
이 중에서 k개 더한 조합이 최소인 걸 구해야 함
 */