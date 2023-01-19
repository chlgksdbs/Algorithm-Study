package day0115;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_16173 {
	static int[] dx = { 1, 0 };
	static int[] dy = { 0, 1 };
	static int N;
	static int nx;
	static int ny;
	static int flag;
	
	public static void bfs(int x, int y, int[][] arr) {
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(x, y));
		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int d = 0; d < 2; d++) {
				nx = p.x + dx[d] * arr[p.x][p.y];
				ny = p.y + dy[d] * arr[p.x][p.y];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || arr[nx][ny]==0)
					continue;
				if(nx==N-1 && ny==N-1) {
					flag = 1; 
					break;
				}
				q.add(new Point(nx, ny));
			}
		}
		if(flag == 1) {
			System.out.println("HaruHaru");
		}else {
			System.out.println("Hing");			
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		int[][] graph = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				graph[i][j] = sc.nextInt();
			}
		}
		bfs(0, 0, graph);
	}
}
