package day0119;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_14716 {
	static int M;
	static int N;
	static int[] dx = { -1, 1, 0, 0, -1, 1, -1, 1 };
	static int[] dy = { 0, 0, -1, 1, -1, -1, 1, 1 };
	static int cnt;

	public static int bfs(int x, int y, int[][] arr, int[][] visit) {
		Queue<Point> q = new LinkedList<Point>();

		if (arr[x][y] == 1) {
			if(visit[x][y]==0) {
				cnt++;
			}
			visit[x][y] = 1;
			q.add(new Point(x, y));
			while (!q.isEmpty()) {
				Point p = q.poll();
				for (int i = 0; i < 8; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];

					if (nx < 0 || ny < 0 || nx >= M || ny >= N || visit[nx][ny] == 1)
						continue;

					if (arr[nx][ny] == 1) {
						visit[nx][ny] = 1;
						q.add(new Point(nx, ny));
					}
				}
			}
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		int[][] graph = new int[M][N];
		int[][] visited = new int[M][N];

		for (int i = 0; i < M; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st2.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				bfs(i, j, graph, visited);
			}
		}
		System.out.println(cnt);
	}
}
