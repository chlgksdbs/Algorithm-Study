package day0202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11657 {
    static ArrayList<Edge> graph;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        //그래프 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 정점의 개수, 간선의 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.add(new Edge(v, w, cost));
        }

        //벨만-포드 알고리즘 수행
        BellmanFord(n, m, 1);
    }

    //정점의 개수, 간선의 개수, 출발지
    public static boolean BellmanFord(int n, int m, int start) {
        long[] dist = new long[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        //정점의 개수만큼 반복
        for (int i = 0; i < n; i++) {
            //간선의 개수만큼 반복
            for (int j = 0; j < m; j++) {
                Edge edge = graph.get(j); //현재 간선

                //현재 간선의 들어오는 정점에 대해 비교
                if (dist[edge.v] != INF && dist[edge.w] > dist[edge.v] + edge.cost) {
                    dist[edge.w] = dist[edge.v] + edge.cost;
                }
            }
        }

        //음수 가중치 확인
        for (int i = 0; i < m; i++) {
            Edge edge = graph.get(i); //현재 간선

            //현재 간선의 들어오는 정점에 대해 비교 -> 더 작은 값 생기면 음수 사이클 존재
            if (dist[edge.v] != INF && dist[edge.w] > dist[edge.v] + edge.cost) {
                //System.out.println("음수 사이클 존재");
                System.out.println(-1);
                return false;
            }
        }

        //출력
        for (int i = 2; i < dist.length; i++) {
            if (dist[i] == INF)
                System.out.println("-1");
            else
                System.out.println(dist[i]);
        }

        return true;
    }
}

class Edge {
    int v; // 나가는 정점
    int w; // 들어오는 정점
    int cost;

    public Edge(int v, int w, int cost) {
        this.v = v;
        this.w = w;
        this.cost = cost;
    }
}