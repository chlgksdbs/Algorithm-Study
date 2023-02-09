package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2981 {
	
	// 2개의 자연수의 최대 공약수를 구하는 유클리드 호제법 알고리즘
	public static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 종이에 적은 수의 개수
		int answer = 0; // papers의 모든 수의 최대 공약수를 저장하는 변수
		
		int[] papers = new int[n + 1];
		int[] subPapers = new int[n + 1]; // papers[k] - papers[k-1] 의 값들을 저장한 배열
		
		for (int i = 1; i < n + 1; i++) {
			papers[i] = Integer.parseInt(br.readLine());
			subPapers[i] = Math.abs(papers[i] - papers[i - 1]); // 두 수의 차 (절댓값)
		}
		
		// n이 2인 경우 (예외 처리)
		if (n == 2) {
			answer = subPapers[2]; // 입력 된 2개의 수의 차에 대한 최대 공약수를 계산
		}
		// n이 3 이상인 경우
		else if (n > 2) {
			answer = gcd(subPapers[2], subPapers[3]); // 입력된 3개의 수의 순차적 차이로 초기값 설정
			for (int i = 4; i < n + 1; i++) {
				answer = gcd(answer, subPapers[i]); // answer과 뒤의 2개의 수의 차이의 최대 공약수 계산
			}
		}
		
		// 모든 수의 최대 공약수에서 1을 제외한 약수를 추출
		for (int i = 2; i <= answer; i++) {
			if (answer % i == 0) {
				System.out.print(i + " ");
			}
		}
	}
}
