// BOJ_1697 : 숨바꼭질

package algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1697 {
	static int[] dx = { -1, 1, 0 };
	static int[] visited;
	static int k;
	
	public static void bfs(int x) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(x);
		
		while (!q.isEmpty()) {
			int tempX = q.poll();
			
			for (int i = 0; i < 3; i++) {
				int nextX = tempX + dx[i];
				if (i == 2) nextX *= 2;
				
				if (nextX < 0 || nextX > 100000) continue; // 범위를 벗어나는 경우 아래 문장 수행 X
				
				if (nextX != x && visited[nextX] == 0) { // 해당 위치를 처음 방문한 경우 (출발 지점 제외)
					visited[nextX] = visited[tempX] + 1;
					q.offer(nextX);
					continue;
				}
				
				// 방문 처리가 되어있으며, 해당 인덱스에 저장된 값보다 갱신할 값이 작은 경우만 수행 후, 큐에 저장 (무한 루프를 피하기 위해)
				if (visited[nextX] != 0 && visited[nextX] > visited[tempX] + 1) {
					visited[nextX] = visited[tempX] + 1;
					q.offer(nextX);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // 수빈이의 현재 위치
		k = sc.nextInt(); // 동생의 현재 위치
		
		visited = new int[100001];
		
		bfs(n);
		
//		for (int i = 0; i <= k; i++) {
//			System.out.print(visited[i] + " ");
//		}
//		System.out.println();
		
		System.out.println(visited[k]); // 동생의 위치에 도달하는 최소 시간 출력
		
	}
}
