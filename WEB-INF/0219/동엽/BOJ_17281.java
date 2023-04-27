import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] mylist;
	static int n;
	static int answer = Integer.MIN_VALUE;
	static int[] output = new int[9];
	static boolean[] isSelected = new boolean[9];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		mylist = new int[n][9];

		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				mylist[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		perm(0);
		System.out.println(answer);

	}

	static void perm(int depth) {
		if (depth == 9) {
			if(output[3] != 0) return;
			calculate(output);
			return;
		}
		for (int i = 0; i < 9; i++) {
			if (isSelected[i])
				continue;

			isSelected[i] = true;
			output[depth] = i;
			perm(depth + 1);
			isSelected[i] = false;
		}

	}

	private static void calculate(int[] order) {
		boolean[] ground = new boolean[4];
		int outcount = 0;
		int hitter = 0;
		int score = 0;
		for (int i = 0; i < n; i++) {
			while (outcount < 3) {
				int num = mylist[i][output[hitter++]];
				hitter %= 9;
				if (num != 0) {
					for (int j = 3; j >= 0; j--) {
						if (!ground[j])
							continue;

						ground[j] = false;
						if (j + num > 3) {
							score++;
						} else {
							ground[j + num] = true;
						}
					}

					if (num == 4)
						score++;
					else
						ground[num] = true;

				} else
					outcount++;

			}
			for (int j = 0; j < 4; j++) {
				ground[j] = false;
			}
			outcount = 0;
		}
		answer = Math.max(answer, score);

	}

}