import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int pair;
	static int cnt;
	
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	
	public static void dfs(int start) {
		visited[start] = true;
		cnt++;
		for(int i=0; i<graph[start].size(); i++) {
			if(visited[graph[start].get(i)]==false) {
				dfs(graph[start].get(i));
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 컴퓨터의 수
		pair = Integer.parseInt(br.readLine());	// 연결 컴퓨터 쌍의 수
		
		graph = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		visited = new boolean[N+1];
		
		for(int i=0; i<pair; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			graph[x].add(y);
			graph[y].add(x);
		}
		dfs(1);
		
		System.out.println(cnt-1);
	}
}
