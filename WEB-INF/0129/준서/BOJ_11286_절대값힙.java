package Sorting;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_11286_절대값힙 {
	static class compare implements Comparable<compare>{
		 int x,y;
		 
		 public compare(int x, int y){
			 this.x = x;
			 this.y = y;
		 }

		@Override
		public int compareTo(compare o) {
			if(this.y == o.y)
				return this.x - o.x; 
			return this.y - o.y;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<compare> pr = new PriorityQueue<>();
		int map[] = new int[n];
		
		for(int i = 0; i < n ; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num ==0 ) {
				if(!pr.isEmpty())
					System.out.println(pr.poll().x);
				else
					System.out.println(0);
			}else {
				pr.add(new compare(num, Math.abs(num)));
			}
		}
		
	}
	

}