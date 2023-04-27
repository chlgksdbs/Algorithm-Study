package day0126;

import java.util.Scanner;

public class BOJ_1463 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		int[] arr = new int[n+1];
		
		for(int i=2;i<=n;i++) {
			int min = Integer.MAX_VALUE;
			if(i%3==0) 
				min = Math.min(arr[i/3]+1, min);
			if(i%2==0)
				min = Math.min(arr[i/2]+1, min); 
			min = Math.min(arr[i-1]+1, min);
			
			arr[i] = min;
			
		}
		System.out.println(arr[n]);
		
		
	}

}
