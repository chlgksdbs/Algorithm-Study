package day0120;

import java.util.Scanner;

public class BJ_1300 {
	static int n;
	static int k;
	static int answer;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		
		binarysearch(1, k, k);
		System.out.println(answer);
	}

	public static void binarysearch(int start, int end, int target) {
		answer = start;

		if (start > end)
			return;

		int mid = (start + end) / 2;
		int now = 0;
		for (int i = 1; i <= n; i++) {
			now += Math.min(mid / i, n);
		}
		if (now < target) {
			binarysearch(mid + 1, end, target);
		} else {
			binarysearch(start,mid-1,target);
		}

	}
}