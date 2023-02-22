package acmicpc0212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main26260 {

	static int[] arr;
	static int n;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		arr = new int[n + 1];

		st = new StringTokenizer(br.readLine());
		int minIndex = 0;
		for (int i = 1; i <= n; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
			if (num == -1) {
				minIndex = i;
			}
		}

		int newNum = Integer.parseInt(br.readLine());
		arr[minIndex] = newNum;

		Arrays.sort(arr);

		postOrder(1, (n + 1) / 2, n);

	}

	static void postOrder(int start, int middle, int end) {
		if (start > end) {
			return;
		}
		postOrder(start, (middle - 1 + start) / 2, middle - 1);
		postOrder(middle + 1, (end + middle + 1) / 2, end);
		System.out.print(arr[middle] + " ");
	}

}
