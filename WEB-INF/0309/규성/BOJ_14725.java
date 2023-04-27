package m3.day0309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_14725 {

	static int n, m;
	static Node root = new Node();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			insert(st);
		}
		
		search(root, 0);
	}
	
	private static void search(Node node, int depth) {
		
		int size = node.childNode.size();
		Node nextNode;
		String[] temp = node.childNode.keySet().toArray(new String[size]);
		Arrays.sort(temp);
//		System.out.println(Arrays.toString(temp));
		for (int i = 0; i < temp.length; i++) {
			for(int j = 0 ; j < depth; j++) {
				System.out.print("--");
			}
			System.out.println(temp[i]);
			nextNode = node.childNode.get(temp[i]);
			search(nextNode, depth+1);
		}
	}

	static void insert(StringTokenizer st) {
//		System.out.println(st.nextToken());
		Node node = root;
		while(st.hasMoreTokens()) {
			node = node.childNode.computeIfAbsent(st.nextToken(), key -> new Node());
		}
		
	}

	static class Node{
		HashMap<String, Node> childNode = new HashMap<>();
		
	}
}

