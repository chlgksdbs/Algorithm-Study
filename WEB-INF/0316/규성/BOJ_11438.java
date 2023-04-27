

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<Integer>[] list;
	static int[] depth;
	static boolean[] visited;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		list = new ArrayList[N+1];
		depth = new int[N+1];
		visited = new boolean[N+1];
		dp = new int[N+1][30];
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		StringTokenizer st;
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			list[from].add(to);
			list[to].add(from);
		}
		
		dfs(1, 0);
		
//		for(int i = 0; i < dp.length; i++) {
//			for(int j = 0; j < dp[0].length; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("--------------------");
		
		calc();
		
//		for(int i = 0; i < dp.length; i++) {
//			for(int j = 0; j < dp[0].length; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("--------------------");
		
		int M = Integer.parseInt(br.readLine());
		
		
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			sb.append(lca(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
	
	private static int lca(int u, int v) {
		if(depth[u] < depth [v]) {
			int temp = u;
			u = v;
			v = temp;
		}
		
		int diff = depth[u] - depth[v];
		
		for(int i = 0; diff != 0; i++) {
			if((diff & 1) == 1) {
				u = dp[u][i];
			}
			diff >>= 1;
		}
		
		if(u == v) return u;
		
		for(int i = 29; i >= 0; i--) {
			if(dp[u][i] != dp[v][i]) {
				u = dp[u][i];
				v = dp[v][i];
			}
		}
		
		return dp[u][0];
		
		
	}


	private static void calc() {
		for(int j = 1; j < 30; j++) {
			for(int i = 1; i <= N; i++) {
				dp[i][j] = dp[dp[i][j-1]][j-1];
			}
		}
	}
	
	
	
	private static void dfs(int index, int depthNum) {
		visited[index] = true;
		depth[index] = depthNum;
		for(int i = 0; i < list[index].size(); i++) {
			if(!visited[list[index].get(i)]) {
				dp[list[index].get(i)][0] = index;
				dfs(list[index].get(i), depthNum+1);
			}
			
		}
		
	}

	
	
}
