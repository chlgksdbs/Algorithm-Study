
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]> shortcut = new ArrayList<>();
		
		for(int i = 0; i < n ; i ++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			int third = Integer.parseInt(st.nextToken());
			
			int [] edge  = {first,second,third};
			shortcut.add(edge);
			
		}
		
		
		int[] highway = new int[d+1];
		Arrays.fill(highway, 987654321);
		
		highway[0] = 0;
		for(int path = 0 ; path < d+1; path++) {
			if(path > 0)
				highway[path] = Math.min(highway[path-1]+1, highway[path]);
		
			for(int[] mylist : shortcut) {
				if(mylist[0] == path && mylist[1] <= d) {
					highway[mylist[1]] = Math.min(highway[mylist[1]], mylist[2]+highway[mylist[0]]) ;
				}
			}
		}
		
		
		System.out.println(highway[d]);
		
		
	}
}
