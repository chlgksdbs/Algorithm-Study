package baekjoon.algorithm.study;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17142 {
	
	static int N, M; // N : 연구소의 크기, M : 바이러스의 개수
	static int ans = 2500; // ans : 모든 빈 칸에 바이러스가 있게 되는 최소 시간
	static int virus_size;
	
	static char[][] map; // 연구소의 상태 (지도)
	static int[][] dist; // 바이러스가 퍼진 시간을 저장하는 배열 (방문 처리)
	static boolean[] selected; // 선택한 바이러스의 좌표 부분의 인덱스
	static List<Point> virus;
	
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	
	static class Virus {
		int x;
		int y;
		int time;
		
		public Virus(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	
	// 디버깅용 함수
	public static void print(int[][] graph) {
		for (int i = 0; i < graph.length; i++) {
			for(int j = 0; j < graph[0].length; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	// deepcopy 함수
	public static char[][] deepcopy(char[][] graph) {
		char[][] tmp = new char[graph.length][graph[0].length]; // 복사용 2차원 배열
		
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[0].length; j++) {
				tmp[i][j] = graph[i][j];
			}
		}
		
		return tmp;
	}
	
	public static int bfs(char[][] graph, Queue<Point> q) {
		int time = 0;
		Queue<Virus> virusq = new LinkedList<>();
		
		// queue가 빌 때까지 수행
		while (!q.isEmpty()) {
			Point p = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				// 범위를 벗어나거나 벽을 만나는 경우 무시
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || graph[nx][ny] == '-') continue;
				
				// 빈 칸인 경우에 방문 처리
				if (dist[nx][ny] == 0 && graph[nx][ny] == '#') {
					graph[nx][ny] = '-'; // 방문 처리
					dist[nx][ny] = dist[p.x][p.y] + 1;
					time = Math.max(time, dist[nx][ny]);
					q.add(new Point(nx, ny));
				}
				
				// 비활성화 바이러스에 대해서도 queue에 넣어두며 처리
				if (graph[nx][ny] == '*') {
					graph[nx][ny] = '-'; // 방문 처리
					virusq.add(new Virus(nx, ny, dist[p.x][p.y] + 1));
				}
			}
		}
		
		boolean flag = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 
				if (graph[i][j] == '#') flag = false;
			}
		}
		
		// 빈 칸이 없는 경우 함수 종료
		if (flag) return time;
		
		// 빈 칸이 남아 있는 경우 비활성화된 바이러스 활성화 하여 수행
		while (!virusq.isEmpty()) {
			Virus v = virusq.poll();
			dist[v.x][v.y] = v.time;
			
			for (int i = 0; i < 4; i++) {
				int nx = v.x + dx[i];
				int ny = v.y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || graph[nx][ny] == '-') continue;
				
				// 이번엔 빈칸과 비활성화된 바이러스에 대해서 동시에 수행
				if (dist[nx][ny] == 0 && (graph[nx][ny] == '#' || graph[nx][ny] == '*')) {
					graph[nx][ny] = '-'; // 방문 처리
					dist[nx][ny] = dist[v.x][v.y] + 1;
					time = Math.max(time, dist[nx][ny]);
					virusq.add(new Virus(nx, ny, dist[nx][ny]));
				}
			}
		}
		
		return time;
	}
	
	// 모든 바이러스의 좌표 중, M개 만큼 뽑아 bfs 수행
	public static void comb(int cnt, int start) {
		
		// M개의 경우의 수를 뽑은 경우, bfs를 수행하고 종료 (기저 조건)
		if (cnt == M) {
			char[][] copyMap = deepcopy(map);
			dist = new int[N][N];
			Queue<Point> queue = new LinkedList<Point>(); // bfs 함수에 넘겨줄 queue 생성
			
			for (int i = 0; i < virus_size; i++) {
				// comb 함수를 통해 뽑힌 M개의 바이러스를 활성화 (값을 3으로 변경)
				if (selected[i]) {
					copyMap[virus.get(i).x][virus.get(i).y] = '0'; // 활성 바이러스 
					queue.add(new Point(virus.get(i).x, virus.get(i).y));
				}
			}
			
			int tmp = bfs(copyMap, queue);
			
			boolean flag = true;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 
					if (copyMap[i][j] == '#') flag = false;
				}
			}
			
			if (flag) ans = Math.min(ans, tmp);
			
			return;
		}
		
		// 조합 구현 부분
		for (int i = start; i < virus_size; i++) {
			if (selected[i]) continue;
			
			selected[i] = true;
			comb(cnt + 1, i + 1);
			selected[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][N];
		virus = new ArrayList<Point>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().charAt(0);
				// 해당 좌표가 빈 칸인 경우, '#'으로 변경
				if (map[i][j] == '0') map[i][j] = '#';
				// 해당 좌표가 벽인 경우, '-'으로 변경
				if (map[i][j] == '1') map[i][j] = '-';
				// 해당 좌표가 바이러스인 경우, virus 리스트에 추가하고 '*'으로 변경
				if (map[i][j] == '2') {
					virus.add(new Point(i, j));
					map[i][j] = '*';
				}
			}
		}
		// 입력 종료
		
		virus_size = virus.size();
		selected = new boolean[virus_size];
		
		comb(0, 0);
		
		if (ans == 2500) {
			System.out.println(-1);
		}
		else {
			System.out.println(ans);
		}
	}
}
