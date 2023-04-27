import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Main {
	static StringBuilder sb = new StringBuilder();
	static Node root = new Node();
	
	static class Node{
		Map<String, Node> childNode = new HashMap<>();

		public void insert(String[] split) {
			int size = split.length;
			Node node = this;
			for(int i = 1 ; i < size; i++) {
				node = node.childNode.computeIfAbsent(split[i], key->new Node());
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n; i++) {
			String mystr = br.readLine();
			root.insert(mystr.split(" "));
		}
		
		print(root,0);
		System.out.println(sb.toString());
		
	}
	
	private static void print(Node r,int depth) {
		if(r == null) return;
		Object[] mylist = r.childNode.keySet().toArray();
		Arrays.sort(mylist);
		
		for(Object key : mylist) {
			for(int i = 0; i < depth;i++) {
				sb.append("--");
			}
			sb.append(key).append("\n");
			print(r.childNode.get(key),depth+1);
		}
	}

}