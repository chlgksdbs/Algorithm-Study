package day0120;

import java.util.Scanner;

public class BJ_6236 {
	static int[] graph;
	static int result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		graph = new int[n];
		for (int i = 0; i < n; i++) {
			graph[i] = sc.nextInt();
		}
		int start = 0;
		int end = 1000000000;

		while (start <= end) {
			boolean flag = false;
			int budget = 0;
			int moneypull = 0;
			int mid = (start + end) / 2;
			for (int i = 0; i < n; i++) {
				if (budget < graph[i]) {
					if (mid < graph[i]) {
						flag = true;
						break;
					}
					moneypull++;
					budget = mid;
				}
				budget -= graph[i];
			}
			if (flag || moneypull > m) {
				start = mid + 1;
			} else {
				result = mid;
				end = mid - 1;
			}

		}
		System.out.println(result);
	}

}
