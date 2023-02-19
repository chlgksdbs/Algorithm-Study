package BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1654_랜선자르기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();
		int arr[] = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		Arrays.sort(arr);

		binarySearch(arr, K);

	}

	public static void binarySearch(int[] arr, int K) {
		int arrSize = arr.length;
		// System.out.println("arrsize:" + arrSize);
		long lowerBound = 1; // 랜선을 자르는 최소단위는 1이니 1로 설정
		long upperBound = arr[arrSize - 1]; // 마지막 인덱스를 최대값으로 설정 정렬된 배열이 들어올 거임)
		long mid = 0;
		long ans = 0;
		while (lowerBound <= upperBound) {// 교차하는 경우를 고려
			 mid = (lowerBound + upperBound) / 2;
			// System.out.println("나 미드"+ mid);
			// 절반 값으로 모든 변수를 나눴을 때 k에 해당하는 값이 11이면 멈추기
			// 절반 값으로 모든 변수를 나웠을 때 k보다 작다면 더 작게 잘라함 ->왼쪽 탐색으로 변경 -> mid을 upper으로 사용
			int cnt = 0;
			for (int i = 0; i < arr.length; i++) {
				cnt += arr[i] / mid;
				// System.out.println(i + "cnt :" + cnt);
			}
			if (cnt < K) {
				// 크게 잘라야함 시작을 mid로변경하기
				upperBound = mid - 1;
			} else {
				ans = mid;
				lowerBound = mid + 1;
			}
		}
		System.out.println(ans );
	}

}