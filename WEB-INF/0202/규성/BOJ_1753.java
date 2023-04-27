package acmicpc0126_;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
입력값
6 11
1
1 2 2
1 3 5
1 4 1
2 3 3
2 4 2
3 2 3
3 6 5
4 3 3
4 5 1
5 3 1
5 6 2
*/


public class Main1753 {

	public static final int INF = Integer.MAX_VALUE; // 무한을 의미하는 값 설정
	// 노드의 개수(n), 간선의 개수(m), 시작 노드 번호(start)
	public static int n, m, start;
	// 각 노드에 연결되어 있는 노드에 대한 정보를 담는 배열
	public static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	// 최단 거리 테이블
	public static int[] distance;
	// 방문 배열
	public static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		start = sc.nextInt();

		distance = new int[n + 1];

		// 최단 거리 테이블을 모두 무한으로 초기화
		Arrays.fill(distance, INF);

		// 그래프 초기화
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Node>());
		}

		// 방문 여부를 체크하는 배열
		visited = new boolean[n + 1];

		// 모든 간선 정보 입력
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			graph.get(a).add(new Node(b, c)); // a번 노드에 서 b번 노드로 가는 비용이 c임
		}

		// 다익스트라 알고리즘 수행
		dijkstra();

		// 모든 노드로 가기 위한 최단 거리 출력
		for (int i = 1; i <= n; i++) {
			if (distance[i] == INF) { // 도달할 수 없는 경우 무한(INFINITY) 출력
//				System.out.println(i + "번  까지의 거리 : INFINITY");
				System.out.println("INF");
			} else {
				System.out.println(distance[i]);
//				System.out.println(i + "번  까지의 거리 : " + distance[i]); // 도달할 수 있는 경우 거리를 출력
			}
		}

		sc.close();
	}
	
	// 다익스트라 알고리즘
	private static void dijkstra() {
		// 우선순위 큐
		PriorityQueue<Node> pq = new PriorityQueue<>();

		// offer 연산 시에 노드를 자동으로 비교하여 넣음
		pq.offer(new Node(start, 0));
		distance[start] = 0;
		
		while (!pq.isEmpty()) { // 큐가 비어있지 않다면
			// 가장 최단 거리가 짧은 노드에 대한 정보 꺼내기
			Node node = pq.poll();
			int now = node.getIndex(); // 현재 노드
			
			if(visited[now])
				continue;
			
			//방문처리
			visited[now] = true;
				
			// 현재 노드와 연결된 다른 인접한 노드들을 확인
			for (int i = 0; i < graph.get(now).size(); i++) {
				Node otherNode = graph.get(now).get(i);
				int cost = distance[now] + otherNode.getDistance();
				// 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우
				if (cost < distance[otherNode.getIndex()] && !visited[otherNode.getIndex()]) {
					distance[otherNode.getIndex()] = cost;
					pq.offer(new Node(otherNode.getIndex(), cost));
				}
			}
		}
	}

	static class Node implements Comparable<Node> {
		private int index;
		private int distance;

		Node(int index, int distance) {
			this.index = index;
			this.distance = distance;
		}

		public int getIndex() {
			return index;
		}

		public int getDistance() {
			return distance;
		}

		// 거리(비용)가 짧은 것이 높은 우선순위를 가지도록 설정
		// 우선순위 큐 offer 과정에서 사용하면 자동으로 사용됨
		@Override
		public int compareTo(Node o) {
			return  this.distance - o.getDistance();
		}
	}
}