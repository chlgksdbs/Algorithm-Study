package m3.day0327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2357 {
	static int n,m,k;
	static int[] num;
	static int[] maxTree;
	static int[] minTree;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		num = new int[n+1];
		maxTree = new int[n*4];
		minTree = new int[n*4];
//		Arrays.fill(minTree, Integer.MAX_VALUE);
		
		for (int i = 1; i <= n; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		
		makeMaxTree(1, n, 1);
		makeMinTree(1, n, 1);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			sb.append(getMin(1, n, 1, a, b)).append(" ").append(getMax(1, n, 1, a, b)).append("\n");
		}
		
		System.out.println(sb.toString());

	}


	private static int getMax(int start, int end, int index, int left, int right) {
		if(end < left || right < start) return 0;
		
		if(left <= start && end <= right) return maxTree[index];
		
		int mid = (start+end)/2;
		return Math.max(getMax(start, mid, index*2, left, right), getMax(mid+1 , end, index*2+1, left, right));
		
	}
	
	private static int getMin(int start, int end, int index, int left, int right) {
		if(end < left || right < start) return Integer.MAX_VALUE;
		
		if(left <= start && end <= right) return minTree[index];
		
		int mid = (start+end)/2;
		return Math.min(getMin(start, mid, index*2, left, right), getMin(mid+1 , end, index*2+1, left, right));
		
	}

	private static int makeMaxTree(int start, int end, int index) {
		if(start == end) return maxTree[index] = num[start];
		
		int mid = (start+end)/2;
		
		maxTree[index] = Math.max(makeMaxTree(start, mid, index*2), makeMaxTree(mid+1, end, index*2+1));
		
		return maxTree[index];
		
	}
	
	private static int makeMinTree(int start, int end, int index) {
		if(start == end) return minTree[index] = num[start];
		
		int mid = (start+end)/2;
		
		minTree[index] = Math.min(makeMinTree(start, mid, index*2), makeMinTree(mid+1, end, index*2+1));
		
		return minTree[index];
		
	}
}
