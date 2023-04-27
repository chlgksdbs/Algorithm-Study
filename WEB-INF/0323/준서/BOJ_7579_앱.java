package Knapsack;

import java.io.*;
import java.util.*;

public class BOJ_7579_앱 {
    static final int MAX_MEMORY = 10001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        StringTokenizer bites = new StringTokenizer(br.readLine());
        StringTokenizer costs = new StringTokenizer(br.readLine());
        int[] dp = new int[MAX_MEMORY];
        int index = 0;
        for (int n = 0; n < N; n++) {
            int bite = Integer.parseInt(bites.nextToken());
            int cost = Integer.parseInt(costs.nextToken());
            if (cost == 0) {
                M -= bite;
                continue;
            }
            for (int i = index; i > 0; i--) {
                if (dp[i] != 0) dp[i + cost] = Math.max(dp[i + cost], dp[i] + bite);
            }
            dp[cost] = Math.max(dp[cost], bite);
            index += cost;
        }
        for (int i = 0; i < MAX_MEMORY; i++) {
            if (dp[i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }
}