package acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main3187 {

	public static int[] di = { -1, 0, 1, 0 };
	public static int[] dj = { 0, 1, 0, -1 };

	public static int R;
	public static int C;

	public static int sheepCount;
	public static int wolfCount;

	public static char[][] arr;
    static boolean[][] visit;
	
	static int sheep;
	static int wolf;
	
	static Queue<int[]> queue = new LinkedList<>();
	// static Queue<Point> queue;

	public static void bfs(int indexI, int indexJ) {

		sheep = 0;
		wolf = 0;

		if (arr[indexI][indexJ] == 'v') {
            wolf++;
        } else {
            sheep++;
        }

		visit[indexI][indexJ] = true;
		int[] index = {indexI, indexJ};
		queue.offer(index);

		while (!queue.isEmpty()) {
			// System.out.println(sheep + " " + wolf);

			int[] p = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nextI = p[0] + di[i];
				int nextJ = p[1] + dj[i];

				if(nextI < 0 || nextJ < 0 || nextI >= R || nextJ >= C)
					continue;

				// # 이 아니라면
				if ( !visit[nextI][nextJ] &&  arr[nextI][nextJ] != '#') {
					int[] nIndex = {nextI, nextJ};
					queue.offer(nIndex);

					visit[nextI][nextJ] = true;

					if (arr[nextI][nextJ] == 'k') {
						sheep++;
						// arr[curI][curJ] = shap;
					} else if (arr[nextI][nextJ] == 'v') {
						wolf++;
						// arr[curI][curJ] = shap;
					}
				}
			}
		}
		if (sheep > wolf) {
			sheepCount += sheep;
		} else {
			wolfCount += wolf;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// String line = 

		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new char[R][C];
        visit = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String line = br.readLine();

			for (int j = 0; j < C; j++) {
				arr[i][j] = line.charAt(j);
			}
		}
		
		queue = new LinkedList<>();

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if ((arr[i][j] == 'k' || arr[i][j] == 'v') && !visit[i][j]) {
					bfs(i, j);
				}
			}
		}

		System.out.println(sheepCount + " " + wolfCount);

	}

	static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
