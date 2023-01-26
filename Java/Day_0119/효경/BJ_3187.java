package day0119;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3187 {
	static int v;
	static int k;
	static int n;
	static int m;
	static char[][] arr;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		 n = Integer.parseInt(st.nextToken());
		 m = Integer.parseInt(st.nextToken());
		
		 arr = new char[n][m];
		
		for(int i=0;i<n;i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		int resultK=0;
		int resultV=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(arr[i][j] == '.' || arr[i][j] == '#') continue;
				else {
					v = k=0;
					bfs(new int[] {i,j});
					if(k>v) resultK+=k; //양이 많으면
					else resultV+=v;
				}
			}
		}
		
		System.out.println(resultK+" "+resultV);
	}
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	private static void bfs(int[] is) {
		// TODO Auto-generated method stub
		
		Queue<int[]> q = new LinkedList<>();
		q.add(is);
		if(arr[is[0]][is[1]]== 'v') v++; //늑대
		else if(arr[is[0]][is[1]] == 'k') k++; //양
		arr[is[0]][is[1]] = '#';
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			for(int i=0;i<4;i++) {
				int x = tmp[0] +dx[i];
				int y= tmp[1]+dy[i];
				
				if(x<0 || x>=n || y<0 || y>=m || arr[x][y] =='#' ) continue;
				
				q.offer(new int[] {x,y});
				if(arr[x][y] == 'v') v++; //늑대
				else if(arr[x][y] == 'k') k++; //양
				arr[x][y] = '#';
			}
			
		}
		
	}

}
