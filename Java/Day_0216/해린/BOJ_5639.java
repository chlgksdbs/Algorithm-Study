import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	
	static int N;
	static int idx;
	
	static ArrayList<Integer> preNodeList;
	static int[] nodeArr;
	
	public static void postorder(int start, int end) {
		// 예외처리
		if(start > end) {
			return;
		}
		int left = -2;		// 초기값
		int right = -2;		// 초기값
		
		nodeArr[idx++] = preNodeList.get(start);	// 루트노드 우선 넣기
		
		if(start+1 < N && preNodeList.get(start) > preNodeList.get(start+1)) {	// 그 다음 노드가 루트보다 작아?
			left = start + 1;													// 그럼 왼쪽 서브트리의 루트야
		}
		for(int i = start+1; i<=end; i++) {							// 재귀에서 end값이 바뀌니까 N까지 탐색이 아니라  end까지만!
			if(preNodeList.get(start) < preNodeList.get(i)) {		// 루트보다 큰 노드가 있어?
				right = i;											// 그럼 오른쪽 서브트리의 루트야
				break;												// 젤 처음 나온 큰 값 찾았으면 탈출시켜줘!!!
			}
		}
		// 재귀돌리며 왼쪽, 오른쪽 서브트리를 완성해보자
		// 출력을 보면 오른쪽 서브트리먼저 출력하지? (역순으로 출력한다는 기준)
		if(right != -2) {		// 값이 있으면!(편향이면 없을 수도 있엉)
			postorder(right, end);
		}
		if(left != -2) {
			postorder(left, right != -2 ? right - 1 : end);
		}
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		preNodeList = new ArrayList<>();
		
		while(true) {
			try {
				preNodeList.add(Integer.parseInt(br.readLine()));			
			}catch(Exception e) {
				break;
			}
		}

//		for(int x : preNodeList) {
//			System.out.print(x + " ");
//		}
//		System.out.println();
		
		N = preNodeList.size();
		nodeArr = new int[N];	// 입력받은 노드 수 크기 만큼 배열 생성
		
		postorder(0, N-1);
		
		// nodeArr 역순으로 출력하면 후위순회한 결과값!
		for(int i=N-1; i>=0; i--) {
			System.out.println(nodeArr[i]);
		}
	}
}
