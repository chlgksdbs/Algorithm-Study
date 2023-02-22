package acmicpc0126_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main14501 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		// 합계 배열
		int[] dp = new int[30];
		boolean[] arr = new boolean[20];
		
		
		ArrayList<ArrayList<Consult>> list = new ArrayList<>();
		
		for(int i = 0; i <= 30; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 1; i <= n; i++) {
			int index = sc.nextInt();
			int cost = sc.nextInt();
			list.get(i+index-1).add(new Consult(i-1, cost));
		}

		for (int i = 1; i <= n; i++) {
			for(int j = 0; j < list.get(i).size(); j++) {
				if (dp[i] < dp[list.get(i).get(j).getIndex()] + list.get(i).get(j).getCost()) {
					dp[i] = dp[list.get(i).get(j).getIndex()] + list.get(i).get(j).getCost();
					// 그 뒤의 모든 dp 값을 그걸로 초기화
					for (int k = i+1; k <= n; k++) {
						dp[k] = dp[i];
					}
//					System.out.println(Arrays.toString(dp));
				}
			}
			
		}
		System.out.println(dp[n]);

		sc.close();
	}
}

class Consult {

	public Consult(int index, int cost) {
		super();
		this.index = index;
		this.cost = cost;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	int index;
	int cost;

}
