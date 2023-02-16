import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Node{
	int data;
	Node left = null;
	Node right = null;
	
	Node(int data){
		this.data = data;
	}
	
	public void setLeft(Node left) {
		this.left = left;
	}
	
	public Node getLeft() {
		return left;
	}
	
	public void setRight(Node right) {
		this.right = right;
	}
	
	public Node getRight() {
		return right;
	}
}

public class Main {
	
	static int N;
	static Node root;
	
	static int rootIdx;
	
	static ArrayList<Integer> list;
	static Node[] nodeArr;
	
	// 후위 순회
	public static void postorder(Node node) {
		if(node != null) {
			postorder(node.getLeft());
			postorder(node.getRight());
			System.out.println(node.data);
		}
	}
	
	// 이진 탐색 트리
	public static void bst(Node root, Node currentNode) {
		if(currentNode.data < root.data) {
			if(root.getLeft() == null) {
				root.setLeft(currentNode);
				return;
			}else {
				bst(root.getLeft(), currentNode);
			}
		}else {
			if(root.getRight() == null) {
				root.setRight(currentNode);
				return;
			}else {
				bst(root.getRight(), currentNode);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		list = new ArrayList<>();

		while((input = br.readLine()) != null) {
			list.add(Integer.parseInt(input));
		}
		N = list.size();
		
		nodeArr = new Node[N];
		
		for(int i=0; i<N; i++) {
			nodeArr[i] = new Node(list.get(i));		// 전위 순회로 입력받은 노드 생성
		}
		root = nodeArr[0];	// 전위 순회 처음 입력값 = 루트노드
		
		for(int i=1; i<N; i++) {	// 전위 순회 입력값 노드 하나씩 루트(부모)노드 기준으로 자리 찾기
			Node currentNode = nodeArr[i];
			bst(root, currentNode);
		}
		postorder(root);
	}
}
