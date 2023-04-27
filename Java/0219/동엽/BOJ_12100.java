import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] graph;
	static boolean[][] isMoved;
	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		graph = new int[n][n];
		int[][] copy = new int[n][n];
		isMoved = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				copy[i][j] = graph[i][j];
			}
		}

		for (int bit = 0; bit < (1 << 10); bit++) {
			
			int k = bit;
			int[][] tmp = new int[n][n];
			for (int index = 0; index < 5; index++) {
				switch (k & 3) {
				case 0: // 동 -> 배열 왼쪽으로 90도 회전
					for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
							tmp[n - 1 - j][i] = graph[i][j];
						}
					}

					for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
							graph[i][j] = tmp[i][j];
						}
					}
					break;
				case 1: // 서 -> 배열 오른쪽으로 90도 회전
					for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
							tmp[j][n - 1 - i] = graph[i][j];
						}
					}
					for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
							graph[i][j] = tmp[i][j];
						}
					}
					break;
				case 2: // 남 -> 배열 위아래로 반전
					for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
							tmp[n - 1 - i][j] = graph[i][j];
						}
					}
					for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
							graph[i][j] = tmp[i][j];
						}
					}
					break;
				case 3: // 북
					break;

				}
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (graph[i][j] == 0)
							continue;

						int xpos = i;
						int ypos = j;
						while (xpos >= 0) {
							if (xpos - 1 >= 0) { // 윗칸이 있나?
								if (graph[xpos - 1][ypos] == 0) { // 빈칸 이동
									graph[xpos - 1][ypos] = graph[xpos][ypos];
									graph[xpos][ypos] = 0;
								} else if (graph[xpos - 1][ypos] == graph[xpos][ypos] && !isMoved[xpos - 1][ypos] && !isMoved[xpos][ypos]) { // 합치기
									isMoved[xpos - 1][ypos] = true;
									graph[xpos - 1][ypos] *= 2;
									graph[xpos][ypos] = 0;
								}
							}
							xpos--;
						}
					}
				} // 밀기

				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						isMoved[i][j] = false;
					}
				}

				k = k >> 2;
			} //5바퀴 다돌았음
			for(int i = 0 ; i < n ; i ++) {
				for(int j = 0 ; j < n ; j ++) {
					answer = Math.max(answer, graph[i][j]);
				}
			}
			
			for(int i = 0 ; i < n ; i++) {
				for(int j = 0 ; j < n ; j++) {
					graph[i][j] = copy[i][j];
				}
			}
		} // bit값 결정

	System.out.println(answer);
	}
}