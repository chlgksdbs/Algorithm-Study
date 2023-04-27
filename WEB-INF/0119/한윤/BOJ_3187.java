import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3187 {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int r; // 세로
	static int c; // 가로
	static boolean[][] visited;
	static char[][] graph;
	static int vCount; // 늑대의 수
	static int kCount; // 양의 수
	
	public static int bfs(int x, int y) {
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(x, y));
		visited[x][y] = true; // 방문처리
		
		vCount = 0; // 늑대의 마릿수
		kCount = 0; // 양의 마릿수
		
		if (graph[x][y] == 'v') vCount++;
		else if (graph[x][y] == 'k') kCount++;
		
		while (!q.isEmpty()) {
			Point p = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				// 범위를 벗어나는 경우 패스
				if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
				
				// 벽을 마주치거나 방문 처리가 된 경우 패스
				if (graph[nx][ny] == '#' || visited[nx][ny]) continue;
				
				if (graph[nx][ny] == 'v') {
					vCount++;
					q.add(new Point(nx, ny));
				}
				else if (graph[nx][ny] == 'k') {
					kCount++;
					q.add(new Point(nx, ny));
				}
				else {
					q.add(new Point(nx, ny));
				}
				visited[nx][ny] = true;
			}
		}
		
		if (kCount > vCount) return 1; // 양이 늑대보다 많은 경우
		else return -1; // 늑대가 양보다 많거나 같은 경우
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken()); // 세로
		c = Integer.parseInt(st.nextToken()); // 가로
		
		int vSum = 0; // 살아남게 되는 늑대의 수
		int kSum = 0; // 살아남게 되는 양이 수
		
		graph = new char[r][c];
		visited = new boolean[r][c];
		
		for (int i = 0; i < r; i++) {
			graph[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				if (graph[i][j] != '#' && !visited[i][j]) {
					int flags = bfs(i, j);
					if (flags == -1) {
						vSum += vCount;
					}
					else {
						kSum += kCount;
					}
				}
			}
		}
		
		System.out.println(kSum + " " + vSum);
	}
}