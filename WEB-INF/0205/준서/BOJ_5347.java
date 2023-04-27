package Day_0205;

import java.util.Scanner;

public class BOJ_5347 {


	public static long GCD(long a, long b) {
		long temp;
		if (a < b) {
			temp = b;
			b = a;
			a = temp;
		}
		if (b == 0) {
			return a;
		}
		return GCD(b, a % b);

	}
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		long  n = sc.nextInt();
		for(long i = 0; i < n ; i++) {
			long a = sc.nextInt();
			long b = sc.nextInt();
			
			long gcd = GCD(a,b);
			
			System.out.println((a * b) / gcd);
			
		}
		
		
	}

}
