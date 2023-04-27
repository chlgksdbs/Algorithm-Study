package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1260 {
	
	static List<Integer>[] graph = new ArrayList[1001]; // 간선의 정보를 저장해놓을 리스트
	static boolean[] visited; // 방문 처리를 위한 배열
	
	public static void dfs(int start) {
		visited[start] = true; // 현재 노드를 방문처리
		System.out.print(start + " "); // 현재 방문한 노드를 출력
		
		int graphSize = graph[start].size();
		
		for (int i = 0; i < graphSize; i++) {
			int destination = graph[start].get(i); // 목적지에 대한 정보
			
			// 목적지에 대한 노드의 방문처리가 되어있지 않은 경우 방문
			if (!visited[destination]) {
				dfs(destination);
			}
		}
	}
	
	public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>(); // queue 생성
		q.add(start); // 시작 지점을 queue에 push
		visited[start] = true; // 현재 노드를 방문처리
		
		while(!q.isEmpty()) {
			int qStart = q.poll(); // queue에서 데이터를 꺼내옴
			System.out.print(qStart + " "); // 해당 노드 출력
			int qSize = graph[qStart].size();
			
			for (int i = 0; i < qSize; i++) {
				int destination = graph[qStart].get(i);
				
				// 목적지에 대한 노드의 방문처리가 되어있지 않은 경우, queue에 해당 노드를 삽입하고 방문처리
				if (!visited[destination]) {
					q.add(destination);
					visited[destination] = true;
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 정점의 개수
		int m = Integer.parseInt(st.nextToken()); // 간선의 개수
		int v = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점의 번호
		
		for (int i = 0; i < n + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 양방향 그래프
			graph[a].add(b); // a에서 b로 이어지는 간선의 정보를 추가
			graph[b].add(a); // b에서 a로 이어지는 간선의 정보를 추가
		}
		
		// 그래프 내부를 오름차순으로 정렬
		for (int i = 0; i < n + 1; i++) {
			graph[i].sort(Comparator.naturalOrder());
		}
		
		visited = new boolean[1001]; // visited 배열 초기화
		dfs(v); // (1) dfs 함수 수행
		
		System.out.println();
		
		visited = new boolean[1001]; // visited 배열 초기화
		bfs(v); // (2) bfs 함수 수행
		
	}
}
