import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static Pair[] mylist;
	static int[] dp;
	static ArrayList<Integer> LISlist;

	static class Pair implements Comparable<Pair> {
		int x;
		int y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			return Integer.compare(x, o.x);
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		mylist = new Pair[n];

		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			mylist[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(mylist);

		dp = new int[n];
		LISlist = new ArrayList<>();
		LISlist.add(Integer.MIN_VALUE);

		for (int i = 0; i < n; i++) {
			if (LISlist.get(LISlist.size() - 1) < mylist[i].y) {
				LISlist.add(mylist[i].y);
				dp[i] = LISlist.size() - 1;
			} else {
				int index = binarySearch(1, LISlist.size() - 1, mylist[i].y);
				LISlist.set(index, mylist[i].y);
				dp[i] = index;
			}
		}

		int LISlength = LISlist.size() - 1;
		System.out.println(n - LISlength);

		Stack<Integer> stack = new Stack<>();
		
		ArrayList<Integer> answer = new ArrayList<>();
		int index = LISlength;
		for (int i = n - 1; i >= 0; i--) {
			if (dp[i] == index) {
				index--;
			} else {
				answer.add(mylist[i].x);
			}
		}
		
		Collections.sort(answer);
		
		for(int e : answer) {
			System.out.println(e);
		}

	}

	private static int binarySearch(int left, int right, int target) {
		while (left < right) {
			int mid = (left + right) / 2;
			if (target > LISlist.get(mid))
				left = mid + 1;
			else
				right = mid;
		}
		return left;
	}

}