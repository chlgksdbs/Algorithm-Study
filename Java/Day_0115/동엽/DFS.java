package day_0115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DFS {
	
	static int[][] graph;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int n;
	static int m;
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer stk = new StringTokenizer(br.readLine());
	    n = Integer.parseInt(stk.nextToken());
	    m = Integer.parseInt(stk.nextToken());
	    String[] mystring;
	    graph = new int[n][m];
	    
	    for(int i=0;i<n;i++) {
	    	mystring = br.readLine().split("");
	    	for(int j=0;j<m;j++) {
	    		graph[i][j] = Integer.parseInt(mystring[j]);
	    	}
	    }
	    
	    int result = 0;
	    
	    for(int i=0;i<n;i++) {
	    	for(int j=0;j<m;j++) {
	    		if(graph[i][j] == 0) {
	    			dfs(i,j);
	    			result++;
	    		}
	    	}
	    }
	    
	    System.out.println(result);
	    
	    
	}
	
	
	public static void dfs(int x,int y) {
		graph[x][y] = 1;
		for(int i=0;i<4;i++) {
			int newx = x+dx[i];
			int newy = y+dy[i];
			
			if(0<= newx && newx < n && 0<= newy && newy <m) {
				if(graph[newx][newy] == 0) {
					dfs(newx,newy);
				}
			}
		}
	}
}