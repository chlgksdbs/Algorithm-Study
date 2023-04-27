package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_14425 {
	
	static class Node {
		
		// 자식 노드
		Map<Character, Node> childNode = new HashMap<>();
		
		// 해당 노드가 문자열의 끝인지 체크하는 변수
		boolean isEnd;
	}
	
	// Node를 사용하여 문자 Tree를 구성하는 Trie 자료구조
	static class Trie {
		
		// Trie 클래스에서 기본적으로 항상 root 노드를 선언
		Node root = new Node(); //  (삽입 연산, 탐색 연산 모두 root노드에서 출발)
		
		// Trie에서 문자열 삽입
		void insert(String str) {
			
			Node node = this.root; // 삽입 연산 시, 항상 root노드에서 출발
			
			int size = str.length(); // 반복문 체크마다 문자열 길이만큼 체크해야 하므로, 미리 변수 선언(최적화)
			
			// 문자열 길이만큼 수행 (문자열의 각 단어마다 가져와서 자식 노드 중 있는지 체크)
			// 없다면, 새로운 자식노드 생성
			for (int i = 0; i < size; i++) {
				node = node.childNode.computeIfAbsent(str.charAt(i), key -> new Node());
			}
			// 반복문 수행이 마치고, 저장 할 문자열의 마지막 단어에 매핑되는 노드에 단어의 끝임을 명시
			node.isEnd = true;
			
		}
		
		// Trie에서 문자열 검색
		boolean search(String str) {
			
			Node node = this.root; // 검색 연산 시, 항상 root노드에서 출발
			
			int size = str.length(); // 반복문 체크마다 문자열 길이만큼 체크해야 하므로, 미리 변수 선언(최적화)
			
			// 문자열 길이만큼 수행 (문자열의 각 단어마다 노드가 존재하는지 체크)
			for (int i = 0; i < size; i++) {
				// 문자열의 i번째에 매핑된 노드가 존재하면 가져와서 다음 node로 수행하고, 아니라면 null을 대입
				node = node.childNode.getOrDefault(str.charAt(i), null);
				
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
	}
	
	static int N, M; // 집합 S에 포함되어 있는 문자열, 검사해야 하는 문자열
	static int ans; // M개의 문자열 중, 집합 S에 포함된 문자열의 수를 저장 (정답)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// Trie 자료구조 생성
		Trie trie = new Trie();
		
		// N개의 문자열 입력 (insert 연산)
		for (int i = 0; i < N; i++) {
			trie.insert(br.readLine());
		}
		
		// M개의 문자열 확인 (search 연산)
		for (int i = 0; i < M; i++) {
			// 해당 문자열이 trie에 저장되어 있는 경우, ans값을 증가
			if (trie.search(br.readLine())) ans++;
		}
		
		System.out.println(ans);
	}
}
