import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BeakJoon_16173 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt(); // n * n 사이즈 입력
		int[][] map = new int[n][n]; // 맵 작성
		boolean[][] visited = new boolean[n][n];// 방문여부 확인하는 배열 선언

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt(); // 정보 입력
//				visited[i][j] = false; // 디폴트 초기화가 false임으로 생략가능

			}
		}//end to for for 
		
		bfs(map,visited); //bfs돌리기 

	}//end to main
	
	static void bfs(int[][] map, boolean[][] visited) { //bfs 메소드 선
		//static을 이용해 메모리에 항상 상주할 수 있도록 선언 
		int len = map.length;
		boolean success = false;
		Queue<int[]> que = new LinkedList<>(); //큐 선언
		que.add(new int[] {0,0}); // 시작 정점 추가 
		
		while(!que.isEmpty()) { //배열이 빌 떄 까지 반복   
			int[] focus = que.poll(); // 큐에 첫번소 뺴기 
			int r = focus[0], c = focus[1]; //행과 열 저장
			visited[r][c] =true; // 방문처리하기 
			
			
			if(map[r][c] == -1) { // 방문 노드가 -1이라면 = 도착지점이라면 끝내
				success = true;
				break;
			}
			
			int down = r + map[r][c], right = c + map[r][c]; //이동한 노드 
			
			if(down <len && !visited[down][c]) { // 범위 안에 있다면 
				que.add(new int[] {r + map[r][c],c});// 아래로 이동한 값 큐에 저장 
				visited[down][c] = true;// 해당 노드 방문처리 
				}
			
			if(right < len && !visited[r][right]) {// 범위 안에 있다면 
				que.add(new int[] {r, c+ map[r][c]});// 오른쪽 이동한 값 큐에 저장 
						
			}
		}//end to while
		if(success) { // 잘 도착했다면 
			System.out.println("HaruHaru");
		}else {
			System.out.println("Hing"); //도착을 못 했다면 
		}
	}
}//end to class
