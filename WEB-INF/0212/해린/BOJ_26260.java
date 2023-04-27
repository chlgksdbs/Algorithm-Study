import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int X;
	
	static int[] arr;		// 초기 배열
	static int[] newArr;	// 탐색 트리 배열
	
	// 왼쪽 이진탐색 트리
	public static void bst_left(int start, int end, int idx) {
		if(start > end) return;
		
		int mid = (start + end) / 2;
		newArr[idx] = arr[mid];
		
		bst_left(start, mid-1, 2*idx);
		bst_left(mid+1, end, 2*idx+1);
	}
	
	// 오른쪽 이진탐색 트리
	public static void bst_right(int start, int end, int idx) {
		if(start > end) return;

		int mid = (start + end) / 2;
		newArr[idx] = arr[mid];
		bst_right(start, mid-1, 2*idx);
		bst_right(mid+1, end, 2*idx+1);

	}
	
	// 후위 순회
	public static void postorder(int current) {
		if(current > N) {
			return;
		}
		postorder(2 * current);
		postorder(2 * current + 1);
		System.out.print(newArr[current] + " ");
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 노드 개수
		
		arr = new int[N+1];
		newArr = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		X = Integer.parseInt(br.readLine());	// 트리에 넣을 수
		for(int i=1; i<N+1; i++) {
			if(arr[i] == -1) {			// -1의 자리에서는
				arr[i] = X;
			}
		}
		Arrays.sort(arr);	// 배열 data값 오름차순 정렬
		
		newArr[1] = arr[(N+1)/2];	// 정렬한 배열의 중간 값을 루트노드로 지정
		
		bst_left(1, ((N+1)/2)-1, 2*1);
			
		bst_right(((N+1)/2)+1, N, 2*1+1);
		
		postorder(1);
	}
}
