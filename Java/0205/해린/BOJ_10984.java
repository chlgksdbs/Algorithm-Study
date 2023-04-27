import java.util.Scanner;

public class Main {
	
	public static void permutation(int[] arr, int[] out, boolean[] visited, int depth, int r) {
		if(depth == r) {
			for(int n : out) {
				System.out.print(n + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				out[depth] = arr[i];
				
				permutation(arr, out, visited, depth+1, r);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] arr = new int[N];
		int r = N;
		for(int i=0; i<N; i++) {
			arr[i] = i + 1;
		}
		
		int[] out = new int[r];
		boolean[] visited = new boolean[arr.length];
		
		permutation(arr, out, visited, 0, r);
	}
}
