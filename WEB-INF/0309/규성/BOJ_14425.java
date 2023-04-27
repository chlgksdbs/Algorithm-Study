package m3.day0309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_14425 {

	static int n, m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < n; i++) {
			insert(br.readLine());
		}
		int sum = 0;
		for(int i = 0; i < m; i++) {
			if(search(br.readLine())) {
				sum++;
			}
		}
		System.out.println(sum);

	}
	
	private static void insert(String str) {
		Node node = root;
		int size = str.length();
		for(int i = 0; i < size; i++) {
			node = node.map.computeIfAbsent(str.charAt(i), key -> new Node());;
		}
		node.isEnd = true;
	}
	
	static boolean search(String str) {

		Node node = root; // 검색 연산 시, 항상 root노드에서 출발
		
		int size = str.length(); // 반복문 체크마다 문자열 길이만큼 체크해야 하므로, 미리 변수 선언(최적화)
		
		// 문자열 길이만큼 수행 (문자열의 각 단어마다 노드가 존재하는지 체크)
		for (int i = 0; i < size; i++) {
			// 문자열의 i번째에 매핑된 노드가 존재하면 가져와서 다음 node로 수행하고, 아니라면 null을 대입
			node = node.map.getOrDefault(str.charAt(i), null);
			
			if (node == null) {
				// getOrDefault에서 나온 값이 null이면, 현재 Trie에 해당 문자열이 없다는 뜻이므로 false를 리턴
				return false;
			}
		}
		// 문자열의 마지막 단어까지 매핑된 노드가 존재해도, 무조건 문자열이 존재하는 것이 아님!
		// ex) ssafy라는 단어를 Trie에 저장해도, ssaf의 마지막 단어 f에 매핑된 노드는 존재하지만, Trie에 저장된 것이 아님
		// 따라서, 현재 노드가 단어의 끝인지 아닌지 체크하는 변수의 값으로 리턴 (위 예시의 경우, false를 리턴)
		return node.isEnd;
	}
	
	static Node root = new Node();

	static class Node{
		HashMap<Character, Node> map = new HashMap<>();
		boolean isEnd;
	}
}
