package day0127;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[][] mygraph = new int[n][3];

		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				mygraph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dp = new int[n][3];
		for (int i = 0; i < 3; i++) {
			dp[0][i] = mygraph[0][i];
		}

		for (int i = 1; i < n; i++) {
			dp[i][0] = Math.min(dp[i-1][1],dp[i-1][2])+mygraph[i][0];
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2])+mygraph[i][1];
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1])+mygraph[i][2];

		}
		
		System.out.println(Math.min(Math.min(dp[n-1][0], dp[n-1][1]),dp[n-1][2]));

	}

}
