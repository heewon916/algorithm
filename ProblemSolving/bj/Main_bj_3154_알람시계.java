import java.io.*;
import java.util.*;

public class Main_bj_3154_알람시계 {
    static int[][] map = new int[][] {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 9 },
            { -1, 0, -1 }
    };
    static int[][] pos = new int[][] {
            { 3, 1 }, { 0, 0 }, { 0, 1 }, { 0, 2 },
            { 1, 0 }, { 1, 1 }, { 1, 2 },
            { 2, 0 }, { 2, 1 }, { 2, 2 }
    };

    static int calcEffort(String h, String m) {
        String hm = h + m;
        int effort = 0;
        int prev = hm.charAt(0) - '0';
        for (int i = 1; i < hm.length(); i++) {
            int now = hm.charAt(i) - '0';
            int[] pp = pos[prev];
            int[] pn = pos[now];
            effort += Math.abs(pp[0] - pn[0]) + Math.abs(pp[1] - pn[1]);
            prev = now;
        }
        return effort;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), ":");
        String hour = st.nextToken();
        String min = st.nextToken();

        ArrayList<String> hours = new ArrayList<>();
        ArrayList<String> mins = new ArrayList<>();

        int temp_h = Integer.valueOf(hour);
        int temp_m = Integer.valueOf(min);
        hours.add(hour);
        while (true) {
            temp_h += 24;
            if (temp_h >= 100)
                break;
            hours.add(temp_h + "");
        }
        mins.add(min);
        while (true) {
            temp_m += 60;
            if (temp_m >= 100)
                break;
            mins.add(temp_m + "");
        }
        // System.out.println("가능한 결과물:" + hours + ", " + mins);
        int minEffort = Integer.MAX_VALUE;
        String[] ans = new String[2];
        for (int i = 0; i < hours.size(); i++) {
            for (int j = 0; j < mins.size(); j++) {
                String h = hours.get(i);
                String m = mins.get(j);
                // System.out.println(h + ":" + m);
                int effort = calcEffort(h, m);
                // System.out.println("=> 필요한 노력: " + effort);
                if (minEffort > effort) {
                    minEffort = effort;
                    ans[0] = h;
                    ans[1] = m;
                    // System.out.println("@@정답 후보됨: " + Arrays.toString(ans));
                }
            }
        }
        // String res = "";
        // if (ans[0] < 10)
        // res += '0' + ans[0];
        // else
        // res += ans[0] + "";
        // res += ":";
        // if (ans[1] < 10)
        // res += '0' + ans[1];
        // else
        // res += ans[1] + "";

        System.out.println(ans[0] + ":" + ans[1]);
    }

}
/**
 * 시간과 분을 나타내는 4개의 숫자를 입력
 * 화면에 나타나는 시간은 24로 나눈 나머지라는 것
 * 분 역시 60으로 나눈 나머지가 화면에 나타나는
 * 
 * 알람시각을 맞추는데 최소한의 노력
 * 키 a에서 키 b로 이동할 때 필요한 노력 = 맨허튼 거리
 * 
 * [입력]
 * HH:MM 형식으로 설정하고 싶은 시각
 * 시간이나 분이 한 자리수라면 선행하는 0이 붙어야
 * 
 * [출력]
 * 최소한의 노력으로 입력 시각 표시하기
 * 답이 여러 가지라면 가장 빠른 시각
 */