import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int[][] graph, tmp;
	static Point[] output;
	static ArrayList<Point> viruses = new ArrayList<>();
	static int length, n, m;
	static int answer = Integer.MAX_VALUE;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new int[n][n];
		tmp = new int[n][n];
		output = new Point[m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				tmp[i][j] = graph[i][j];
				if (graph[i][j] == 2)
					viruses.add(new Point(i, j));
			}
		}
		length = viruses.size();
		comb(0, 0);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	static void comb(int depth, int start) {
		if (depth == m) {
			bfs(output);
			return;
		}

		for (int i = start; i < length; i++) {
			output[depth] = viruses.get(i);
			comb(depth + 1, i + 1);
		}
	}

	static void bfs(Point[] out) {
		ArrayDeque<Point> q = new ArrayDeque<>();
		for (Point p : out) {
			graph[p.x][p.y] = -1;
			q.add(p);
		}
		int time = 0;
		int qsize = q.size();
		boolean prevNotDone = false;
		while (true) {
			boolean isNotDone = false;
			for (int cnt = 0; cnt < qsize; cnt++) {
				Point thispoint = q.poll();
				for (int i = 0; i < 4; i++) {
					int newx = thispoint.x + dx[i];
					int newy = thispoint.y + dy[i];
					if (0 <= newx && newx < n && 0 <= newy && newy < n) {
						if (graph[newx][newy] == 0) {
							graph[newx][newy] = -1;
							isNotDone = true;
							prevNotDone = false;
							q.add(new Point(newx, newy));
						} else if (graph[newx][newy] == 2) {
							graph[newx][newy] = -1;
							q.add(new Point(newx, newy));
						}
					}
				}
			}
			if(!isNotDone) {
				if(!prevNotDone) prevNotDone = true;
				else {
					time--;
					break;
				}
			}
			if (q.isEmpty())
				break;
			qsize = q.size();
			time++;
		}
		if (check()) {
			answer = Math.min(answer, time);
		}
		recover();
	}

	private static boolean check() {
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ;j < n ; j++) {
				if(graph[i][j] == 0) return false;
			}
		}
		return true;
	}

	private static void recover() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				graph[i][j] = tmp[i][j];
			}
		}
	}

}