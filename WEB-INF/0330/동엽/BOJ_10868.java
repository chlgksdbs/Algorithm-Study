import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] graph;
	static int[] tree;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		graph = new int[n];
		tree = new int[4 * n];

		for (int i = 0; i < n; i++) {
			graph[i] = Integer.parseInt(br.readLine());
		}
		init(0,n-1,1);
		int a,b = 0;
		for(int j = 0 ; j< m ; j ++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			System.out.println(find(0,n-1,1,a-1,b-1));
		}
		
		

	}

	static int init(int start, int end, int node) {
		if (start == end)
			return tree[node] = graph[start];
		int mid = (start + end) / 2;

		return tree[node] = Math.min(init(start, mid, 2 * node), init(mid + 1, end, 2 * node + 1));
	}
	
	static int find(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return 1987654321;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		return Math.min(find(start,mid,2*node,left,right),find(mid+1,end,2*node+1,left,right));
	}

}
