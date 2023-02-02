import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int num = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)-> { return (o1[0]-o2[0]) == 0 ? o1[1]-o2[1] : o1[0]-o2[0];});
		for (int i = 0; i < n; i++) {
			num = sc.nextInt();
			if(num != 0) {
				pq.add(new int[] {Math.abs(num),num});
			}else {
				if(pq.isEmpty()) System.out.println(0);
				else {
					System.out.println(pq.poll()[1]);
				}
			}
		}
	}

}
