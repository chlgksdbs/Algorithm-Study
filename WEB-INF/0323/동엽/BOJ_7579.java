import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] mylist = new int[n+1];
		int[] c = new int[n+1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			mylist[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			c[i] = Integer.parseInt(st.nextToken());
		}

		int costsum = 0;
		for (int e : c) {
			costsum += e;
		}

		long[][] graph = new long[n + 1][costsum + 1];

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= costsum; j++) {
				if (i == 0 || j == 0)
					continue;

				if (j < c[i]) {
					graph[i][j] = graph[i - 1][j];
				} else {
					graph[i][j] = Math.max(graph[i - 1][j], graph[i - 1][j - c[i]] + mylist[i]);
				}
			}
		}

		for (int j = 1; j <= costsum; j++) {
			if(graph[n][j] >= m) {
				System.out.println(j);
				System.exit(0);
			}
		}

	}

}