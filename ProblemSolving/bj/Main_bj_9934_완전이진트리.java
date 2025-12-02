import java.io.*;
import java.util.*;

public class Main_bj_9934_완전이진트리 {
    static int K;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        int N = (int) Math.pow(2, K)-1;
        arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int i = 0;
        int[] tree = new int[N];
        if(N == 1){
            System.out.println(arr[0]);
            return;
        }
        // 1. 가장 왼쪽 끝을 구한다
        int pos = N/2;
        tree[pos] = arr[i]; i++;
        while(true){
            // 2. 부모는 2로 나누고, 형제는 +1 더해서 채운다
            int parent = pos/2;
            int bro = pos+1;
            if(parent == 0){
                pos = parent;
                tree[parent] = arr[i]; i++;
                break;
            }
            tree[parent] = arr[i]; i++;
            tree[bro] = arr[i]; i++;
            pos = parent;
        }
//        System.out.println(Arrays.toString(tree) + i);
        // 3. 그러다 부모가 0이 되면 오른쪽 서브 트리에 대해서 1,2번을 반복한다.
        int left = pos+2;
        while(true){
            if(left * 2 + 1 >= N) break;
            left  = left * 2 + 1;
        }
        pos = left;
        tree[pos] = arr[i]; i++;
        while(i < N-1){
            // 2. 부모는 2로 나누고, 형제는 +1 더해서 채운다
            int parent = pos/2;
            int bro = pos+1;
            tree[parent] = arr[i]; i++;
            tree[bro] = arr[i]; i++;
            pos = parent;
        }
//        System.out.println(Arrays.toString(tree) );
        int size = 1;
        int u = 0;
        StringBuilder sb = new StringBuilder();
        while(u < N-1){
            for(int k=0; k<size; k++){
                sb.append(tree[u+k] + " ");
            }
            sb.append("\n");
            u += size;
            size *= 2;

        }
        System.out.println(sb);
    }
}
