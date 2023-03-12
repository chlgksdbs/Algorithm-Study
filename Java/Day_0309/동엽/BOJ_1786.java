import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	static int[] pi;
	static char[] T;
	static char[] P;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = br.readLine().toCharArray();
		P = br.readLine().toCharArray();
		ArrayList<Integer> answer = new ArrayList<Integer>();
		
		pi = new int[P.length];
		preprocessing();

		int j = 0;
		for(int i = 0 ; i < T.length;i++) {
			while(j > 0 && T[i] != P[j])
				j = pi[j-1];
			if(T[i] == P[j]) {
				 if(j==P.length-1) {
					 answer.add(i+2-P.length);
					 j = pi[j];
				 }else {
					 j++;
				 }
			}
		}
		System.out.println(answer.size());
		for(int i = 0;i<answer.size();i++) {
			System.out.print(answer.get(i)+" ");
		}
		
	}

	public static void preprocessing() {
		int j = 0;
		for (int i = 1; i < pi.length; i++) {
			while(j > 0 && P[i] != P[j]) {
				j = pi[j-1];
			}
			if(P[i] == P[j]) {
				pi[i] = ++j;
			}
		}
		
		
	}

}