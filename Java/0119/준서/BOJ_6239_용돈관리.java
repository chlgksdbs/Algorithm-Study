package BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_6239_용돈관리 {
	static int arr[];
	static int ans;
	static int n, m;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt(); // 사용할 일 수
		m = sc.nextInt(); // 출금 가능 횟수

		arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

	
		binarySearch(arr, m);
	}

	public static void binarySearch(int arr[], int k) {
		int upper = 0;
		int lower = 1;
		for (int i = 0; i < arr.length; i++) {
			upper += arr[i];
			if (lower < arr[i]) lower = arr[i];
		} 

		
		int ans = 0;
		int cnt = 0;

		while (lower <= upper) {
			int mid = (lower + upper) / 2; // 1 ~ 총 잔액에서 움직이는 범위 설정 
			if (k >= passMoney(mid)) {
				ans = mid;
//				System.out.println(ans);
				upper = mid - 1;

			} else {
				lower = mid + 1;
			}

		}
		System.out.println(ans);
	}

	static int passMoney(int getMoney) {
		int cnt = 1; // 첫 인출 카운트 ㅋ
		int money = getMoney;
		for (int i : arr) {
			money -= i; // 돈 인출하기 
			// 돈 없으면 다시 인출하기
			if (money < 0) {
				cnt += 1; //인출 카운트 업 
				money = getMoney - i; // 잔액
			}
		}
//		for (int i = 0; i < arr.length; i++) {
//			if (arr[i] > money) {
//				money = getMoney;
//				cnt++;
//			}
//			money -= arr[i];
//		}
		//System.out.println("cnt : " + cnt);
		return cnt;
	}
}