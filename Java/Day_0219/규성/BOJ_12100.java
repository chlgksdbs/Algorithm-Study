package acm0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main12100 {
	static int n;
	static int max = 0;
	static int[][] arr, temp, roteTemp;
	static int[] card;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n][n];
		temp = new int[n][n];
		roteTemp = new int[n][n];
		card = new int[5];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		permu(0);
		
		System.out.println(max);
			
	}
	
	static void permu(int cnt) {
		
		if(cnt == 5) {
//			System.out.println(Arrays.toString(card));
			//배열 복사
			copyArr();
			
			//스윕
			for(int i = 0; i < 5; i++) {
				swip(card[i]);
				
			}
			
//			//합계 연산
//			count();
			
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			card[cnt] = i;
			permu(cnt+1);
		}
	}
	
	static void copyArr() {
		for(int i = 0; i < n; i++) {
			for(int j = 0 ; j < n ; j ++) {
				temp[i][j] = arr[i][j];
			}
		}
	}
	
	static void swip(int swipNum) {
		System.out.println("11");
		print();
		//그 방향에 맞게 돌리기
		for(int i = 0;  i< swipNum; i++) {
			rotate();
		}
		System.out.println("22");

		print();
		
		//밀기
		push();
		
		System.out.println("33");

		print();
		
		//원래 방향에 맞게 돌려놓기
		if(swipNum != 0) {	//0이면 안돌려도 됨..
			for(int i = 0;  i< 4-swipNum; i++) {
				rotate();
			}
		}
		
		
	}
	
	//90도 돌리는 기능
	static void rotate() {
		//회전
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				roteTemp[j][n-i-1] = temp[i][j];
			}
		}
		
		//복사
		for(int i = 0; i < n; i++) {
			for(int j=  0; j < n; j++) {
				temp[i][j] = roteTemp[i][j];
			}
		}
		
	}
	
	//아래로 내리는 기능
	static void push() {
		Queue<Integer> queue = new LinkedList<>();
		int tempNum = 0;
		for(int j = 0; j < n; j++) {
			
			for(int i = n-1; i >= 0; i--) {
				if(temp[i][j] != 0) {
//					System.out.println(temp[i][j]);
					//전 값과 합쳐짐
					if(tempNum == temp[i][j]) {
						queue.add(tempNum*2);
						tempNum = 0;
						
					} else {	//전 값과 다름
						if(tempNum != 0) {
							queue.add(tempNum);
							tempNum = temp[i][j];
						} else {
							tempNum = temp[i][j];
						}
						
					}
					temp[i][j] = 0;
				}
			}
			if(tempNum != 0)
				queue.add(tempNum);
			
			
			for(int k = 0; k < queue.size(); k++) {
				int pollNum = queue.poll();
				
				temp[n-1-k][j] = pollNum;
				if(max < pollNum) {
					max = pollNum;
				}
				
			}
			
		}
		
	}
	
	static void print() {
		for(int i = 0; i < n; i++) {
			for(int j=  0; j < n; j++) {
				System.out.print(temp[i][j] + " ");;
			}
			System.out.println();
		}
		System.out.println("----------------------");
	}


}
