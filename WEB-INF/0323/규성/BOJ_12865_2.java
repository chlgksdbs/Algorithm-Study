package m3.day0323;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_12865_2 {
	static ArrayList<Integer> list;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] m = new int[n+1];
		int[] c = new int[n+1];
		int min = Integer.MAX_VALUE;
		
		int cSum = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			c[i] = Integer.parseInt(st.nextToken());
			cSum+= c[i];
			
		}
		
		int[] dp = new int[cSum+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			m[i] = Integer.parseInt(st.nextToken());
			
		}

		for(int i = 1; i <= n; i++) {	//물품
			for(int j = cSum; j >0; j--) {	//무게
				if(c[i] < j) {
					dp[j] = Math.max(dp[j], m[i]+dp[j-c[i]]);
					if(dp[j] <= k && min > j) {
						min = j;
					}
				}
				
			}
		}

		
		System.out.println(min);
		
	}


}
