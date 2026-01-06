package bj;
import java.io.*;
import java.util.*;
public class Main_bj_13423_ThreeDots {
    static int N;
    static int[] pos;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int tc=0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine(), " ");
            pos = new int[N];
            for(int i=0; i<N; i++){
                pos[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(pos);

            int[][] mat = new int[N][N];
            for(int i=0; i<N; i++){
                int n = pos[i];
                for(int j=i+1; j<N; j++){
                    mat[i][j] = Math.abs(n - pos[j]);
                }
            }
            // i번째 숫자에 대해서 n-i개의 차이 중 하나를 고르면 그 인덱스에 대해서 다시 조사
            int cnt = 0;
            for(int i=0; i<N; i++){ // 첫번째 숫자 i 위치
                for(int j=i+1; j<N; j++){
                    int diff = mat[i][j];
                    int left = j+1, right = N-1;
                    boolean able = false;
                    while(left <= right){
                        int mid = (left + right) / 2;
                        if(mat[j][mid] > diff) right = mid-1;
                        else if(mat[j][mid] < diff) left = mid+1;
                        else{
                            able = true; break;
                        }
                    }
                    if(able) cnt++;
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}
