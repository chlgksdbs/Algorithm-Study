package day0120;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3187 {

	static char[][] mygraph;
	static boolean[][] visited;
	static int sheep;
	static int wolf;
	static int bfssheep = 0;
	static int bfswolf = 0;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		mygraph = new char[r][c];
		visited = new boolean[r][c];
		for (int i = 0; i < r; i++) {
			String mystr = br.readLine();
			for (int j = 0; j < c; j++) {
				mygraph[i][j] = mystr.charAt(j);

			}
		}

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (mygraph[i][j] != '#' && !visited[i][j]) {
					bfswolf = 0;
					bfssheep = 0;
					dfs(i, j);
					if(bfswolf >= bfssheep) {
						wolf += bfswolf;
					}else {
						sheep += bfssheep;
					}
				}
			}
		}

		System.out.println(sheep + " " + wolf);

	}


	public static void dfs(int x, int y) {
		visited[x][y] = true;
		if (mygraph[x][y] == 'k')
			bfssheep += 1;
		if (mygraph[x][y] == 'v')
			bfswolf += 1;
		for (int k = 0; k < 4; k++) {
			int newx = x + dx[k];
			int newy = y + dy[k];
			if (0 <= newx && 0 <= newy && newx < mygraph.length && newy < mygraph[0].length && !visited[newx][newy]) {
				if (mygraph[newx][newy] != '#') {
					dfs(newx,newy);
				}
			}
		}
	}

}
