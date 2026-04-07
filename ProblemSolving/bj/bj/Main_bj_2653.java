package bj;
import java.awt.*;
import java.util.*;
import java.io.*;
public class Main_bj_2653 {
    static int n;
    static int[][] map;
    static boolean[] v;
    static ArrayList<ArrayList<Integer>> group;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        v = new boolean[n];
        group = new ArrayList<>();

        for(int i=0; i<n; i++){
            if(!v[i]) bfs(i);
        }

        boolean able = true;
        for(ArrayList<Integer> g: group){
            if(g.size() == 1) {
                System.out.println(0); return;
            }
            for(int i=0; i<g.size(); i++){
                int a = g.get(i);
                for(int j=i+1; j<g.size(); j++){
                    int b = g.get(j);
                    if(map[a][b] == 1) {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(group.size()).append("\n");
        for(ArrayList<Integer> g: group){
            Collections.sort(g);
        }
        Collections.sort(group, (a, b) -> a.get(0)-b.get(0));
        for(ArrayList<Integer> g: group){
            for(int n: g) sb.append(n+1).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static void bfs(int node){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(node);
        v[node] = true;
        ArrayList<Integer> tmp = new ArrayList<>();
        while(!q.isEmpty()){
            int cur = q.poll();
            tmp.add(cur);
            for(int i=0; i<n; i++){
                if(!v[i] && map[cur][i] == 0 && i != cur){
                    q.add(i);
                    v[i] = true;
                }
            }
        }
        group.add(tmp);
    }
}
/*

하나의 네트워크 안에 속한 모든 정점끼리 좋아함
여러 개의 네트워크
    각 네트워크 안에서는 모두 서로 좋아함
    서로 다른 네트워크끼리는 모두 서로 싫어함
    네트워크를 이루는 정점 개수 >= 2

어떤 한 사람을 좋아하는 사람이 아무도 없다면 이 집단은 안정된 집단이 아니다.

1..n
좋아하면 0
싫어하면 1

안정 vs 노안정
안정 -> 한 개의 집단, 여러 개의 소집단 분할 가능?

하나의 정점 기준
정점 1을 기준으로 주변에 0으로 이어진 정점을 방문해서

그 트리와 연결된 정점과의 최대 가중치가 0이면 포함

정점 중심이라 프림?

1. 집단으로 나눈다
- 서로 이어진 간선이 전부 다 0인 부분 집합
2. 집단의 크기 = 1인 곳이 있다 -> 안정적이지 않다.

 */