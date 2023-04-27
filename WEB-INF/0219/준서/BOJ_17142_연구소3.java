package Samsung_Type_A_Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Virus {
	int x;
	int y;
	int time;

	public Virus(int x, int y, int time) {

		this.x = x;
		this.y = y;
		this.time = time;
	}

}

public class BOJ_17142_연구소3{

	static int N, M;
	static int map[][];
	static int temp_map[][];
	static boolean visited[][];
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, -1, 0, 1 };
	// 조합에 필요한 재료
	static int result[];
	static int VirusTotal;
	static int min = Integer.MAX_VALUE;

	static ArrayList<Virus> virusCnt = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		temp_map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tempVirus = Integer.parseInt(st.nextToken());
				// 바이러스면 해당 좌표를 기억함과 동시에 바이러스 초기시간 0으로 넣어줌
				if (tempVirus == 2) {
					virusCnt.add(new Virus(i, j, 1));
				} else if (tempVirus == 0) {
					VirusTotal++;
				}
				map[i][j] = tempVirus;
			}
		} // end input
		
		
		if(VirusTotal == 0) {
			System.out.println(0);
			return;
		}

		result = new int[M];
		comb(0, 0);
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		System.out.println(min);

	}// end main

	static void bfs(int result[]) {
		visited = new boolean[N][N];
		Queue<Virus> q = new LinkedList<>();
		// 뽑은 바이러스를 큐에 먼저 넣어주기
		for (int i = 0; i < result.length; i++) {
			Virus v = virusCnt.get(result[i]);
			visited[v.x][v.y] = true;
			q.add(v);
			temp_map[v.x][v.y] = 0;
			;

		}

		int time = 0;
		int cnt = 0;

		while (!q.isEmpty()) {
			Virus now = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				// print(temp_map);

				time = now.time;

				if (!(nx >= 0 && nx < N && ny >= 0 && ny < N) || visited[nx][ny])
					continue;
				if (map[nx][ny] == 2) {
					// 비활성바이러스라면
					visited[nx][ny] = true;
					q.add(new Virus(nx, ny, now.time + 1));
				}
				if (map[nx][ny] == 0) {
					visited[nx][ny] = true;
					cnt++;
					q.add(new Virus(nx, ny, now.time + 1));
				}

			}
			
			if(cnt == VirusTotal) {
				time++;
				break;
			}

		}
		if(cnt != VirusTotal) return;
		
		min = Math.min(min, time -1);

	}// end bfs

	static void comb(int idx, int start) {
		if (idx == M) {
			//System.out.println(Arrays.toString(result));
			// 경우의 수가 만족할 떄 마다 보내기
			bfs(result);
			return;
		}

		for (int i = start; i < virusCnt.size(); i++) {
			result[idx] = i;
			comb(idx + 1, i + 1);

		}


	}// end comb

	static void print(int map[][]) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("----------------");
	}// end print

	static void deepCopy(int map[][], int temp_map[][]) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				temp_map[i][j] = map[i][j];
			}
		}
	}// end deepcopy

}
