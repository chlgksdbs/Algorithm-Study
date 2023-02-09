package day0202;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1446 {

	static ArrayList<ArrayList<Node>> al;
	static ArrayList<ArrayList<Node>> endAl;

	static int N;
	static int D;
	static int[] distance;
	static int[] startIndex;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		D = scan.nextInt();

		distance = new int[D + 1];
		startIndex = new int[N];

		al = new ArrayList<>(D + 1);
		endAl = new ArrayList<>(D + 1);

		for (int i = 0; i <= D; i++) {
			al.add(new ArrayList<Node>());
			endAl.add(new ArrayList<Node>());
		}

		for (int i = 0; i < N; i++) {

			int s = scan.nextInt();
			int e = scan.nextInt();
			int d = scan.nextInt();

			if (!(s >= 0 && s <= D && e >= 0 && e <= D))
				continue;

			al.get(s).add(new Node(e, d));
			startIndex[i] = s;

			if (e <= D)
				endAl.get(e).add(new Node(s, d));
		}

		for (int i = 0; i <= D; i++) {
			distance[i] = i;
		}

		for (int i = 0; i < N; i++) {
			int s = startIndex[i];

			for (Node tmp : al.get(s)) {

				int e = tmp.getIndex();
				int d = tmp.getDistance();

				distance[e] = Math.min(distance[e], distance[s] + d);

				for (int k = e + 1; k <= D; k++) {
					distance[k] = Math.min(distance[k - 1] + 1, distance[k]);

					for (Node endtmp : endAl.get(k))
						distance[k] = Math.min(distance[k], distance[endtmp.getIndex()] + endtmp.getDistance());
				}

			}
		}

		System.out.println(distance[D]);

	}

}

class Node {
	int index;
	int distance;

	Node(int i, int d) {
		index = i;
		distance = d;
	}

	public int getIndex() {
		return index;
	}

	public int getDistance() {
		return distance;
	}

}