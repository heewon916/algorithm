package bj;
import java.io.*;
import java.util.*;
public class Main_bj_3687_성냥개비 {
    static int N;
    static ArrayList<Integer> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        int[] tmp = new int[]{6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
        for(int i=0; i<10; i++){
            list.add(tmp[i]);
        }
        for(int tc=1; tc<=T; tc++){

        }
    }
}
/*
성냥개비마다 필요한 개수가
, 6

만들 수 있는 최대 큰 수..


 */