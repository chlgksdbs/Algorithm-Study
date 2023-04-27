package LCA;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_3176_도로네크워크 {
    static int[] depth;
    static int[][] parent; //
    private static class node{ // 클래스 생성
        int y, weight;

        public node( int y, int weight) {

            this.y = y;
            this.weight = weight;
        }
    }
    static int N,M,K;

    static ArrayList[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        tree = new ArrayList[N +1] ;
        N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<node>();
        }


        for (int i = 0; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            tree[x].add(new node(y, weight));
            tree[y].add(new node(x, weight));
        }


            dfs(1, 1);

            // 3. 2^N 까지 parent 채우기
            fillParent();

            // 4. LCA 진행
            M = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            for (int i=1; i<=M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                sb.append(lca(a,b)+"\n");
            }

            bw.write(sb.toString());

            bw.flush();
            bw.close();
            br.close();
        }

        // DEPTH 확인 DFS
        static void dfs(int id, int cnt) {
            // 1. depth를 기록
            depth[id] = cnt;

            // 2. 자식들의 depth를 기록
            int len = tree[id].size();
            for (int i = 0; i < len; i++) {
                int next = (Integer)tree[id].get(i);
                // 아직 깊이를 모르면 → 미방문 노드
                if (depth[next] == 0) {
                    dfs(next, cnt+1);
                    parent[0][next] = id;		// 첫번째 부모를 기록
                }
            }
            return;
        }

        // 부모 채우기
        static void fillParent() {
            for (int i = 1; i<K; i++) {
                for (int j = 1; j<=N; j++) {
                    parent[i][j] = parent[i-1][parent[i-1][j]];
                }
            }
        }

        // 최소공통조상
        static int lca(int a, int b) {
            // 1. depth[a] >= depth[b] 이도록 조정하기
            if (depth[a] < depth[b]) {
                int tmp = a;
                a = b;
                b = tmp;
            }

            // 2. 더 깊은 a를 2^K승 점프하여 depth를 맞추기
            for (int i = K-1; i>=0; i--) {
                if (Math.pow(2, i) <= depth[a] - depth[b]) {
                    a = parent[i][a];
                }
            }

            // 3. depth를 맞췄는데 같다면 종료
            if (a == b) return a;

            // 4. a-b는 같은 depth이므로 2^K승 점프하며 공통부모 바로 아래까지 올리기
            for (int i = K-1; i >= 0; i--) {
                if (parent[i][a] != parent[i][b]) {
                    a = parent[i][a];
                    b = parent[i][b];
                }
            }
            return parent[0][a];
        }
    }