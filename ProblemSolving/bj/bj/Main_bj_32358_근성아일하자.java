package bj;

import java.io.*;
import java.util.*;

public class Main_bj_32358_근성아일하자 {
    static int N;
    static PriorityQueue<Integer> pq; // 좌표, 차이
    static int start;
    static int totalMove;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        start = 0;
        pq = new PriorityQueue<>((o1, o2) -> {
            if (Math.abs(start - o1) == Math.abs(start - o2)) {
                return o1 - o2; // 더 작은 걸 리턴
            } else {
                return Math.abs(start - o1) - Math.abs(start - o2);
            }
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int cmd = -1, pos = 0;
            if (st.countTokens() == 2) {
                cmd = Integer.parseInt(st.nextToken());
                pos = Integer.parseInt(st.nextToken());
                // System.out.println("cmd: " + cmd + ", " + pos);
            } else if (st.countTokens() == 1) {
                cmd = Integer.parseInt(st.nextToken());
                // System.out.println("cmd: " + cmd);
            }

            if (cmd == 1) {
                pq.add(pos);
            } else {
                while (!pq.isEmpty()) {
                    int newPos = pq.poll();
                    totalMove += Math.abs(newPos - start);
                    start = newPos;
                    PriorityQueue<Integer> temp = new PriorityQueue<>((o1, o2) -> {
                        if (Math.abs(start - o1) == Math.abs(start - o2)) {
                            return o1 - o2; // 더 작은 걸 리턴
                        } else {
                            return Math.abs(start - o1) - Math.abs(start - o2);
                        }
                    });
                    for(int a: pq){
                        temp.add(a); 
                    }
                    pq = temp; 
                    // System.out.println("새로운 이동위치: " + newPos + ", 총 이동거리 : " + totalMove);
                }
            }

        }
        System.out.println(totalMove);
    }
}
