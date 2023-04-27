package segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2041_구간합구하기 {
	static long[] tree;
	static int num[];
	static int N,M,K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		num = new int[N +1];
		tree = new long[4 * N];
		
		for (int i = 1; i <= N; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		
		
//	 함수 실행 
		makeTree(1, N, 1);
		
		System.out.println(Arrays.toString(tree));
		
		for (int i = 0; i < M+K ; i++) {
			st = new StringTokenizer(br.readLine());
			if( 1 == Integer.parseInt(st.nextToken())) {
				modify(1, N, 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}else {
				System.out.println(sumTree(1, N, 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
				
			}
				
		}
		
		// 연산
		
		
		
	}
	
	private static long makeTree(int start, int end, int index) {
		if(start == end) return tree[index] = num[start];
		int mid = (start + end) /2;		
		return tree[index] = makeTree(start, mid, index*2) + makeTree(mid+1, end, index+2+1);
		
	}
	
	private static long sumTree(int start, int end, int index ,int left, int right) {
		if(end < left || right < start) return 0;
		
		if(left <= start && end <= right) return tree[index];
		int mid = (start + end)/2;
		return sumTree(start, mid, index*2, left, right) + sumTree(mid+1 , end, index*2+1, left, right);
		
		
	}
	
	private static void modify(int start, int end, int index, int target, int changeNum) {
		if(target < start || end < target) return;
		
		tree[index] = tree[index] - num[target] + changeNum;
		
		if(start == end) {
			num[target] = changeNum;
			return;
		}
		
		int mid = (start + end )/2;
		modify(start, mid, index*2, target, changeNum);
		modify(mid+1, end, index*2+1, target, changeNum);

		
		
	}
}
