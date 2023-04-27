import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<Edge> graph;
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		graph = new ArrayList<Edge>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph.add(new Edge(v, w, cost));
		}

		bellmanford(n, m, 1);
	}

	public static void bellmanford(int n, int m, int start) {
		long[] dist = new long[n + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				Edge edge = graph.get(j);
				if (dist[edge.v] != INF && dist[edge.w] > dist[edge.v] + edge.cost) {
					dist[edge.w] = dist[edge.v] + edge.cost;
				}
			}
		}

		for (int i = 0; i < m; i++) {
			Edge edge = graph.get(i); // 현재 간선

			if (dist[edge.v] != INF && dist[edge.w] > dist[edge.v] + edge.cost) {
				System.out.println(-1);
				return;
			}
		}

		// 출력
		for (int i = 2; i < dist.length; i++) {
			if (dist[i] == INF)
				System.out.println(-1);
			else
				System.out.println(dist[i]);
		}

		return;
	}

}

class Edge {
	int v;
	int w;
	int cost;

	public Edge(int v, int w, int cost) {
		this.v = v;
		this.w = w;
		this.cost = cost;
	}
}