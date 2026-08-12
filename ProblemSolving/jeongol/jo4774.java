import java.util.*;
import java.io.*;

class jo4774 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int L = 0;
        int maxDt = 0;
        int ans = 0;


        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            L = Math.max(L, d);
            maxDt = Math.max(maxDt, d + t);
        }
        ans = Math.max(2*L, maxDt);
        System.out.println(ans);
    }
}

/**
 -- 내가 해낸 것
 a[i] 중에서 최장 거리 = L 이라고 하면
 시작점 0부터 L까지 갈 때, a[i] >= t[i] 이면 따먹을 수 있음
 거기서 다 못 따먹은 거는, 돌아올 때 L + (L-a[i]) >= t[i]이면 먹을 수 있음
 그럼에도 못 따먹은 건 어떡하지? 다시 가야 하나? ==> 여기서 막힘

 -- 내가 막힌 이유
 나에게 있는 선택지는 3가지
 1. 왼쪽으로 이동
 2. 오른쪽으로 이동
 3. 가만히 기다림

 나는 1,2까진 했지만 3을 활용하지 않음.
 그렇다면 L 위치에서 W만큼만 기다린다고 해보자.
 그 뒤에, 되돌아올 때 2L + W - a[i] >= t[i]이면 모두 먹을 수 있어야 함

그럼 여기서 드는 생각이 '어디서 기다린다고 할 건데?' 라는 변수가 생김.

 그래서 간단하게 생각하면 이렇게 됨

 어느 a[i] 위치에 있다고 하면 t[i] 초가 될 때까지 못 땀. t[i]초 후에 a[i] 거리만큼 되돌아와야 함
 따라서 하나의 딸기를 따는데 걸리는 최소 시간이 t[i] + a[i] 임.

 전체적으로 본다면 그 최대치가 2*L일 수 있음.
 ===
 답 >= max(
 2L,
 max(T[i] + A[i])
 )
 */