package algorithm;

import java.util.Scanner;

public class BOJ_5347 {
	public static long gcd(long x, long y) { // 최대 공약수를 구하는 함수
		if (y == 0) return x;
		
		return gcd(y, x % y);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int i = 0; i < T; i++) { // 테스트 케이스에 대한 부분
			long a = sc.nextInt();
			long b = sc.nextInt();
			
			long tmp = gcd(a, b);
			System.out.println(a * b / tmp);
		}
	}
}
