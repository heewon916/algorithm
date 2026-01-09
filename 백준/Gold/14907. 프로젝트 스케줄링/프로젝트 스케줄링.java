import java.io.*;
import java.util.*;
public class Main { // 나에게 잔디 심기를 허용해줘;; 
    static int[] indegree; // 진입차수 계산하기
    static List<Integer>[] list; // 연결 리스트: 'A':0 ,'B':1, ..
    static int[] days; // 작업당 소요되는 시간
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        indegree = new int[26];
        days = new int[26];
        list = new List[26];
        for(int i=0; i<26; i++) list[i] = new ArrayList<>();

        // 입력 받음
        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) { // !!!!! 문제
            st = new StringTokenizer(line, " ");
            int toIdx = st.nextToken().charAt(0)-'A';
            int time = Integer.parseInt(st.nextToken());
            // days 초기화
            days[toIdx] = time;

            if(st.hasMoreTokens()){
                String froms = st.nextToken(); // 선행 작업
                // indegree 초기화
                for(int j = 0; j< froms.length(); j++){
                    int f = froms.charAt(j) - 'A';
                    list[f].add(toIdx);
                    indegree[toIdx]++;
                }
            }
//            System.out.println(toIdx + " " + time + " " + Arrays.toString(indegree));
        }
//        System.out.println("indegree:" + Arrays.toString(indegree));
//        System.out.println("days:" + Arrays.toString(days));
//        for(int i=0; i<26; i++){
//            System.out.println((char)('A'+i) + " -> " + list[i]);
//        }

        ArrayDeque<int[]> q = new ArrayDeque<>(); // <인덱스, 깊이>
        int[] dp = new int[26];
        int totalTime = 0;

        // 진입차수가 0인 애들 큐에 저장
        for(int i=0; i<26; i++){
            if(indegree[i] == 0) {
                q.add(new int[]{i, days[i]});
                dp[i] = days[i];
                totalTime = Math.max(dp[i], totalTime);
            }
        }

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0], ct = cur[1];
//            System.out.println("ci: " + (char)(ci+'A') + ", ct: " + ct);
//            if(list[ci].isEmpty()){
//                System.out.println("END");
//                totalTime += ct;
//                continue;
//            }
            for(int adj: list[ci]){
                dp[adj] = Math.max(days[adj] + ct, dp[adj]);
                if(--indegree[adj] == 0) q.add(new int[]{adj, dp[adj]});
                totalTime = Math.max(dp[adj], totalTime);
            }
//            System.out.println("after: dp = " + Arrays.toString(dp));
//            System.out.println("after: indegree = " + Arrays.toString(indegree));
        }
        System.out.println(totalTime);
    }
}
