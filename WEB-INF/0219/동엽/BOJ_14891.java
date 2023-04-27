import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] mylist = new int[4];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 4; i++) {
			mylist[i] = calculate(br.readLine().toCharArray());
		}

		int k = Integer.parseInt(br.readLine());

		StringTokenizer st;
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());

			int chain = Integer.parseInt(st.nextToken()) - 1;
			int spin = Integer.parseInt(st.nextToken());

			int right = chain;
			while (right + 1 <= 3) {
				if ((mylist[right] & (1 << 5)) > 0 != (mylist[right + 1] & (1 << 1)) > 0) {
					right++;
				} else
					break;
			}


			int left = chain;
			while (left - 1 >= 0) {
				if ((mylist[left] & (1 << 1)) > 0 != (mylist[left - 1] & (1 << 5)) > 0) {
					left--;
				} else
					break;
			}
//			System.out.println("left : " + left + " right : " + right);
			for (int j = left; j <= right; j++) {
				mylist[j] = go(Math.abs(chain - j) % 2 == 0 ? spin : -1 * spin, j);
			}
		}
		int sum = 0;
		for (int idx = 0; idx < 4; idx++) {
			sum += ((mylist[idx] & (1<<7)) > 0 ? 1 : 0) * (1 << idx);
		}
		System.out.println(sum);

	}

	private static int go(int spin, int chain) {
		int num = mylist[chain];
		if (spin == -1) {
			boolean isOne = (num & (1 << 7)) > 0;
			num = num << 1;
			num = num & ((1 << 8) - 1);
			if (isOne)
				num = num | 1;
		} else {
			boolean isOne = (num & 1) > 0;
			num = num >> 1;
			if (isOne)
				num = num | (1 << 7);
		}
		return num;
	}

	private static int calculate(char[] charArray) {
		int mysum = 0;
		for (int i = 7; i >= 0; i--) {
			if (charArray[i] == '0')
				continue;

			mysum += 1 << (7 - i);

		}
		return mysum;
	}

}