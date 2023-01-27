package day0127;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1149 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][3];
		int[][] rgb_sum = new int[N][3];
		
		// 입력부분
		for(int i=0; i<N; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				arr[i][j] = Integer.parseInt(st2.nextToken());
			}
		}
		
		// dp테이블 1행 값은 고정
		for(int k=0; k<3; k++) {
			rgb_sum[0][k] = arr[0][k];			
		}
		
		// dp테이블 RGB각 열인 경우의 누적 최솟값 저장
		for(int i=1; i<N; i++) {
			for(int j=0; j<3; j++) {
				if(j == 0) rgb_sum[i][j] = arr[i][j] + Math.min(rgb_sum[i-1][1], rgb_sum[i-1][2]);
				if(j == 1) rgb_sum[i][j] = arr[i][j] + Math.min(rgb_sum[i-1][0], rgb_sum[i-1][2]);
				if(j == 2) rgb_sum[i][j] = arr[i][j] + Math.min(rgb_sum[i-1][0], rgb_sum[i-1][1]);
			}
		}
		
		// 마지막행 = 누적 값 중 최솟값 출력
		System.out.println(Math.min(rgb_sum[N-1][0], Math.min(rgb_sum[N-1][1], rgb_sum[N-1][2])));
	}
}
