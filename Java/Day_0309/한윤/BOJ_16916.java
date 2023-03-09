package baekjoon.algorithm.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16916 {
	
	public static int[] makeTable(String pattern) {
		
		int size = pattern.length();
		int[] table = new int[size];
		
		int idx = 0;
		for (int i = 1; i < size; i++) {
			while(idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx - 1];
			}
			if (pattern.charAt(i) == pattern.charAt(idx)) {
				table[i] = ++idx;
			}
		}
		return table;
	}
	
	public static boolean KMP(String parent, String pattern) {
		
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
					return true;
				}
				else {
					idx++;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String S = br.readLine();
		String P = br.readLine();
		
		if(KMP(S, P)) System.out.println(1);
		else System.out.println(0);
	}
}
