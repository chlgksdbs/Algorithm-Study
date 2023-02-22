package ac0216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main14502 {

	static int n, m;
	static int[][] arr;
	static int[][] temp;
	static int[] iDir = { 1, 0, -1, 0 };
	static int[] jDir = { 0, 1, 0, -1 };
	static ArrayList<int[]> virus;
	static int max = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		temp = new int[n][m];
		virus = new ArrayList<int[]>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < m; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 2) {
					virus.add(new int[] { i, j });
				}
				arr[i][j] = num;
			}
		}

		backTracking(0, 0);

		System.out.println(max);

	}

	static void backTracking(int index, int cnt) {
		if (cnt == 3) {
			calc();
			return;
		}

		for (int i = index; i < n * m; i++) {
			if (arr[i / m][i % m] == 0) {
				arr[i / m][i % m] = 1;
				backTracking(i, cnt + 1);
				arr[i / m][i % m] = 0;

			}
		}

	}

	static void calc() {

		copyArr();
		for (int i = 0; i < virus.size(); i++) {
			bfs(virus.get(i)[0], virus.get(i)[1]);
		}

		int sum = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (temp[i][j] == 0)
					sum++;
			}
		}

		if (max < sum) {
			max = sum;
//			print();
		}
	}

	static void bfs(int iStart, int jStart) {

		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { iStart, jStart });

		while (!queue.isEmpty()) {
			int[] now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nI = now[0] + iDir[i];
				int nJ = now[1] + jDir[i];
				if (nI >= 0 && nI < n && nJ >= 0 && nJ < m && temp[nI][nJ] == 0) {
					temp[nI][nJ] = 2;
					queue.add(new int[] { nI, nJ });
				}
			}

		}
	}

	static void copyArr() {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				temp[i][j] = arr[i][j];
			}
		}
	}

	// 출력
	static void print() {
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				System.out.print(temp[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-----------------------");
	}

}
