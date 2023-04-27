package m3.day0323;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2568 {
	static ArrayList<Integer> list;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		list = new ArrayList<>();
		
		list.add(Integer.MIN_VALUE);
		
		List<Line> numList = new ArrayList<Line>();
		int[] dp = new int[n+1];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			numList.add(new Line(A,B));
		}
		
		Collections.sort(numList, new Comparator<Line>() {
			public int compare(Line l1, Line l2) {
				return l1.from - l2.from;
			}
		});
		
		
		for(int i = 0; i < n ; i++) {
			if(numList.get(i).to > list.get(list.size()-1)){	//제일 큼
				list.add(numList.get(i).to);
				dp[i] = list.size()-1;	//index 저장
			} else {	//제일 크지 않아서 적당한 위치 찾아야 함
				int index = binarySearch(0, list.size()-1, numList.get(i).to);
				list.set(index , numList.get(i).to);
				dp[i] = index;
			}
		}
		
		
		int result = 0;
		Stack<Integer> stack = new Stack<>();
		int index = list.size()-1;
		for(int i = n-1; i >= 0; i--) {
			if(dp[i] == index) {
				index--;
			} else {
				result++;
				stack.add(numList.get(i).from);
			}
		}
		
		System.out.println(result);
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append("\n");
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
	
	static class Line{
		
		public Line(int from, int to) {
			super();
			this.from = from;
			this.to = to;
		}

		int from;
		int to;

		
	}

}
