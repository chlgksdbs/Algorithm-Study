// BOJ_1789 : 수들의 합
// 입력 범위에 대해 유심히 보고, int형으로 받을 지, long형으로 받을 지 확인해야한다.

package algorithm;

import java.util.Scanner;

public class BOJ_1789 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long x = sc.nextLong(); // 자연수 x (서로 다른 n개의 자연수의 합)
		long xSum = 0; // 1 ~ 특정 수 까지의 합
		
		long numValue = 1;
		
		while (true) {
			// xSum과 numValue의 합이 x(목표 값)보다 큰 경우 반복문 탈출 
			if (xSum + numValue > x) {
				numValue--;
				break;
			}
			// 위의 조건문에 걸리지 않은 경우, xSum에 값을 누적시키고 numValue 값 1 증가
			xSum += numValue;
			numValue++;
		}
		
		// 자연수 n의 최댓값 출력
		System.out.println(numValue);
	}
}
