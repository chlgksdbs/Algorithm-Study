package acm0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main14891 {

	static int[][] gear = new int[4][8];
	static int k;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 4; i++) {
			String line = br.readLine();
			for (int j = 0; j < 8; j++) {
				gear[i][j] = line.charAt(j)-'0';
			}
		}

		k = Integer.parseInt(br.readLine());

		for (int i = 0; i < k; i++) {
			visited = new boolean[4];
			st = new StringTokenizer(br.readLine());
			int gearNum = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			rotate(gearNum-1, dir);
//			for(int j = 0; j < 4; j++) {
//				System.out.println(Arrays.toString(gear[j]));;
//			}
		}
		
		int sum = 0;
		for(int i = 0; i<4; i++) {
			if(gear[i][0] == 1) {
				sum = sum + (int)Math.pow(2, i);
			}
		}
		
		System.out.println(sum);
		

	}

	static void rotate(int gearNum, int dir) {
		visited[gearNum] = true;
		int nDir = 1;
		if(dir == 1) {
			nDir = -1;
		}
		//왼쪽 기어 확인
		if (gearNum - 1 >= 0 && !visited[gearNum - 1]) {
			if(gear[gearNum - 1][2] != gear[gearNum][6]) {
				rotate(gearNum - 1, nDir);
			}
		}
		
		//오른쪽 기어 확인
		if (gearNum + 1 < 4 && !visited[gearNum + 1]) {
			if(gear[gearNum + 1][6] != gear[gearNum][2]) {
				rotate(gearNum + 1, nDir);
			}
		}
		
		//회전
		
		if(dir == -1) {	//반시계
			int temp = gear[gearNum][0];
			for(int i = 1 ; i < 8; i++) {
				gear[gearNum][i-1] = gear[gearNum][i];
			}
			gear[gearNum][7] = temp;
		}else {	//시계
			int temp = gear[gearNum][7];
			for(int i = 7 ; i > 0; i--) {
				gear[gearNum][i] = gear[gearNum][i-1];
			}
			gear[gearNum][0] = temp;
		}
	}
	
	

}
