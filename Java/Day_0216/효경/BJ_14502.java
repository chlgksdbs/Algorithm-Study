package study.day0216;

import java.util.*;

public class BJ_14502 {

static int N;
static int M;

static int[][] arr;
static ArrayList<Point> zero;
static ArrayList<Point> two;
static Point[] resultComb;
static boolean[][] visit;
static int max = Integer.MIN_VALUE;

static int combCount=0;
static class Point{
    int x,y;
    Point( int x, int y){
        this.x=x;
        this.y=y;
    }

    @Override
    public String toString() {
        return "["+x+", "+y+"]";
    }
}

public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);
    N= scan.nextInt();
    M= scan.nextInt();
    arr = new int[N][M];
    zero = new ArrayList<>();
    two = new ArrayList<>();

    for(int i=0;i<N;i++){
        for(int j=0;j<M;j++){
            arr[i][j] = scan.nextInt();
            if(arr[i][j]== 0) zero.add(new Point(i,j));
            else if(arr[i][j] == 2) two.add(new Point(i,j));
        }
    }

    resultComb = new Point[3];
    combination(0,0);

   // System.out.println(combCount);
    System.out.println(max);

}

    private static void combination(int cnt, int start) {

        if(cnt ==3){

          //  combCount++;
          //  System.out.println(Arrays.toString(resultComb));

            for(int i=0;i<3;i++){
                arr[resultComb[i].x][resultComb[i].y] =1;
            }
            visit = new boolean[N][M];
            bfs();

            int safeCount = zeroCount();
           // System.out.println(safeCount);
           // printArr();

            if(safeCount >max)
                max = safeCount;

            for(int i=0;i<3;i++){
                arr[resultComb[i].x][resultComb[i].y] =0;
            }
            return;
        }

        for(int i=start;i<zero.size();i++){
            resultComb[cnt] = zero.get(i);
            combination(cnt+1, i+1);
        }
    }

    private static void printArr(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(visit[i][j]) System.out.print("# " );
                else System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int zeroCount() {

        int count=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(!visit[i][j] && arr[i][j]==0) count++;
            }
        }
        return count;
    }

    //상오하왼 탐색
    static int[] dx ={-1,0,1,0};
    static int[] dy={0,1,0,-1};

    private static void bfs(){

        Queue<Point> q = new LinkedList<>();
        for(int i=0;i< two.size();i++){
            q.offer(two.get(i));
            visit[two.get(i).x][two.get(i).y]=true;
        }

        while(!q.isEmpty()){

            Point now= q.poll();

            for(int i=0;i<4;i++){
                int x = now.x+dx[i];
                int y = now.y+dy[i];

                if(x>=0 && x<N && y>=0 && y<M && !visit[x][y] && arr[x][y]==0){
                    q.add(new Point(x,y));
                    visit[x][y]=true;

                }
            }

        }

    }
}