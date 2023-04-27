package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14891 {
	
	static int K; // 회전 횟수
	static int ans; // 톱니바퀴의 점수의 합
	
	static char[][] gear; // 1 ~ 4번 톱니바퀴의 상태
	static boolean[] visited; // 1 ~ 4번 톱니바퀴의 처리 상태
	
	static class Pair {
		int gearNum; // 회전시킨 톱니바퀴의 번호
		int dir; // 회전 방향 (1 : 시계 방향, -1 : 반시계 방향)
		
		public Pair(int gearNum, int dir) {
			this.gearNum = gearNum;
			this.dir = dir;
		}
	}
	
	public static void print(char[] gear) {
		for (int i = 0; i < 8; i++) {
			System.out.print(gear[i] + " ");
		}
		System.out.println();
	}
	
	// 시계 방향 회전
	public static void forwardRotate(char[][] gear, boolean[] visited, int gearNum) {
		
		// 현재 번호를 기준으로 오른쪽 톱니바퀴 확인
		if (gearNum < 4) {
			if (!visited[gearNum + 1] && gear[gearNum][2] != gear[gearNum + 1][6]) {
				visited[gearNum + 1] = true;
				reverseRotate(gear, visited, gearNum + 1);
			}
		}
		
		// 현재 번호를 기준으로 왼쪽 톱니바퀴 확인
		if (gearNum > 0) {
			if (!visited[gearNum - 1] && gear[gearNum][6] != gear[gearNum - 1][2]) {
				visited[gearNum - 1] = true;
				reverseRotate(gear, visited, gearNum - 1);
			}
		}
		
		char tmp = gear[gearNum][0];
		
		for (int i = 7; i >= 0; i--) {
			gear[gearNum][(i + 1) % 8] = gear[gearNum][i];
		}
		
		gear[gearNum][1] = tmp;
	}
	
	// 반시계 방향 회전
	public static void reverseRotate(char[][] gear, boolean[] visited, int gearNum) {
		
		// 현재 번호를 기준으로 오른쪽 톱니바퀴 확인
		if (gearNum < 4) {
			if (!visited[gearNum + 1] && gear[gearNum][2] != gear[gearNum + 1][6]) {
				visited[gearNum + 1] = true;
				forwardRotate(gear, visited, gearNum + 1);
			}
		}
		
		// 현재 번호를 기준으로 왼쪽 톱니바퀴 확인
		if (gearNum > 0) {
			if (!visited[gearNum - 1] && gear[gearNum][6] != gear[gearNum - 1][2]) {
				visited[gearNum - 1] = true;
				forwardRotate(gear, visited, gearNum - 1);
			}
		}
		
		char tmp = gear[gearNum][0];
		
		for (int i = 0; i < 8; i++) {
			gear[gearNum][i] = gear[gearNum][(i + 1) % 8];
		}
		
		gear[gearNum][7] = tmp;
	}
	
	// [2] : 톱니바퀴 오른쪽 맞닿는 인덱스, [6] : 톱니바퀴 왼쪽 맞닿는 인덱스
	public static void perform(Pair[] p, int cnt) {
		
		// 함수의 종료 조건
		if (cnt == K) return;
		
		visited = new boolean[5];
		
		int gearNum = p[cnt].gearNum;
		int dir = p[cnt].dir;
		
		visited[gearNum] = true;
		
		// 톱니바퀴 회전 연산
		if (dir == 1) {
			forwardRotate(gear, visited, gearNum);
		}
		else {
			reverseRotate(gear, visited, gearNum);
		}
		
		perform(p, cnt + 1);
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		gear = new char[5][8]; // 0번 인덱스는 사용하지 않음
		
		String tmp;
		
		for (int i = 1; i <= 4; i++) {
			tmp = br.readLine();
			for (int j = 0; j < 8; j++) {
				gear[i][j] = tmp.charAt(j);
			}
		}
		
		K = Integer.parseInt(br.readLine());
		Pair[] p = new Pair[K];
		
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			p[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		// 입력 종료
		
		perform(p, 0);
		
		for (int i = 1; i <= 4; i++) {
			if (gear[i][0] == '1') ans += (int)Math.pow(2, i - 1);
		}
		
		System.out.println(ans);
	}
}
