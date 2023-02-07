package day0207;

import java.util.Scanner;

public class BOJ_2004 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int uppercount2 = calc(n, 2) - calc(n - m, 2);
		int uppercount5 = calc(n, 5) - calc(n - m, 5);
		int lowercount2 = calc(m, 2);
		int lowercount5 = calc(m, 5);
		System.out.println(uppercount2 - lowercount2 < uppercount5 - lowercount5 ? uppercount2-lowercount2 : uppercount5 - lowercount5);

	}

	static int calc(int base, int div) {
		assert(div != 0);
		int count = 0;
		long thisnum = div;
		while (thisnum <= (long) base) {
			count += (base / thisnum);
			thisnum *= div;
		}

		return count;
	}

}
