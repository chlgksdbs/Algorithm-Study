// BJ_1463 : 1로 만들기

package algorithm;

import java.util.Scanner;

public class BJ_1463 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] dp = new int[n + 1]; // dp 테이블 생성
		
		for (int i = 1; i < n; i++) {
			// 계속된 최솟값 갱신을 위해 높은 수로 초기화
			// 0번째 인덱스는 사용하지 않으므로 초기화 하지 않는다.
			// n번째 인덱스는 최솟값 갱신을 위한 시작점이므로 0으로 둔다.
			dp[i] = n;
		}
		
		for (int i = n; i > 0; i--) {
			if (i % 3 == 0) { // 정수 n이 3으로 나누어 떨어지는 경우, 저장된 값과 비교하여 최솟값 갱신
				dp[i / 3] = Math.min(dp[i / 3], dp[i] + 1);
			}
			if (i % 2 == 0) { // 정수 n이 2로 나누어 떨어지는 경우, 저장된 값과 비교하여 최솟값 갱신
				dp[i / 2] = Math.min(dp[i / 2], dp[i] + 1);
			}
			// 현재 값에 1을 뺀 dp 테이블에 저장된 값과 비교하여 최솟값 갱신
			dp[i - 1] = Math.min(dp[i - 1], dp[i] + 1);
		}
		
//		// 출력 디버깅
//		for (int i = 0; i < n + 1; i++) {
//			System.out.print(dp[i] + " ");
//		}
		
		System.out.println(dp[1]); // 1로 만들어진 연산의 최솟값을 출력
	}
}
