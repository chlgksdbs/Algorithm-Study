import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;	// 세로크기
	static int M;	// 가로크기
	static int minCnt = Integer.MIN_VALUE;	// 안전한 영역 크기 최대값
	
	static ArrayList<Point> empty;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
		
	public static void bfs(int[][] copyArr, Queue<Point> virus) {
		boolean[][] visited = new boolean[N][M];
		Queue<Point> q = new LinkedList<>();
		while(!virus.isEmpty()) {
//			System.out.println(virus);
			Point p4 = new Point();
			p4 = virus.poll();
			q.add(p4);
			while(!q.isEmpty()) {
				Point p3 = new Point();
				p3 = q.poll();
				for(int i=0; i<4; i++) {
					int nx = p3.x + dx[i];
					int ny = p3.y + dy[i];
					
					if(nx<0 || ny<0 || nx>=N || ny>=M || copyArr[nx][ny]==1) {
						continue;
					}
					if(copyArr[nx][ny] == 0) {
						q.add(new Point(nx, ny));
						copyArr[nx][ny] = 2;			// 바이러스 퍼진 칸..으로 만들어
					}
				}
			}
			
		}
		
		
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(copyArr[i][j] == 0) {
					cnt++;
				}
			}
		}
//		minCnt.add(cnt);
		minCnt = Math.max(minCnt, cnt);		// 둘 중 최솟값을 지님
	}
	
	public static void combination(int[][] arr, boolean[] selected, Point[] tmpWall, Queue<Point> virus, int idx, int start) {
		if(idx == 3) {
			int[][] arrCopy = new int[N][M];
			Queue<Point> virusCopy = new LinkedList<>();
			
			int tmpIdx = 0;
			for(int i=0; i<empty.size(); i++) {
				if(selected[i]) {
					tmpWall[tmpIdx++] = empty.get(i);
				}
			}
			
			for(int a=0; a<N; a++) {			// 깊은 배열 복사
				for(int b=0; b<M; b++) {
					arrCopy[a][b] = arr[a][b];
					if(arr[a][b] == 2) {
						virusCopy.add(new Point(a, b));
					}
				}
			}
			
			for(int i=0; i<3; i++) {
//				arrCopy= arr.clone();
				
				Point p1 = new Point();
				p1 = tmpWall[i];
				arrCopy[p1.x][p1.y] = 1;					// 조합으로 뽑은 3개의 빈 칸 좌표에 벽을 넣어줘보자
			}
			bfs(arrCopy, virusCopy);

			return;
		}
		for(int i=start; i<empty.size(); i++) {
			if(!selected[i]) {
				selected[i] = true;
				combination(arr, selected, tmpWall, virus, idx+1, i+1);
				selected[i] = false;
			}			
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		Point[] tmpWall = new Point[3];		// (x, y)형태로 벽을 세울 빈칸 3개 담을 수 있음
		
		
		empty = new ArrayList<>();
		Queue<Point> virus = new LinkedList<>();

		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 0) empty.add(new Point(i, j));		// 빈칸 좌표 -> ok
				if(arr[i][j] == 2) virus.add(new Point(i, j));		// 바이러스 좌표 -> ok
			}
		}
		boolean[] selected = new boolean[empty.size()];
		
		combination(arr, selected, tmpWall, virus, 0, 0);
		
		System.out.println(minCnt);
	}
}
