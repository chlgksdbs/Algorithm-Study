package acm0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17142 {
	static int n, m;
	static int[][] room, temp;
	static boolean[][] visited;
	static ArrayList<int[]> virus = new ArrayList<>();
	static int[] card;
	static int min = Integer.MAX_VALUE;
	static int curValue;
	static int[] iDir = { 1, 0, -1, 0 };
	static int[] jDir = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		room = new int[n][n];
		temp = new int[n][n];
		visited = new boolean[n][n];
		card = new int[m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 2) {
					room[i][j] = -2;
					virus.add(new int[] { i, j });

				} else if (temp == 1) {
					room[i][j] = -1;
				}
			}
		}

		comb(0, 0);
		System.out.println(min);
	}

	static void comb(int cnt, int start) {
		if (cnt == m) {
			curValue = 0;
			copyArr();
			for (int i = 0; i < m; i++) {
				visitedClear();
				bfs(virus.get(card[i]));
			}
			
			count();

			return;
		}
		for (int i = start; i < virus.size(); i++) {
			card[cnt] = i;
			comb(cnt + 1, i + 1);

		}
	}

	static void bfs(int[] start) {

		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {start[0], start[1], 0});
		temp[start[0]][start[1]] = 0;
		visited[start[0]][start[1]] = true;
		int dis = 0;
		while (!queue.isEmpty()) {

			int[] point = queue.poll();
			int cI = point[0];
			int cJ = point[1];
			dis = point[2];
			
			for (int d = 0; d < 4; d++) {
				int nI = cI + iDir[d];
				int nJ = cJ + jDir[d];
				if (nI >= 0 && nI < n && nJ >= 0 && nJ < n && !visited[nI][nJ] && temp[nI][nJ] != -1) {
					
					if(temp[nI][nJ] == 0) {
						temp[nI][nJ] = dis+1;
						queue.add(new int[] {nI, nJ,  dis+1});
						visited[nI][nJ] = true;
						
					} else if(temp[nI][nJ] == -2) {
						queue.add(new int[] {nI, nJ, dis+1});
						visited[nI][nJ] = true;
						
					} else if(temp[nI][nJ] > dis+1) {
						
						temp[nI][nJ] = dis+1;
						queue.add(new int[] {nI, nJ, dis+1});
						visited[nI][nJ] = true;
						
					}
					

				}
			}

		}
		
		temp[start[0]][start[1]] = -3;
	}

	static void count() {
		int curr = 0;
		boolean isValid = true;
		for(int i = 0; i < n; i++) {
			if(!isValid) {
				break;
			}
			for(int j = 0; j < n; j++) {
				
				//중간에  바이러스가 퍼지지 않았을 경우
				if(temp[i][j] == 0) {
					isValid = false;
					if(min == Integer.MAX_VALUE) {
						min = -1;
					}
					break;
				} else {
					if(curr < temp[i][j]) {
						curr = temp[i][j];
					}
				}
			}
		}
		
		if(isValid) {
			if(min > curr) {
				min = curr;
			} else if(min == -1) {
				min = curr;
			}
		}

	}
	
	static void visitedClear() {
		for(int i = 0 ; i < n; i++) {
			for(int j = 0 ; j < n; j++) {
				visited[i][j] = false;
			}
		}
	}

	static void copyArr() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				temp[i][j] = room[i][j];
			}
		}
	}

	static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(temp[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("----------------------");
	}

}
