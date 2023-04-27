import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] color;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> mygraph;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			mygraph = new ArrayList<ArrayList<Integer>>();

			for (int i = 0; i < v + 1; i++) {
				mygraph.add(new ArrayList<Integer>());
			}

			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int startnode = Integer.parseInt(st.nextToken());
				int endnode = Integer.parseInt(st.nextToken());

				mygraph.get(startnode).add(endnode);
				mygraph.get(endnode).add(startnode);
			}
			ArrayList<Integer> target = new ArrayList<>();
			visited = new boolean[v + 1];
			for (int i = 1; i <= v; i++) {
				if (!visited[i]) {
					dfs(i);
					target.add(i);
				}
			}

			if (v != 1) {
				boolean flag = false;
				for (int i = 0; i < target.size(); i++) {
					visited = new boolean[v + 1];
					if (bfs(target.get(i)))
						continue;
					else {
						System.out.println("NO");
						flag = true;
						break;
					}
				}
				if(!flag) {
					System.out.println("YES");
				}
				
			} else {
				System.out.println("NO");
			}

		}
	}

	static boolean bfs(int i) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		int level = 0;
		color = new int[mygraph.size()];
		visited[i] = true;
		color[i] = level;
		q.add(new int[] { i, level });
		while (!q.isEmpty()) {
			int[] thisiter = q.poll();
			int thisnode = thisiter[0];
			int thislevel = thisiter[1];
			for (int elem = 0; elem < mygraph.get(thisnode).size(); elem++) {
				int nextnode = mygraph.get(thisnode).get(elem);
				int nextlevel = (thislevel + 1) & 1;
				if (!visited[nextnode]) {
					visited[nextnode] = true;
					color[nextnode] = nextlevel;
					q.add(new int[] { nextnode, nextlevel });
				} else {
					if (nextlevel != color[nextnode]) {
						return false;
					}
				}
			}
		}
		return true;
	}

	static void dfs(int i) {
		visited[i] = true;
		for (int elem = 0; elem < mygraph.get(i).size(); elem++) {
			int nextnode = mygraph.get(i).get(elem);
			if (!visited[nextnode]) {
				dfs(nextnode);
			}
		}
	}

}