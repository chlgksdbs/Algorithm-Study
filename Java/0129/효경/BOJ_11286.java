package day0129;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_11286 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		
		PriorityQueue<Integer> q = new PriorityQueue<>(new absSort());
		
		for(int i=0;i<n;i++) {
			
			int tmp = scan.nextInt();
			if(tmp == 0) {
				if(q.isEmpty()) System.out.println(0);
				else System.out.println(q.poll());
			}else 
				q.add(tmp);

		}
		
	}

}

class absSort implements Comparator<Integer>{

	@Override
	public int compare(Integer o1, Integer o2) {
		int abs1 = Math.abs(o1);
		int abs2 = Math.abs(o2);
		
		if(abs1 == abs2) return o1-o2;
		return abs1-abs2;
	}
	
}