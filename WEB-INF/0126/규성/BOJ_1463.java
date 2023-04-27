package acmicpc0126_;

import java.util.Scanner;

public class Main1463 {

	static int N;

	static int[] arr = new int[1000001];

	static int culc(int n){
		
		if(n == 1){
			return 0;
		}
		if(n == 0){
			return 0;
		}
		if(arr[n] != 0){
			return arr[n];
		}

		int num1 = 1000001;
		int num2 = 1000001;
		int num3 = 1000001;
		if(n %2 == 0) 
			num1 = culc(n/2) + 1;
		
		if(n%3 == 0) 
			num2 = culc(n/3) + 1;

		num3 = culc(n-1) + 1;

			
		
		arr[n] = Math.min(Math.min(num1, num2), num3);
		return arr[n];

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		
		

		arr[1]=0;
		arr[2]= arr[3] = 1;

		System.out.println(culc(N));

		sc.close();
	}
}
