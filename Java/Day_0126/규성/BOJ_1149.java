package acmicpc;

import java.util.Scanner;

public class Main1149 {


	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int redStartSum = 0;
		int greenStartSum = 0;
		int blueStartSum = 0;

		int[][] arr = new int[N+1][3];

		arr[0][0] = 0;
		arr[0][1] = 0;
		arr[0][2] = 0;
		

		for(int i = 1; i <= N; i++){
			int redCost = sc.nextInt();
			int greenCost = sc.nextInt();
			int blueCost = sc.nextInt();

			arr[i][0] += Math.min(arr[i-1][1], arr[i-1][2]) + redCost;
			arr[i][1] += Math.min(arr[i-1][0], arr[i-1][2]) + greenCost;
			arr[i][2] += Math.min(arr[i-1][0], arr[i-1][1]) + blueCost;
		}

		System.out.println(Math.min(arr[N][0], Math.min(arr[N][1], arr[N][2])));
	}
}