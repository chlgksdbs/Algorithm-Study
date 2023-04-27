package baekjoon.algorithm.study;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_5639 {
	
	static class Node {
		int data;
		Node left; // 왼쪽 자식 노드
		Node right; // 오른쪽 자식 노드
		
		// data를 세팅해주는 생성자
		public Node(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
		
		public void setLeft(Node node) {
			this.left = node;
		}
		
		public void setRight(Node node) {
			this.right = node;
		}
	}
	
	static class Tree {
		Node root; // root 노드를 가리키는 node
		
		// 전위 순회의 결과로 tree를 생성해주는 함수
		public void nodeSet(Node node, Node currentNode) {
			if (node.data > currentNode.data) {
				if (node.left == null) {
					node.setLeft(currentNode);
				}
				else {
					nodeSet(node.left, currentNode);
				}
			}
			else {
				if (node.right == null) {
					node.setRight(currentNode);
				}
				else {
					nodeSet(node.right, currentNode);
				}
			}
		}
		
		// 후위 순회의 결과를 출력하는 함수
		public void postorder(Node node) {
			if (node != null) {
				postorder(node.left);
				postorder(node.right);
				System.out.println(node.data);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Integer> input = new ArrayList<Integer>();
		
		// EOF 입력
		while(sc.hasNext()) {
			input.add(sc.nextInt());
		}
		
		Tree t = new Tree(); // 트리 생성
		
		// root노드 설정
		Node root = new Node(input.get(0));
		t.root = root;
		
		for (int i = 1; i < input.size(); i++) {
			int tmp = input.get(i);
			Node n = new Node(tmp); // tmp 값을 data로 하는 node 생성
			t.nodeSet(t.root, n); // root노드에서 출발하여 tree를 세팅
		}
		
		t.postorder(t.root);
	}
}
