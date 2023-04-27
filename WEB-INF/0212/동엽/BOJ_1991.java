import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	static Map<Character, char[]> mymap;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		mymap = new HashMap<>();
		
		for(int i = 0; i < n ; i ++) {
			char thisnode = sc.next().charAt(0);
			char leftnode = sc.next().charAt(0);
			char rightnode = sc.next().charAt(0);
			
			mymap.put(thisnode, new char[] {leftnode,rightnode});
		}		
		
		preorder('A');
		System.out.println();
		inorder('A');
		System.out.println();
		postorder('A');
		
	}
	
	static void inorder(char elem) {
		char[] thislist = mymap.get(elem);
		if(thislist[0] != '.')
			inorder(thislist[0]);
		System.out.print(elem);
		if(thislist[1] != '.')
			inorder(thislist[1]);
		
		
	}
	
	static void preorder(char elem) {
		char[] thislist = mymap.get(elem);
		System.out.print(elem);
		if(thislist[0] != '.')
			preorder(thislist[0]);
		if(thislist[1] != '.')
			preorder(thislist[1]);
		
	}
	
	static void postorder(char elem) {
		char[] thislist = mymap.get(elem);
		if(thislist[0] != '.')
			postorder(thislist[0]);
		if(thislist[1] != '.')
			postorder(thislist[1]);
		System.out.print(elem);
		
	}

}