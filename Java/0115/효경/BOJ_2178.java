package day0115;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2178 {
	static int n;
	static int m;
	static int[][] arr;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][m];

		for (int i = 0; i < n; i++) {
			char[] chArr = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				arr[i][j] = chArr[j] - '0';
			}
		}

		bfs(0, 0);
		// System.out.println(Arrays.deepToString(arr));
		System.out.println(arr[n - 1][m - 1]);

	}

	static int[] dx = { 0, 1, -1, 0 };
	static int[] dy = { 1, 0, 0, -1 };

	private static void bfs(int i, int j) {

		Queue<int[]> queue = new LinkedList<>();

		queue.offer(new int[] { i, j });

		while (!queue.isEmpty()) {
			int[] tmp = queue.poll();

			for (int k = 0; k < 4; k++) {
				int x = tmp[0] + dx[k];
				int y = tmp[1] + dy[k];

				if (x >= 0 && x < n && y >= 0 && y < m && arr[x][y] == 1) {
					arr[x][y] = arr[tmp[0]][tmp[1]] + 1;
					queue.offer(new int[] { x, y });
				}
			}
		}

	}
}