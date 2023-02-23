package day0219;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_17281 {
    static int N;
    static int[][] arr;
    static int[] tasoon;

    static int maxScore = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N+1][10];

        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=1;j<=9;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        tasoon = new int[10];
        tasoon[4] = 1;
        combination(1,2);
        System.out.println(maxScore);
    }

    private static void  combination(int cnt, int start){

        if(cnt ==4) combination(cnt+1, start);

        if(cnt == 10){
            //타순 완성

            System.out.println(Arrays.toString(tasoon));

            return;
//            int score = calScore();
//            if(score >maxScore){
//                maxScore = score;
//            }
//
//            return;
        }

        for(int i=start;i<=9;i++){
            tasoon[cnt] = i;
            combination(cnt+1, i+1);
        }

    }

    private static int calScore() {
        int nowtasuck = 1;
        int outCount=0;
        int score=0;

        //int[] baseNumber = new int[5]; //1번, 2번,3번, home

        for(int i=1;i<=N;i++) {
            int[] baseNumber = new int[5];

            while (outCount < 3) {

                int result = arr[i][tasoon[nowtasuck]];
                if (result==0){
                    outCount++;
                }else{
                    for(int k=1;k<=result;k++){
                        score+=oneStep(baseNumber);
                    }
                }


            }//아웃카운트 만큼 끝

        }//이닝의 수 만큼 끝

        return score;

    }
    private static int oneStep(int[] baseNumber){
        for(int i=3;i>=1;i--){
            baseNumber[i] = baseNumber[i+1];
        }
        baseNumber[1] = 0;
        if(baseNumber[4]>0){ //홈에 왔으면 1점 반환!!
            baseNumber[4]=0;
            return 1;
        }
        return 0;
    }
}
