package algorithm;

import java.util.Scanner;

public class BOJ_1517 {
	static long swapCount; // Swap 횟수 저장
	
	public static void MergeSort(int[] arr, int start, int end) {
		if (start < end) {
			int mid = (start + end) / 2;
			
			// 분할
			MergeSort(arr, start, mid);
			MergeSort(arr, mid + 1, end);
			
			// 정복
			Merge(arr, start, mid, end);
		}
	}
	
	public static void Merge(int[] arr, int low, int mid, int high) {
		int[] tempArr = new int[high - low + 1]; // 정렬된 배열을 저장하는 임시 배열
		
		int idx = 0; // 임시 배열(tempArr)의 인덱스를 가리키는 변수
		int x = low; // 분할된 첫 번째 배열의 인덱스를 가리키는 변수
		int y = mid + 1; // 분할된 두 번째 배열의 인덱스를 가리키는 변수
		
		// 두 그룹 중, 하나의 그룹이 끝날 때 까지 병합
		while (x <= mid && y <= high) {
			// *** 값이 같은 경우는 swap 할 필요가 없으므로, 등호를 추가해주어야 한다.
			if (arr[x] <= arr[y]) {
				tempArr[idx++] = arr[x++];
			}
			else {
				tempArr[idx++] = arr[y++];
				
				// *** 앞의 숫자가 뒤의 숫자보다 큰 경우 swap 발생
				// *** bubble sort (X), merge sort (O)
				// *** merge sort로 정렬하고, 앞의 숫자가 뒤의 숫자보다 큰 경우에 카운트하면 동일한 swap 횟수 출력 가능
				// *** 왼쪽 배열에 남아있는 숫자 만큼 계속 swap 
				swapCount += (mid - x + 1);
			}
		}
		
		// 위의 병합 과정이 끝난 경우에서, 나머지 그룹을 처리
		while (x <= mid) {
			tempArr[idx++] = arr[x++];
		}
		
		while (y <= high) {
			tempArr[idx++] = arr[y++];
		}
		
		// 임시로 저장된 배열을 원 배열로 복사
		for (int i = low; i <= high; i++) {
			arr[i] = tempArr[i - low];
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // 배열의 크기
		int[] arr = new int[n];
		
		for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
		
		MergeSort(arr, 0, arr.length - 1); // 병합 정렬 수행
		
		System.out.println(swapCount);
	}
}
