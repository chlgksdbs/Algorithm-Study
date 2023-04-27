package Tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class BOJ_5639_동엽이형버전으로_풀어보기 {
	static ArrayList<Integer> TreeList;
	static int[] result;
	static int treeTop = 0; // 리트의 맨 위를 의미
	static int n; // 리스트의 사이즈를 버장
	
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		TreeList = new ArrayList<>();
		
		while(true) { // 오류 발생 시 멈춰@
			try {
				TreeList.add(Integer.parseInt(br.readLine()));
			} catch(Exception e) {
				break; 
			}
		}
		
		
		
		n = TreeList.size();
		
		result = new int[n];
		
		postOrder(0,n-1);
		
		for (int i = n-1; i >=0 ; i--) {
			System.out.println(result[i]);
			
		}
		
		
		
	}
	
	
	static void postOrder(int start, int end) {
		if(start > end)
			return;
		result[treeTop++] = TreeList.get(start);
		//result 0 = 트리의 start = 
		// 현재 result 0 = 50
		int left = -1;
		int right = -1;
		
		if(start +1 <= n-1 && TreeList.get(start)> TreeList.get(start +1))
			// 길이 안에 있고 현재 요소가 다음 요소보다 큰 경우  
			// 50 > 30 이라면 
			left = start +1;
		//left = 30 
		
		for(int i = start +1; i <= end; i++) {
			//30의 인덱스인 2부터 끝까지 돌면서  
			if(TreeList.get(start)< TreeList.get(i)) {
				// 내 이전 50 보다 큰 값을 찾아서 
				right = i; // 넣기 
				// 30 좌   50 루트  더큰 놈이 오른쪽 
				break;
			}
		}
		
		
		if(right != -1) {
			postOrder(right, end);
		}
		if(left != -1) {
			postOrder(left,right == -1? end : right -1);
		}
	}
	
	
	
	
	
	

}