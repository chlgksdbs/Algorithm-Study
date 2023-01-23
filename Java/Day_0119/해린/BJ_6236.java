package day0123;

import java.util.Arrays;
import java.util.Scanner;

public class BJ_6236 {

	static int[] arr;
	static int sum;
	static int max;
	
	public static void binarySearch(int m) {
		int start = max;
		int end = sum;
		int min = 0;
		
		while(start<=end) {
			int k = (start + end) / 2;
			int cnt = 1;
			int tmp = k;
			
			for(int i=0; i<arr.length; i++) {
				if(tmp < arr[i]) {
					cnt++;
					tmp = k;
				}
				tmp -= arr[i];
			}
			if(cnt > m) {
				start = k + 1;
			}else {
				min = k;
				end = k - 1;
			}
		}
		System.out.println(min);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
			sum += arr[i];
			if(max<arr[i]) max = arr[i];
		}
		binarySearch(M);
	}

}
