package acmicpc0126_;

import java.util.ArrayList;
import java.util.Scanner;



public class Main1446 {
	
	public static final int INF = Integer.MAX_VALUE; // 무한을 의미하는 값 설정
	// 노드의 개수(n), 간선의 개수(m), 시작 노드 번호(start)
	public static int n;
	// 각 노드에 연결되어 있는 노드에 대한 정보를 담는 배열
	public static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	// 최단 거리 테이블
	public static int[] distance;
	
	static int totalLength;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		totalLength = sc.nextInt();
		
		distance = new int[totalLength+1];
		
		for (int i = 0; i <= totalLength; i++) {
			distance[i] = INF;
		}
		
		for (int i = 0; i <= totalLength; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		for(int i = 0; i < n; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int length = sc.nextInt();
			if(to > totalLength) {
				continue;
			}
			graph.get(from).add(new Node(to, length));
			
		}
		
		// 다익스트라 알고리즘 수행
		dijkstra();
		
		System.out.println(distance[totalLength]);

		sc.close();
	}

		// 다익스트라 알고리즘
		public static void dijkstra() {
			// 시작 노드에 대해서 초기화
			distance[0] = 0;
			
			int length = 0;

			for (int i = 0; i <= totalLength; i++) {
				
				
				if(length < distance[i] ) {
					distance[i] = length;
				} else {
					length = distance[i];
					
				}
				length++;
				
				// 현재 노드와 연결된 다른 노드 확인
				for (int j = 0; j < graph.get(i).size(); j++) {
					int cost = distance[i] + graph.get(i).get(j).getLength();
					// 현재 노드를 거쳐서 다른 노드를 이동하는 거리가 더 짧은 경우
					if (cost < distance[graph.get(i).get(j).getTo()]) {
						distance[graph.get(i).get(j).getTo()] = cost;
//						System.out.println(graph.get(i).get(j).getTo()+" : cost : "+cost);
					}
						
				}
				
				

				
			}
		}
	
	
}

class Node{

	public Node( int to, int length) {
		super();
		this.setTo(to);
		this.setLength(length);
	}

	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	private int to;
	private int length;
	
}

