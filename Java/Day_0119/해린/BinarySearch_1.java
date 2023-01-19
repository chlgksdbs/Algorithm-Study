package day0119;

import java.util.Arrays;
import java.util.Scanner;

public class BJ_1654 {
	
	public static void binarySearch_loop(long[] arr, int n) {
		long lowerBound = 1;
		long upperBound = arr[arr.length-1];
		long max = 0;
		while(lowerBound <= upperBound) {
			long mid = (upperBound + lowerBound) / 2;
			long sum = 0;
			for(int i=0; i<arr.length; i++) {
				sum += arr[i] / mid;
			}
			if(sum < n) {
				upperBound = mid - 1;
			}else {
				max = mid;
				lowerBound = mid + 1;
			}
		}
		System.out.println(max);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		int N = sc.nextInt();
		long[] line = new long[K];
		for(int i=0; i<K; i++) {
			line[i] = sc.nextInt();
		}
		Arrays.sort(line);
		binarySearch_loop(line, N);
		sc.close();
	}
}
