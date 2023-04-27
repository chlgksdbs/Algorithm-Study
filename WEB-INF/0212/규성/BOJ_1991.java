package acmicpc0212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main1991 {

	static Map<Character, List<Character>> map;

	static void preOrder(char index) {

		if (index == '.') {
			return;
		}
		System.out.print(index);
		preOrder(map.get(index).get(0));
		preOrder(map.get(index).get(1));
	}

	static void inOrder(char index) {

		if (index == '.') {
			return;
		}
		inOrder(map.get(index).get(0));	
		System.out.print(index);
		inOrder(map.get(index).get(1));
	}

	static void postOrder(char index) {

		if (index == '.') {
			return;
		}
		postOrder(map.get(index).get(0));
		postOrder(map.get(index).get(1));
		System.out.print(index);
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		map = new HashMap<>();

		for (int i = 0; i < n; i++) {

			st = new StringTokenizer(br.readLine());
			char a = st.nextToken().charAt(0);
			char b = st.nextToken().charAt(0);
			char c = st.nextToken().charAt(0);
			ArrayList<Character> tempList = new ArrayList<>();
			tempList.add(b);
			tempList.add(c);
			map.put(a, tempList);

		}

		preOrder('A');
		System.out.println();
		inOrder('A');
		System.out.println();
		postOrder('A');

	}

}
