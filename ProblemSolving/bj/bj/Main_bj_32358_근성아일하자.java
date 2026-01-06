package bj;

import java.io.*;
import java.util.*;

public class Main_bj_32358_근성아일하자 {
    static int N;
    static TreeMap<Integer, Integer> map; 
    static Integer start;
    static int totalMove;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        start = 0;
        map = new TreeMap<>();
        totalMove = 0; 
        int trashCnt = 0; 
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int cmd = -1, pos = 0;
            if (st.countTokens() == 2) {
                cmd = Integer.parseInt(st.nextToken());
                pos = Integer.parseInt(st.nextToken());
            } else if (st.countTokens() == 1) {
                cmd = Integer.parseInt(st.nextToken());
            }

            if (cmd == 1) {
                if(map.containsKey(pos)){
                    map.put(pos, map.get(pos)+1);
                }else{
                    map.put(pos, 1);
                }
                trashCnt++; 
            } else {
                while(trashCnt > 0){
                    Integer left = map.floorKey(start);
                    Integer right = map.ceilingKey(start);
                    Integer target = null; 

                    if(left == null){
                        target = right; 
                    }else if(right == null){
                        target = left; 
                    }else{
                        // 둘 다 있으면 거리 차이 가져오고 
                        int ldiff = Math.abs(start - left);
                        int rdiff = Math.abs(start - right);
    
                        // 왼쪽이 더 가까우면 
                        if(ldiff < rdiff) {
                            target = left; 
                        }else if(ldiff > rdiff){ 
                            // 오른쪽이 더 가까우면 
                            target = right; 
                        }else{
                            // 둘이 거리가 같으면 더 작은 좌표를 타겟으로 가져감 
                            if(left <= right){
                                target = left; 
                            }else{
                                target = right; 
                            }
                        }
                        // 이동거리 축적하고 현재 위치 target으로 변경 
                        totalMove += Math.abs(start - target);
                        start = target; 
                        // 만약 target 좌표에 있던 쓰레기 개수가 0이 되면 그 좌표는 아예 delete
                        if(map.get(target) == 0){
                            map.remove(target);
                        }else{
                            map.put(target, map.get(target)-1);
                        }
                    }
                    trashCnt--; 
                }
            }

        }
        System.out.println(totalMove);
    }
}
