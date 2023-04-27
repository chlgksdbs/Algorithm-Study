package m3.day0323;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_12865 {
	static ArrayList<Integer> list;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] value = new int[n+1];
		int[] weigth = new int[n+1];
		int[][] dp = new int[n+1][k+1];
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			weigth[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
			
		}

//		System.out.println(Arrays.toString(value));
//		System.out.println(Arrays.toString(weigth));
		
		for(int i = 1; i <= n; i++) {	//물품
			for(int j = 1; j <= k; j++) {	//무게
				if(weigth[i] <= j) {
//					System.out.println(dp[i-1][j] + " " + (value[i]+dp[i-1][j-weigth[i]]));
					dp[i][j] = Math.max(dp[i-1][j], value[i]+dp[i-1][j-weigth[i]]);
				} else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
//		for(int i = 1; i <= n; i++) {
//			for(int j = 1 ; j <= k; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		
		System.out.println(dp[n][k]);
		
	}


}
