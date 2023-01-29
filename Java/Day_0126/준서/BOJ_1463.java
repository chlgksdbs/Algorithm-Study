import java.util.Scanner;

public class BOJ_1463 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int dp[] = new int[10000001]; // 들어올 수 있는 최대수에 해당하는 dp 테이블 작성

		// 보텀업 방식 사용 - 그래프를 그려보면 이전에 사용했던 것을 지속해서 사용
		// 미리 계산된걸 가져다가 있으면 그대로 사용하겠다
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + 1; // 1로 뻬기

			if (i % 3 == 0) // 3으로 나누어 떨어진다면
				dp[i] = Math.min(dp[i], dp[i / 3] + 1) ;
			if (i % 2 == 0) //2로 나누어 떨어진다면 
				dp[i] = Math.min(dp[i], dp[i / 2] + 1) ;
			//이미 계산되어 있는 이전 인덱스 값에서 더 작은걸 취함 

		}
		System.out.println(dp[n]);
	}
}
