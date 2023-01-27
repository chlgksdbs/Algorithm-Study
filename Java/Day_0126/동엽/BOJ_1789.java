package day0127;

import java.util.Scanner;

public class BOJ_1789 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		long s = sc.nextLong();
		
		long start = 1;
		long end = Integer.MAX_VALUE;
		while(start <= end) {
			long mid = (start+end)/2;
			long calc = ((long) mid)*(mid+1)/2;
			if(calc > s) {
				end = mid-1;
			}
			else {
				start = mid+1;
			}
		}
		System.out.println(end);

	}

}
