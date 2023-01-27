

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_16173 {
	
	public static int N;
	public static int[][] vector = {{1,0},{0,1}};
	static int[][] plate;
	
	public static boolean bfs() {
		
		Queue<int[]> queue = new LinkedList<>();
		
		int[] index1 = {0,0};
		
		queue.offer(index1);
		
		int currentI = 0;
		int currentJ = 0;
		
		while(true) {
			
			//실패..
			if(queue.isEmpty()) {
				return false;
			}
			int[] index = queue.poll();
			currentI = index[0];
			currentJ = index[1];

//			System.out.println("poll : " + currentI + ", "+ currentJ);
			
			//목적지 도달!
			if(currentI == N-1 && currentJ == N-1) {
				return true;
			}
			
			
			
			for(int i = 0; i < 2; i++) {
				int jump = plate[currentI][currentJ];
				
				
				int nextI = currentI+vector[i][0]*jump;
				int nextJ = currentJ+vector[i][1]*jump;
				
				
				
				
				//범위를 빠져나가지 않는 선에서 큐에 넣음
				if(nextI >= 0 && nextI < N && nextJ >= 0 && nextJ < N ) {
					if(plate[nextI][nextJ] == 0) {
						continue;
					} else {
						int[] nextIndex = {nextI, nextJ};
						queue.offer(nextIndex);
						
					}
					
//					System.out.println("input : " + nextI + ", " + nextJ);
				}
			}
			plate[currentI][currentJ] = 0;
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//0,0 시작 정사각형 내에서 이동가능
		//오른쪽 아래로만 이동가능
		//n,n 에 도달하면 승리
		//칸 마다 숫자가 쓰여있고 그 만큼 정확히 직선 이동해야한다.
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		plate = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				plate[i][j] = sc.nextInt();
			}
		}
		
		if(bfs()) {
			System.out.println("HaruHaru");
		} else {
			System.out.println("Hing");
		}
		
		sc.close();
	}

}