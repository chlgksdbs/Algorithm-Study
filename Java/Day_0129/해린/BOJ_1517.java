import java.util.Scanner;

public class Main {

	
	static long cnt;

	private static void sort(int[] arr, int low, int high) {
		if (high - low < 1) {
			return;
		}
		int mid = (low + high) / 2;

		sort(arr, low, mid);
		sort(arr, mid + 1, high);

		merge(arr, low, mid, high);
	}

	private static void merge(int[] arr, int low, int mid, int high) {
		int[] tmp = new int[high - low + 1];
		int t = 0;
		int l = low;
		int r = mid + 1;
		
		while (l <= mid && r <= high) {
			if (arr[l] <= arr[r]) {
				tmp[t++] = arr[l++];
			} else {
				cnt += mid - l + 1;
				tmp[t++] = arr[r++];
			}
		}

		while (l <= mid) {
			tmp[t++] = arr[l++];
		}
		while (r <= high) {
			tmp[t++] = arr[r++];
		}
		for (int i = low; i <= high; i++) {
			arr[i] = tmp[i - low];
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		sort(arr, 0, arr.length - 1);
		System.out.println(cnt);
	}
}
