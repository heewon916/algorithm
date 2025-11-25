import java.util.*; 
import java.io.*; 
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		String S = br.readLine(); 
		String P = br.readLine(); 
		int cnt = 0; 
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
			// System.out.println(P.substring(idx, idx+maxLen));
			idx += maxLen; 
			cnt++; 
		}
		System.out.println(cnt);

	}

}
/*

*/