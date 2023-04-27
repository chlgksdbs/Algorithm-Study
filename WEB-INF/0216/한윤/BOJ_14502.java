package baekjoon.algorithm.study;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_14502 {
	
	static int N; // 지도의 세로 크기
	static int M; // 지도의 가로 크기
	static int safety = 0; // 안전 영역 크기의 최댓값
	
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] graph;
	static boolean[] selected; // comb에서 뽑은 경우의 수의 방문 처리 배열
	
	static List<Point> blank = new ArrayList<>(); // 빈칸의 위치를 담는 리스트
	
	public static void bfs(int[][] graph, int x, int y) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(x, y));
		
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				// 그래프의 범위를 벗어나거나 벽을 만난 경우 무시
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || graph[nx][ny] == 1) continue;
				
				// 빈 칸인 경우 바이러스로 물들이고 queue에 삽입
				if (graph[nx][ny] == 0) {
					graph[nx][ny] = 2;
					queue.add(new Point(nx, ny));
				}
			}
		}
	}
	
	// 빈칸에 벽을 세우는 경우의 수를 뽑는 함수
	public static void comb(int cnt, int start) {
		
		// 3가지의 벽을 세운 경우 bfs를 수행하고 종료
		if (cnt == 3) {
			int[][] subGraph = new int[N][M]; // graph를 copy하여 사용할 배열
			int tmp = 0; // bfs 함수를 수행한 후, 안전 영역의 크기
			
			// deep copy (깊은 복사)
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					subGraph[i][j] = graph[i][j];
				}
			}
			
			for (int i = 0; i < blank.size(); i++) {
				// 방문 처리가 된 경우 수행
				if (selected[i]) {
					subGraph[blank.get(i).x][blank.get(i).y] = 1; // 벽을 세움
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					// 해당 부분이 바이러스 인 경우 bfs 수행
					if (subGraph[i][j] == 2) {
						bfs(subGraph, i, j);
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (subGraph[i][j] == 0) tmp++;
				}
			}
			
			safety = Math.max(safety, tmp);
			
			return;
		}
		
		for (int i = start; i < blank.size(); i++) {
			// 방문 처리가 된 경우 무시
			if (selected[i]) continue;
			
			selected[i] = true;
			comb(cnt + 1, start + 1);
			selected[i] = false;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		graph = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				graph[i][j] = sc.nextInt();
				// 현재 인덱스의 그래프 값이 0일 때, blank 리스트에 입력
				if (graph[i][j] == 0) {
					blank.add(new Point(i, j));
				}
			}
		}
		
		selected = new boolean[blank.size()];
		
		comb(0, 0);
		
		System.out.println(safety);
	}
}
