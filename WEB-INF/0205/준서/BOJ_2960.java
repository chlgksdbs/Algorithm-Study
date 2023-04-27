package Day_0205;

import java.util.Scanner;

public class BOJ_2960 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		// 배열 선언 		
		
		int cnt =0;
		boolean[] arr  = new boolean[n + 1];
		
		for(int i=2; i<=n; i++) {
			for(int j=i; j<=n; j+=i) {	// i배 제거
				if(!arr[j]) {
					arr[j] = true;
					cnt++;
				}
				if(cnt == k) {				// j: k번째로 지워진 수
					System.out.println(j);
					return;
				}
			}
		}
		
		
		
		
		
	}
}
