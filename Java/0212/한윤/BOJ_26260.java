package baekjoon.algorithm.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_26260 {
	
	static int n; // 노드의 수
	
	static int[] BST; // 기존의 포화 이진 검색 트리
	static int[] newBST; // 알 수 없는 리프 노드를 제외하고 새로운 수를 추가한 포화 이진 검색 트리
	
	// (1) newBST (root 기준) left 자식 트리 세팅
	public static void leftNodeSet(int start, int end, int idx) {
		// 시작점과 끝점이 엇갈린다면 종료
		if (start > end) return;
		
		int mid = (start + end) / 2; // 중간 인덱스
		newBST[idx] = BST[mid]; // 값 설정
		
		leftNodeSet(start, mid - 1, idx * 2); // 왼쪽 자식 노드 설정
		leftNodeSet(mid + 1, end, idx * 2 + 1); // 오른쪽 자식 노드 설정
	}
	
	// (2) newBST (root 기준) right 자식 트리 세팅
	public static void rightNodeSet(int start, int end, int idx) {
		// 시작점과 끝점이 엇갈린다면 종료
		if (start > end) return;
		
		int mid = (start + end) / 2; // 중간 인덱스
		newBST[idx] = BST[mid];
		
		rightNodeSet(start, mid - 1, idx * 2); // 왼쪽 자식 노드 설정
		rightNodeSet(mid + 1, end, idx * 2 + 1); // 오른쪽 자식 노드 설정
	}
	
	// 1차원 배열의 인덱스를 활용한 후위 순회 함수
	public static void postorder(int idx) {
		if (idx <= n) {
			postorder(idx * 2); // 왼쪽 자식 노드로 이동
			postorder(idx * 2 + 1); // 오른쪽 자식 노드로 이동
			System.out.print(newBST[idx] + " ");
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		BST = new int[n + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			BST[i] = Integer.parseInt(st.nextToken());
		}
		
		int x = Integer.parseInt(br.readLine()); // 트리에 넣을 수
		
		// 트리에서 지워진 수 대신 x 삽입
		for (int i = 1; i <= n; i++) {
			if (BST[i] == -1) BST[i] = x;
		}
		
		Arrays.sort(BST); // 배열을 오름차순으로 정렬
		
		newBST = new int[n + 1]; // 새로운 BST 생성
		newBST[1] = BST[(n + 1) / 2]; // root 노드 설정
		
		// newBST 왼쪽 자식 세팅
		int lstart = 1;
		int lend = (n + 1) / 2 - 1;
		leftNodeSet(lstart, lend, 2);
		
		// newBST 오른쪽 자식 세팅
		int rstart = (n + 1) / 2 + 1;
		int rend = n;
		rightNodeSet(rstart, rend, 3);
		
		postorder(1); // 후위 순회 실행
		
	}
}
