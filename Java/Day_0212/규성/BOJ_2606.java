package acmicpc0212;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2606 {
	static int n, m;
	static boolean[] visited;
	static int sum = 0;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		visited = new boolean[n+1];
		
		for(int i = 0; i<= n; i++) {
			list.add(new ArrayList<Integer>());
		}
		
		m = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list.get(a).add(new Integer(b));
			list.get(b).add(new Integer(a));
			
		}
		
		dfs(1);
		
		System.out.println(sum);
		
		
		
	}
	
	static void dfs(int start) {
		visited[start] = true;
		
		for(int i = 0; i < list.get(start).size();i++) {
			int nI = list.get(start).get(i);
			if(!visited[nI]) {
				dfs(nI);
				sum ++;
			}
		}

	}

}
