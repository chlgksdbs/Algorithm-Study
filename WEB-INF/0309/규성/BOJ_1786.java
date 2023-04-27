package m3.day0309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1786 {

	static int n, m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		StringBuilder sb = new StringBuilder();
		int[] table = makeTable(str2);
//		System.out.println(Arrays.toString(table));
		
		int sLen = str1.length();
		int pLen = str2.length();
		int ans = 0;
		int index = 0;
		for (int i = 0; i < sLen; i++) {
			while(index > 0 && str1.charAt(i) != str2.charAt(index)) {
				index = table[index-1];
			}
			
			if(str1.charAt(i) == str2.charAt(index)) {
				if(index == pLen-1) {
					ans ++;
					sb.append(i-pLen+2).append(" ");
					index = table[index];
//					System.out.println(i);
				} else {
					index++;
				}
				
			}
		}
		System.out.println(ans);
		System.out.println(sb.toString());
	}

	private static int[] makeTable(String str2) {
		int size = str2.length();
		int[] table = new int[size];
		int index = 0;
		for (int i = 1; i < size; i++) {
			while(index > 0 && str2.charAt(index) != str2.charAt(i)) {
				index = table[index-1];
			}
			
			if(str2.charAt(index) == str2.charAt(i)) {
				table[i] = ++index;
			}
		}
		
		return table;
	}
}
