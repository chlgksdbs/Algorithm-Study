package day0213;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1707 {

	static int V;
	static int E;
	static ArrayList<Integer>[] al;
	static int[] visit;
	static int count;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			al = new ArrayList[V + 1];
			for (int i = 1; i <= V; i++)
				al[i] = new ArrayList<>();

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				al[a].add(b);
				al[b].add(a);
			}


			if (isBipartite())
				System.out.println("YES");
			else
				System.out.println("NO");

		} // tc end

	}// mian end

	static boolean bfs(int n) {

		Queue<Integer> q = new LinkedList<>();
		q.offer(n);
		visit[n] = 1;

		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				int now = q.poll();

				for (int next : al[now]) {

					if (visit[next] > 0 && visit[next] == visit[now])
						return false;
					else if (visit[next] > 0 && visit[next] != visit[now])
						continue;

					visit[next] = visit[now] == 1 ? 2 : 1;
					q.offer(next);
				}
			}
		}

		return true;

	}
	
	
	
	static boolean isBipartite() {
		
		boolean result=false;
		
		//for (int i = 1; i <= V; i++) {

			visit = new int[V + 1];

			if (bfs(1)) {
				result =true;
				for (int k = 1; k <= V; k++) {
					if (visit[k] == 0) {
						if(bfs(k)) continue;
						else result=false;
					}
				}
				if(result) return true;;
			}
//		}

		return false;
	} 
}

/*
 * 분리된 그래프 처리 못함
 1
 5 4 
 1 2 
 3 4 
 3 5
 4 5
 
  NO 시간초과
 */
