// BOJ_11726 : 2xn 타일링

package algorithm;

import java.util.Scanner;

public class BOJ_11726 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // 직사각형의 가로 크기
		int[] dp = new int[1001]; // dp 테이블 생성
		
		// 기저값 생성
		dp[1] = 1;
		dp[2] = 2;
		
		for (int i = 3; i <= n; i++) {
			// 현재 크기의 직사각형을 타일로 채우는 방법
			// dp[i - 1] : 저장된 값에 세로 타일 하나 추가
			// dp[i - 2] : 저장된 값에 가로 타일 두개 추가
			dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
		}
		
		System.out.println(dp[n]);
	}
}
