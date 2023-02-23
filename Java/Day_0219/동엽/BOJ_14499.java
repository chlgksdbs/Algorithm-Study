import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Dice {
	Point p;
	int front;
	int rear;
	int up;
	int down;
	int left;
	int right;

	public Dice(Point p) {
		this.p = p;
		front = 0;
		rear = 0;
		up = 0;
		down = 0;
		left = 0;
		right = 0;
	}

	public int roll(int i) {
		int tmp;
		switch (i) {
		case 1:
			tmp = this.down;
			this.down = this.right;
			this.right = this.up;
			this.up = this.left;
			this.left = tmp;
			break;
		case 2:
			tmp = this.down;
			this.down = this.left;
			this.left = this.up;
			this.up = this.right;
			this.right = tmp;
			break;
		case 3:
			tmp = this.down;
			this.down = this.rear;
			this.rear = this.up;
			this.up = this.front;
			this.front = tmp;
			break;
		case 4:
			tmp = this.down;
			this.down = this.front;
			this.front = this.up;
			this.up = this.rear;
			this.rear = tmp;
			break;
		}
		return up;
	}
}

public class Main {
	static int n, m;
	static int[] dx = { -1, 0, 0, -1, 1 };
	static int[] dy = { -1, 1, -1, 0, 0 };
	static int[][] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new int[n][m];
		Dice d = new Dice(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		int T = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int dir = Integer.parseInt(st.nextToken());
			
			int newx = d.p.x + dx[dir];
			int newy = d.p.y + dy[dir];
			if(0<=newx && newx < n && 0<= newy && newy < m) {
				System.out.println(d.roll(dir));
				d.p = new Point(newx,newy);
				if(graph[newx][newy] == 0) graph[newx][newy] = d.down;
				else {
					d.down = graph[newx][newy];
					graph[newx][newy] = 0;
				}
			}
			
		}

	}

}