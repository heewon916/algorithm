import java.io.*; 
import java.util.*; 
public class Main {
	/**
	 * i부터 K개의 숫자를 아예 뒤집은 배열을 돌려준다. 
	 * @param cur
	 * @param i
	 * @param K
	 * @return
	 */
	static int swap(int cur, int i, int N, int K) {
		String curStr = String.valueOf(cur); 
		StringBuilder sb = new StringBuilder(); 
		sb.append(curStr.substring(0, i));
		StringBuilder mid = new StringBuilder(curStr.substring(i, i+K));
		sb.append(mid.reverse());
		sb.append(curStr.substring(i+K, N));
		return Integer.parseInt(sb.toString());
	}
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null; 
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N]; 
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
                
        int strt = 0; 
        int end = 0; 
        for(int i=0; i<N; i++) {
        	strt = strt*10 + arr[i]; 
        }
        Arrays.sort(arr);
        for(int i=0; i<N; i++) {
        	end = end*10 + arr[i]; 
        }
        
        // depth별로 관리하는 게 어떨까 
        // depth별로 만들어진 문자열 관리 + 전체 문자열 관리 
        Set<Integer> allVisited = new HashSet<>(); 
        ArrayDeque<Integer> q = new ArrayDeque<>(); 
        q.add(strt);
        allVisited.add(strt);
        /**
         * q에는 현재 depth에 해당하는 문자열들이 들어가있게 된다. 
         * 이때 모든 문자열을 이미 다 만들어봐서 q에 더 추가되는 게 없거나 
         * end랑 같은 문자열을 찾으면? 그 depth를 줘야 하는 거 아닐까 
         */
        int depth = 0; 
        boolean able;
        int answer = -1; 
        while(!q.isEmpty()) {
        	int size = q.size(); 
        	
        	for(int s=0; s<size; s++) {
        		int cur = q.poll(); 
        		
        		if(cur == end) {
        			System.out.println(depth);
        			return; 
        		}
        		
        		for(int i=0; i<=N-K; i++) {
        			int newStr = swap(cur, i, N, K);
//        			System.out.println("새로운 str: " + newStr);
        			if(allVisited.add(newStr)) {
        				q.offer(newStr);
//        				System.out.println("-> 추가됨");
        			}
        		}
        	}
        	
        	depth++; 
        }
        System.out.println(answer);
        /*
        while(true) {
        	ArrayDeque<Integer> nextDepthQ = new ArrayDeque<>(); 
//        	System.out.println("현재 depth에 들어가있는 숫자들: ");
//        	for(String str: q) {
//        		System.out.println(str);
//        	}
        	able = false; 
        	
        	while(!q.isEmpty()) {
//        		String cur = q.poll(); 
        		int cur = q.poll(); 
//        		System.out.println("레벨" + depth + "에 있는 수: " + cur);
        		if(cur == end) {
        			able = true; 
        			break; 
        		}
        		for(int i=0; i<=N-K; i++) {
        			int newStr = swap(cur, i, N, K);
//        			System.out.println("새로운 str: " + newStr);
        			if(allVisited.add(newStr)) {
        				nextDepthQ.offer(newStr);
//        				System.out.println("-> 추가됨");
        			}
        		}
        	}
//        	System.out.println("다음 depth에 들어갈 숫자들: ");
//        	for(String str: nextDepthQ) {
//        		System.out.println(str);
//        	}
        	if(able) {
        		answer = depth; 
        		break; 
        	}
        	if(nextDepthQ.isEmpty()) {
        		break; 
        	}
        	q = nextDepthQ; 
        	depth++; 
        }
        System.out.println(answer);
        */
    }
}
/*
1~N까지의 숫자
N자리의 순열 
K 

어떤 수 뒤집으면 
그 수 포함해서, 오른쪽으로 K개의 수 뒤집기 
i, .. i+(K-1)

반드시 K개의 수를 뒤집어야 한다
숫자를 뒤집어도, 내 뒤에 K-1개의 수가 있어야 한다 

순열 오름차순으로 만드려고 함. 최소 몇 개 선택해야 됨? 

5 4 3 2 1 에서 K=3 
어떤 수를 뒤집으면 -> 그 수부터 오른쪽으로 K개의 수를 뒤집는다 

5 4 3 2 1 ㅏ=2
5 4 3 1 2 
5 4 1 3 2
5 1 4 3 2 
1 5 4 3 2
15423 

가장 작은 수를 기준으로 뒤집고 있음 

3 2 1 
1 2 3 

3 2 4 1 5  k=4 
1 4 2 3 5 

7 2 1 6 8 4 3 5 k=4 

5 4 3 2 1
???????????????
뒤집기.. 최대한 많은 수가 오름차순이 되어야 하는 게 맞을까/ 


 */