package day0120;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14716 {

	static int[][] graph;
	static int[] dx = { 0, 1, 0, -1, 1, -1, 1, -1 };
	static int[] dy = { 1, 0, -1, 0, 1, -1, -1, 1 };
	static int M;
	static int N;

	public static void main(String[] args) throws IOException {
		int count = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		graph = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (graph[i][j] == 1) {
					count++;
					dfs(i, j);
				}
			}
		}
		
		System.out.println(count);

	}

	public static void dfs(int x, int y) {
		graph[x][y] = 0;
		for (int i = 0; i < 8; i++) {
			int newx = x+dx[i];
			int newy = y+dy[i];
			if (0 <= newx && newx < M && 0 <= newy && newy < N) {
				if(graph[newx][newy] == 1) {
					dfs(newx,newy);
				}
			}

		}
	}
}
