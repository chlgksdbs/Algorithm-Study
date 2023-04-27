import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.awt.Point;

public class BJ_16173 {
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};

    public static void bfs(int[][] graph, int x, int y, int graph_size) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (graph[p.x][p.y] == 0) { // 현재 좌표 값이 0인 경우
                continue;
            }

            for (int i = 0; i < 2; i++) {
                int nx = p.x + dx[i] * graph[p.x][p.y];
                int ny = p.y + dy[i] * graph[p.x][p.y];

                if (nx < 0 || nx >= graph_size || ny < 0 || ny >= graph_size) { // 그래프 범위를 벗어난 경우
                    continue;
                }

                if (graph[nx][ny] == -1) { // 끝점에 도달한 경우 출력문을 작성하고 함수 종료
                    System.out.println("HaruHaru");
                    return;
                }

                q.offer(new Point(nx, ny));
            }

        }

        System.out.println("Hing");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 게임 구역의 크
        int[][] graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        bfs(graph, 0, 0, n);
    }
}
