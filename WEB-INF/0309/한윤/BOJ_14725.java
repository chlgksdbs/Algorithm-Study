package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_14725 {
	
	static class Node {
		
		// 자식 노드
		Map<String, Node> childNode = new HashMap<>();
		
		// 현재 노드에서 자식 노드의 수를 반환하는 함수
		int size() { 
			return childNode.size();
		}
		
		// 현재 노드에서 자식 노드의 key 값 (String)을 배열로 반환하는 함수
		String[] show() {
			int size = size(); // 자식 노드의 수를 체크
			
			String[] keys = new String[size];
			int idx = 0;
			// 자식 노드들의 key 값을 keys 배열에 저장
			for (String key : childNode.keySet()) {
				keys[idx++] = key;
			}
			// 저장한 배열을 반환
			return keys;
		}
	}
	
	static class Trie {
		
		Node root = new Node(); // root 노드
		
		// 삽입 연산
		void insert(String[] str) {
			
			Node node = this.root; // root부터 출발
			int size = str.length; // 삽입 범위
			
			// 해당 문자열이 존재하면 node로 이동, 없다면 생성
			for (int i = 0; i < size; i++) {
				node = node.childNode.computeIfAbsent(str[i], key -> new Node());
			}
		}
		
		// 검색 연산 (전위 순회)
		void preorder(Node node, String stick) {
			// 해당 node가 끝 node인 경우 (자식 노드가 없는 경우)
			if (node.size() == 0) {
				return;
			}
			// 현재 노드의 자식 노드들을 String 배열에 담는 작업
			String[] tmp = node.show();
			Arrays.sort(tmp, Comparator.naturalOrder()); // 오름차순 및 크기 순 정렬
			int size = node.size();
			
			// 자식 노드들 중, 하나를 수행하고 재귀를 통해 다음 수행으로 보냄
			// 재귀를 끝내고 돌아왔을 때, 다음 자식 노드 수행을 통해 전위 순회 완성
			for (int i = 0; i < size; i++) {
				System.out.println(stick + tmp[i]);
				preorder(node.childNode.get(tmp[i]), stick + "--");
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Trie trie = new Trie(); // Trie 자료구조 생성
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int K = Integer.parseInt(st.nextToken()); // 먹이 정보 개수 (띄어쓰기로 구분)
			String[] foods = new String[K]; // K개의 먹이 정보를 담을 문자열 배열
			
			for (int j = 0; j < K; j++) {
				foods[j] = st.nextToken();
			}
			
			// Trie 자료구조를 이용하여 문자열 트리 생성
			trie.insert(foods);
			
		} // end input
		
		trie.preorder(trie.root, ""); // 전위 순회
	}
}
