package study;

import java.util.Scanner;

public class BOJ_11726 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] arr = new int[1002];
		arr[1] = 1;
		arr[2] = 2;

		for (int i = 3; i < n + 1; i++) {
			arr[i] = (arr[i - 2] + arr[i - 1]) % 10007;
		}
		System.out.println(arr[n]);
	}
}
