import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static int[] depth;
	static int[][] parent;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		graph = new ArrayList[n+1];
		visited = new boolean[n+1];
		depth = new int[n+1];
		parent = new int[n+1][30];
		
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		StringTokenizer st;
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		dfs(1,0);
		calculate();
		
		int m = Integer.parseInt(br.readLine());
		
		for(int i = 0;  i < m ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			System.out.println(lca(a,b));
		}
	}
	
	static void dfs(int v, int d){
		visited[v] = true;
		depth[v] = d; // 해당 노드의 높이를 저장하는 배열을 활용한다.
		for(int node : graph[v]){
			if(!visited[node]){
				parent[node][0] = v; // 그래프의 자식노드들의 첫번째 조상 노드를 세팅 -> calculate의 기저case
				dfs(node, d+1);
			}	
		}
	}
	
	static void calculate(){
		for(int j = 1 ; j < 30 ; j++){ // 2^30 = 10억, 이 이상 조상 노드가 존재하는게 말이 안됨
			for(int i = 1 ; i <= n ; i++){
				parent[i][j] = parent[parent[i][j-1]][j-1];	
			} 
		}
	}
	
	static int lca(int u, int v){
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