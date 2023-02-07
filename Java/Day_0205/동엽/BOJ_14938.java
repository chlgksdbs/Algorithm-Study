package day0208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14938 {
	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		int[] items = new int[n + 1];
		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}

		int[][] graph = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			Arrays.fill(graph[i], INF);
		}

		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			graph[start][end] = weight;
			graph[end][start] = weight;
		}

		for (int i = 1; i <= n; i++) {
			graph[i][i] = 0;
		}

		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					int temp = graph[i][k] + graph[k][j];
					graph[i][j] = graph[i][j] < temp ? graph[i][j] : temp;
				}
			}
		}
		int answer = 0;
		for (int i = 1; i <= n; i++) {
			int item = 0;
			for (int j = 1; j <= n; j++) {
				if(graph[i][j] <= m)
					item += items[j];
			}
			
			answer = answer < item ? item : answer;
		}
		
		System.out.println(answer);

	}

}
