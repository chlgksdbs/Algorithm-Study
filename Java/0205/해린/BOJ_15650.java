import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void combination(int[] arr, boolean[] visited, int start, int depth, int r) {
		if (depth == r) {
			for (int i = 0; i < arr.length; i++) {
				if (visited[i]) {
					System.out.print(arr[i] + " ");
				}
			}
			System.out.println();
			return;
		}
		for (int i = start; i < arr.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				combination(arr, visited, i + 1, depth + 1, r);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();

		int[] arr = new int[N];
		int r = M;
		for (int i = 0; i < N; i++) {
			arr[i] = i + 1;
		}
		boolean[] visited = new boolean[arr.length]; // 방문처리 배열
		
		combination(arr, visited, 0, 0, r);
	}
}
