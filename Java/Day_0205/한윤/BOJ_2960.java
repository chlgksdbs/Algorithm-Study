package algorithm;

import java.util.Scanner;

public class BOJ_2960 {
	static boolean visited[]; // 방문 처리를 확인하는 배열
	
	public static int isPrimeNumberCount(int primeNumber_size, int target) {
		int answer = 0; // k번째 지워지는 수에 대한 정보
		int cnt = 0; // 수가 지워진 횟수를 저장
		
		for (int i = 2; i <= primeNumber_size; i++) {
			int idx = 1;
			while (true) {
				if (i * idx > primeNumber_size) break; // 범위를 벗어나는 경우 반복문 탈출
				if (!visited[i * idx]) {
					visited[i * idx] = true;
					cnt++;
					if (cnt == target) return i * idx; // target에 해당하는 값은 찾은 경우, 그 값은 반환
				}
				idx++; // 값 증가
			}
		}
		
		
		return answer;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		visited = new boolean[n + 1];
		
		System.out.println(isPrimeNumberCount(n, k));		
	}
}
