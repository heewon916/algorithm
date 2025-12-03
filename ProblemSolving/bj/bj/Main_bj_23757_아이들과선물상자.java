package bj;
import java.io.*;
import java.util.*;
public class Main_bj_23757_아이들과선물상자 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

//        Integer[] budgets = new Integer[N];
        PriorityQueue<Integer> budgets = new PriorityQueue<>((o1, o2) -> o2-o1);
        int[] childs = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++){
//            budgets[i] = Integer.parseInt(st.nextToken());
            budgets.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<M; i++){
            childs[i] = Integer.parseInt(st.nextToken());
        }
//        Arrays.sort(budgets, Collections.reverseOrder());
        boolean able = true;
        int budget_ptr = 0;

        for(int i=0; i<M; i++){
//            System.out.println(Arrays.toString(budgets));
//            System.out.println(budgets);
//            System.out.println(Arrays.toString(childs));
            // 더 이상 줄 수 있는 선물이 없거나,
//            if(budgets[0] == 0) { // || (budgets.peek() == 0 && budgets.size() == 1)
//                able = false;
//                break;
//            }
//            int left = budgets[budget_ptr];
            int left = budgets.poll();
            if(left < childs[i]){
                able = false;
                break;
            }
//            budgets[budget_ptr] -= childs[i];
            budgets.add(left - childs[i]);
//            Arrays.sort(budgets, Collections.reverseOrder());
//            // 남은 선물은 더 있는데
//            if(budgets[budget_ptr] == 0) {
//                budget_ptr++;
//            }
//            System.out.println("after " + i + ": " + budgets);
        }
        if(able) System.out.println(1);
        else System.out.println(0);
    }
}

/*
N개의 선물 상자
M명의 아이들
현재 선물이 가장 많이 들은 상자에서 각자 원하는 만큼 가져감
아이들은 순서대로
자신이 원하는 것보다 항상 선물이 많이 들어있어야 함

4 3 2 1

a[i] 가 몇 학생들이 희망하는 선물 개수의 합과 딱 일치해야 됨
4 1 2 3 이렇게 선물이 있는데
2 2 2 이런
 */