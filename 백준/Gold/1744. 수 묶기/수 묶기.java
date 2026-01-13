
import java.io.*;
import java.util.*;
public class Main {
    static ArrayList<Integer> upList;
    static ArrayList<Integer> underList;
    static int N, upSum, underSum, result;
    static boolean hasZero;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        upList = new ArrayList<>();
        underList = new ArrayList<>();
        upSum = 0; underSum = 0; result = 0;
        hasZero = false;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if(n == 1) result += 1;
            else if(n < 0) underList.add(n);
            else if(n > 0) upList.add(n);
            else hasZero = true;
        }
/**
 * <양수의 개수, 음수의 개수>를 쌍으로 봤을 때
 * <짝수, 짝수>이면 둘 다 2개씩 묶었을 때 안 남으니까 up_sum + under_sum(+0)이 정답
 * <홀수, 짝수>이면 양수만 1개 더 남으니 up_sum + under_sum + 남은양수1개 (+0)이 정답
 * <짝수, 홀수>이면 음수만 1개 남는데, 0이 있을 때는 0과 곱하고, 0이 없을 때는 그대로 빼서 up_sum + under_sum + 남은음수1개 가 정답이야.
 * <홀수, 홀수>이면 가장 작은 양수, 가장 큰 음수가 1개씩 남는데, 0이 있을 때는 음수와 곱하고, 0이 없을 때는 up_sum + under_sum + 남은양수 + 남은 음수가 답이야.
 */
        upList.sort((o1, o2) -> o2-o1);
        underList.sort((o1, o2) -> o1-o2);

        for (int i = 0; i < upList.size() - 1; i += 2) {
            result += upList.get(i) * upList.get(i + 1);
        }
        for (int i = 0; i < underList.size() - 1; i += 2) {
            result += underList.get(i) * underList.get(i + 1);
        }

        int upListSize = upList.size() % 2;
        int underListSize = underList.size() % 2;

        if(upListSize == 1 && underListSize == 1){
            result += upList.get(upList.size() - 1);
            if(!hasZero) result += underList.get(underList.size() - 1);

        }else if(upListSize == 1 && underListSize == 0){
            result += upList.get(upList.size() - 1);

        }else if(upListSize == 0 && underListSize == 1){
            if(!hasZero) result += underList.get(underList.size() - 1);

        }
        System.out.println(result);

    }
}
