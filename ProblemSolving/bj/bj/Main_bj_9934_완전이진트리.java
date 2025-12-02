package bj;

import java.io.*;
import java.util.*;

/**
 * 중위 순회한 결과물을 이용해서 거꾸로 트리를 구성해야 할 때
 * 이분 탐색 느낌으로, 중위순회의 정중앙은 무조건 트리의 루트 노드가 된다
 * 이때 각 트리 레벨 별로 애들을 묶는단 생각도 같이 하면 좋다!
 */
public class Main_bj_9934_완전이진트리 {
    static int K;
    static int[] arr;
    static int[] tree;
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        N = (int) Math.pow(2, K)-1; // 전체 노드 개수
        arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        treeLevels = new ArrayList[N+1];
        for(int i=0; i<K; i++) treeLevels[i] = new ArrayList<>();
        // N/2가 무조건 루트가 된다
        dfs(0, N-1, 0);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<K; i++){
            for(int node: treeLevels[i]){
                sb.append(node).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static ArrayList<Integer>[] treeLevels;
    static void dfs(int s, int e, int depth){
        if(s > e) return;
        int root = (s+e)/2;

        treeLevels[depth].add(arr[root]);
        dfs(s, root-1, depth+1);
        dfs(root+1, e, depth+1);
    }
}
