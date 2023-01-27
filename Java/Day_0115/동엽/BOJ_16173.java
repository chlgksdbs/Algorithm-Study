package day_0115;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_16173 {

	static int[][] graph;
	static boolean[][] visited;
	static int[] dx = {0,1};
	static int[] dy = {1,0};
	static String result="Hing";
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer stk;
		graph = new int[n][n];
		visited = new boolean[n][n];
		for(int i=0;i<n;i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				graph[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		dfs();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(result+"\n");
		bw.flush();
		bw.close();
		
	}
	
	public static void dfs() {
		Stack<int[]> mystack = new Stack<>();
		int[] mypos = {0,0};
		mystack.push(mypos);
		
		while(!mystack.isEmpty()) {
			mypos = mystack.pop();
			
			if(mypos[0] == n-1 && mypos[1] == n-1) {
				result = "HaruHaru";
				return;
			}
			visited[mypos[0]][mypos[1]] = true;
			
			for(int i=0;i<2;i++) {
				int newx = mypos[0]+dx[i]*graph[mypos[0]][mypos[1]];
				int newy = mypos[1]+dy[i]*graph[mypos[0]][mypos[1]];
				if(newy<n&&newx<n&&0<=newx&&0<=newy&&!visited[newx][newy]) {
					mystack.push(new int[] {newx,newy});
				}
				
			}
			
		}
	
	}

}