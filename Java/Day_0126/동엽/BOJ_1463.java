package day0127;

import java.util.Scanner;

public class BOJ_1463 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[] dp = new int[n + 1];
		if (n == 1)
			System.out.println(0);
		else if (n == 2 | n == 3)
			System.out.println(1);
		else {
			dp[1] = 0;
			dp[2] = 1;
			dp[3] = 1;
			for (int i = 4; i <= n; i++) {
				int mymax = Integer.MAX_VALUE;
				if (i % 2 == 0) {
					mymax = Math.min(mymax, dp[i / 2]);
				}
				if (i % 3 == 0) {
					mymax = Math.min(mymax, dp[i / 3]);
				}
				mymax = Math.min(mymax, dp[i - 1]);
				mymax++;
				dp[i] = mymax;
			}
			System.out.println(dp[n]);
		}
	}

}
