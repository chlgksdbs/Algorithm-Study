package day0215;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1707 {
	
	static int[] visited; // 방문 처리를 확인하는 배열
	
	public static int bfs(List<Integer>[] graph, int start, StringBuilder sb) {		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start); // 출발점을 1로 지정
		visited[start] = 1; // 1로 방문 처리
		
		// queue가 빌 때까지 수행
		// 중간에 체크를 통해 인접한 값의 변화가 생긴다면 이분 그래프가 아니다를 출력하고 함수 종료
		while (!queue.isEmpty()) {
			int v = queue.poll();
			
			for (int i = 0; i < graph[v].size(); i++) {
				int u = graph[v].get(i);
				
				// 현재 정점의 방문 처리 방식과 다른 방식으로 처리
				if (visited[v] == 1) {
					if (visited[u] == 0) {
						visited[u] = 2;
						queue.add(u);
					}
					else {
						if (visited[u] == 1) {
							sb.append("NO" + "\n");
							return -1;
						}
					}
				}
				else {
					if (visited[u] == 0) {
						visited[u] = 1;
						queue.add(u);
					}
					else {
						if (visited[u] == 2) {
							sb.append("NO" + "\n");
							return -1;
						}
					}
				}
			}
		}
		return 1;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb1 = new StringBuilder();
		
		int T = sc.nextInt(); // 테스트 케이스의 개수
		for (int tc = 1; tc <= T; tc++) {
			StringBuilder sb2 = new StringBuilder(); // 정답을 저장하는 변수
			int v = sc.nextInt(); // 정점의 개수
			int e = sc.nextInt(); // 간선의 개수
			
			List<Integer>[] graph = new ArrayList[v + 1]; // 0을 사용하지 않고, 1부터 v까지의 정점의 정보를 담는 그래프
			visited = new int[v + 1];
			
			for (int i = 1; i <= v; i++) {
				graph[i] = new ArrayList<>(); // 2차원 ArrayList 생성
			}
			
			for (int i = 0; i < e; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				// 양방향에 대한 정보를 입력
				graph[a].add(b);
				graph[b].add(a);
			}
			
			int flags = 0;
			// 비연결 그래프임을 고려하여 모든 노드를 시작점으로 탐색
			for (int i = 1; i <= v; i++) {
				// 이미 방문이 된 정점의 경우 수행하지 않음
				if (visited[i] == 0) {
					flags = bfs(graph, i, sb2); // bfs를 통해 인접한 정점끼리 같은 값을 가진다면 이분 그래프이고, 그렇지 않다면 이분 그래프가 아님을 확인
					if (flags == -1) break;
				}
			}
			if (flags != -1) {
				// 위 반복문을 수행하고 나왔다면 이분 그래프
				sb2.append("YES" + "\n");
			}
			
			sb1.append(sb2);
		}
		System.out.println(sb1);
	}
}
