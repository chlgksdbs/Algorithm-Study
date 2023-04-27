package acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14716 {
	
	public static int[] di = {-1, -1, 0, 1, 1, 1, 0, -1};
	public static int[] dj = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static int M;
	public static int N;
	
	
	public static void dfs(int indexI , int indexJ) {
		
		int nextI;
		int nextJ;
		
		for(int i = 0; i < di.length; i++) {
			nextI = indexI + di[i];
			nextJ = indexJ + dj[i];
			
			if(nextI >= 0 && nextI < M && nextJ >= 0 && nextJ < N && arr[nextI][nextJ] == 1) {
				arr[nextI][nextJ] = 0;
				dfs(nextI, nextJ);
			}
		}
		
	}
	
	public static int[][] arr;


	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		
		StringTokenizer st = new StringTokenizer(line);
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[M][N];
		
		for(int i = 0; i < M; i++) {
			line = br.readLine();
			st = new StringTokenizer(line);
			
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int sum = 0;
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(arr[i][j] == 1) {
					sum++;
					dfs(i,j);
				}
					
			}
		}
		
		System.out.println(sum);
	}
}