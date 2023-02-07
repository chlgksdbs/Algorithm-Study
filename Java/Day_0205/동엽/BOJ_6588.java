package day0207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_6588 {

	static boolean[] isPrime = new boolean[1000001];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n;

		for (int i = 2; i <= Math.sqrt(1000000); i++) { // 2부터 n의 제곱근까지의 모든 수 확인
			if (isPrime[i] == false) { // i가 소수면
				int j = 2; // i * 2, i * 3, ... i의 배수 지우기
				while (i * j <= 1000000) {
					isPrime[i * j] = true; // 지워진 배수 false로
					j += 1;
				}
			}
		}

		while ((n = Integer.parseInt(br.readLine())) != 0) {
			calc(n);
		}

	}

	static void calc(int n) {
		for (int i = 2; i < n; i++) {
			if (!isPrime[i] && !isPrime[n - i]) {
				System.out.println(n + " = " + i + " + " + (n - i));
				break;
			}
		}
	}

}
