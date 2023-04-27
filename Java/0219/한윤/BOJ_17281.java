package baekjoon.algorithm.study;

import java.util.Scanner;

public class BOJ_17281 {
	
	static int N; // 이닝 수
	static int ans; // 얻을 수 있는 최대 점수
	
	static int idx; // 현재 타석에 있는 타자를 가리키는 인덱스
	static int maxSum; // 현재 이닝에서의 최대 점수
	
	static int[] orders; // 순열을 통해 타순을 새로 저장한 배열
	static boolean[] selected; // 뽑아진 타순에 대한 배열
	
	static int[][] batters; // N 이닝까지의 1 - 9 번까지 선수의 결과를 저장하는 배열
	
	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(batters[i][orders[j]] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
			
	// 해당 경우의 수에서 점수를 계산하는 함수
	public static int calculate() {
		int sum = 0;
		
		for (int tc = 0; tc < N; tc++) {
			int outCount = 0; // out count가 3이 되면 함수 종료
			boolean[] score = new boolean[8]; // 진루 상황을 체크할 배열
						
			while (outCount < 3) {
				if (batters[tc][orders[idx]] == 0) outCount++;
				
				// 1, 2, 3루타를 친 경우에는 해당 타수만큼 배열에 있는 값을 밀어주고 이전 값을 false로 채움
				else if (batters[tc][orders[idx]] == 1) {
					for (int i = 4; i > 1; i--) {
						score[i] = score[i - 1];
						score[i - 1] = false;
					}
					
					// 해당 타수에 해당하는 값을 다시 채움
					score[1] = true;
					
					// 4부터는 점수 획득에 성공한 케이스 이므로, 점수를 count하고 false로 채움
					for (int i = 4; i < 5; i++) {
						if (score[i]) {
							sum++;
							score[i] = false;
						}
					}
				}
				else if (batters[tc][orders[idx]] == 2) {
					for (int i = 5; i > 2; i--) {
						score[i] = score[i - 2];
						score[i - 2] = false;
					}
					score[2] = true;
					for (int i = 4; i < 6; i++) {
						if (score[i]) {
							sum++;
							score[i] = false;
						}
					}
				}
				else if (batters[tc][orders[idx]] == 3) {
					for (int i = 6; i > 3; i--) {
						score[i] = score[i - 3];
						score[i - 3] = false;
					}
					score[3] = true;
					for (int i = 4; i < 7; i++) {
						if (score[i]) {
							sum++;
							score[i] = false;
						}
					}
				}
				else if (batters[tc][orders[idx]] == 4) {
					for (int i = 7; i > 4; i--) {
						score[i] = score[i - 4];
						score[i - 4] = false;
					}
					score[4] = true;
					for (int i = 4; i < 8; i++) {
						if (score[i]) {
							sum++;
							score[i] = false;
						}
					}
				}
				
				idx = (idx + 1) % 9;
			}
		}
		
		return sum;
	}
		
	// 순서가 상관이 있으므로 순열 함수 -> 하지만 동일한 숫자인 경우는 건너 뛰어도 되는데??? -> 다른 방법 쓰자!
	public static void perm(int cnt) {
		
		// 4번 타자는 1번 선수로 고정되어 있으므로, 다음 재귀로 건너 뜀
		if (cnt == 3) {
			perm(cnt + 1);
			return;
		}
		
		// 9명의 타순이 정해진 경우
		if (cnt == 9) {
			idx = 0;
			ans = Math.max(ans, calculate());
			
			return;
		}
		
		// 경우의 수
		for (int i = 1; i < 9; i++) {
			if (selected[i]) continue;
				selected[i] = true;
				orders[cnt] = i;
				perm(cnt + 1);
				selected[i] = false;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		ans = 0;
		
		orders = new int[9];
		selected = new boolean[9];
		
		batters = new int[N][9];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 9; j++) {
				batters[i][j] = sc.nextInt();
			}
		} // 입력 종료
		
		orders[3] = 0;
		perm(0);
		
		System.out.println(ans);
	}
}
