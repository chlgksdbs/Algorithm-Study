import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Pair {
	int x;
	int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int n, m;
	static int[][] graph, tmpgraph;
	static ArrayList<Pair> viruses, empty;
	static Pair[] output = new Pair[3];
	static int answer = Integer.MIN_VALUE;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new int[n][m];
		tmpgraph = new int[n][m];
		viruses = new ArrayList<>();
		empty = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (graph[i][j] == 2)
					viruses.add(new Pair(i, j));
				if (graph[i][j] == 0)
					empty.add(new Pair(i, j));
				tmpgraph[i][j] = graph[i][j];
			}
		}

		comb(0, 0);
		System.out.println(answer);
	}

	static void comb(int depth, int start) {
		if (depth == 3) {
			for (Pair thispair : output) {
				graph[thispair.x][thispair.y] = 1;
			}
			bfs();
			int sum = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (graph[i][j] == 0)
						sum++;
				}
			}
			answer = Math.max(answer, sum);
			recover();
			return;
		}
		for (int i = start; i < empty.size(); i++) {
			output[depth] = empty.get(i);
			comb(depth + 1, i + 1);
		}
	}

	private static void recover() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				graph[i][j] = tmpgraph[i][j];
			}
		}
	}

	private static void bfs() {
		ArrayDeque<Pair> q = new ArrayDeque<>();
		for (Pair thispair : viruses) {
			q.add(thispair);
		}
		while (!q.isEmpty()) {
			Pair thisiter = q.poll();
			for (int i = 0; i < 4; i++) {
				int newx = thisiter.x + dx[i];
				int newy = thisiter.y + dy[i];
				if (0 <= newx && newx < n && 0 <= newy && newy < m && graph[newx][newy] == 0) {
					graph[newx][newy] = 2;
					q.add(new Pair(newx,newy));
				}
			}
		}

	}

}