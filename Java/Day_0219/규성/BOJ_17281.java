package acm0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main17281 {
	static int n;
	static int[][] hit;
	static int[] card;
	static boolean[] roo;
	static int curPoint;
	static int max = 0;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;

		hit = new int[n][9];
		card = new int[9];
		visited = new boolean[9];
		roo = new boolean[3];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				hit[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		card[3] = 0;
		visited[0] = true;
		permu(0);
		System.out.println(max);

	}

	static void permu(int cnt) {
		if(cnt == 3) {
			permu(cnt + 1);
			return;
		}

		if (cnt == 9) {
			
			clearRoo();
			curPoint = 0;
			count(0, 0, 0);

			if (max < curPoint) {
				max = curPoint;
			}

			return;
		}

		for (int i = 1; i < 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				card[cnt] = i;
				permu(cnt + 1);
				visited[i] = false;
			}

		}
	}

	static void count(int ining, int index, int outCount) {
		if (ining == n) {
			return;
		}
		
		int hitNum = hit[ining][card[index]];
		int curOut = outCount;
		if (hitNum == 0) {
			curOut++;
		} else if (hitNum == 4) {
			// 루에 올라간 사람 수
			for (int i = 0; i < 3; i++) {
				if (roo[i])
					curPoint++;
			}
			// 타자 점수
			curPoint++;
			clearRoo();

			// n루타
		} else {
			for (int i = 0; i < hitNum; i++) {
				rotate();
			}
			// 타자 자리 채움
			roo[hitNum - 1] = true;
		}
		
		int curIning = ining;
		if (curOut == 3) {
			curIning++;
			clearRoo();
		}
		

		count(curIning, (index + 1) % 9, curOut%3);
	}

	static void clearRoo() {
		for (int i = 0; i < 3; i++) {
			roo[i] = false;
		}
	}

	static void rotate() {
		if (roo[2]) {
			curPoint++;
		}
		roo[2] = roo[1];
		roo[1] = roo[0];
		roo[0] = false;
	}

}
