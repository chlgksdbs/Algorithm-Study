package Samsung_Type_A_Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14891_톱니바퀴 {
	static int N =4;
	static int cogwheel_size = 8;
	static int map[][];
	static int R;
	static int score;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[N][cogwheel_size];
		
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < cogwheel_size; j++) {
				map[i][j]  = temp.charAt(j) -'0';
			}
		} //for end 
		
		
		R = Integer.parseInt(br.readLine());
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			// 인덱스 .... 어후,..,.-1 해주자 
			int r_num = Integer.parseInt(st.nextToken()) -1;
			int r_d = Integer.parseInt(st.nextToken());
			
			operation(r_num, r_d);
			
		}
		
		int cnt = 1;
		for (int i = 0; i < N; i++) {
			score += Math.pow(2,i)* map[i][0];
			
		}
		
		System.out.println(score);
		
		
	}//main end 
	
	
	
	//회전 결과
	static void Rotate(int num, int d) {
		print(map);
		if(d == 1) {// 시계방향이라면 
			// 큐 이용하자
			int temp = map[num][cogwheel_size -1];
			for(int i = cogwheel_size -1; i>0; i--) {
				map[num][i] = map[num][i-1];
			}
			map[num][0] = temp;
			
		//	print(map);
			
		}else { // 반시계 방향이라면 
			int temp = map[num][0];
			for(int i = 0 ; i < cogwheel_size - 1; i++ ) {
				map[num][i] = map[num][i+1];
			}
			map[num][cogwheel_size-1] = temp;
		//	print(map);
		}
		
	}//rotate end
	
	
	
	
	static void operation(int num, int d) { // 회전 연산 
		leftgear(num -1, -d);
		rightgear(num + 1, -d);
		Rotate(num, d);
	}
	
	
	
	private static void rightgear(int num, int d) { // 오른쪽 확인 
		if(num > N-1) return;
		if(map[num][6] == map[num -1][2]) return;
		rightgear(num +1, -d);
		Rotate(num, d);
	}




	private static void leftgear(int num, int d) {//왼쪽 확인 
		if(num  < 0) return;
		if(map[num][2] == map[num +1][6]) return;
		leftgear(num -1, -d);
		Rotate(num, d);
		
	}




	static void print(int arr[][]) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("------------------------");
	}//end print

}