import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static Node root;
	static class Node{
		Map<Character, Node> childNode = new HashMap<>();
		boolean isEnd;
	}
	static class Trie{
		Node root = new Node();
		void insert(String str) {
			Node node = this.root;
			int size = str.length();
			for (int i = 0; i < size; i++) {
				node = node.childNode.computeIfAbsent(str.charAt(i), key->new Node());
			}
			node.isEnd = true;
		}
		
		boolean search(String str) {
			Node node = this.root;
			int size = str.length();
			
			for (int i = 0; i < size; i++) {
				node = node.childNode.getOrDefault(str.charAt(i), null);
				if(node == null) return false;
			}
			
			return node.isEnd;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Trie trie = new Trie();
		for (int i = 0; i < n; i++) {
			trie.insert(br.readLine());
		}
		int sum = 0;
		for(int i = 0 ; i < m ; i++) {
			if(trie.search(br.readLine())) sum++;
		}
		System.out.println(sum);
	}

}