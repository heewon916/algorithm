package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_bj_20058_마법사상어와파이어스톰 {


    static int N, Q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;


        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        int size = (int) Math.pow(2, N);
        int[][] mat = new int[size][size];

        for(int i=0; i<size; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<size; j++){
                mat[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /**
         * 파이어스톰 시전하기
         * 1. 2^L 정사각형으로 나눠서 90도 시계방향으로 돌리기
         * 2. 각 칸에 대해서 인접한 상하좌우 칸 중, 얼음 칸 < 3 이면 그 칸의 얼음 -1
         * 위의 과정을 Q번 반복하기
         *
         * 구해야 되는 거
         * 1. 전체 mat배열의 얼음 총 합
         * 2. bfs로 가장 큰 얼음 덩어리의 칸 개수 세기
         */

        for(int i=0; i<Q; i++){
            // 1번 수행


        }
    }
}
