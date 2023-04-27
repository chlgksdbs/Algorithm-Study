// BOJ_1149 : RGB거리

package algorithm;

import java.util.Scanner;

public class BOJ_1149 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // 집의 개수
		int[][] graph = new int[n][3]; // 각 집을 칠하는 비용
		int[][] dp = new int[n][3]; // dp 테이블 생성
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				graph[i][j] = sc.nextInt();
			}
		}
		
		// 기저값 생성
		dp[0][0] = graph[0][0];
		dp[0][1] = graph[0][1];
		dp[0][2] = graph[0][2];
		
		for (int i = 1; i < n; i++) {
			// 각각의 값은 이전 값들 중, 자신의 윗 색을 제외한 나머지 두 값의 최솟값에 현재 자신의 값을 더하여 갱신
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + graph[i][0];
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + graph[i][1];
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + graph[i][2];
		}
		
		// 마지막 저장된 값 중 최솟값을 저장하여 출력
		int answer = Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));
		
		System.out.println(answer);
		
	}
}
