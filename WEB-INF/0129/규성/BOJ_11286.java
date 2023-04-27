package acmicpc0126_;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main11286 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		PriorityQueue<Num> queue = new PriorityQueue<>();
		
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			int num = sc.nextInt();
			
			arr[i] = num;
			
		}
		
		for(int i = 0; i < n; i++) {
			
			//출력
			if(arr[i] == 0) {
				if(!queue.isEmpty())
					System.out.println(queue.poll().num);
				else {
					System.out.println(0);
				}
			} else {	//입력
				queue.add(new Num(arr[i]));
			}
		}
		
		sc.close();
		
		
		

	}
	
	
}



class Num implements Comparable<Num>{
	int num;
	
	Num(int num){
		this.num = num;
	}

	@Override
	public int compareTo(Num o) {
		// TODO Auto-generated method stub
		if(Math.abs(this.num) - Math.abs(o.num) == 0) {
			if(this.num > o.num) {
				return 1;
			}else {
				return -1;
			}
		}
		return Math.abs(this.num) - Math.abs(o.num);
	}
	
}
