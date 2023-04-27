package acmicpc0126_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// 14% 틀림

public class Main1865 {
	
	//충분히 큰 max 값
	static final int intMax = 25000001;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		
		String[] arr = new String[tc];
		
		for(int i = 0; i < tc; i++) {
			int n = sc.nextInt();
			
			int[] distance = new int[n+1];
			
			Arrays.fill(distance, intMax);
			
			ArrayList<Edge> list = new ArrayList<>();
			
			int m = sc.nextInt();
			int w = sc.nextInt();
			//간선 정보
			for(int j = 0; j < m; j++) {
				int s = sc.nextInt();
				int e = sc.nextInt();
				int t = sc.nextInt();
				list.add(new Edge(s,e,t));
				list.add(new Edge(e,s,t));
			}
			
			//웜홀 정보
			for(int j = 0; j < w; j++) {
				int s = sc.nextInt();
				int e = sc.nextInt();
				int t = sc.nextInt();
				list.add(new Edge(s,e,-t));
			}
			
			//일단은 1로 시작
			if(bellman (n, list, distance, 1)) {
				arr[i] = "YES";
			} else {
				arr[i] = "NO";
			}
				
		}
		
		for(String st : arr) {
			System.out.println(st);
		}
		
		sc.close();

	}
	
	static boolean bellman(int n, ArrayList<Edge> list, int[] distance, int start) {
		
		//반드시 1에서 시작하지 않을 수 있음
		//고리가 2개 있고 하나는 일반, 하나는 음의 사이클이라면?
		distance[start] = 0;
		
		//n번 반복하면 반드시 최단 경로를 발견할 것
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < list.size(); j++) {
//				//출발지가 무한
//				if(distance[list.get(j).getFrom()] == intMax) {
//					continue;
//				}
				
				//경로 갱신 가능
				int cost = list.get(j).getDis() + distance[list.get(j).getFrom()];
				if(distance[list.get(j).getTo()] > cost) {
					distance[list.get(j).getTo()] = cost;
				}
			}
		}
		
		
		boolean flag = false;
		//한번 더 반복해서 루프가 도는지 확인
		for(int j = 0; j < list.size(); j++) {
			
			//경로 갱신 가능
			int cost = list.get(j).getDis() + distance[list.get(j).getFrom()];
			if(distance[list.get(j).getTo()] > cost) {
				flag = true;
			}
		}
		
		if(flag) {
			return true;
		}
		
		return false;
	}
	
	static class Edge {
		public Edge(int from, int to, int dis) {
			super();
			this.from = from;
			this.to = to;
			this.dis = dis;
		}
		public int getTo() {
			return to;
		}
		public void setTo(int index) {
			this.to = index;
		}
		public int getDis() {
			return dis;
		}
		public void setDis(int dis) {
			this.dis = dis;
		}
		public int getFrom() {
			return from;
		}
		public void setFrom(int from) {
			this.from = from;
		}
		
		
		
		private int from;
		private int to;
		private int dis;
		
	}
}


