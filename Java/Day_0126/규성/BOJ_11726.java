package acmicpc0126_;

import java.util.Scanner;

public class Main11726 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int [] dp = new int[N+1];
		
		dp[1] = 1;
		
		if(N == 1) {
			System.out.println(1);
		} else {
			dp[2] = 2;
			
			for(int i = 3; i < N+1; i++) {
				dp[i] = (dp[i-2] + dp[i-1])%10007;
			}
			
			System.out.println(dp[N]%10007);
		}
		
		
		sc.close();
		
		
		

	}
}
