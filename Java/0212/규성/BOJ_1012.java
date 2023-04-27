package acmicpc0212;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main1012 {
	
	static boolean[][] arr;
	static int M,N;
	static int[] iDir = {0,1,0,-1};
	static int[] jDir = {1,0,-1,0};
	static Queue<int[]> que = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);

//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;

//		int T = Integer.parseInt(br.readLine());
		int T = sc.nextInt();
		
		for(int tc = 0; tc < T; tc++) {
//			st = new StringTokenizer(br.readLine());
//
//			M = Integer.parseInt(st.nextToken());
//			N = Integer.parseInt(st.nextToken());

			M = sc.nextInt();
			N = sc.nextInt();
			
			arr = new boolean[M][N];
			
//			int K = Integer.parseInt(st.nextToken());
			int K = sc.nextInt();
			for(int kNum = 0; kNum < K; kNum ++) {
				
//				st = new StringTokenizer(br.readLine());
//				int x = Integer.parseInt(st.nextToken());
//				int y = Integer.parseInt(st.nextToken());
				int x = sc.nextInt();
				int y = sc.nextInt();
				
				arr[x][y] = true;
			}
			int sum = 0;
			for(int i = 0; i < M; i++) {
				for(int j = 0; j < N; j++) {
					if(arr[i][j]) {
//						bfs(i,j);
						dfs(i,j);
//						System.out.println(i + " : " + j);
						sum ++;
					}
				}
			}
			
			System.out.println(sum);
		}

	}
	
	static void bfs(int iIndex, int jIndex) {
		
		que.add(new int[] {iIndex, jIndex});
		
		while(!que.isEmpty()) {
			int[] temp = que.poll();
			int nowI = temp[0];
			int nowJ = temp[1];
			
			arr[nowI][nowJ] = false;
			
			
			for(int i = 0; i < 4; i++) {
				int nI = temp[0] + iDir[i];
				int nJ = temp[1] + jDir[i];
				
				if(nI >= 0 && nI < M && nJ >= 0 && nJ < N && arr[nI][nJ]) {
					que.add(new int[] {nI, nJ});
				}
			}
			
		}
	}
	
	static void dfs(int iIndex, int jIndex) {
		arr[iIndex][jIndex] = false;
		for(int i = 0; i < 4; i++) {
			int nI = iIndex + iDir[i];
			int nJ = jIndex + jDir[i];
			
			if(nI >= 0 && nI < M && nJ >= 0 && nJ < N && arr[nI][nJ]) {
				dfs(nI, nJ);
			}
		}
	}

}
