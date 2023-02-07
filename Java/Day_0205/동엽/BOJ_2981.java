import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] list = new int[n];
		for (int i = 0; i < n; i++) {
			list[i] = sc.nextInt();
		}
		ArrayList<Integer> mylist = new ArrayList<>();
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				mylist.add(Math.abs(list[i] - list[j]));
			}
		}
		int mygcd;
		if (mylist.size() == 1) {
			mygcd = mylist.get(0);
		} else {
			mygcd = gcd(mylist.get(0), mylist.get(1));
		}
		for (int i = 0; i < mylist.size() - 1; i++) {
			mygcd = gcd(mylist.get(i), mygcd);
		}

		for (int i = 2; i < mygcd + 1; i++) {
			if (mygcd % i == 0)
				System.out.print(i + " ");
		}

	}

	public static int gcd(int a, int b) {

		// a > b로 만들어주는 과정
		int tmp;
		if (a < b) {
			tmp = b;
			b = a;
			a = tmp;
		}

		if (b == 0) { // b가 0일때
			return a; // a값은 최대공약수
		}
		return gcd(b, a % b); // b와 a%b를 파라미터로 재귀호출
	}

}