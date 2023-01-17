

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_1260 {
    static int N;

    static boolean[] isVisit;
    static boolean[][] line;

    static void dfs(int V){
        isVisit[V] = true;
        System.out.print(V + " ");

        for(int i = 1; i <= N; i++){
            if(line[V][i] == true && !isVisit[i]){
                
                dfs(i);
            }
                
        }
    }

    static void bfs(int V){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(V);
        isVisit[V] = true;

        while(true){
            if(queue.isEmpty()){
                break;
            }
            int point = queue.poll();
            System.out.print(point + " ");
            for(int i = 1; i <= N; i++){
                if(line[point][i] == true && !isVisit[i]){
                    isVisit[i] = true;
                    queue.add(i);
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        int M = sc.nextInt();

        int V = sc.nextInt();

        isVisit = new boolean[N+1];

        line = new boolean[N+1][N+1];

        //간선 정보
        for(int i = 0 ; i < M; i++){
            int point1 = sc.nextInt();
            int point2 = sc.nextInt();

            line[point1][point2] = true;
            line[point2][point1] = true;
        }

        dfs(V);

        System.out.println();

        for(int i = 0; i <= N; i++){
            isVisit[i] = false;
        }

        bfs(V);

        sc.close();
    }
}