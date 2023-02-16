import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int T;
	static int N;
	static int M;
	static int K;

	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static int bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		visited[x][y] = true;
		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx < 0 || ny < 0 || nx >= M || ny >= N || visited[nx][ny] == true)
					continue;
				if (arr[nx][ny] == 1 && visited[nx][ny] == false) {
					visited[nx][ny] = true;
					q.add(new Point(nx, ny));
				}
			}
		}
		return 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine()); // 테케
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 밭 가로 = 열 10
			M = Integer.parseInt(st.nextToken()); // 밭 세로 = 행 8
			K = Integer.parseInt(st.nextToken()); // 배추 위치

			arr = new int[M][N]; // 8 * 10
			visited = new boolean[M][N];

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				arr[y][x] = 1;
			}
			int cnt = 0;
			for(int i=0; i<M; i++) {		// 8
				for(int j=0; j<N; j++) { 	// 10
					if(arr[i][j] == 1 && visited[i][j] == false) {
						cnt += bfs(i, j);
					}
				}
			}
			System.out.println(cnt);
		}
	}
}
