package day0115;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16173 {

	static int n;
	static int[][] arr;
	static Queue<int[]> queue = new LinkedList<>();
	static String str = "Hing";
	static boolean[][] visit;

	public static void main(String[] args) throws Exception{

		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		n= Integer.parseInt(br.readLine());
		
		visit = new boolean[n][n];
		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfs(new int[] { 0, 0 });
		System.out.println(str);

	}

	private static void bfs(int[] tmp) {
		queue.add(tmp);
		
		while (!queue.isEmpty()) {
			int[] array = queue.poll();

			int x = array[0];
			int y = array[1];
			
			visit[x][y]=true;

			if (x == n - 1 && y == n - 1) {
				str = "HaruHaru";
				return;
			}

			int number = arr[x][y];
			
			
			if (x < n && y + number < n && !visit[x][y+number] ) 
				queue.add(new int[] { x, y + number });
			
			if (x + number < n && y < n && !visit[x+number][y]) 
				queue.add(new int[] { x + number, y });
			

		}
	}

	
	
}
