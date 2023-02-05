package algorithm;

import java.util.Scanner;

public class BOJ_10974 {
	static boolean visited[]; // 방문 처리를 위한 배열
	static int printOut[]; // 순열 출력을 위해 임시로 저장해두는 배열
	static int[] arr; // 1 ~ n까지의 숫자를 저장하는 배열
	
	// depth : 순열의 개수를 측정하기 위한 변수
	public static void permutation(int n, int depth) {
		if (depth == n) { // 출력 부분
			for (int num : printOut) System.out.print(num + " ");
			System.out.println();
		}
		
		for (int i = 0; i < n; i++) {
			if(!visited[i]) { // 해당 부분이 방문 처리가 되지 않은 경우
				visited[i] = true;
				printOut[depth] = arr[i];
				permutation(n, depth + 1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		visited = new boolean[n];
		printOut = new int[n];
		arr = new int[n];
		
		for (int i = 1; i <= n; i++) {
			arr[i - 1] = i;
		}
		
		permutation(n, 0);
	}
}
