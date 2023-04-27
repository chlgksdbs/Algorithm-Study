package m3.day0327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11505 {
	static int n,m,k;
	static int[] num;
	static long[] tree;
	static final long PER_NUM = 1000000007;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		num = new int[n+1];
		tree = new long[n*4];
		
		for (int i = 1; i <= n; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		
		makeTree(1, n, 1);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a == 1) {
				num[b] = c;
				updateTree(1, n, 1, b, c);
				
//				System.out.println(Arrays.toString(num));
//				System.out.println(Arrays.toString(tree));
			} else {
				sb.append(mulTree(1, n, 1, b, c)).append("\n");
			}
		}
		
		System.out.println(sb.toString());

	}

	private static long updateTree(int start, int end, int index, int targetIdx, int changeNum) {
		if(targetIdx < start || end < targetIdx) return tree[index];
		if(start == end) {
			if(start == targetIdx) {
				return tree[index] = num[targetIdx];
			}
			return tree[index];
		}
		int mid = (start + end )/2;
		return tree[index] = (updateTree(start, mid, index*2, targetIdx, changeNum) % PER_NUM *
		updateTree(mid+1, end, index*2+1, targetIdx, changeNum) % PER_NUM)%PER_NUM;
		
	}

	private static long mulTree(int start, int end, int index, int left, int right) {
		if(end < left || right < start) return 1;
		
		if(left <= start && end <= right) return tree[index];
		
		int mid = (start+end)/2;
		return (mulTree(start, mid, index*2, left, right)%PER_NUM * mulTree(mid+1 , end, index*2+1, left, right)%PER_NUM)%PER_NUM;
		
	}

	private static long makeTree(int start, int end, int index) {
		if(start == end) return tree[index] = num[start];
		
		int mid = (start+end)/2;
		
		tree[index] = (makeTree(start, mid, index*2)%PER_NUM * makeTree(mid+1, end, index*2+1)%PER_NUM)%PER_NUM;
		
		return tree[index];
		
	}
	
}
