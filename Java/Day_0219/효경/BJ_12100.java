package day0219;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_12100 {
    static int N;
    static int[][] arr;
    static int[] result;
    static  int[][] copyArr;
    static int[][] tmp;
    static Stack<Integer> stack = new Stack<>();
    static int maxBlock = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         N = Integer.parseInt(br.readLine());

        arr= new int[N][N];
        StringTokenizer st;
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = new int[5];
        tmp = new int[N][N];

        powerSet(0);

        System.out.println(maxBlock);
    }

    private static void powerSet(int cnt) {

        if(cnt == 5){

            copyArr = deepCopy(arr);
            int blockNum = func();
            if(maxBlock<blockNum)
                maxBlock = blockNum;
            return;
        }

        for(int i=1;i<=4;i++){
            result[cnt] = i;
            powerSet(cnt+1);
        }
    }

    private static int[][] deepCopy(int[][] array) {
        int[][] copyArray = new int[N][N];
        for(int i=0;i<N;i++){
            copyArray[i] = Arrays.copyOf(array[i], N);
        }
        return copyArray;
    }

    private static int func() {

        for(int i=0;i<5;i++){

            int direction = result[i];

            if(direction==1){
                rotateRightArr();
                pushRight();
                rotateLeftArr();
            }else if(direction==2){
                pushRight();
            }else if(direction==3){
                rotateLeftArr();
                pushRight();
                rotateRightArr();
            }else if(direction==4){
                rotateRightArr();
                rotateRightArr();
                pushRight();
                rotateLeftArr();
                rotateLeftArr();
            }
        } //5번 끝

        int max =Integer.MIN_VALUE;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
               if(max <copyArr[i][j]) max = copyArr[i][j];
            }
        }
        return max;

    }

    //시계 방향
    private static void rotateRightArr() {
        //int[][] tmp = new int[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                tmp[j][N-1-i] = copyArr[i][j];
            }
        }
        copyArr = deepCopy(tmp);
    }

    //반시계 방향
    private static void rotateLeftArr() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                tmp[N-1-j][i] = copyArr[i][j];
            }
        }
        copyArr = deepCopy(tmp);
    }


    private static void pushRight(){

        for(int i=0;i<N;i++){

            //뺴고, 밀어서
            boolean combine =false;
            for(int j=N-1;j>=0;j--){
                if(copyArr[i][j]==0) continue;

                if(!stack.isEmpty()&& stack.peek() == copyArr[i][j] && !combine){
                    stack.pop();
                    stack.push(copyArr[i][j]*2);
                    combine=true;
                }else{
                    stack.push(copyArr[i][j]);
                    combine =false;
                }
            }

            //다시 넣어
            for(int j=0;j<N;j++){
                if(j<N-stack.size()){
                    copyArr[i][j]=0;
                }else{
                    copyArr[i][j]=stack.pop();
                }
            }
        }
    }

}
