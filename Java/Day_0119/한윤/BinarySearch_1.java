import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch_1 {
	public static int binarySearch(int[] num, int num_size, int key) {
		// 반복문으로 구현
		int start = 0;			// 시작점
		int end = num_size - 1;	// 끝점
		
		if (start > end) // 초기 조건 확인 
			return 0;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (num[mid] == key) // 값을 찾은 경우 1을 리턴
				return 1;
			else if (num[mid] > key) { // 배열의 중간 값이 key보다 큰 경우, 배열의 왼쪽 부분을 재탐색
				end = mid - 1;
			}
			else { // 배열의 중간 값이 key보다 작은 경우, 배열의 오른쪽 부분을 재탐색
				start = mid + 1;
			}
		}
		
		return 0; // 값을 찾지 못하고 반복문을 종료한 경우 0을 리턴
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] arr = new int[n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr); // 배열의 오름차순 정렬
		
		int m = sc.nextInt();
		int[] keys = new int[m]; // 찾고자 하는 값이 담긴 배열
		
		for (int i = 0; i < m; i++) { // 입력과 동시에 출력하는 예제가 아니므로, 배열에 값을 저장
			keys[i] = sc.nextInt();
		}
		
		for (int i = 0; i < m; i++) {
			// 존재한다면 1을 출력, 존재하지 않는다면 0을 출력
			System.out.println(binarySearch(arr, n, keys[i]));
		}
	}
}
