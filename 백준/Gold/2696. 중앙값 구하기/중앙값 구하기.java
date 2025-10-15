
import java.io.*; 
import java.util.*; 
public class Main {
	static int M; 
	static int[] arr; 
	static void input(BufferedReader br, StringTokenizer st) throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M]; 
		int idx = 0; 
		for(int i=0; i<M/10; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			while(st.hasMoreTokens()) {
				arr[idx++] = Integer.parseInt(st.nextToken());
			}	
		}
		if(M%10 != 0) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<M%10; i++) {
				arr[idx++] = Integer.parseInt(st.nextToken());
			}
		}
	}
	static PriorityQueue<Integer> lower;
	static PriorityQueue<Integer> higher;
	static void addNum(int num) {
		if(lower.isEmpty() || num <= lower.peek()) {
			lower.add(num);
		}else {
			higher.add(num);
		}
		
		if(lower.size() > higher.size()+1) {
			higher.add(lower.poll());
		}else if(higher.size() > lower.size()+1) {
			lower.add(higher.poll());
		}
	}
	static int findMedian() {
		if(lower.size() <= higher.size()) return higher.peek();
		else return lower.peek(); 
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder(); 
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int tc=1; tc<=T; tc++) {
			input(br, st);
			
			lower = new PriorityQueue<>(Collections.reverseOrder());
			higher = new PriorityQueue<>();
		
			ArrayList<Integer> list = new ArrayList<>(); 
			for(int i=0; i<M; i++) {
//				System.out.println("추가: " + arr[i]);
				addNum(arr[i]);
				int median = -1;
				if(i%2 == 0) {
					median = findMedian(); 
					list.add(median);					
				}
//				Iterator iter = lower.iterator();
//				System.out.print("lower: ");
//				while(iter.hasNext()) {
//					System.out.print(iter.next() + " ");
//				}
//				System.out.println();
//				iter = higher.iterator();
//				System.out.print("higher: ");
//				while(iter.hasNext()) {
//					System.out.print(iter.next() + " ");
//				}
//				System.out.println("=> " + median +"\n----");
			}
			
//			System.out.println(list);
			sb.append(list.size()).append("\n");
			int len = 0; 
			for(Integer n: list) {
				sb.append(n + " ");
				if(++len == 10) sb.append("\n");
			}
			sb.append("\n");
			
		}
		System.out.println(sb);
	}

}
/*
홀수번째 수 읽을 때마다 지금까지 입력받은 값의 중앙값 mid = (0+N-1)/2 

T = 10^3
수열 크기 M = 9999 = 10000 = 10^4 
1초 -> 테케당 10^5
1중 반복문만 가능한 상태 
*/