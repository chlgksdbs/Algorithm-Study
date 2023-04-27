package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_6588 {
	
	static boolean[] primeNumber;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		primeNumber = new boolean[1000001]; // 1부터 100만까지의 수 중, 소수인 수만 false값을 가지는 배열
		
		// 에라토스테네스의 체를 이용하여 1부터 100만까지의 수에서 소수를 체크
		for (int i = 2; i <= 1000000; i++) {
			if (!primeNumber[i]) {
				int idx = 2;
				while (i * idx <= 1000000) {
					primeNumber[i * idx++] = true;
				}
			}
		}
		
		while (true) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0) break; // 종료 조건
			
			int a = -1;
			// 홀수만 체크하는 반복문
			for (int j = 3; j <= (n / 2); j+=2) {
				if (!primeNumber[j] && !primeNumber[n - j]) {
					a = j;
					break;
				}
			}
			
			if (a == -1) {
				System.out.println("Goldbach's conjecture is wrong.");
			}
			else {
				System.out.println(n + " = " + a + " + " + (n - a));
			}
		}
	}
}
