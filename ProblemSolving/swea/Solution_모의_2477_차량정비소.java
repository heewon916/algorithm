
import java.io.*; 
import java.util.*; 

public class Solution_모의_2477_차량정비소 {
    static int N, M, K;  // 접수 창구 개수, 정비 창구 개수, 고객 수 
    static int A, B; // 지갑 놓고 간 접수창구, 정비창구 번호 
    static int[] applyCounter; // 각 접수 창구별로 걸리는 시간 
    static int[] fixCounter;  // 각 정비 창구별로 걸리는 시간
    static ArrayList<Customer> customers; // 각 고객 정보 관리 배열 
    static class Customer{
        int id; // 내 번호 
        int arriveTime; // 처음 도착한 시간 
        int usedApplyN; // 이용한 접수창구 번호 
        int usedFixN;   // 이용한 정비창구 번호 
        int finApplyTime; // 접수를 마친 시간 -> 정비창구 대기자가 된다. 
        int finFixTime; // 정비를 마친 시간 
        public Customer(int id, int arriveTime, int usedApplyN, int usedFixN, int finApplyTime, int finFixTime) {
            this.id = id; 
            this.arriveTime = arriveTime;
            this.usedApplyN = usedApplyN;
            this.usedFixN = usedFixN;
            this.finApplyTime = finApplyTime;
            this.finFixTime = finFixTime; 
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder(); 
        int T = Integer.parseInt(st.nextToken());

        for(int tc=1; tc<=T; tc++){
            input(br, st);

            int time = 0; // 현재 시간 
            int doneCnt = 0; // 정비까지 마친 고객 명수 

            // 접수창구 대기자 목록: 여러 명이 기다릴 때, 고객번호가 빠른 순으로 나간다. 
            PriorityQueue<Customer> waitApply = new PriorityQueue<>((o1, o2) -> {
                // 고객 번호가 빠른 순으로 빠져나간다. 
                return Integer.compare(o1.id, o2.id);
            });
            // 정비창구 대기자 목록: 여러 명이 기다릴 때, 
            // (시간, 이용한 접수창구번호) 순으로 오름차순 정렬 
            PriorityQueue<Customer> waitFix = new PriorityQueue<>((o1, o2) -> {
                if(o1.finApplyTime == o2.finApplyTime){
                    return Integer.compare(o1.usedApplyN, o2.usedApplyN);
                }else{
                    return Integer.compare(o1.finApplyTime, o2.finApplyTime);
                }
            });
            // 각 접수창구가 사용 중인지 체크한다. 
            // 각 창구 <- 사용 중인 고객의 정보 넣기
            Customer[] usingApply = new Customer[N+1];

            // 각 정비창구가 사용 중인지 체크한다. 
            Customer[] usingFix = new Customer[M+1];

            while(doneCnt < K){
                // 해당 time에 도착한 유저가 있는지 파악하고, 접수자 대기목록에 추가한다. 
                for(Customer c: customers){
                    if(c.arriveTime == time) {
                        waitApply.offer(c);
                    }
                }

                // 해당 time에 접수창구 중에서 이용이 끝난 고객이 있으면 정비창구 대기자로 옮긴다. 
                for(int i=1; i<=N; i++){
                    if(usingApply[i] != null && usingApply[i].finApplyTime == time){
                        waitFix.offer(usingApply[i]);
                        usingApply[i] = null; 
                    }

                }
                
                // 해당 time에 정비창구 중에서 이용이 끝난 고객이 있으면 이용을 마치게 한다. 
                for(int i=1; i<=M; i++){
                    if(usingFix[i] != null && usingFix[i].finFixTime == time){
                        usingFix[i] = null; 
                        doneCnt++; 
                    }
                }
                
                // 접수 창구에 대기하는 유저가 있으면 
                if(waitApply.size() > 0){
                    for(int i=1; i<=N; i++){
                        if(usingApply[i] == null) {
                            if(!waitApply.isEmpty()){
                                Customer c= waitApply.poll(); 
                                c.usedApplyN = i; 
                                c.finApplyTime = time + applyCounter[i];
                                usingApply[i] = c;
                            }
                        } 
                    }
                }

                // 정비 창구에 대기하는 유저가 있으면 
                if(waitFix.size() > 0){ 
                    for(int i=1; i<=M; i++){
                        if(usingFix[i] == null){
                            if(!waitFix.isEmpty()){
                                Customer c= waitFix.poll(); 
                                c.usedFixN = i; 
                                c.finFixTime = time + fixCounter[i];
                                usingFix[i] = c;
                            }
                        }
                    }
                }

                time++; 
            }

            // 전체 고객에 대해서, 지갑 잃은 사람이 사용한 창구와 동일한 곳을 오고간 사람의 번호 찾기 
            int ans = 0; 
            for(Customer c: customers){
                if(c.usedApplyN == A && c.usedFixN == B) ans += c.id; 
            }
            if(ans == 0) ans = -1; 
            sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }
        System.out.println(sb);
        /*
         * 모든 고객이 정비까지 마칠 때까지 
         *  해당 time에 도착한 유저가 있는지 
         *      전체 customers 중에서 time == arriveTime이면 
         *          접수창구 대기자목록에 추구한다.  
         *  
         *  해당 time에 접수창구 중에서 
         *      이용이 끝난 고객은 -> 내 도착 시간 + 접수창구 이용 시간 == time 
         *          finApplyTime 갱신
         *          정비창구 대기자 목록에 추가한다. 
         *  해당 time에 정비창구 중에서 
         *      이용이 끝난 고객은 -> finApplyTime + 정비창구 이용 시간 == time 
         *          doneCnt++ 
         *          
         * 
         *  접수창구에 대기하는 유저가 있으면 
         *      빈 창구가 없을 때 pass 
         *      빈 창구가 있을 때 
         *          고객 번호 <- 접수창구 대기자 목록에서 poll
         *          이용할 창구 번호 <- for문 전체 돌 때 가장 먼저 만난 빈 창구로 이동 
         *          해당 고객의 usedApplyN <- 이용할 창구 번호 
         *          
         *  정비창구에 대기하는 유저가 있으면 (정비창구 대기자목록이 안 비었으면)
         *      빈 창구가 없을 때 pass 
         *      빈 창구가 있을 때 
         *          고객 번호 <- 정비창구 대기자 목록에서 poll 
         *          이용할 창구 번호 <- for문 전체 돌 때 가장 먼저 만난 빈 창구로 이동
         */
    }
    static void input(BufferedReader br, StringTokenizer st) throws Exception{
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 접수 창구 개수 
        M = Integer.parseInt(st.nextToken()); // 정비 창구 개수 
        K = Integer.parseInt(st.nextToken()); // 고객 수 
        A = Integer.parseInt(st.nextToken()); // 지갑 놓고 간 접수창구
        B = Integer.parseInt(st.nextToken()); // 지갑 놓고 간 정비창구 

        applyCounter = new int[N+1];
        fixCounter = new int[M+1];
        customers = new ArrayList<>();

        st = new StringTokenizer(br.readLine(), " "); 
        for(int i=1; i<=N; i++){
            applyCounter[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=M; i++){
            fixCounter[i] = Integer.parseInt(st.nextToken());
        } 

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=K; i++){
            customers.add(new Customer(i, Integer.parseInt(st.nextToken()), 0, 0, 0, 0));
        } 
    }
}
/*
N개의 접수창구 
M개의 정비창구 
K명의 고객 
접수창구 기준 
- 빈 창구가 없으면 
    기다린다. 
- 빈 창구가 있으면 
    고객 선택: 대기 고객이 2명 이상이면 -> 낮은 번호 고객 먼저 
    창구 선택: 낮은 창구번호 먼저 
    
각 접수창구마다 필요한 것 
- 각 창구가 사용중인지 체크 
전체 접수창구에 대해서 
- 빈 창구가 있는지 체크 
    있다면 
        어느 창구가 비었는지 번호 저장 -> 낮은 번호 순이니까 우선순위큐이면 될 듯 
접수창구 대기자 목록 
- 낮은 번호 고객일수록 우선순위가 높다
    번호 기준으로 우선순위 큐 사용 


정비창구 기준 
- 빈 창구가 없으면 
    기다린다. 
- 빈 창구가 있으면 
    고객 선택: 시간 기준으로 먼저 온 사람 먼저 -> FIFO = 큐
        동시 도착 존재: 이용한 접수창구 번호 작은 사람 먼저 
    창구 선택: 여러 곳이면 작은 번호 먼저 

각 정비창구마다 필요한 것 
- 각 창구가 사용중인지 체크 
전체 정비창구에 대해서 
- 빈 창구가 있는지 체크 
    있다면 
        어느 창구가 비었는지 번호 저장 -> 낮은 번호 순이니까 우선순위 큐로 구현 
정비창구 대기자 목록 
- 접수 완료한 시간이 작을수록 우선순위가 높다 
    접수창구에서 끝난 시간 기준으로 우선순위 큐 사용 
        2차 정렬은, 고객이 사용한 접수창구 번호를 기준으로 오름차순 정렬

고객 정보 
- 이용한 접수창구 번호 
- 이용한 정비창구 번호 
- 처음 도착 시간 
- 접수창구 이용마친 시간 

고객이 어디 창구번호를 이용했는지는 고객이 관리하게 하자.
전체 고객이 모두 정비까지 마치면 while문을 종료한다. 
 */