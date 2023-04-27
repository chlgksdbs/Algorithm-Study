import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Pair {
	int x;
	int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int n, m;
	static int[][] graph, tmpgraph;
	static ArrayList<Pair> cameras = new ArrayList<>();
	static int answer = Integer.MAX_VALUE;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new int[n][m];
		tmpgraph = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (1 <= graph[i][j] && graph[i][j] <= 5) {
					cameras.add(new Pair(i, j));
				}
				tmpgraph[i][j] = graph[i][j];
			}
		}

		int l = cameras.size();
		for (int bit = 0; bit < (1 << (2 * l)); bit++) {
			int k = bit;
			for(int i = 0 ; i < l ; i++) {
				int dir = k & 3;
				draw(cameras.get(i),dir);
				k = k >> 2;
			}
			int tmp = 0;
			for(int i = 0 ; i < n ; i++) {
				for(int j = 0 ; j < m ; j++) {
					if(graph[i][j] == 0) tmp++;
				}
			}
			answer = Math.min(answer, tmp);
			recover();
		}
		
		System.out.println(answer);

	}
	
	private static void draw(Pair pair, int dir) {
		int cam = graph[pair.x][pair.y];
		int x = pair.x;
		int y = pair.y;
		int tmpx = x;
		int tmpy = y;
		switch (cam) {
		case 1:
			while(valid(x,y)) {
				if(graph[x][y] == 0) graph[x][y] =  -1;
				x = x + dx[dir];
				y = y + dy[dir];
			}
			break;
		case 2:
			while(valid(x,y)) {
				if(graph[x][y] == 0) graph[x][y] = -1;
				x = x + dx[dir];
				y = y + dy[dir];
			}
			
			x = tmpx;
			y = tmpy;
			int dir2 = (dir+2) % 4;
			while(valid(x,y)) {
				if(graph[x][y] == 0) graph[x][y] = -1;
				x = x + dx[dir2];
				y = y + dy[dir2];
			}
			break;
		case 3:
			while(valid(x,y)) {
				if(graph[x][y] == 0) graph[x][y] = -1;
				x = x + dx[dir];
				y = y + dy[dir];
			}
			
			x = tmpx;
			y = tmpy;
			int dir3 = (dir+3) % 4;
			while(valid(x,y)) {
				if(graph[x][y] == 0) graph[x][y] = -1;
				x = x + dx[dir3];
				y = y + dy[dir3];
			}
			break;
		case 4:
			while(valid(x,y)) {
				if(graph[x][y] == 0) graph[x][y] = -1;
				x = x + dx[dir];
				y = y + dy[dir];
			}
			
			x = tmpx;
			y = tmpy;
			int dir4 = (dir+3)%4;
			while(valid(x,y)) {
				if(graph[x][y] == 0) graph[x][y] = -1;
				x = x + dx[dir4];
				y = y + dy[dir4];
			}
			
			x = tmpx;
			y = tmpy;
			int dir5 = (dir+2)%4;
			while(valid(x,y)) {
				if(graph[x][y] == 0) graph[x][y] = -1;
				x = x + dx[dir5];
				y = y + dy[dir5];
			}
			break;
		case 5:
			while(valid(x,y)) {
				if(graph[x][y] == 0) graph[x][y] = -1;
				x = x + dx[dir];
				y = y + dy[dir];
			}
			
			x = tmpx;
			y = tmpy;
			int dir6 = (dir+3)%4;
			while(valid(x,y)) {
				if(graph[x][y] == 0) graph[x][y] = -1;
				x = x + dx[dir6];
				y = y + dy[dir6];
			}
			
			x = tmpx;
			y = tmpy;
			int dir7 = (dir+2)%4;
			while(valid(x,y)) {
				if(graph[x][y] == 0) graph[x][y] = -1;
				x = x + dx[dir7];
				y = y + dy[dir7];
			}
			
			x = tmpx;
			y = tmpy;
			int dir8 = (dir+1)%4;
			while(valid(x,y)) {
				if(graph[x][y] == 0) graph[x][y] = -1;
				x = x + dx[dir8];
				y = y + dy[dir8];
			}
			break;


		}
	}

	private static void recover() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				graph[i][j] = tmpgraph[i][j];
			}
		}
	}
	
	private static boolean valid(int x, int y) {
		if(0 <= x && x < n && 0<= y && y < m && graph[x][y] != 6)
			return true;
		return false;
	}

}