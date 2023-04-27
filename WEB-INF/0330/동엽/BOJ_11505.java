import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n, m, k;
	static long[] graph;
	static final long MOD = 1000000007;

	static long[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		graph = new long[n + 1];
		tree = new long[4 * n + 10];
		for (int i = 1; i <= n; i++) {
			graph[i] = Long.parseLong(br.readLine());
		}
		init(1, n, 1);

		for (int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			if (a == 1) {
				if (graph[b] == 0) {
					graph[b] = c;
					init(1, n, 1);
				} else {
					graph[b] = c;
					update(1, n, 1, b, c);
				}
			} else {
				long answer = query(1, n, 1, b, c);
				bw.write(Long.toString(answer));
				bw.write('\n');
			}
		}
		bw.flush();
		bw.close();
	}

	private static long query(int start, int end, int node, int left, long right) {
		if (end < left || start > right)
			return 1;

		if (left <= start && end <= right)
			return tree[node] % MOD;

		int mid = (start + end) / 2;
		long cl = query(start, mid, 2 * node, left, right) % MOD;
		long cr = query(mid + 1, end, 2 * node + 1, left, right) % MOD;
		return (cl*cr) % MOD;
	}

	private static long update(int start, int end, int node, int index, long change) {
		if (index < start || index > end)
			return tree[node];

		if(start == end) {
			tree[node] = change;
			return tree[node];
		}
		int mid = (start+end)/2;
		long lc = update(start,mid,2*node,index,change) % MOD;
		long rc = update(mid+1,end,2*node+1,index,change) % MOD;
		return tree[node] = (lc*rc) % MOD;
		
		
	}

	private static long init(int start, int end, int node) {
		if (start == end)
			return tree[node] = graph[start] % MOD;

		int mid = (start + end) / 2;
		long cl = init(start, mid, 2 * node) % MOD;
		long cr = init(mid+1,end,2*node+1) % MOD;
		tree[node] = (cl*cr) % MOD;
		return tree[node];
	}

}