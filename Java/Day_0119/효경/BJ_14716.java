package day0119;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_14716 {
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
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				arr[i][j] = st.nextToken().charAt(0);
			}
		}
		
		int count=0;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(arr[i][j]=='1') {
					bfs(new int[] {i,j});
					count++;

				}
			}
		}
		System.out.println(count);
		

	}
	
	static int[] dx = {-1,-1,-1,0,1,1,1,0};
	static int[] dy = {-1,0,1,1,1,0,-1,-1};
	
	
	private static void bfs(int[] is) {
		
		Queue<int[]> q = new LinkedList<int[]>();
		
		q.offer(is);
		arr[is[0]][is[1]]='0';

		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			
			for(int i=0;i<8;i++) {
				int x = tmp[0]+dx[i];
				int y = tmp[1]+dy[i];
				
				if(x<0 ||x >=n || y<0 ||y>=m || arr[x][y]=='0')
					continue;
				q.offer(new int[]{x,y});
				arr[x][y]='0';

				
			}
		}
		
	}

}
