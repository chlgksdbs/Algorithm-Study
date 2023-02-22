package acmicpc0206;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main14939 {
	static int n , m , r;
	static ArrayList<Edge>[] list;
	static int[] items;
	static int maxValue = Integer.MAX_VALUE;
	static int sum = 0;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		m = sc.nextInt();
		
		r = sc.nextInt();
		
		items = new int[n+1];

		for(int i = 1; i <= n; i++) {
			items[i] = sc.nextInt();
		}

		list = new ArrayList[n+1];
		
		for(int i = 1; i <= n; i++) {
			list[i] = new ArrayList<Edge>();
		}
		
		for(int i = 1; i <= r; i++ ) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			list[a].add(new Edge(b,c));
			list[b].add(new Edge(a,c));
		}
		
		for(int i = 1; i<= n; i++) {
			dijkstra(i);
		}
		
		System.out.println(sum);
		
		sc.close();

	}
	
	static void dijkstra(int start) {

		boolean[] visited = new boolean[n+1];
		int[] distance = new int[n+1];
		
		Arrays.fill(distance, maxValue);
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
//		visited[start] = true;
		distance[start] = 0;
		pq.add(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			Edge edge =  pq.poll();
			int toIdx = edge.getTo();
//			int dis = edge.getDis();
			
//			//필요이상의 거리는 필요 없음
//			if(dis > m) {
//				continue;
//			}
			
			for(int i = 0; i < list[toIdx].size(); i++) {
				int nextIdx = list[toIdx].get(i).getTo();
				int cost = distance[toIdx] + list[toIdx].get(i).getDis();
				
				if(distance[nextIdx] > cost && cost <= m) {
					distance[nextIdx] = cost;
					pq.add(new Edge(nextIdx, cost));
				}
			}
		}
		
		//다익스트라 종료
		int temp = 0;
		for(int i = 1; i <= n; i++) {
			if(distance[i] <= m) {
				temp += items[i];
			}
		}
		
		//먹을 수 있는 item의 개수가 더 많다면 갱신
		if(sum < temp) {
			sum = temp;
		}
		
	}
	
	static class Edge implements Comparable<Edge>{
		public int getTo() {
			return to;
		}
		public void setTo(int to) {
			this.to = to;
		}
		public int getDis() {
			return dis;
		}
		public void setDis(int dis) {
			this.dis = dis;
		}
		public Edge(int to, int dis) {
			super();
			this.to = to;
			this.dis = dis;
		}
		int to;
		int dis;
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.dis - o.getDis();
		}
	}
	
}


