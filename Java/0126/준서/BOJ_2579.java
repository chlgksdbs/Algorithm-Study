import java.util.Scanner;
 
public class BOJ_2579 {
  
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
 
		int N = in.nextInt();
 
		int[] dp = new int[N + 1];
		int[] map = new int[N + 1];
 
 
		for (int i = 1; i <= N; i++) {
			map[i] =in.nextInt();
		}
        
		
		//핵심 아이디어 
		//도착 할 수 있는 경우  
		// 1. 전칸에서 +1로 올라는 경우 
		// 3칸 연속으로 못 올라온다는 조건으로 인해 n-3에서(지금까지 더 해진 비용) 2칸 올라오고 n-1(이전 칸은 따로 더하기) 에서 올라오는 수 + 현재칸 
		//2. 전칸에서 +2로 올라오는 경우
		// 2칸(지금까지 더 해진 비용)+ 현재칸 
		dp[1] = map[1];
        
		
		if (N >= 2) {
			dp[2] = map[1] + map[2];
		}
 
		for (int i = 3; i <= N; i++) {
			dp[i] = Math.max(dp[i - 2] , dp[i - 3] + map[i - 1]) + map[i];
		}
 
		System.out.println(dp[N]);
 
	}
 
}