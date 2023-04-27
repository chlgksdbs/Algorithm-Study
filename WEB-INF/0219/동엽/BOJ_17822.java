import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<LinkedList<Integer>> graph = new ArrayList<LinkedList<Integer>>();;
	static int n, m, sum = 0;
	private static boolean iscleared = false;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			graph.add(new LinkedList<Integer>());
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				graph.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}

		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			int cnt = 1;
			while (x * cnt <= n) {
				spin(x * cnt - 1, d, k);
				cnt++;
			}

			for (int r = 0; r < n; r++) {
				for (int c = 0; c < m; c++) {
					if (graph.get(r).get(c) != 0) {
						bfs(r, c);
					}
				}
			}
			if (!iscleared) {
				int count = 0;
				int tmpsum = 0;
				for (int r = 0; r < n; r++) {
					for (int c = 0; c < m; c++) {
						if (graph.get(r).get(c) != 0) {
							tmpsum += graph.get(r).get(c);
							count++;
						}
					}
				}

				double avg = ((double) tmpsum) / count;

				for (int r = 0; r < n; r++) {
					for (int c = 0; c < m; c++) {
						int thisnode = graph.get(r).get(c);
						if (thisnode == 0)
							continue;
						if (thisnode < avg)
							graph.get(r).set(c, thisnode + 1);
						else if (thisnode > avg)
							graph.get(r).set(c, thisnode - 1);
					}
				}	
			}

			if (i == t - 1) {
				int answer = 0;
				for (int r = 0; r < n; r++) {
					for (int c = 0; c < m; c++) {
						answer += graph.get(r).get(c);
					}
				}
				System.out.println(answer);
			}

			iscleared = false;
		}
	}

	private static void bfs(int x, int y) {
		ArrayDeque<Point> q = new ArrayDeque<>();
		boolean thiscleared = false;
		int target = graph.get(x).get(y);
		q.add(new Point(x, y));
		graph.get(x).set(y, 0);
		while (!q.isEmpty()) {
			Point thisiter = q.pollFirst();
			for (int i = 0; i < 4; i++) {
				int newx = thisiter.x + dx[i];
				int newy = thisiter.y + dy[i];
				if (newy == -1)
					newy = m - 1;
				if (newy == m)
					newy = 0;
				if (0 <= newx && newx < n && graph.get(newx).get(newy) == target) {
					graph.get(newx).set(newy, 0);
					iscleared = true;
					thiscleared = true;
					q.add(new Point(newx,newy));
				}

			}
		}
		if(!thiscleared) graph.get(x).set(y, target);
	}

	private static void spin(int i, int d, int k) {
		LinkedList<Integer> mylist = graph.get(i);
		if (d == 0) {
			for (int idx = 0; idx < k; idx++) {
				int tmp = mylist.getLast();
				mylist.removeLast();
				mylist.addFirst(tmp);
			}
		} else {
			for (int idx = 0; idx < k; idx++) {
				int tmp = mylist.getFirst();
				mylist.removeFirst();
				mylist.addLast(tmp);
			}
		}

	}
}