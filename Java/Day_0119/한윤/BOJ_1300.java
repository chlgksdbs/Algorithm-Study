package baekjoon.algorithm.study;

import java.util.Scanner;

public class BOJ_1300 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 배열 A와 B의 인덱스는 1부터 시작
		int n = sc.nextInt(); // 배열의 크기
		int k = sc.nextInt(); // 찾고자 하는 배열 B의 인덱스
		
		// 1. idea : B[k] = x라고 한다면, k는 항상 x보다 작거나 같다. (x보다 작거나 같은 원소의 개수가 최소 k개)
		// ex) 4 X 4 행렬을 1차원으로 변형했을 때, B[11] = 8
		// 즉, B행렬의 11번째 값은 8 -> 8이라는 값보다 작거나 같은 원소의 개수는 최소 11개
		// -> 이러한 성질을 활용해서 문제 접근!
		
		int start = 1; // 이진 탐색의 시작점 (숫자가 1부터 시작)
		int end = k; // 이진 탐색의 끝점 (우리가 찾아야 하는 B[k]는 항상 k보다 작거나 같으므로 끝점 설정)
		int answer = -1; // 정답을 저장하는 변수
		
		// 2. idea : 어떤 수 x가 있을 때, 구구단에서 해당 수보다 작거나 같은 수의 개수는 x / (구구단의 단) 이다.
		// ex) x = 8, 구구단 1단 : 8 / 1 = 8개, 구구단 2단 : 8 / 2 = 4개 ...
		
		while (start <= end) {
			int x = (start + end) / 2; // x로 예상하는 값
			int xCount = 0;
			
			for (int i = 1; i <= n; i++) {
				// 1 ~ n단 까지의 구구단에서 mid보다 작은 값을 구하는 공식
				// 여기서 x / i가 n을 넘기지 못하므로, 다음과 같이 두 비교 값의 최솟값을 넣어야 함
				xCount += Math.min(x / i, n);
			}
			
			// 임의의 x보다 작거나 같은 수의 개수(xCount)와 k의 값을 비교
			if (xCount < k) {
				start = x + 1;
			}
			
			// xCount가 많다는 것은 임의의 x보다 작은 수가 B[k]보다 많다는 뜻
			else {
				end = x - 1;
			}
		}
		
		System.out.println(start);
	}
}
