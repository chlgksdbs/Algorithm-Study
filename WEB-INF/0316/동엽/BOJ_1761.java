import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point implements Comparable<Point> {
	int x;
	int length;

	public Point(int x, int length) {
		super();
		this.x = x;
		this.length = length;
	}

	@Override
	public int compareTo(Point o) {
		return this.length - o.length;
	}
}

public class Main {
	static int n;
	static ArrayList<Point>[] graph;
	static int[] dist;
	static int[][] parent;
	static int[] depth;
	static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		graph = new ArrayList[n + 1];
		for(int i = 1 ; i <= n ; i++) {
			graph[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			graph[a].add(new Point(b, dist));
			graph[b].add(new Point(a, dist));
		}

		dist = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		dijkstra();
		
		visited = new boolean[n+1];
		depth = new int[n+1];
		parent = new int[n+1][30];
		
		dfs(1,0);
		calculate();
		
		int m = Integer.parseInt(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 0 ;  i < m ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			bw.write(Integer.toString(dist[a]+dist[b]-2*dist[lca(a,b)]));
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}

	private static void calculate() {
		for(int j = 1 ; j < 30 ; j++) {
			for(int i = 1;  i <= n ; i++) {
				parent[i][j] = parent[parent[i][j-1]][j-1];
			}
		}
	}

	private static void dfs(int node, int d) {
		visited[node] = true;
		depth[node] = d;
		for(Point p : graph[node]) {
			if(!visited[p.x]) {
				parent[p.x][0] = node;
				dfs(p.x,d+1);
			}
		}
	}

	private static void dijkstra() {
		PriorityQueue<Point> q = new PriorityQueue<>();
		dist[1] = 0;
		q.add(new Point(1,0));
		while(!q.isEmpty()) {
			Point thisiter = q.poll();
			
			if(thisiter.length > dist[thisiter.x]) continue;
			
			for(Point nextiter : graph[thisiter.x]) {
				int newlength = nextiter.length + thisiter.length;
				if(newlength < dist[nextiter.x]) {
					dist[nextiter.x] = newlength;
					q.add(new Point(nextiter.x,newlength));
				}
			}
		}
	}
	private static int lca(int u, int v){
		if(depth[u] < depth[v]){
			u = u ^ v;
			v = u ^ v;
			u = u ^ v;
	  }
		int diff = depth[u] - depth[v];
		for(int i=0; diff > 0; i++){
			if((diff & 1) == 1) u = parent[u][i];
			diff >>= 1;
		}
		if(u == v) return u;

		for(int i=29; i>=0; i--){
			if(parent[u][i] != parent[v][i]){
				u = parent[u][i];
				v = parent[v][i];
			}
		}
		return parent[u][0];
	}

}