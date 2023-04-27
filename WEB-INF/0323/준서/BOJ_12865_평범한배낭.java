package Knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865_평범한배낭 {

    static int N, K;
    static int W[];
    static int V[];

    static int dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        W = new int[N+1];
        V = new int[N+1];

        dp = new int[N+1][K+1];


        for (int i = 1; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }
        int ans = knapsack();
        System.out.println(ans);


    }

    private static int knapsack(){
        for (int i = 1; i <= N ; i++) {
            for (int j = 1; j <= K ; j++) {
                if(W[i] <= j){
                    dp[i][j]= Math.max(V[i] + dp[i-1][j - W[i]], dp[i-1][j]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }


        return dp[N][K];
    }


}
