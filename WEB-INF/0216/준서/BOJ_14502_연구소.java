package Samsung_Type_A_Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502_연구소  {
//바이러스는 상하좌우로 퍼저나갈 수 있다 = 4방 탐색
//새로 세울 수 있는 벽의 개수 3개이고 전부 사용해야함 
// 0 빈 칸, 1은 벽,  2는 바이러스
// 벽을 세우고 난 뒤 안전 영역의 최대 값을 구하자 
// 바이러스는 여러개 일 수 있음 	

// 전체 경우의 수를 조합으로 계산 후 바이러스 확산 시키고  모든 지역을 접근 할 수 없다면 안전구역 카운트
// 전체 경우에서 제일 큰 안전 구역을 갔는 경우를 출력하자

// 구현 메소드 : dfs, comb, main ,safeZone
// static 변수 : map ,N,M 

	static int N, M;
	static int R = 3; // 세울 수 있는 벽의 계수
	static int map[][];
	static int map_temp[][]; // 원본 배열을 회손하지 않고 여러가지 상황을 비교하기 위한 임시 배열 생성

	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 }; // 4방으로 확산 바이러스의 이동 경로

	static int safecnt = Integer.MIN_VALUE; // 큰 결과와 비교해야하기 때문에 초기 값을 가장 작은 값으로 설정

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		map_temp = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		map_temp = map; // 원본 배열을 비교를 위한 임시 배열에 저장하기

		// 조합 돌리기
		comb(0);

		System.out.println(safecnt);

	}

	// comb를 사용해서 2차원 배열에서 세울 수 있는 경우를 계산
	// 1.N * M 1차원 배열을 만들어서 전부 저장할까?
	// 2. 위 방법은 또 연산해줘야해서 불편할 것 같은데 혹시라도 2차원 배열에 한번에 입력 받아서 계산 할 수 있는 방법이 있을까>
	// 어떻게 해야할까 여기서 부터 생각을 해보자
	public static void comb(int cnt) {
		if (cnt == R) {
			// 벽을 다 세웠다면 바이러스 돌리기
//			System.out.println("debug comb call");
//			System.out.println(Arrays.deepToString(map_temp));
			bfs();
			return;
		}
		// 2차원 배열에 한번에 비교하자
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map_temp[i][j] == 0) { // 해당 구역이 빈 칸이라면 벽을 세울 수 있기 때문에 0인 경우만 선택
					map_temp[i][j] = 1; // 벽 생성
					comb(cnt + 1);
					map_temp[i][j] = 0; // 사용 후 돌려두기

				}
			}
		}

	}

	// 2차원 배열에서 사용할 수 있는 경우의 수를 적어서 표현하면 되는거잖아

	// dfs bfs 둘 중에하나를 만들어서 전부 순회를 해야하네??
	public static void bfs() {
		// 큐를 이용해서 풀어야겠지
		// 새로운 배열을 안만들고 기존 배열을 사용하니까 계속 값이 변함 
		// 새로운 바이러스 배열 생성
//		System.out.println(Arrays.deepToString(map_temp));
		int virus_map [][] = new int[N][M];
		Queue<virus> q = new LinkedList<virus>();
		//System.out.println("debug bfs call");
		
//		
//	
//		
//		virus_map = map_temp; //이렇게 넣으면 안된다 why? -> 이러게하면 주소를 복사하는거여서 바이러스값을 수정해도 맵의 값이 변경된다 
		// 이 방법으로 하면 주소가 아닌 값을 복사하는 작업이여서 원본을 회손하지 않는다 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				virus_map[i][j] = map_temp[i][j];
			}
		} 
		
	
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (virus_map[i][j] == 2) {
					// 큐에 넣어야하는데 좌표 형태로 넣어줘하네?....바이러스 객체 생성
					//System.out.println("debug viruszone call");
					q.add(new virus(i, j));

				}
			}
		}

		while (!q.isEmpty()) {
			// 바이러스 퍼트리고 큐에 넣어서 지속적으로 퍼트려 못 퍼질 떄 까지
			virus v = q.poll(); // 큐에서 첫 값 뽑아와
			// 4방으로 퍼지기
			for (int i = 0; i < 4; i++) {
				int nx = v.x + dx[i];
				int ny = v.y + dy[i];
				// 범위를 넘지 않고 해당 자리에 벽이 아닌 경우 확장
				// 0이상이고 N 이하이고 0이상이고 M 이하이고 1
				if (0 <= nx && 0 <= ny && nx < N && ny < M ) {
					// 여기서 안멈추네....??
					// System.out.println("debug 4-way No problem call");
					if (virus_map[nx][ny] == 0) {
							virus_map[nx][ny] = 2;
							q.add(new virus(nx, ny)); // 큐에 넣어줘서 지속 반복
					}
				}

			}

		}
		// 비교가 끝났으면 안전 구역 확인해야하는데 메서드를 제작해서 관리
		safeZone(virus_map);
	}

	static void safeZone(int[][] virus_map) {
		int cnt = 0;
		//System.out.println("debug safezone call");
		//System.out.println(Arrays.deepToString(virus_map));
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(virus_map[i][j] == 0) { //해당 구역이 안전 구역인 경우 카운트  
					cnt++;
				}
				
			}
		}
		safecnt = Math.max(cnt, safecnt);
	}



	static class virus {
		int x, y;

		virus(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}
