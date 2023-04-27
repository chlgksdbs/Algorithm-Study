
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] graph;
	static int[] mintree;
	static int[] maxtree;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		graph = new int[n];
		mintree = new int[4 * n];
		maxtree = new int[4 * n];

		for (int i = 0; i < n; i++) {
			graph[i] = Integer.parseInt(br.readLine());
		}
		initmin(0, n - 1, 1);
		initmax(0, n - 1, 1);
		int a, b = 0;
		for (int j = 0; j < m; j++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			System.out.print(findmin(0, n - 1, 1, a - 1, b - 1)+" ");
			System.out.println(findmax(0, n - 1, 1, a - 1, b - 1));
		}

	}

	static int initmin(int start, int end, int node) {
		if (start == end)
			return mintree[node] = graph[start];
		int mid = (start + end) / 2;

		return mintree[node] = Math.min(initmin(start, mid, 2 * node), initmin(mid + 1, end, 2 * node + 1));
	}

	static int initmax(int start, int end, int node) {
		if (start == end)
			return maxtree[node] = graph[start];
		int mid = (start + end) / 2;

		return maxtree[node] = Math.max(initmax(start, mid, 2 * node), initmax(mid + 1, end, 2 * node + 1));
	}

	static int findmin(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return 1987654321;
		}

		if (left <= start && end <= right) {
			return mintree[node];
		}

		int mid = (start + end) / 2;
		return Math.min(findmin(start, mid, 2 * node, left, right), findmin(mid + 1, end, 2 * node + 1, left, right));
	}

	static int findmax(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return -1987654321;
		}

		if (left <= start && end <= right) {
			return maxtree[node];
		}

		int mid = (start + end) / 2;
		return Math.max(findmax(start, mid, 2 * node, left, right), findmax(mid + 1, end, 2 * node + 1, left, right));
	}

}
