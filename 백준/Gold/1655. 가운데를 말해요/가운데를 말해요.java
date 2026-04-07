

import java.io.*;
import java.util.*;

public class Main {
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();


        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> left = new PriorityQueue<>((a, b) -> b-a);
        PriorityQueue<Integer> right = new PriorityQueue<>((a, b) -> a-b);

        // right 항상 1개가 더 많게 유지
        // 만약 n 이 홀수면 right에서 하나 꺼내오면 되고, 아니면 minHeap에서 맨 뒤에거

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            if(i%2 == 0){
                left.add(num);
            }else{
                right.add(num);
            }
            if(left.size() >=1 && right.size() >=1){
                if(left.peek() > right.peek()){
                    int tmp = left.poll();
                    left.add(right.poll());
                    right.add(tmp);
                }
            }

            sb.append(left.peek()).append("\n");
        }
        System.out.println(sb);


    }
}
/*

left는 내림차순 -> 가장 큰 게 맨 위에 있음; 중앙값보다 작은 게 들어가 있음
right는 오름차순 -> 가장 작은 게 맨 위에 있음; 중앙값보다 큰 게 들어가 있음

번갈아 가면서 넣으면 항상 크기가 같거나 1 차이 나게 됨
left에서 가장 큰 게, right에서 가장 작은 것보다 크면 -> 규칙이 틀리니까
이 경우엔 left.peek()랑 right.peek()를 바꿔줘야 함

그리고 결론은 left.peek()가 항상 답이 됨
왜냐면 left에 항상 1개가 더 들어가고,
n이 짝수개일 땐 더 작은 게 중앙값이라고 했으니까
 */