import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static long[] h;
    static class Entity{
        long height;
        int idx;

        public Entity(long height, int idx) {
            this.height = height;
            this.idx = idx;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        h = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            h[i] = Long.parseLong(st.nextToken());
        }
        int[] res = new int[N];
        Stack<Entity> stack = new Stack<>();
        stack.push(new Entity(h[0], 0));
        for(int i=1; i<N; i++){
            long newH = h[i];
            while(!stack.isEmpty()){
                long leftH = stack.peek().height;
                if(leftH < newH) stack.pop();
                else break;
            }
            // leftH > newH
            if(stack.isEmpty()) res[i] = 0;
            else res[i] = stack.peek().idx+1;
            stack.push(new Entity(h[i], i));
        }
        StringBuilder sb = new StringBuilder();
        for(int i: res){
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}
