package day0127;

import java.util.Scanner;

public class BOJ_2579 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] stairs = new int[n + 1];
		int[] dp = new int[n + 1];
		
		stairs[0] = 0;
		dp[0] = 0;
		
		for (int i = 1; i < n + 1; i++) {
			stairs[i] = sc.nextInt();
			dp[i] = 0;
		}
		
		if (n == 1) {
			System.out.println(stairs[1]);
		}
		else if (n == 2) {
			System.out.println(stairs[1] + stairs[2]);
		}
		else {
			// 기저값 세팅
			dp[1] = stairs[1];
			dp[2] = stairs[1] + stairs[2];
			
			for (int i = 3; i < n + 1; i++) {
				dp[i] = Math.max(dp[i - 2], dp[i - 3] + stairs[i - 1]) + stairs[i];
			}
			
			System.out.println(dp[n]);
		}
		
	}
}