import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static int cctvSize;
	static int minCnt = Integer.MAX_VALUE;

	static String[][] originArr;
	static String[][] copyArr;

	static int[] dx = { -1, 0, 1, 0 }; // 상, 좌, 하, 우
	static int[] dy = { 0, -1, 0, 1 };

	static ArrayList<Point> cctv = new ArrayList<>();

	public static void bfs(int x, int y, int dx, int dy, String[][] copyArr) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y)); // 처음 시작(cctv)좌표
		while (!q.isEmpty()) {
			Point p = q.poll();
			int nx = p.x + dx;
			int ny = p.y + dy;
			if (nx < 0 || ny < 0 || nx >= N || ny >= M || originArr[nx][ny].equals("6"))
				continue;
			if (copyArr[nx][ny].equals("0")) {
				q.add(new Point(nx, ny));
				copyArr[nx][ny] = "#";
			}else {
				q.add(new Point(nx, ny));
			}
		}
	}

	public static void comb(int idx, String[][] copyArr) {

		if (idx == cctvSize) {
			int cnt = 0;
			for (String[] r : copyArr) {
				for (String c : r) {
					if (c.equals("0")) {
						cnt++;
					}
				}
			}

			minCnt = Math.min(minCnt, cnt);
			return;
		}

		Point p = cctv.get(idx); // cctv좌표 get
		String[][] newCopyArr = new String[N][M];
		if (originArr[p.x][p.y].equals("1")) {

			for (int i = 0; i < 4; i++) {
				for (int a = 0; a < N; a++)
					newCopyArr[a] = copyArr[a].clone(); // 딥카피
				bfs(p.x, p.y, dx[i], dy[i], newCopyArr); // 상,하,좌,우 중 한번 수행하고
				comb(idx + 1, newCopyArr); // 다음 cctv 탐색
			}

		} else if (originArr[p.x][p.y].equals("2")) {

			for (int i = 0; i < 2; i++) {
				for (int a = 0; a < N; a++)
					newCopyArr[a] = copyArr[a].clone(); // 딥카피
				for (int j = 0; j < 4; j += 2) { // 상,하 또는 좌,우
					bfs(p.x, p.y, dx[i + j], dy[i + j], newCopyArr); // 한번 수행하고
				}
				comb(idx + 1, newCopyArr); // 다음 cctv 탐색
			}

		} else if (originArr[p.x][p.y].equals("3")) {

			for (int i = 0; i < 4; i++) {
				for (int a = 0; a < N; a++)
					newCopyArr[a] = copyArr[a].clone(); // 딥카피
				for (int j = 0; j < 2; j++) {
					bfs(p.x, p.y, dx[(i + j) % 4], dy[(i + j) % 4], newCopyArr);
				}
				comb(idx + 1, newCopyArr);
			}

		} else if (originArr[p.x][p.y].equals("4")) {
			for (int i = 0; i < 4; i++) { // 4가지 경우인 3방향 탐색
				for (int a = 0; a < N; a++)
					newCopyArr[a] = copyArr[a].clone(); // 딥카피
				for (int j = 0; j < 3; j++) {
					bfs(p.x, p.y, dx[(i + j) % 4], dy[(i + j) % 4], newCopyArr); // 3방향 경우1 수행하고
				}
				comb(idx + 1, newCopyArr); // 다음 cctv 탐색
			}

		} else if (originArr[p.x][p.y].equals("5")) {
			for (int a = 0; a < N; a++)
				newCopyArr[a] = copyArr[a].clone(); // 딥카피
			for (int i = 0; i < 4; i++) {
				bfs(p.x, p.y, dx[i], dy[i], newCopyArr); // 상,하,좌,우 탐색하고
			}
			comb(idx + 1, newCopyArr); // 다음 cctv 탐색
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 사무실 세로 크기
		M = Integer.parseInt(st.nextToken()); // 사무실 가로 크기

		originArr = new String[N][M];
		copyArr = new String[N][M];

		// 사무실 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				originArr[i][j] = st.nextToken();
				if (!originArr[i][j].equals("0") && !originArr[i][j].equals("6"))
					cctv.add(new Point(i, j)); // cctv 좌표 모음집
			}
		}
		// 깊은 배열 복사
		for (int i = 0; i < N; i++) {
			copyArr[i] = originArr[i].clone();
		}

		cctvSize = cctv.size();

		comb(0, copyArr);

		System.out.println(minCnt);
	}
}
