import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int k = Integer.parseInt(br.readLine());
            
            PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
            PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
            
            boolean[] isDeleted = new boolean[k];

            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                int val = Integer.parseInt(st.nextToken());

                if (cmd.equals("I")) {
                    minHeap.add(new int[]{val, i});
                    maxHeap.add(new int[]{val, i});
                } else {
                    if (val == 1) { // 최댓값 삭제
                        cleanHeap(maxHeap, isDeleted);
                        
                        if (!maxHeap.isEmpty()) {
                            int[] node = maxHeap.poll();
                            isDeleted[node[1]] = true; 
                        }
                    } else { // 최솟값 삭제
                        cleanHeap(minHeap, isDeleted);
                        
                        if (!minHeap.isEmpty()) {
                            int[] node = minHeap.poll();
                            isDeleted[node[1]] = true; 
                        }
                    }
                }
            }
            
            // 모든 연산이 끝난 후, 힙의 최상단에 삭제된 노드가 남아있을 수 있으므로 한 번 더 청소
            cleanHeap(maxHeap, isDeleted);
            cleanHeap(minHeap, isDeleted);

            if (maxHeap.isEmpty() || minHeap.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(maxHeap.peek()[0]).append(" ").append(minHeap.peek()[0]).append("\n");
            }
        }
        System.out.print(sb);
    }

    // 힙의 맨 위에 있는 '이미 삭제된 데이터'를 제거하는 함수
    private static void cleanHeap(PriorityQueue<int[]> pq, boolean[] isDeleted) {
        while (!pq.isEmpty() && isDeleted[pq.peek()[1]]) {
            pq.poll();
        }
    }
}