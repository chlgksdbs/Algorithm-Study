package day0213;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_26260 {
	static int n;
	static int[] arr;
	static int[] result;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(br.readLine());

		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (arr[i] == -1)
				arr[i] = x;
		}

		Arrays.sort(arr);
		result = new int[n];
		makeCompleteBinaryTree(0, n - 1);
		System.out.println(sb);
		// System.out.println(Arrays.toString(result));

	}

	static void makeCompleteBinaryTree(int start, int end) {

		if (end - start <= 1) {
			if (end - start == 1)
				sb.append(arr[start] + " " + arr[end] + " ");
			else
				sb.append(arr[end] + " ");
			return;
		}

		int mid = (end + start + 1) / 2;
		makeCompleteBinaryTree(start, mid - 1);
		makeCompleteBinaryTree(mid + 1, end);
		sb.append(arr[mid] + " ");

	}
}