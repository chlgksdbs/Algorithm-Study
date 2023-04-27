package baekjoon.algorithm.study;

import java.util.Scanner;

public class BOJ_2004 {
	
	// 팩토리얼에서 5의 승수를 구하는 공식
	// 5부터는 5가 1개이므로 10의 배수가 한 개씩 나타남
	// 25부터는 5가 2개이므로 10의 배수가 두 개씩 나타남
	// 125부터는 5가 3개이므로 10의 배수가 세 개씩 나타남
	
	public static long five_power(long num) {
		long cnt = 0;
		while (num >= 5) {
			cnt += num / 5;
			num /= 5;
		}
		
		return cnt;
	}
	
	// 팩토리얼에서 2의 승수를 구하는 공식
	public static long two_power(long num) {
		long cnt = 0;
		while (num >= 2) {
			cnt += num / 2;
			num /= 2;
		}
		
		return cnt;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long n = sc.nextLong();
		long m = sc.nextLong();
		
		// idea : nCm = n! / {(n - m)! * m!} 이다.
		// n! / {(n - m)! * m!} 에서 다른 수를 제외하고 2와 5의 승수만 확인해보자.
		// (2^a1 * 5^a1) / {(2^a2 * 5^a2) * (2^a3 * 5^a3)} 와 같이 나타낼 수 있고,
		// 이는 2^(a1 - a2 - a3) * 5^(a1 - a2 - a3)로 나타낼 수 있다.
		// 따라서 n, n-m, m에 대해 2의 승수와 5의 승수를 구한다.
		// 또 0의 개수는 10의 제곱 수와 같은 데, 10은 2와 5로 이루어져 있다.
		// 따라서 위에서 구한 2와 5의 승수 중, 최솟값을 출력하면 끝자리 0의 개수가 된다.
		
		long count5 = five_power(n) - five_power(n - m) - five_power(m);
		long count2 = two_power(n) - two_power(n - m) - two_power(m);
		
		System.out.println(Math.min(count5, count2));
	}
}
