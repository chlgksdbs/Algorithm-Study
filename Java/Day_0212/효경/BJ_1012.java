package day0213;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1012 {


    static int M;
    static int N;

    static int k;

    static boolean[][] arr;


    public static void main(String[] args) throws Exception{
    	
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        for(int t=1;t<=tc;t++){

            StringTokenizer st;

            st = new StringTokenizer(br.readLine());
            
            M = Integer.parseInt(st.nextToken());
            N= Integer.parseInt(st.nextToken());
            k= Integer.parseInt(st.nextToken());

            arr= new boolean[M][N];

            for(int i=0;i<k;i++){
                st = new StringTokenizer(br.readLine());
                arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]=true;
            }


            System.out.println(Arrays.deepToString(arr));


            int count=0;
            for(int i=0;i<M;i++){
                for(int j=0;j<N;j++){
                    if(!arr[i][j]) continue;

                    BFS(i,j);
                    count++;
                }
            }

            sb.append(count);

        }
        System.out.println(sb);
    }

    static int[] dx = {0,-1,0,1};
    static int[] dy={-1,0,1,0};


    static void BFS(int i,int j){

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i,j});

        while (!q.isEmpty()){

            int[] now = q.poll();
            arr[now[0]][now[1]] = false;

            for(int l=0;l<4;l++){
                int x = now[0]+dx[l];
                int y= now[1]+dy[l];

                if(x>=0 && x<M && y>=0 && y<N && arr[x][y]) {
                    q.offer(new int[]{x,y});
                }
            }
            
        }
    }
}

