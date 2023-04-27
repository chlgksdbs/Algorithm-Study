import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BJ_15683 {
    static int N;
    static int M;
    static int[][] arr;
    static ArrayList<Point> cctv;
    static int[] direction;
    static boolean[][] visit;
    static int resultSetNum=0;

    static int min = Integer.MAX_VALUE;

    static class Point {
        int value, x, y;

        Point(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        N = scan.nextInt();
        M = scan.nextInt();
        arr = new int[N][M];
        cctv = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = scan.nextInt();
                if (arr[i][j] >= 1 && arr[i][j] <= 5)
                    cctv.add(new Point(arr[i][j], i, j));

            }
        }


        direction = new int[cctv.size()];
        subSet(0);
     //   System.out.println(resultSetNum);
        System.out.println(min);


    }

    private static void subSet(int cnt) {  //cnt는 자리

        if (cnt == cctv.size()) {
         //   resultSetNum++;
           // System.out.println(Arrays.toString(direction));

            int blindSpot = caculation();
          //  printArr();
            if(min > blindSpot)
                min = blindSpot;
            return;
        }

        int cctvNum = cctv.get(cnt).value;

        if (cctvNum == 1 || cctvNum == 3 || cctvNum == 4) {
            for (int i = 1; i <= 4; i++) {
                direction[cnt] = i;
                subSet(cnt + 1);
            }
        } else if (cctvNum == 2) {
            for (int i = 1; i <= 2; i++) {
                direction[cnt] = i;
                subSet(cnt + 1);
            }
        } else if (cctvNum == 5) {
            direction[cnt] = 1;
            subSet(cnt + 1);
        }

    }

    private static int caculation() {

        visit = new boolean[N][M];
        for (int i = 0; i < direction.length; i++) {
            if (cctv.get(i).value == 1)
                one(direction[i], cctv.get(i).x, cctv.get(i).y);
            else if (cctv.get(i).value == 2)
                two(direction[i], cctv.get(i).x, cctv.get(i).y);
            else if (cctv.get(i).value == 3)
                three(direction[i], cctv.get(i).x, cctv.get(i).y);
            else if (cctv.get(i).value == 4)
                four(direction[i], cctv.get(i).x, cctv.get(i).y);
            else
                five(direction[i], cctv.get(i).x, cctv.get(i).y);
        }

        int count=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(!visit[i][j] && arr[i][j]==0) count++;
            }
        }
        //System.out.println(count);
        return count;

    }

    private static void one(int direcNum, int x, int y) {

        if (direcNum == 1) { //상
            for (int i = x - 1; i >= 0; i--) {
                if (arr[i][y] == 6) break;
                else if (arr[i][y] == 0) visit[i][y] = true;
            }
        } else if (direcNum == 2) { //우
            for (int j = y + 1; j < M; j++) {
                if (arr[x][j] == 6) break;
                else if (arr[x][j] == 0) visit[x][j] = true;
            }
        } else if (direcNum == 3) { //하
            for (int i = x + 1; i < N; i++) {
                if (arr[i][y] == 6) break;
                else if (arr[i][y] == 0) visit[i][y] = true;
            }
        } else if (direcNum == 4) { //좌
            for (int j = y - 1; j >= 0; j--) {
                if (arr[x][j] == 6) break;
                else if (arr[x][j] == 0) visit[x][j] = true;
            }
        }
    }

    private static void two(int direcNum, int x, int y) {
        if (direcNum == 1) {
            one(1, x, y);//상
            one(3, x, y); //하
        } else if (direcNum == 2) {
            one(2, x, y);//우
            one(4, x, y); //좌
        }
    }

    private static void three(int direcNum, int x, int y) {
        if (direcNum == 1) { //상우
            one(1, x, y);
            one(2, x, y);
        } else if (direcNum == 2) { //우하
            one(2, x, y);
            one(3, x, y);
        } else if (direcNum == 3) { //하좌
            one(3, x, y);
            one(4, x, y);
        } else if (direcNum == 4) { //상좌
            one(1, x, y);
            one(4, x, y);
        }
    }

    private static void four(int direcNum, int x, int y) {
        if (direcNum == 1) { //좌상우
            one(4, x, y);
            one(1, x, y);
            one(2, x, y);
        } else if (direcNum == 2) { //상우하
            one(1, x, y);
            one(2, x, y);
            one(3, x, y);
        } else if (direcNum == 3) { //좌하우
            one(4, x, y);
            one(3, x, y);
            one(2, x, y);
        } else if (direcNum == 4) { //상좌하
            one(1, x, y);
            one(4, x, y);
            one(3, x, y);
        }
    }

    private static void five(int direcNum, int x, int y) {

        one(1, x, y);//상
        one(2, x, y); //하

        one(3, x, y);//우
        one(4, x, y); //좌

    }

    private static void printArr(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(visit[i][j]) System.out.print("# ");
                else System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
