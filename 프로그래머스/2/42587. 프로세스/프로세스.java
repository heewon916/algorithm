import java.util.*; 
class Solution {
    class Process{
        int pid; 
        int priority; 
        public Process(int pid, int priority){
            this.pid = pid; 
            this.priority = priority; 
        }
    }
    public int solution(int[] priorities, int location) {
        int answer = 0;
        /*
        큐 안에 있는 프로세스 중에서, 우선순위의 max값을 항상 알아야겠는데
        큐: 프로세스의 초기 인덱스 숫자, 내 우선순위 
        */
        ArrayDeque<int[]> q = new ArrayDeque<>(); 
        List<Integer> pList = new LinkedList<>();
        
        int seq = 1; 
        int maxP = -1; 
        for(int i=0; i<priorities.length; i++){
            q.add(new int[]{i, priorities[i]});
            pList.add(priorities[i]);
        }
        // pList = new ArrayList(new HashSet<>(pList)); 
        Collections.sort(pList, Collections.reverseOrder());
        /*
        0. 가장 큰 우선순위 구하기 -> maxP에 저장 
        1. 맨 앞에 있는 애가 locaiton == i인지 검사 
            맞으면 break seq가 answer임 
            아니면 2. 
        2. location == i가 될 때까지 while문 돌려 
            찾으면 stop 하고 break 
        */
        
        while(!q.isEmpty()){
           maxP = pList.remove(0);

           int[] cur = null; 
           while(true){
               cur = q.poll(); 
               if(maxP == cur[1]){
                   break; 
               }else{
                   q.offer(cur);
               }
           }

           if(location == cur[0]){
               answer = seq; 
               break; 
           }
           seq++; 
        }
        return answer;
    }
}