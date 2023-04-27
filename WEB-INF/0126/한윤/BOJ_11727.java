// BOJ_11727 : 2xn 타일링 2

package algorithm;

import java.util.Scanner;

public class BOJ_11727 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // 직사각형의 가로 크기
		int[] dp = new int[1001]; // dp 테이블 생성
		
		// 기저값 생성
		dp[1] = 1;
		dp[2] = 3;
		
		for (int i = 3; i <= n; i++) {
			// dp[i - 1] : 전 값의 타일의 수에 2x1 타일 1개 추가
			// dp[i - 2] : 전전 값의 타일의 수에 1x2 타일 2개 추가, 2x2 타일 1개 추가
			
			dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10007; 
		}
		
		System.out.println(dp[n]);
	}
}
