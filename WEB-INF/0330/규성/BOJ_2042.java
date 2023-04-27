package m3.day0327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2042 {
	static int n,m,k;
	static long[] num;
	static long[] tree;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		num = new long[n+1];
		tree = new long[n*4];
		
		for (int i = 1; i <= n; i++) {
			num[i] = Long.parseLong(br.readLine());
		}
		
		makeTree(1, n, 1);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			if(a == 1) {
				updateTree(1, n, 1, b, c);
				num[b] = c;
			} else {
				sb.append(sumTree(1, n, 1, b, (int)c)).append("\n");
			}
		}
		
		System.out.println(sb.toString());

	}

	private static void updateTree(int start, int end, int index, int targetIdx, long changeNum) {
		if(targetIdx < start || end < targetIdx) return;
		tree[index] = tree[index] - num[targetIdx] + changeNum;
		if(start == end) return;
		
		int mid = (start + end )/2;
		updateTree(start, mid, index*2, targetIdx, changeNum);
		updateTree(mid+1, end, index*2+1, targetIdx, changeNum);
		
	}

	private static long sumTree(int start, int end, int index, int left, int right) {
		if(end < left || right < start) return 0;
		
		if(left <= start && end <= right) return tree[index];
		
		int mid = (start+end)/2;
		return sumTree(start, mid, index*2, left, right) + sumTree(mid+1 , end, index*2+1, left, right);
		
	}

	private static long makeTree(int start, int end, int index) {
		if(start == end) return tree[index] = num[start];
		
		int mid = (start+end)/2;
		
		tree[index] = makeTree(start, mid, index*2) + makeTree(mid+1, end, index*2+1);
		
		return tree[index];
		
	}
	
	
	
}


