package programmers;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PG_92335 {
	
	static int N, K; // 양의 정수, 바꾸고자 하는 진수
	
	// 해당 수가 소수인지 판별하는 함수
	public static boolean isPrimeNumber(double n) {
		
		// 1은 소수가 아님
		if (n == 1) return false;
		
		// 2와 3은 소수 (아래 반복문을 수행하지 않으므로 여기서 처리)
		if (n == 2 || n == 3) return true;
		
		// 제곱근까지 체크하여 나누어떨어지는 경우, 소수가 아님
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) return false;
		}
		
		return true; // 중간에 반복문을 종료하지 않은 경우, 소수임
	}
	
	public static int solution(int n, int k) {
		
		int primeCount = 0; // 문제 조건에 맞는 소수의 개수 저장
		
		Queue<Character> q = new LinkedList<>(); // 0을 만났을 때 처리하기 위한 queue 자료구조
		
		String kBinary;
		
		// k가 10인 경우
		if (k == 10) {
			kBinary = Integer.toString(n);
		}
		// k가 10을 제외한 나머지 수인 경우
		else {
			kBinary = Integer.toString(n, k);	
		}
		
		// kBinary 길이만큼 체크
		for (int i = 0; i < kBinary.length(); i++) {
			// 0을 만난 경우
			if (kBinary.charAt(i) == '0') {
				String tmp = "";
				// queue가 빌 때까지 수행
				while (!q.isEmpty()) tmp += q.poll();
				// tmp가 비어있지 않고, 10진법으로 변환한 부분이 소수인 경우 count 증가
				// 소수로 바꾼 부분이 지나치게 커질 수 있으므로, double형으로 체크 (-> 테스트 1, 11번 런타임 에러 관련)
				if (!tmp.equals("") && isPrimeNumber(Double.parseDouble(tmp))) primeCount++;
				
				continue; // 0은 추가하지 않음
			}
			q.add(kBinary.charAt(i));
		}
		
		// 남아있는 부분에 대해 처리
		String tmp = "";
		while (!q.isEmpty()) tmp += q.poll();
		
		if (!tmp.equals("") && isPrimeNumber(Double.parseDouble(tmp))) primeCount++;
		
		return primeCount;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		// 자바에서 10진수 -> n진수 변환법
		// Integer.toString(n, k); -> 10진수 n을 k진수로 변환 (String)		
		System.out.println(solution(N, K));
	}
}
