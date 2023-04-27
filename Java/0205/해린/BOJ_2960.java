import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		boolean[] arr = new boolean[N+1];
		Arrays.fill(arr, true);
	
		int cnt = 0;
		for(int i = 2; i <= N; i++){
			if(arr[i] == true){
				arr[i] = false;
				cnt++;
				if (cnt == K) {
					System.out.println(i);
					return;
				}
				int j = 2;
				while(i * j <= N){
					if(arr[i * j] == true) cnt++;
					arr[i * j] = false;
					if (cnt == K) {
						System.out.println(i*j);
						return;
					}
					j += 1;
				}
			}
		}
	}
}
