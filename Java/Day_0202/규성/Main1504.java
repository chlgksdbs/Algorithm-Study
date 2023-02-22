package acmicpc0126_;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main1504 {

	static final int mInt = Integer.MAX_VALUE;
	static int n, m;
	static ArrayList<ArrayList<Node>> list = new ArrayList<>();
	static int[] distance;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		distance = new int[n + 1];

		for (int i = 0; i <= n; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();

			// 양방향 넣기
			list.get(a).add(new Node(b, c));
			list.get(b).add(new Node(a, c));
		}

		int need1 = sc.nextInt();
		int need2 = sc.nextInt();
		
		boolean flag = true;

		int case1 = 0;

		int case11 = dijstra(1, need1);
		
		int case12 = dijstra(need1, need2);

		int case13 = dijstra(need2, n);

		if (case11 != -1 && case12 != -1 && case13 != -1)
			case1 = case11 + case12 + case13;
		else {
			flag = false;
		}
		

		int case2 = 0;

		int case21 = dijstra(1, need2);
		
		int case22 = dijstra(need2, need1);

		int case23 = dijstra(need1, n);

		if (case21 != -1 && case22 != -1 && case23 != -1)
			case2 = case21 + case22 + case23;
		else {
			flag = false;
		}
		
		if(flag) {
			System.out.println(Math.min(case1, case2));
		}else {
			System.out.println(-1);
		}
		

		sc.close();

	}

	static int dijstra(int start, int end) {
		for (int i = 1; i <= n; i++) {
			distance[i] = mInt;
		}

		PriorityQueue<Node> queue = new PriorityQueue<>();
		distance[start] = 0;
		queue.offer(new Node(start, 0));

		while (!queue.isEmpty()) {
			// 가장 가까운 노드를 꺼내서 확인
			Node node = queue.poll();
			int index = node.getIndex();
			int dis = node.getCost();

			// 꺼낸 노드 정보가 의미 없음 넘김
			if (distance[index] < dis) {
				continue;
			}

			// 의미가 있는 노드
			// 연결된 간선의 수 만큼 반복
			for (int i = 0; i < list.get(index).size(); i++) {
				int cost = distance[index] + list.get(index).get(i).getCost();
				int nIndex = list.get(index).get(i).getIndex();

				if (distance[nIndex] > cost) {
					distance[nIndex] = cost;
					queue.offer(new Node(nIndex, cost));
				}
			}

			for (int i = 1; i <= n; i++) {
			}
		}

		if (distance[end] == mInt) {
			return -1;
		}

		return distance[end];

	}

	static class Node implements Comparable<Node> {
		public Node(int index, int cost) {
			super();
			this.index = index;
			this.cost = cost;
		}

		private int index;
		private int cost;

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public int getCost() {
			return cost;
		}

		public void setCost(int cost) {
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.cost - o.getCost();
		}

	}
}
