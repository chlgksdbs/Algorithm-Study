import java.util.*;
import java.awt.Point;

public class BJ_2178 {
    public static int n;
    public static int m;
    public static boolean[][] visited;
    public static int[][] graph;

    public static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<Point>();

        q.offer(new Point(x, y));
        visited[x][y] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (q.size() > 0) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if (graph[nx][ny] == 1 && visited[nx][ny] == false) {
                    visited[nx][ny] = true;
                    graph[nx][ny] = graph[p.x][p.y] + 1;
                    q.offer(new Point(nx, ny));
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); // 세로
        m = sc.nextInt(); // 가로

        sc.nextLine(); // 버퍼 비우기

        visited = new boolean[n][m];
        graph = new int[n][m];

        for(int i = 0; i < n; i++) { // 입력
            String temp = sc.nextLine();
            for(int j = 0; j < m; j++) {
                graph[i][j] = temp.charAt(j) - '0';
            }
        }

        bfs(0, 0);

        System.out.println(graph[n - 1][m - 1]);
    }
}
