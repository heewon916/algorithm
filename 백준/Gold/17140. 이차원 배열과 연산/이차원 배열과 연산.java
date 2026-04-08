import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[100][100];

        for(int i=0; i<3; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<3; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int row_count = 3;
        int col_count = 3;
        int sec = 0;
        int[] appearCnt = new int[101];
        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                if(o1[1] == o2[1]){ // 빈도수가 같으면 수 기준으로 오름차순 정렬
                    return o1[0] - o2[0];
                }else{ // 빈도수가 다르면 빈도수 기준으로 정렬
                    return o1[1] - o2[1];
                }
            }
        });
        while(sec <= 100){
            if(map[R-1][C-1] == K) break;
            sec++;

            // 행의 개수 >= 열의 개수 -> R연산을 해야 하고
            int max_len = 0;
            if(row_count >= col_count){
                // 행을 들고 와서, 정렬 수행
                // 각 행마다 수, 등장횟수를 세야 한다.

                // 행마다
                for(int r=0; r<row_count; r++){
                    // 행에 나타나는 수와 빈도수를 계산한다.
                    for(int j=0; j<100; j++){
                        if(map[r][j] > 0){
                            appearCnt[map[r][j]]++;
                            map[r][j] = 0;
                        }
                    }
                    // 센 등장횟수를 기준으로 오름차순 정렬; 횟수가 같으면 수 기준으로 오름차순 정렬
                    for(int j=1; j<101; j++){
                        if(appearCnt[j] > 0) {
                            q.offer(new int[]{j, appearCnt[j]});
                            appearCnt[j] = 0;
                        }
                    }
                    // 그 상태에서 map에 다시 저장
                    // 늘어나는 열의 길이는 size*2가 된다.
//                    for(int[] a: q) System.out.println(Arrays.toString(a));
                    int size = q.size();
                    int new_len = size*2;
                    if(new_len>100) new_len = 100;
                    max_len = Math.max(max_len, new_len);
//                    col_count = Math.max(size * 2, col_count);
                    int idx = 0;
                    for(int j=0; j<size; j++){
                        int[] cur = q.poll();
                        map[r][idx++] = cur[0];
                        map[r][idx++] = cur[1];
                    }
//                    System.out.println(Arrays.toString(map[i]));
//                    System.out.println("R연산 완료: " + row_count + " " + col_count + " q.isEmpty? " + q.isEmpty());
                }
                col_count = max_len;
            }else{ // 행의 개수 < 열의 개수
                // 열을 들고 와서, 정렬 수행
                // 각 열마다 수, 등장횟수를 세야 한다.

                // 열마다
                for(int col=0; col<col_count; col++){
                    // 열에 나타나는 수와 빈도수를 계산한다.
                    for(int r=0; r<row_count; r++){
                        if(map[r][col] > 0){
                            appearCnt[map[r][col]]++;
                            map[r][col] = 0;
                        }
                    }

                    // 등장횟수를 기준으로 오름차순 정렬하기 위해서 q에 offer한다.
                    for(int i=1; i<101; i++){
                        if(appearCnt[i] > 0){
                            q.offer(new int[]{i, appearCnt[i]});
                            appearCnt[i] = 0;
                        }
                    }

                    // 그 상태에서 map에 다시 저장
                    // 늘어나는 행의 길이는 size*2가 된다.
                    int size = q.size();
                    int new_len = size*2;
                    if(new_len>100) new_len = 100;
                    max_len = Math.max(max_len, new_len);
//                    row_count = Math.max(size*2, row_count);
                    int idx = 0;
//                    for(int[] a: q) System.out.println(Arrays.toString(a));
                    for(int i=0; i<size; i++){
                        int[] cur = q.poll();
                        map[idx++][col] = cur[0];
                        map[idx++][col] = cur[1];
                    }
//                    System.out.print("[ ");
//                    for(int i=0; i<100; i++){
//                        System.out.print(map[i][col] + " ");
//                    }
//                    System.out.print("]\n");
//                    System.out.println("C연산 완료: " + row_count + " " + col_count + " q.isEmpty? " + q.isEmpty());
                }
                row_count = max_len;
            }
        }

        if(sec == 101) System.out.println(-1);
        else System.out.println(sec);
    }
}
/*
[설계]
배열을 100x100으로 선언하고,
초마다 채워져 있는 행과 열의 size를 계산해야하지 않을까?

행 크기 4
열 크기 3
1 2 2
1 2 1
2 1 3
3 3 3

1초: R연산 (행 개수 4>= 열 개수)
각 행마다 숫자들을 집합으로 만들고, 개수를 센다.
그 크기가 몇인지 계산한다.

1 1 2 2 0 0
2 1 1 2 0 0
1 1 2 1 3 1
3 3 0 0 0 0

2초: C연산 (행 개수 4 < 열 개수 6)

1 2 1 3 을 읽고, 1 2 2 1 3 1 을 만들고 넣는다. 이때의 행 길이 6으로 늘어남
 */