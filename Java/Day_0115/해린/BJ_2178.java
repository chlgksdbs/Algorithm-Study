package day0121;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2178 {
	
	static int[][] graph;
	static int[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int N;
	static int M;
	static int nx;
	static int ny;
	
	public static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(x, y));
		visited[x][y] = 1;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i=0; i<4; i++) {
				nx = p.x + dx[i];
				ny = p.y + dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=M || graph[nx][ny]==0 || visited[nx][ny]==1) {
					continue;
				}
				if(graph[nx][ny]==1 && visited[nx][ny]==0) {
					q.add(new Point(nx, ny));
					visited[nx][ny] = visited[p.x][p.y] + 1;
				}
			}
		}
		System.out.println(visited[N-1][M-1]);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		visited = new int[N][M];
		for(int i=0; i<N; i++) {
			String temp = bf.readLine();
			for (int j=0; j<temp.length(); j++) {
				graph[i][j] = temp.charAt(j) - '0';
			}
		}
		bfs(0, 0);
	}
}
