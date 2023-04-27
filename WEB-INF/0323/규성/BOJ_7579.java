package m3.day0323;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_7579 {
	static ArrayList<Integer> list;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		int m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		
		list.add(Integer.MIN_VALUE);
		
		int[] memory = new int[n+1];
		int[] dp = new int[n*100+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			
			memory[i] = Integer.parseInt(st.nextToken());
			if(memory[i] > list.get(list.size()-1)){
				list.add(memory[i]);
				dp[i] = list.size()-1;
			} else {
				int index = binarySearch(0, list.size()-1, memory[i]);
				list.set(index , memory[i]);
				dp[i] = index;
			}
			
			
		}
		
		System.out.println(list.size()-1);
		
		Stack<Integer> stack = new Stack<>();
		int index = list.size()-1;
		for(int i = n-1; i >= 0; i--) {
			if(dp[i] == index) {
				stack.push(memory[i]);
				index--;
			}
		}
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		
		System.out.println(sb.toString());
	}

	private static int binarySearch(int left, int right, int target) {
		
		while(left < right) {
			int mid = (left+right)/2;
			if(target > list.get(mid))
				left = mid+1;
			else
				right = mid;
		}
		
		return left;
	}

}
