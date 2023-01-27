// BJ_2579 : 계단 오르기

package algorithm;

import java.util.Scanner;

public class BJ_2579 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // 계단의 개수
		int[] scores = new int[n + 1]; // 각 계단에 쓰여 있는 점수 (0번째 인덱스는 사용하지 않는다.)
		
		// 입력
		for (int i = 1; i < n + 1; i++) {
			scores[i] = sc.nextInt();
		}
		
		if (n == 1) { // 계단의 개수가 1개인 경우 (예외 처리)
			System.out.println(scores[1]);
		}
		else if (n == 2) { // 계단의 개수가 2개인 경우 (예외 처리)
			System.out.println(scores[1] + scores[2]);
		}
		else { // 계단의 개수가 3개 이상인 경우
			int[] dp = new int[n + 1]; // dp 테이블 생성
			
			// 기저값 생성 (dp는 항상 현재 상황에서의 최적의 값 (여기서는 최댓값)을 저장함)
			dp[1] = scores[1];
			dp[2] = scores[1] + scores[2];
			
			for (int i = 3; i < n + 1; i++) {
				// (1) 2단계 이전의 계단을 밟고 경우와
				// (2) 3단계 이전의 계단을 밟고 이전 계단을 밟은 경우 중 최댓값으로 갱신하여 현재 값을 더함
				dp[i] = Math.max(dp[i - 2], dp[i - 3] + scores[i - 1]) + scores[i];
			}
			
			System.out.println(dp[n]); // 마지막 계단의 저장된 값을 출력
		}
	}
}
