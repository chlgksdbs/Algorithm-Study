package algorithm;

import java.util.Scanner;

public class BOJ_11399 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] arr = new int[n];
		
		for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
		
		int arrSum = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (arr[i] > arr[j]) {
					// swap
					int tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}
		
		for (int i = 1; i < n; i++) {
			arr[i] += arr[i - 1];
		}
		
		for (int i = 0; i < n; i++) arrSum += arr[i];
		
		System.out.println(arrSum);
		
	}
}
