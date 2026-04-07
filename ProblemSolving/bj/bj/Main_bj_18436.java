package bj;

import java.io.*;
import java.util.*;

public class Main_bj_18436 {
    static int[] arr;
    static int[] checkOdd; // 짝수면 0 홀수면 1
    static int n, m;
    static int[] tree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        arr = new int[n]; checkOdd = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            checkOdd[i] = (arr[i] % 2) == 0? 0: 1;
        }

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        int h = (int) Math.ceil(Math.log(n) / Math.log(2));
        tree = new int[(int)Math.pow(2, h+1)];
        init(0, n-1, 1);

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken()); // i, l
            int b = Integer.parseInt(st.nextToken()); // x, r

            if(cmd == 1){
                // 데이터 변경하기 Ai를 x로 바꾼다.
                int idx = a - 1;
                int newOddStat = (b % 2) == 0? 0: 1;
                if(newOddStat != checkOdd[idx]){
                    int diff =  newOddStat - checkOdd[idx];
                    checkOdd[idx] = newOddStat;
                    update(1, 0, n-1, idx, diff);
                }
                arr[idx] = b;

            }else if(cmd == 2){
                // l..r 사이의 짝수 개수

                int totalCount = (b - a + 1);
                int oddCount = sum(1, 0, n-1, a-1, b-1);
                sb.append(totalCount - oddCount).append("\n");
            }else{
                // l..r 사이의 홀수 개수
                int oddCount = sum(1, 0, n-1, a-1, b-1);
                sb.append(oddCount).append("\n");
            }
        }
        System.out.println(sb);
    }

    /**
     * 구간 합 트리 생성하기
     * @param start 시작 인덱스
     * @param end 끝 인덱스
     * @param node 초기값은 1
     * @return
     */
    static int init(int start, int end, int node){
        if(start == end) return tree[node] = checkOdd[start]; // 홀수면 1, 짝수면 0을 저장
        int mid = (start + end) / 2;
        // 재귀적으로 두 부분으로 나눈 뒤에, 그 합은 자기 자신으로 한다.: 왼쪽과 오른쪽 자식 노드로 출발
        return tree[node] = init(start, mid, node * 2) + init(mid+1, end, node * 2 + 1);
    }

    /**
     * 구간 합 트리 수정하기
     * @param node 현재 노드 idx
     * @param start 배열의 시작
     * @param end 배열의 끝
     * @param idx 변경된 데이터의 idx
     * @param diff 원래 데이터 값과 변경 데이터 값의 차이
     */
    static void update(int node, int start, int end, int idx, int diff){
        // 만약 변경할 index 값이 범위 밖이면 확인 불필요
        if(idx < start || idx > end) return;

        tree[node] += diff;
        if(start != end){ // 리프 노드가 아니면 자식도 확인하러 감
            int mid = (start + end) / 2;
            update(node*2, start, mid, idx, diff);
            update(node*2+1, mid+1, end, idx, diff);
        }

    }

    /**
     * 구간 합 구하기
     * @param node 현재 노드 idx
     * @param start 배열의 시작
     * @param end 배열의 끝
     * @param left 원하는 누적합의 시작
     * @param right 원하는 누적합의 끝
     * @return
     */
    static int sum(int node, int start, int end, int left, int right){
        // 배열 범위를 벗어나면 더할 필요 없음
        if(left > end || right < start) return 0;

        // l..r 범위 안에 완전히 포함되면 더 내려가지 않고 바로 리턴한다
        if(left <= start && end <= right) return tree[node];

        // 그 외의 경우는 좌/우 자식 노드 지속 탐색 수행
        int mid = (start + end) / 2;
        return sum(node*2, start, mid, left, right) + sum(node*2+1, mid+1, end, left, right);
    }
}
/*

 */