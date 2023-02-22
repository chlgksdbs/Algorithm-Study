package acmicpc0212;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Main5639 {
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	static Node firstNode;
	static Stack<Node> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp = br.readLine();
		if(temp == null || temp.equals("")) {
			return;
		}
		firstNode = new Node();
		firstNode.key = Integer.parseInt(temp);
		stack.add(firstNode);
		while(true) {
			temp = br.readLine();
			if(temp == null || temp.equals("")) {
				break;
			} else {
				
				selectNode2(firstNode, Integer.parseInt(temp));
			}
		}
		
		postOrder(firstNode);

		
	}
	
	
	
	
	static void selectNode2(Node node, int key) {
		
		if(node.key > key) {
			if(node.left != null) {
				selectNode2(node.left, key);
			} else {
				Node newNode = new Node();
				newNode.key = key;
				node.left = newNode;
			}
		} else if(node.key < key) {
			if(node.right != null) {
				selectNode2(node.right, key);
			} else {
				Node newNode = new Node();
				newNode.key = key;
				node.right = newNode;
			}
		}
		
	}
	
	static void selectNode(int key) {
		Node newNode = new Node();
		newNode.key = key;
//		print();
		Node temp = null;
		while(true) {
			if(stack.isEmpty()) {
				temp.right = newNode;
				stack.push(newNode);
				break;
			}

			Node cutemp = stack.pop();
			if(cutemp.key > key) {
				if(cutemp.left != null) {
					temp.right = newNode;
					stack.push(cutemp);
					break;
				} else {
					cutemp.left = newNode;
					stack.push(cutemp);
					stack.push(newNode);
					break;
				}
				
			} else if(cutemp.key < key){
				temp = cutemp;
				continue;
			}
			
		}
		
	}
	
	static class Node{
		Node right;
		Node left;
		int key;
		
	}
	
	static void postOrder(Node node) {
		if(node.left != null)
			postOrder(node.left);
		if(node.right != null)
			postOrder(node.right);
		
		System.out.println(node.key);
	}
	
	static void print() {
		Node[] temp = stack.toArray(new Node[stack.size()]);
		for(int i = 0; i < temp.length; i++) {
			System.out.print(temp[i].key+" ");
		}
		System.out.println();
	}

}
