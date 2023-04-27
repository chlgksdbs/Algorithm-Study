package day0119;

import java.util.Arrays;
import java.util.Scanner;

public class BJ_1920 {
	
	public static void binarySearch_loop(int[] arr, int x) {
		int arrSize = arr.length;
		int lowerBound = 0;
		int upperBound = arrSize - 1;
		
		while(lowerBound <= upperBound) {
			int mid = (upperBound + lowerBound) / 2;
			
			if(arr[mid] == x) {
				System.out.println(1);
				return;
			}
			else if(arr[mid] > x) {
				upperBound = mid - 1;
			}
			else {
				lowerBound = mid + 1;
			}
		}
		System.out.println(0);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] A = new int[N];
		for(int i=0; i<N; i++) {
			A[i] = sc.nextInt();
		}
		
		Arrays.sort(A);
		
		int M = sc.nextInt();
		for(int i=0; i<M; i++) {
			int key = sc.nextInt();
			binarySearch_loop(A, key);
		}
	}
}
