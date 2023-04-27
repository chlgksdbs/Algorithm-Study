import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static ArrayList<ArrayList<Integer>> mygraph;
	static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int e = Integer.parseInt(br.readLine());
		
		visited = new boolean[n+1];
		mygraph = new ArrayList<ArrayList<Integer>>();
		for(int i = 0 ; i < n+1 ; i ++) {
			mygraph.add(new ArrayList<Integer>());
		}
		for(int i = 0; i < e ; i++) {
			String[] mystr = br.readLine().split(" ");
			int start = Integer.parseInt(mystr[0]);
			int end = Integer.parseInt(mystr[1]);
			
			mygraph.get(start).add(end);
			mygraph.get(end).add(start);
		}
		
		dfs(1);
		int count = 0;
		for(int i = 1; i <= n ; i++) {
			if(visited[i] == true) count++;
		}
		
		System.out.println(count-1);
	}
	
	static void dfs(int index) {
		visited[index] = true;
		for(int i = 0 ; i < mygraph.get(index).size();i++) {
			if(!visited[mygraph.get(index).get(i)]) {
				dfs(mygraph.get(index).get(i));
			}
		}
	}

}