import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	String data = null;
	Node left = null;
	Node right = null;
	
	// 생성자
	Node(String data){
		this.data = data;
	}

	public boolean setLeft(Node left) {
		if(left != null) {
			this.left = left;
			return true;
		}
		return false;
	}
	
	public Node getLeft() {
		return left;
	}
	
	public boolean setRight(Node right) {
		if(right != null) {
			this.right = right;	
			return true;
		}
		return false;
	}
	
	public Node getRight() {
		return right;
	}
	
	@Override
	public String toString() {
		return data;
	}
}

public class Main {
	
	static int N;
	
	public static void preorder(Node node) {
		if(node != null) {
			System.out.print(node.data);
			preorder(node.getLeft());
			preorder(node.getRight());			
		}
	}
	
	public static void inorder(Node node) {
		if(node != null) {
			inorder(node.getLeft());
			System.out.print(node.data);
			inorder(node.getRight());
		}
	}
	
	public static void postorder(Node node) {
		if(node != null) {
			postorder(node.getLeft());
			postorder(node.getRight());
			System.out.print(node.data);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 노드 수
		
		Node[] nodeArr = new Node[N];
		// 객체 배열 생성
		for(int i=0; i<N; i++) {
			nodeArr[i] = new Node(String.valueOf((char)(i+65)));
		}
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int tmp1 = st.nextToken().charAt(0)-'0'- 17;
			Node node = nodeArr[tmp1];		// current 노드
			
			int tmp2 = st.nextToken().charAt(0)-'0'- 17;
			if(tmp2 == -19) {
				node.setLeft(null);				// left 자식 노드
			}else {
				node.setLeft(nodeArr[tmp2]);	// left 자식 노드
			}
			
			int tmp3 = st.nextToken().charAt(0)-'0'- 17;
			if(tmp3 == -19) {
				node.setRight(null);				// right 자식 노드
			}else {
				node.setRight(nodeArr[tmp3]);	// right 자식 노드
			}
		}
		
		preorder(nodeArr[0]);
		System.out.println();
		inorder(nodeArr[0]);
		System.out.println();
		postorder(nodeArr[0]);
	}
}
