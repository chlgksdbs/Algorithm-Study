import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] mytree;
	static int[] mylist;
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		mylist = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			mylist[i] = Integer.parseInt(st.nextToken());
		}

		int target = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			if (mylist[i] == -1)
				mylist[i] = target;
		}

		
		Arrays.sort(mylist);
		
		mytree = new int[n+1];
		draw(0,n-1,1);
		postorder(1);
	}
	
	static void draw(int start, int end, int index) {
		int mid = (start+end)/2;
		mytree[index] = mylist[mid];
		if(start != end) {
			draw(start,mid-1,2*index);
			draw(mid+1,end,2*index+1);
		}
		
	}
	
	static void postorder(int index) {
		if(2*index <= n)
			postorder(2*index);
		if(2*index+1<=n)
			postorder(2*index+1);
		System.out.print(mytree[index]+" ");
	}

}