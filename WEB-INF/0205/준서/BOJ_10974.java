package Day_0205;

import java.util.Scanner;

public class BOJ_10974 {

	public static void permutation(int[] arr, int[] out, boolean[] visited, int depth, int r) {

		// depth의 값과 r이 같은 경우,
		// out을 반복문을 돌리면서 해당 요소를 num으로 받아서 출력 후 return으로 종료
		if (depth == r) {
			for (int num : out) {
				System.out.print(num + " ");
			}
			System.out.println();
			return;
		}

		// arr의 길이 만큼 반복문을 수행
		// 순서를 고려하기위해 0부터 탐색 ex){1, 2} {2, 1}
		for (int i = 0; i < arr.length; i++) {
			if (!visited[i]) { // visited의 i번째가 false라면
				visited[i] = true; // 해당 i번째의 값을 true로 바꾸고
				out[depth] = arr[i]; // out의 depth번째에 arr의 i번쨰 값을 대입

				// depth에 1을 더한 값을 대입하여 permutation을 재귀 호출
				// 다시 호출 했을 때, if(depth == r)을 조건문을 만족하지 못하면 반복문으로 다시 돌아오고,
				// 만족한다면 해당 조건문을 수행한 후 다시 돌아와서 i번째 visited의 값을 false로 변환
				permutation(arr, out, visited, depth + 1, r);
				visited[i] = false;
			}
		}
		return;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int arr[] = new int[n];
		
		for(int i = 0 ; i < n ; i++) {
			arr[i] = i+1;
		}
		
		int r =n;
		
		
		
		

		int[] out = new int[r];
		boolean[] visited = new boolean[arr.length];

		permutation(arr, out, visited, 0, r);
	}
}
