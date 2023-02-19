package BinarySearch;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class BOJ_3187_양치기꿍 {
    private static boolean[][] isVisited;
    private static char[][] map;
    private static int R;
    private static int C;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};
    private static int sheepResult = 0;
    private static int wolfResult = 0;

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt(); //행
        C = sc.nextInt(); //열
        map = new char[R][C];
        isVisited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String line = sc.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!isVisited[i][j] && map[i][j] != '#') {
                    bfs(new Point(i, j));
                }
            }
        }
        System.out.println(sheepResult + " " + wolfResult);
    }

    private static void bfs(Point point) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(point);
        isVisited[point.r][point.c] = true;
        int wolfCnt = 0;
        int sheepCnt = 0;
        while (!queue.isEmpty()) {
            Point point2 = queue.poll();
            int r = point2.r;
            int c = point2.c;
            if (map[r][c] == 'k') {
                sheepCnt++;
            } else if (map[r][c] == 'v') {
                wolfCnt++;
            }
            for (int i = 0; i < 4; i++) {
                int r2 = point2.r + dx[i];
                int c2 = point2.c + dy[i];
                if ((r2 > 0 && r2 < R && c2 > 0 && c2 < C) && !isVisited[r2][c2] && map[r2][c2] != '#') {
                    isVisited[r2][c2] = true;
                    queue.offer(new Point(r2, c2));
                }
            }
        }
        if (sheepCnt > wolfCnt) {
            sheepResult += sheepCnt;
        } else {
            wolfResult += wolfCnt;
        }
    }

    static class Point {
        int r;
        int c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}