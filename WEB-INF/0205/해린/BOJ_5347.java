import java.util.Scanner;

public class Main {
	
	public static long getGCD(long a, long b) {
		long tmp;
		if(a < b) {
			tmp = b;
			b = a;
			a = tmp;
		}
		if(b == 0) return a;
		
		return getGCD(b, a%b);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i=0; i<n; i++) {
			long a = sc.nextInt();
			long b = sc.nextInt();
			
			long gcd = getGCD(a, b);
			long lcm = (a * b) / gcd;
			
			System.out.println(lcm);
		}
	}
}
