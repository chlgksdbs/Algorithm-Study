import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s1 = sc.next();
		String s2 = sc.next();
		KMP(s1, s2);
	}

	public static int[] makeTable(String pattern) {
		int pLen = pattern.length();
		int[] table = new int[pLen];

		int idx = 0;
		for (int i = 1; i < pLen; i++) {
			while (idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx - 1];
			}

			if (pattern.charAt(i) == pattern.charAt(idx)) {
				table[i] = ++idx;
			}
		}
		return table;
	}

	public static void KMP(String str, String pattern) {
		int[] table = makeTable(pattern);

		int sLen = str.length();
		int pLen = pattern.length();

		int idx = 0; // 현재 대응되는 글자 수
		for (int i = 0; i < sLen; i++) {
			while (idx > 0 && str.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx - 1];
			}
			if (str.charAt(i) == pattern.charAt(idx)) {
				if (idx == pLen - 1) {
					System.out.println(1);
					System.exit(0);
				} else {
					idx++;
				}
			}
		}
		System.out.println(0);
	}
}