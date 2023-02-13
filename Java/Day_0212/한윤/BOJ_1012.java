package baekjoon.algorithm.study;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1012 {
	
	static int m; // 가로 길이
	static int n; // 세로 길이
	
	static int[][] graph;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	
	
	public static int bfs(int x, int y) {
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(x, y));
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			Point p = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				// 범위를 벗어난 경우 수행하지 않음
				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				
				// 배추가 심어져있지 않거나 방문 처리 된 경우도 수행하지 않음
				if (graph[nx][ny] == 0 || visited[nx][ny]) continue;
				
				visited[nx][ny] = true; // 방문처리
				q.offer(new Point(nx, ny)); // queue에 원소 삽입
			}
		}
		
		return 1;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			
			m = sc.nextInt(); // 가로 길이
			n = sc.nextInt(); // 세로 길이
			
			graph = new int[n][m];
			visited = new boolean[n][m];
			
			int res = 0;
			
			int k = sc.nextInt(); // 배추가 심어진 위치의 개수
			
			for (int i = 0; i < k; i++) {
				int tx = sc.nextInt();
				int ty = sc.nextInt();
				
				graph[ty][tx] = 1;
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (graph[i][j] == 1 && !visited[i][j]) {
						res += bfs(i, j);
					}
				}
			}
			
			System.out.println(res);
			
		}
	}
}
