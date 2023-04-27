package ac0216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2263 {

	static int n;
	static int[] inOrder;
	static int[] postOrder;
	static int[] preOrder;
	static int index = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		inOrder = new int[n];
		postOrder = new int[n];
		preOrder = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			inOrder[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			postOrder[i] = Integer.parseInt(st.nextToken());
		}

		makeTree(0, n-1, 0, n-1);
		
		for(int i = 0 ; i < n; i++) {
			System.out.print(preOrder[i] + " ");
		}
		
	}

	static void makeTree(int instart, int inend, int poststart, int postend) {
		
		//범위를 벗어나면 stop
		if(instart > inend || poststart > postend) {
			return;
		}
		
		//root 노드는 항상 postorder의 마지막
		int root = postOrder[postend];
		preOrder[index++] = root;
		
		//root 노드의 위치 찾기
		int temp = instart;
		for(int i = instart ;i < inend; i++) {
			if(inOrder[i] == root) {
				temp = i;
				break;
			}
		}
		
		//사이에 몇개의 요소가 들어있는지
		int num = temp-instart;
		
		//루트 기준 왼쪽 트리
		makeTree(instart, temp-1, poststart, poststart+num-1);
		
		//루트 기준 오른쪽 트리
		makeTree(temp+1, inend , poststart+num, postend-1);
		
		
	}
	

}
