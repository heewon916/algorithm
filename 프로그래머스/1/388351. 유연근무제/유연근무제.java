class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;

        for (int i = 0; i < schedules.length; i++) {
            int h = schedules[i] / 100;
            int m = schedules[i] % 100;
            int deadline = h * 60 + m + 10;

            boolean success = true;

            for (int j = 0; j < 7; j++) {
                int day = (startday + j) % 7;

                // 토요일(6), 일요일(0)은 제외
                if (day == 6 || day == 0) {
                    continue;
                }

                int log = timelogs[i][j];
                int logM = (log / 100) * 60 + (log % 100);

                if (logM > deadline) {
                    success = false;
                    break;
                }
            }

            if (success) {
                answer++;
            }
        }

        return answer;
    }
}
/*
모든 직원에 대해서 timelogs[i]를 돌려보면 될 것 같긴 하네
timelogs[i][j]에 대해서, 
    (j+startday) % 7 = 6, 0이면 패스하면 됨 
    
시간 복잡도는? 
    n * (7) 괜찮지 않나? 

직원 i
schedules[i] + 10 >= timelogs[i][day]
    주의: 그냥 더 일찍 오는 건 상관 없기 때문에 timelogs[i][day] >= schedules[i]는 무의미하다 
    
시간 계산 
    h = schedules[i] / 100 
    m = schedules[i] % 100 
    deadline = h * 60 + m + 10 
    logM = timelogs[i][j] / 100 * 60 + timelogs[i][j] % 100 
    logM > deadline -> continue 
    
// day = j+1일차에 출근한 시각
startday = 1 to 7 월~일 

schedules[i] / 100 = ? .. 59 (분? )
timelogs[i][day] / 100 = ? .. 59 

*/