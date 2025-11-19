package bj;
import java.util.*; 
import java.io.*; 
public class Main_bj_2195_문자열복사 {

	/**
	 * 문자열 인덱스 계산이랑 substring 잘하면 됐는데.. 
	 * 너무 복잡하게 생각한 게 문제였던 ㅠ 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		String S = br.readLine(); 
		String P = br.readLine(); 
		int cnt = 0; 
//		for(int i=0; i<P.length(); i++) {
//			boolean canReplace = false; 
//			int l = 1; 
//			// l: 바꿀 수 있는 부분 문자열 최대 길이 
//			// i+l: 바꾸려는 범위가 P 문자열의 최대 인덱스보다 커지면 안된다 
//			System.out.println(i + "; " + l);
//			while(l<S.length() && (i+l)<P.length()) {
//				canReplace = S.contains(P.substring(i, i+l));
//				if(canReplace) System.out.println("i:" + i + "/ l:" + l +"/ substring:" + P.substring(i, i+l));
//				if(l>0 && !canReplace) {
//					break; // 부분문자열 최대 길이를 구했고, 다시 찾을 수 없게 되면 그게 끝이다 
//				}
//				l++; 
//			}
//			cnt++; 
////			i = i+l-1;
//		}
		int idx=0; 
		while(idx < P.length()) {
			int maxLen = 0; 
			for(int l=1; idx+l <= P.length(); l++) {
				String tmp = P.substring(idx, idx+l);
				
				if(S.contains(tmp)) {
					maxLen = l; 
				}else {
					break; 
				}
			}
			System.out.println(P.substring(idx, idx+maxLen));
			idx += maxLen; 
			cnt++; 
		}
		System.out.println(cnt);

	}

}
/*

*/