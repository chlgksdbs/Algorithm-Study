package day_0115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main2178 {

	public static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static int n;
	static int m;
	static int[][] mygraph;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] mystring = br.readLine().split(" ");
		n = Integer.parseInt(mystring[0]);
		m = Integer.parseInt(mystring[1]);
		mygraph = new int[n][m];
		for (int i = 0; i < n; i++) {
			mystring = br.readLine().split("");
			for (int j = 0; j < m; j++) {
				mygraph[i][j] = Integer.parseInt(mystring[j]);
			}
		}
		System.out.println(bfs());

	}

	public static int bfs() {
		Queue<Pair> q = new LinkedList<>();
		int result = 0;
		q.offer(new Pair(0, 0));
		while (!q.isEmpty()) {
			Pair mypair = q.poll();
			if (mypair.x == n - 1 && mypair.y == m - 1) {
				return mygraph[n-1][m-1];
			}
			for (int i = 0; i < 4; i++) {
				int newx = mypair.x + dx[i];
				int newy = mypair.y + dy[i];
				if (0 <= newx && newx < n && 0 <= newy && newy < m) {
					if(mygraph[newx][newy] == 1) {
						mygraph[newx][newy] = mygraph[mypair.x][mypair.y] + 1;
						q.offer(new Pair(newx,newy));
					}
				}
			}

		}
		return result;

	}

}