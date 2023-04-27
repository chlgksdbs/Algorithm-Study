package study;

import java.util.Scanner;

public class BOJ_2579 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // 계단 수
		int[] arr = new int[n + 1];

		for (int i = 1; i < n + 1; i++) {
			arr[i] = sc.nextInt();
		}
		int[] sum_score = new int[n + 1];

		int tmp1 = 0;
		int tmp2 = 0;
		
		if(n==1) {
			System.out.println(arr[1]);
			return;
		}else if(n==2) {
			System.out.println(arr[1] + arr[2]);
			return;
		}else if(n==3) {	// 런타임 에러 예외처리
			System.out.println(Math.max(arr[1], arr[2]) + arr[3]);
			return;
		}
		
		sum_score[1] = arr[1];
		sum_score[2] = arr[1] + arr[2];
		sum_score[3] = Math.max(arr[1], arr[2]) + arr[3];	// 예외처리 안하면 런타임 에러

		for (int i = 4; i < n + 1; i++) {
			tmp1 = sum_score[i - 2] + arr[i];					// 3계단 연속 안되므로, 전전 계단에서 오는 경우
			tmp2 = sum_score[i - 3] + arr[i - 1] + arr[i];		// 전계단에서 왔다면 그 전전 계단까지의 누적과 더함
			sum_score[i] = Math.max(tmp1, tmp2);
		}
		System.out.println(sum_score[n]);
	}
}
