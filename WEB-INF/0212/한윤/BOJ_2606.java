package day0213;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_2606 {
	
	static int n; // 컴퓨터의 수
	static int m; // 네트워크 상에서 직접 연결되어 있는 컴퓨터 쌍의 수
	static int cnt = 0; // 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수
	
	static List<Integer>[] graph; // 각 지점 별 연결된 정보를 담는 리스트
	static boolean[] visited; // 방문 처리를 위한 배열
	
	public static void dfs(int start) {
		visited[start] = true; // 해당 지점을 방문 처리
		
		for (int i = 0; i < graph[start].size(); i++) {
			if (!visited[graph[start].get(i)]) {
				dfs(graph[start].get(i));
				cnt++; // count 값 증가
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		graph = new ArrayList[n + 1];
		
		for (int i = 0; i < n + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		visited = new boolean[n + 1];
		
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			// 양방향 간선 추가
			graph[a].add(b);
			graph[b].add(a);
		}
		
		dfs(1); // dfs 함수 실행
		
		System.out.println(cnt);
		
	}
}
