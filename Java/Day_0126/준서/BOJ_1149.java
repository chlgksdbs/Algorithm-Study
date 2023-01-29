import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1149 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		//int n = Integer.parseInt(br.readLine());
		// 최초의 요소가 이전 값을 참조하여야하는데 이전 값은 없음으로 1부터시작하고 0번쨰 행을 0으로 선언해줘
		// 첫번쨰 참조 비용을 0으로 지정
		int dp[][] = new int[n + 1][3]; // dp_table RGB
		int map[][] = new int[n + 1][3]; // 입력 받을맵

		for (int i = 1; i <= n; i++) {// 0번쨰 행은 0으로 초기화해야함으로 1번 행부터 시작
			for (int j = 0; j < 3; j++) { // 순서대로 RGB
//				map[i][j] = Integer.parseInt(br.readLine());
				map[i][j] = sc.nextInt();
			}
		}
		// map dp_table전부 0번째 인덱스를 0으로 초기화
		dp[0][0] = dp[0][1] = dp[0][2] = map[0][0] = map[0][1] = map[0][2] = 0;

		for (int i = 1 ; i <= n; i++) {                                // 과거의 선택이 현재에 영향을 주고 있다 
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + map[i][0]; // n-1쨰가R을 골랐다면n은 GB중에 고를 수 있다 이 떄 비용은 n -1 번 R 선택 + 현재의 G, B 최소
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + map[i][1]; //위와 같이 G를 골랐다면 
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + map[i][2]; //위와 같이 B를 골랐다면 
		}
		System.out.println(Math.min(dp[n][2],(Math.min(dp[n][0], dp[n][1])))); // 비용이 가장 적은 dp 테이블의 값을 출력 
	}
}
