package ac0216;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main5639_2 {
	static ArrayList<Integer> mylist;
	static int[] resultlist;
	static int top = 0;
	static int n;
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//길이는 알 수 없기 때문
		mylist = new ArrayList<>();
		while(true) {
			try {
				mylist.add(Integer.parseInt(br.readLine()));
			} catch (Exception e) {
				break;
			}
		}
		n = mylist.size();
		resultlist = new int[n];
		postorder(0,n-1);
		//뒤에서 부터 출력
		for(int i = n-1 ; i >= 0 ; i--) {
			System.out.println(resultlist[i]);
		}
	}
	
	static void postorder(int start, int end) {
		if(start > end) return;
		//저장
		resultlist[top++] = mylist.get(start);
		
		//서브 트리 index를 -1로 초기화
		int left = -1;
		int right = -1;
		
		//전위순회+이진검색트리 특성상 다음 값이 작으면 반드시 왼쪽 노드로 삽입
		if(start+1 <= n-1 && mylist.get(start) > mylist.get(start+1)) 
			left = start+1;
		
		//오른쪽은 언제 나올지 알 수 없기 때문에 for 문으로 처리
		for(int i = start+1 ; i <= end ; i++){
			if(mylist.get(start) < mylist.get(i)) {
				right = i;
				break;
			}
		}
		
		//오른쪽 서브트리 세부 내용 처리
		//후위 순회 거꾸로 출력을 위해 오른쪽 먼저
		if(right != -1)
			postorder(right, end);
		
		if(left != -1)
			
			//왼쪽 에서 나온 서브 트리 처리
			//단, 루트보다 오른쪽이 있는 경우는 그 이전 까지만 처리
			postorder(left,right == -1 ? end : right-1);
	}

}