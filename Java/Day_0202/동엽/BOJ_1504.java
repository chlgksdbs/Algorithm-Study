package day0207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ_1504 {
	
	static ArrayList<ArrayList<int[]>> graph;
	static int[] firstdistance;
	static int[] seconddistance;
	static final int INF = 1000000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		
		graph = new ArrayList<ArrayList<int[]>>();
		for(int i = 0; i < v+1;i++) {
			graph.add(new ArrayList<int[]>());
		}
		firstdistance = new int[v+1];
		seconddistance = new int[v+1];
		Arrays.fill(firstdistance, INF);
		Arrays.fill(seconddistance, INF);
		
		for(int i = 0 ; i < e ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(start).add(new int[] {end, weight});
			graph.get(end).add(new int[] {start,weight});
			
		}
		
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		
		dijkstra(v1,firstdistance);
		dijkstra(v2,seconddistance);
		
		long answer = Math.min((long) firstdistance[v2]+firstdistance[v]+seconddistance[1],(long) firstdistance[v2]+seconddistance[v]+firstdistance[1]);
		
		if(answer >= INF) System.out.println(-1);
		else System.out.println(answer);
		
	}
	
	static void dijkstra(int node, int[] distance) {
		distance[node] = 0;
		PriorityQueue<int[]> q = new PriorityQueue<>((o1,o2)->{return o1[1] - o2[1];});
		q.add(new int[] {node,0});
		while(!q.isEmpty()) {
			int[] elem = q.poll();
			int now = elem[0];
			int dist = elem[1];
			
			if(distance[now] < dist) continue;
			
			for(int[] thiselem : graph.get(now)) {
				int newdist = dist + thiselem[1];
				if(newdist < distance[thiselem[0]]) {
					distance[thiselem[0]] = newdist;
					q.add(new int[] {thiselem[0],newdist});
				}
			}
		}
	}
	
	

}
