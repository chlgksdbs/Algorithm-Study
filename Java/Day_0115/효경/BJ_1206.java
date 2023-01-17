package day0115;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_1206 {
	static int n;
	static int m;
	static int v;
	static boolean[] visit;
	static ArrayList<Integer>[] al;
	static StringBuffer sb;

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		v = scan.nextInt();

		visit = new boolean[n + 1];
		al = new ArrayList[n + 1];
		sb = new StringBuffer();

		// arrayList 초기화
		for (int i = 1; i <= n; i++)
			al[i] = new ArrayList<>();

		// 인접리스트 저장
		for (int i = 0; i < m; i++) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			al[a].add(b);
			al[b].add(a);
		}

		// 인접리스트 정렬
		for (int i = 1; i <= n; i++)
			Collections.sort(al[i]);

		dfs(v);
		sb.append("\n");

		Arrays.fill(visit, false);
		bfs(v);

		System.out.println(sb);

	}

	private static void bfs(int v) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(v);
		visit[v] = true;
		sb.append(v + " ");

		while (!queue.isEmpty()) {
			int k = queue.poll();
			for (int tmp : al[k]) {
				if (!visit[tmp]) {
					queue.offer(tmp);
					visit[tmp] = true;
					sb.append(tmp + " ");
				}
			}
		}

	}

	private static void dfs(int v) {
		visit[v] = true;
		sb.append(v + " ");
		for (int k : al[v]) {
			if (!visit[k])
				dfs(k);
		}
	}
}