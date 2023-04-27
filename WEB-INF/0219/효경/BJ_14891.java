package day0219;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14891 {
    static char[][] gear;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        gear = new char[5][8];
        for(int i=1;i<=4;i++){
            gear[i] = br.readLine().toCharArray();
        }

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(st.nextToken());
            int roDirect = Integer.parseInt(st.nextToken());

            int[] comapre =  new int[4];

            //같은 방향이면 1, 다른 방향이면 2저장
            comapre[1] = gear[1][2] ==gear[2][6] ? 1 : 2;
            comapre[2] = gear[2][2] ==gear[3][6] ? 1 : 2;
            comapre[3] = gear[3][2] ==gear[4][6] ? 1 : 2;


            int[] direction = new int[5];
            if(gearNum==1) {
                direction[1] = roDirect;
                direction[2] = comapre[1] == 1 ? 0 : direction[1]*-1; //같은 방향이면 안움직임, 다른 방향이면 반대 방샹
                //앞이 0이면 나도 0, 앞이 움직이면 나도 움직임 같은 방향이면 0 ,
                direction[3] = direction[2] == 0  || comapre[2] == 1 ? 0 :direction[2]*-1;
                direction[4] = direction[3] == 0 || comapre[3] == 1 ? 0 : direction[3]*-1;
            }else if(gearNum==2){
                direction[2] = roDirect;
                direction[1] = comapre[1] == 1 ? 0 : direction[2]*-1;
                direction[3] = comapre[2] == 1 ? 0 : direction[2]*-1;
                direction[4] =  direction[3] == 0 ? 0 : comapre[3] == 1 ? 0 : direction[3]*-1;

            }else if(gearNum==3){
                direction[3] = roDirect;
                direction[2] = comapre[2] == 1 ? 0 :  direction[3]*-1;
                direction[4] = comapre[3] == 1 ? 0 :  direction[3]*-1;
                direction[1] = direction[2] == 0  || comapre[1] == 1 ? 0 : direction[2]*-1;

            }else{
                direction[4] = roDirect;
                direction[3] = comapre[3] == 1 ? 0 : direction[4]*-1;
                direction[2] = direction[3] == 0 ? 0 : comapre[2] == 1 ? 0 : direction[3]*-1;
                direction[1] = direction[2] == 0 ? 0 : comapre[1] == 1 ? 0 : direction[2]*-1;
            }

            for(int k=1;k<=4;k++){
                rotation(k, direction[k]);
            }
//            System.out.println(Arrays.toString(direction));
//            printArr();
        }

        System.out.println(sumScore());

    }

    static void rotation(int num, int rotateNum){
    //방향이 1이면 시계, -1이면 반시계

        if(rotateNum==1){
            char tmp = gear[num][7];

            for(int i=7;i>0;i--){
                gear[num][i] = gear[num][i-1];
            }
            gear[num][0] = tmp;
        } else if(rotateNum== -1){
            char tmp = gear[num][0];

            for(int i=0;i<7;i++){
                gear[num][i] = gear[num][i+1];
            }
            gear[num][7] = tmp;
        }
    }

    static int sumScore(){
        int sum=0;
        for(int i=1;i<=4;i++){
            if(gear[i][0] =='1')
                sum+= Math.pow(2,i-1);
        }
        return sum;

    }
}
