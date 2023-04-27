import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static int[][] graph;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			graph = new int[m][n];

			for (int j = 0; j < k; j++) {
				st = new StringTokenizer(br.readLine());
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());

				graph[row][col] = 1;
			}

			int mysum = 0;
			for (int myrow = 0; myrow < m; myrow++) {
				for (int mycol = 0; mycol < n; mycol++) {
					if (graph[myrow][mycol] == 1) {
						dfs(myrow, mycol);
						mysum++;
					}
				}
			}

			System.out.println(mysum);
		}
	}

	static void dfs(int x, int y) {
		graph[x][y] = 0;
		for (int i = 0; i < 4; i++) {
			int newx = x + dx[i];
			int newy = y + dy[i];

			if (0 <= newx && newx < graph.length && 0 <= newy && newy < graph[0].length && graph[newx][newy] == 1) {
				dfs(newx,newy);
			}
		}

	}
}