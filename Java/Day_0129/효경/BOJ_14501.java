package day0129;


import java.util.Scanner;

public class BOJ_14501 {

    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        int[] T = new int[n+1];
        int[] P = new int[n+1];

        int[] dp = new int[n+2];

        for(int i=1;i<=n;i++){
            T[i] = scan.nextInt();
            P[i] = scan.nextInt();
        }


        for(int i=n;i>0;i--){
            if(i+T[i]-1 >n)
                dp[i] = dp[i+1];

            else
                dp[i] = Math.max(P[i]+ dp[i+T[i]], dp[i+1]);
        }
        System.out.println(dp[1]);


    }
}
