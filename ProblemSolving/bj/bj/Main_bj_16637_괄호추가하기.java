package bj;
import java.io.*; 
import java.util.*; 
public class Main_bj_16637_괄호추가하기 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String input = br.readLine(); 
		ArrayList<Character> op = new ArrayList<>(); 
		ArrayList<Integer> num = new ArrayList<>();
		for(int i=0; i<N; i++) {
			char in = input.charAt(i);
			if(in == '*' || in == '+' || in == '-') {
				op.add(in);
			}else {
				num.add(Integer.valueOf(in-'0'));
			}
		}
		
	}	

}
/*
길이가 N인 수식 
0~9의 정수와 연산자 구성
연산자의 우선순위 없음 -> 모두 동일함 -> 왼쪽에서부터 순서대로 계산해야 됨 
괄호를 추가하면, 괄호는 먼저 계산 가능함 
중첩된 괄호는 사용이 불가능함 
괄호를 추가해서 결과의 최댓값을 구해야 함 
괄호를 아예 안 추가해도 되고, 무한대로 추가해도 됨 
괄호 안에 연산자는 1개만 
*/ 