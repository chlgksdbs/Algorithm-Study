package day0207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1865 {
	static int[] distance;
	static int[][] edges;
	static final int INF = 987654321;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());

		for (int i = 0; i < tc; i++) {
			st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edges = new int[2 * m + w][3];

			for (int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int[] firstrow = { a, b, c };
				int[] secondrow = { b, a, c };
				edges[2 * j] = firstrow;
				edges[2 * j + 1] = secondrow;

			}

			for (int j = 2 * m; j < 2 * m + w; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int[] thisrow = { a, b, -c };
				edges[j] = thisrow;
			}

			distance = new int[n + 1];
			Arrays.fill(distance, INF);

			if (bellman_ford(n)) {
				System.out.println("YES");
			} else
				System.out.println("NO");
		}
	}

	static boolean bellman_ford(int round) {
		distance[1] = 0;
		for (int i = 0; i < round; i++) {
			for (int j = 0; j < edges.length; j++) {
				int thisnode = edges[j][0];
				int nextnode = edges[j][1];
				int weight = edges[j][2];

				if (distance[nextnode] > distance[thisnode] + weight) {
					distance[nextnode] = distance[thisnode] + weight;
				}
			}
		}

		for (int j = 0; j < edges.length; j++) {
			int thisnode = edges[j][0];
			int nextnode = edges[j][1];
			int weight = edges[j][2];

			if (distance[nextnode] > distance[thisnode] + weight) {
				return true;
			}
		}
		return false;
	}

}
