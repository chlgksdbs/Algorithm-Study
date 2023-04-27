import java.util.Scanner;

public class Main {
	static int[][] graph;
	static int n;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		graph = new int[n][2];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2; j++) {
				graph[i][j] = sc.nextInt();
			}
		}

		System.out.println(calc(0,0));
	}

	public static int calc(int day, int profit) {
		if(day == n) {
			return profit;
		}
		int left = 0;
		int right = 0;
		if (day + graph[day][0] <= n) {
			left = calc(day + graph[day][0], profit + graph[day][1]);
		}
		if (day + 1 <= n) {
			right = calc(day + 1, profit);
		}
		return Math.max(Math.max(left, right), profit);

	}

}
