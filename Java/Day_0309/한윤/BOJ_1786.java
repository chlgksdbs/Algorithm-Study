package baekjoon.algorithm.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1786 {
	
	static int cnt; // pattern이 나타나는 횟수
	
	static StringBuilder sb; // 횟수를 먼저 출력하고, 후에 위치를 출력하기 위해 사용
	
	// pattern의 table을 만드는 함수 (실패 함수)
	public static int[] makeTable(String pattern) {
		
		int size = pattern.length();
		int[] table = new int[size];
		
		int idx = 0;
		for (int i = 1; i < size; i++) {
			while (idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx - 1];
			}
			
			if (pattern.charAt(i) == pattern.charAt(idx)) {
				table[i] = ++idx;
			}
		}
		
		return table;
	}
	
	// KMP 알고리즘 수행 (parent에서 pattern 문자열 탐색)
	public static void KMP(String parent, String pattern) {
		
		// pattern의 테이블 생성 (실패 함수)
		int[] table = makeTable(pattern);
		
		int parentSize = parent.length();
		int patternSize = pattern.length();
		
		int idx = 0;
		for (int i = 0; i < parentSize; i++) {
			while (idx > 0 && parent.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx - 1];
			}
			
			if (parent.charAt(i) == pattern.charAt(idx)) {
				if (idx == patternSize - 1) {
					cnt++;
					sb.append(i - idx + 1 + " ");
					idx = table[idx]; // 다음 탐색을 위해 사용
				}
				else {
					idx++;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		String text = br.readLine(); // 탐색할 문자열
		String pattern = br.readLine(); // 찾고자 하는 문자열
		
		KMP(text, pattern); // KMP 알고리즈 수행
		
		System.out.println(cnt);
		System.out.println(sb);
	}
}
