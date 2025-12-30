import java.io.*; 
import java.util.*; 
public class Main {
	static int[] nums = {0,1,2,3,4,5,6,7,8,9};
	static Integer[] b; 
	static PriorityQueue<Long> arr; 
	static Set<Long> set; 
	static boolean[] v; 
	static void makeNum(int cnt, int start, int depth) {
		/*
		 * @param cnt 선택한 숫자의 개수가 depth만큼 뽑으면 멈추기 
		 * @param start 중복하지 않도록 수를 고르는 시작점 
		 */
		if(cnt == depth) {
			// 만들어진 수들의 조합을 감소하는 수로 만든다. 
			
//			System.out.println(Arrays.toString(b));
			//!!!!!! b를 내림차순 정렬하면, 원본에도 영향이 가서 그 다음 결과물에 영향을 준다. 
			//!!!!!! 따라서 b를 clone해서 사용해야 한다. 
			Integer[] res = b.clone(); 
			Arrays.sort(res, Collections.reverseOrder());
			StringBuilder sb = new StringBuilder(); 
			for(int i=0; i<depth; i++) {
				sb.append(res[i]);
			}
			arr.add(Long.parseLong(sb.toString()));
			return; 
		}
		for(int i=start; i<10; i++) {
			b[cnt] = nums[i]; 
			makeNum(cnt+1, i+1, depth);
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		arr = new PriorityQueue<>((o1, o2) -> Long.compare(o1, o2)); 
		set = new TreeSet<>(); 	// 자동 정렬 + 자동 중복 제거 
		// 10개 중에서 최소 1개, 최대 10개를 골라 감소하는 수를 만드니까, 최대길이도 10이다. 
		for(int i=1; i<11; i++) {
			b = new Integer[i];
			makeNum(0, 0, i);
		}
		for(int i=0; i<N; i++) arr.poll(); 
		if(arr.isEmpty()) {
			System.out.println(-1);
		}else {
			
			System.out.println(arr.poll());
		}

	}
}
/*
10개 중에서 R개를 뽑아 수를 만든다. 중복은 허용하지 않는다. 
그 수를 내림차순 정렬해서 arr에 넣는다. 
이때 arr은 작은 수 순서대로 저장되어야 하니까, PQ를 쓰자. 
R >= 1



*/
