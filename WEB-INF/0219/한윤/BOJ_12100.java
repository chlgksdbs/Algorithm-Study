package baekjoon;

import java.util.Scanner;

public class BOJ_12100 {

	static int N; // 보드의 크기
	static int ans; // 만들 수 있는 가장 큰 블록의 값 (정답)
	
	static int[] selected; // 회전 방향의 정보를 담은 배열
	
	static int[][] gameBoard; // 게임판의 초기 상태
	
	// 현재 보드판의 모습을 출력하는 함수
	public static void print(int[][] board) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	// 게임판(2차원 배열)를 deep copy 해주는 함수
	public static int[][] deepcopy(int[][] map) {
		
		int[][] copyMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		
		return copyMap;
	}
	
	// 현재 보드판에서의 최댓값을 찾는 함수
	public static int maxSearch(int[][] board) {
		int tmp = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (tmp < board[i][j]) tmp = board[i][j];
			}
		}
		return tmp;
	}
	
	// 게임판을 이동하는 함수 (모든 이동 작업을 위로 진행)
	public static void moveBoard(int[][] board) {
		for (int j = 0; j < N; j++) {
			boolean flag = false; // 합치기 작업이 발생했다면 true, 아니면 false
			for (int i = 1; i < N; i++) {
				
				if (board[i][j] == 0) continue; // 현재 값이 0이면 수행할 필요 X -> 이 조건 없으면 11%에서 탈락, 왜 Why???!!!
				
				int idx = i;
				
				// 이전에 합치기 작업이 이루어진 경우는 값만 밀기
				if (flag) {
					flag = false;
					
					while (idx > 0) {
						if (board[idx - 1][j] != 0) break;
						else {
							board[idx - 1][j] = board[idx][j];
							board[idx][j] = 0; // 빈 칸으로 덮어씌우기
							idx--;
						}
					}					
				}
				// 이전에 합치기 작업이 이루어지지 않은 경우에는 같은 값을 발견했을 때 합치기 작업 수행
				else {
					while (idx > 0) {
						// 배열의 윗 값이 0이 아니라면 같은 값인지 확인하여 계산 후 반복문 탈출
						if (board[idx - 1][j] != 0) {
							// 같은 값인 경우 합치기 작업
							if (board[idx - 1][j] == board[idx][j]) {
								board[idx - 1][j] *= 2;
								board[idx][j] = 0;
								flag = true;
							}
							break;
						}
						// 배열의 윗 값이 0이라면 계속 밀기
						else {
							board[idx - 1][j] = board[idx][j];
							board[idx][j] = 0; // 빈 칸으로 덮어씌우기
							idx--;
						}
					}
				}
			}
		}
	}
	
	// 시계 방향 회전
	public static void forwardRotateBoard(int[][] board) {
		int[][] tmp = deepcopy(board);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = tmp[N - 1 - j][i];
			}
		}		
	}
	
	// 반시계 방향 회전
	public static void reverseRotateBoard(int[][] board) {
		int[][] tmp = deepcopy(board);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = tmp[j][N - 1 - i];
			}
		}				
	}
	
	// 한 번의 이동마다 해야하는 작업 (이동 작업 수행 전, 회전 작업 수행)
	public static void playBoard(int[][] board, int dir) {
		
		// dir 크기 만큼 회전 (시계 방향)
		for (int i = 0; i < dir; i++) {
			forwardRotateBoard(board);
		}
		
		moveBoard(board);
		
		// dir 크기 만큼 회전 (반시계 방향) -> 원상태
		for (int i = 0; i < dir; i++) {
			reverseRotateBoard(board);
		}
	}
	
	// 5번의 움직임에 대한 정보를 가지고 게임을 진행하는 함수
	public static int playgame() {
		
		int maxValue = 0;
		int[][] copyBoard = deepcopy(gameBoard); // 원본 배열의 깊은 복사
		// 움직임 연산 수행
		for (int i = 0; i < 5; i++) {
			playBoard(copyBoard, selected[i]);
		}
		
		maxValue = maxSearch(copyBoard); // 최댓값 찾기
		
		return maxValue;
	}
	
	// 게임판을 5번 이동하는 경우의 수는 중복 순열로 나타낼 수 있으며 (4^5), 이미 완성된 상태에서도 이동을 진행해도 최댓값에는 차이가 없기 때문에 모든 경우의 수 진행
	public static void perm(int cnt) {
		
		// 종료 조건 (5번의 이동 실행 후)
		if (cnt == 5) {
			int tmp = playgame();
			ans = Math.max(ans, tmp); // 최댓값 갱신
			return;
		}
		
		// 4^5 경우의 수
		for (int i = 0; i < 4; i++) {
			selected[cnt] = i;
			perm(cnt + 1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		selected = new int[5];
		gameBoard = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				gameBoard[i][j] = sc.nextInt();
			}
		} // input end
		
		perm(0);
		
		System.out.println(ans);
	}
}
