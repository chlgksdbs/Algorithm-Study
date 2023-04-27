package Tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_5639_동엽이형버전 {
	static ArrayList<Integer> mylist;
	static int[] resultlist;
	static int top = 0;
	static int n;
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		mylist = new ArrayList<>();
		while(true) {
			try {
				mylist.add(Integer.parseInt(br.readLine()));
			} catch (Exception e) {
				break;
			}
		}
		n = mylist.size();
		resultlist = new int[n];
		postorder(0,n-1);
		for(int i = n-1 ; i >= 0 ; i--) {
			System.out.println(resultlist[i]);
		}
	}
	
	static void postorder(int start, int end) {
		if(start > end) return;
		resultlist[top++] = mylist.get(start);
		int left = -1;
		int right = -1;
		if(start+1 <= n-1 && mylist.get(start) > mylist.get(start+1)) 
			left = start+1;
		
		for(int i = start+1 ; i <= end ; i++){
			if(mylist.get(start) < mylist.get(i)) {
				right = i;
				break;
			}
		}
		
		if(right != -1)
			postorder(right, end);
		if(left != -1)
			postorder(left,right == -1 ? end : right-1);
	}

}