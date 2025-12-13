import java.io.*;
import java.util.*;

/**
 * 양쪽 팀 전부한테 계산해 줄 필요가 없었음. 한 쪽 팀의 승리 횟수를 전체 n*m에서 빼주면 그게 상대 팀의 승리횟수가 됨
 * 그리고 이분탐색할 때 left = mid + 1 이거 자꾸만 left ++ 이걸로 계산하지 마라
 * 이분탐색만으로는 내가 몇 명을 이겼는지 정확한 계산이 안됨.
 * 중복되는 코딩 실력이 있을 수 있으니까.
 */
public class Main {
    static int N, M;
    static int[] t1;
    static int[] t2;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());  M = Integer.parseInt(st.nextToken());
        t1 = new int[N]; t2 = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) t1[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<M; i++) t2[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(t1); Arrays.sort(t2);
        long t1Win = 0, draw = 0;
        for(int i=0; i<N; i++) {
            int lower = lowerBound(t2, t1[i]);
            int upper = upperBound(t2, t1[i]);
            draw += (upper - lower);
            t1Win += lower;
        }
        long total = (long) N * M;
        long t2Win = total - t1Win - draw;
        System.out.println(t1Win + " " + t2Win + " " + draw);
    }

    /**
     * @param arr : 상대 팀 배열
     * @param target : 내 팀에서 계산 대상이 될 점수
     * @return 처음으로 arr[mid] >= target이 되는 위치
     */
    static int lowerBound(int[] arr, int target){
        int left = 0, right = arr.length;
        while(left < right){
            int mid = (left + right) / 2;
            /**
             * arr[mid] >= target 인 이유:
             * arr[mid] > target 일 때 right = mid -1 로 해버리면 중복인 수가 있을 때 left가 한칸 앞으로 이동하면서 최소 인덱스 보장이 깨진다.
             */
            if(arr[mid] >= target) {
                right = mid;
            }
            else left = mid + 1;
        }
        return left;
    }
    /**
     * @param arr : 상대 팀 배열
     * @param target : 내 팀에서 계산 대상이 될 점수
     * @return 처음으로 arr[mid] > target이 되는 위치
     */
    static int upperBound(int[] arr, int target){
        int left = 0, right = arr.length;
        while(left < right){
            int mid = (left + right) / 2;
            /**
             * arr[mid] >= target 인 이유:
             * arr[mid] > target 일 때 right = mid -1 로 해버리면 중복인 수가 있을 때 left가 한칸 앞으로 이동하면서 최소 인덱스 보장이 깨진다.
             */
            if(arr[mid] > target) {
                right = mid;
            }
            else left = mid + 1;
        }
        return left;
    }

}
/*
이 문제 각 팀의 코딩 실력을 오름차순 정렬하고 난 다음에,
A팀의 각 점수에 대해서 이분탐색으로 B팀의 점수 상에서 A팀의 점수가 있어야 하는 인덱스를 구하면 그게 승, 패할 횟수를 구할 수 있게 되잖아.
이걸 B팀에 대해서도 수행하고.
그리고 무승부는 나와 같은 점수를 가진 경우의 횟수만 구하면 되는 거니까 각 팀에 대해서 전체 for문 한번씩 돌리면 되지 않나?
 */