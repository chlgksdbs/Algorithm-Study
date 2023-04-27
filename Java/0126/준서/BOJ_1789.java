import java.util.Scanner;

public class BOJ_1789 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		long num = scan.nextLong();
		long sum = 0;
		int count = 0;
		
		for(int i=1; ; i++) {
			if(sum > num)	break;  //num 40이라면 서로 다른 수를 더해야하기 때문에 1부터 다 더해
			//n이 최대값이 되려면 작은 수 부터 더해야해 
			sum += i; // 1+ 2+ 3 + 4 + 5+ 6 + 7 + 8 + 9 = 45 
			count ++; 
		}
		System.out.println(count-1); // 이때 수를 맞춰주기 위해 5를 뺴야하니까 -1 카운트  
									//1+2+3+4+6+78+9
		scan.close();
	}

}