package ac0216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main15683 {

	static int n, m;
	static int[][] arr;
	static int[][] temp;
	static int[] numbers;
	static ArrayList<int[]> cctv = new ArrayList<>();
	static boolean[][] cctvDir = { { false, false, false, true }, { false, true, false, true },
			{ false, false, true, true }, { false, true, true, true }, { true, true, true, true } };

	static boolean[] tempDir = new boolean[4];
	static int[][] dir = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		temp = new int[n][m];

		tempDir = new boolean[4];

		min = Integer.MAX_VALUE;
//		max = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < m; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num != 0 && num != 6) {
					cctv.add(new int[] { i, j, num });

				}
				arr[i][j] = num;
			}
		}

		numbers = new int[cctv.size()];

		comb(0);

		System.out.println(min);

	}

	static void comb(int cnt) {

		if (cnt == cctv.size()) {
//			System.out.println(Arrays.toString(numbers));
			copyArr();

			show();
			check();
			return;
		}

		for (int i = 0; i < 4; i++) {
			numbers[cnt] = i;
			comb(cnt + 1);
		}
	}

	static void show() {
		for (int i = 0; i < cctv.size(); i++) {
			// cctv 방향 회전

			int cctvNum = cctv.get(i)[2];
			dirCopy(cctvNum - 1);
			rotate(numbers[i]);
			// 각 cctv 당 4가지 방향에 대한 값 밀기
			for (int j = 0; j < 4; j++) {
				int ci = cctv.get(i)[0];
				int cj = cctv.get(i)[1];
				// cctv가 해당 방향을 보고 있다면
				if (tempDir[j]) {
					int nI;
					int nJ;
					while (true) {

						nI = ci + dir[j][0];
						nJ = cj + dir[j][1];
						// 범위 체크 및 6이 아닌 값 까지
						if (nI >= 0 && nI < n && nJ >= 0 && nJ < m && temp[nI][nJ] != 6) {
							// 6이 아닌 아무값이나 채움
							if (temp[nI][nJ] == 0) {
								temp[nI][nJ] = 1;
							}

							ci = nI;
							cj = nJ;
						} else {
							break;
						}
					}
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

	static void check() {
		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (temp[i][j] == 0) {
					sum++;
				}
			}
		}

		if (min > sum) {
			min = sum;
//			print();
		}
//		min = Math.min(sum, min);
	}

	static void rotate(int cctvDir) {
		for (int i = 0; i < cctvDir; i++) {

			boolean temp = tempDir[0];
			tempDir[0] = tempDir[1];
			tempDir[1] = tempDir[2];
			tempDir[2] = tempDir[3];
			tempDir[3] = temp;
		}
	}

	static void dirCopy(int index) {
		for (int i = 0; i < 4; i++) {
			tempDir[i] = cctvDir[index][i];
		}
	}

	static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(temp[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("--------------------");

	}

}
