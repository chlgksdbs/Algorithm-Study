import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static ArrayList<Integer>[] graph;
	static int[] visited1;
	static int[] visited2;

	public static void bfs(int v) {
		Queue<Integer> q = new LinkedList<>();
		q.add(v);
		visited2[v] = 1;
		System.out.print(v + " ");
		while (!q.isEmpty()) {
			int tmp1 = q.poll();
			for(int i=0; i<graph[tmp1].size(); i++) {
				int tmp2 = graph[tmp1].get(i);
				if(visited2[tmp2]!=1) {
					q.add(tmp2);
					visited2[tmp2] = 1;
					System.out.print(tmp2 + " ");
				}
			}
		}
	}

	public static void dfs(int v) {
		visited1[v] = 1;
		System.out.print(v + " ");
		for (int i = 0; i < graph[v].size(); i++) {
			int nextV = graph[v].get(i);
			if (visited1[nextV] != 1) {
				dfs(nextV);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int V = sc.nextInt();

		// 배열 초기화
		graph = new ArrayList[N + 1];

		// 방문처리 배열 초기회
		visited1 = new int[N + 1];
		visited2 = new int[N + 1];

		// 객체 생성 및 초기화
		for (int i = 0; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		// 각 객체 배열에 값 입력
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			graph[a].add(b);
			graph[b].add(a);
		}

		// 정렬시키기
		for (ArrayList<Integer> x : graph) {
			Collections.sort(x);
		}

		dfs(V);
		System.out.println();

		bfs(V);
	}
}
