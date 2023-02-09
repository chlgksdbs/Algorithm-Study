package day0129;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BOJ_1377 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[n][2];
		
		for(int i=0;i<n;i++) {
			arr[i][0] = i;//인덱스
			arr[i][1] =Integer.parseInt(br.readLine());//요소
		}
		
		  Arrays.sort(arr, new Comparator<int[]>() {
	            @Override
	            public int compare(int[] o1, int[] o2) {
	                return o1[1] - o2[1];
	            }
	        });
		  
		  
		  int max =0;
		  for(int i=0;i<n;i++) {
			  if(arr[i][0]-i >max) { //정렬 전 index- 정렬후 index(i)
				  max = arr[i][0]-i;
			  }
		  }
		  
		  System.out.println(max+1);
		
	}

}
