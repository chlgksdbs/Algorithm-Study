package day0126;

import java.util.Scanner;

public class BOJ_11726 {
	public static void main(String[] args) {

        Scanner scan  = new Scanner(System.in);

        int n = scan.nextInt();

        long[] D = new long[1001];

        D[1]=1;
        D[2] = 2;

        long mod = 10007;
        for(int i=3;i<=n;i++){
            D[i] =(D[i-1]+D[i-2])%mod;
        }

        System.out.println(D[n]);
    }
}
