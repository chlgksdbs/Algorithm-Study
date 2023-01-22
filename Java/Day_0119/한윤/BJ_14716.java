import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.awt.Point;

public class BJ_14716 {
    static int m;
    static int n;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

    public static int bfs(int x, int y) {
        Queue<Point> q = new LinkedList<Point>();
        q.offer(new Point(x, y));
        visited[x][y] = true; // 현재 위치 방문 처리

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 8; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                // nx, ny가 범위를 벗어났거나 해당 위치가 방문 처리 된 경우, 또는 값이 0인 경우 continue
                if (nx < 0 || ny < 0 || nx >= m || ny >= n || visited[nx][ny] || graph[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                q.offer(new Point(nx, ny));
            }
        }

        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int count = 0; // 글자의 갯수를 세는 변수

        graph = new int[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st2.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 1 && !visited[i][j]) {
                    count += bfs(i, j);
                }
            }
        }

        System.out.println(count);
    }
}
