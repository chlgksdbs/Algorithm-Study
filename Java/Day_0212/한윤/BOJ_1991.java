package baekjoon.algorithm.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1991 {
		
	static class Node {
		char data;
		Node left;
		Node right;
		
		// 생성자로 초기화
		public Node(char data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
		
		public void addLeft(Node node) {
			this.left = node;
		}
		
		public void addRight(Node node) {
			this.right = node;
		}
	}
	
	static class Tree {
		public Node addNode(char data) {
			Node node = new Node(data);
			return node;
		}
		
		public void preorder(Node node) { // 전위 순회
			if (node != null) {
				System.out.print(node.data);
				preorder(node.left);
				preorder(node.right);
			}
		}
		
		public void inorder(Node node) { // 중위 순회
			if (node != null) {
				inorder(node.left);
				System.out.print(node.data);
				inorder(node.right);
			}
		}
		
		public void postorder(Node node) { // 후위 순회
			if (node != null) {
				postorder(node.left);
				postorder(node.right);
				System.out.print(node.data);
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Tree tree = new Tree(); // 트리 생성
		
		int n = Integer.parseInt(br.readLine()); // 이진 트리의 노드의 개수
		
		char[] nodeLeft = new char[n]; // 노드의 왼쪽 자식 노드
		char[] nodeRight = new char[n]; // 노드의 오른쪽 자식 노드
		
		Node[] nodes = new Node[n]; // 노드 생성
		
		// 입력
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			nodes[i] = tree.addNode(st.nextToken().charAt(0));
			nodeLeft[i] = st.nextToken().charAt(0);
			nodeRight[i] = st.nextToken().charAt(0);
		}
		
		for (int i = 0; i < n; i++) {
			if (nodeLeft[i] != '.') {
				for (int j = 0; j < n; j++) {
					if (nodes[j].data == nodeLeft[i]) nodes[i].addLeft(nodes[j]);
				}
			}
			else nodes[i].addLeft(null);
			
			if (nodeRight[i] != '.') {
				for (int j = 0; j < n; j++) {
					if (nodes[j].data == nodeRight[i]) nodes[i].addRight(nodes[j]);
				}
			}
			else nodes[i].addRight(null);
		}
		
		// 전위 순회 출력
		tree.preorder(nodes[0]);
		System.out.println();
		
		// 중위 순회 출력
		tree.inorder(nodes[0]);
		System.out.println();
		
		// 후위 순회 출력
		tree.postorder(nodes[0]);
	}
}
