package acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main6236 {

	//N일 동안 사용할 금액을 계산
	//M번만 빼서 사용
	//한번에 K만큼만 뺌
	//남은돈은 다음 날 사용가능 단 부족하면 다시 넣어야 함
	//K원을 M 번 만큼 빼면됨
	//가장 작은 K를 구하라

	//핵심은 가장 큰 수보다는 높지만 가장 작은 수를 찾는 것

	//parametric binary search

	//최소 값(lower) 은 일일 사용 최고값
	//최고 값은(higher) 은  

	public static int lower = 0;
	public static int higher = 0;

	public static int N;

	public static int M;

	public static int[] arr;

	

	public static void binarySearch(){
		
		int middle = (lower + higher)/2;
		
		
		while(lower < higher){
			
			// System.out.println(middle);
			int sum = 0;

			middle = (lower + higher)/2;

			int mod = 0;			
			
			for(int i = 0; i < N; i++){

				//나머지 값에서 현재값을 빼고 나머지 값 저장
				if(mod >= arr[i]){
					mod = mod - arr[i];
					
				//나머지 값이 현재 값보다 작으면 sum을 하나 늘리고 다시 채움
				} else {
					sum++;
					mod = middle;
					mod = mod - arr[i];
				}
			}


			//연산이 끝나고 sum 의 숫자가 작거나 같다면 조건은 맞는데 숫자가 큰거 숫자를 줄여서 최소값을 찾아야 함 
			if(sum <= M){
				higher = middle;
			}

			//sum의 숫자가 너무 크다면 조건이 안맞는거 숫자를 키워야 함
			if(sum > M){
				lower = middle + 1;
			}

			middle = (lower + higher)/2;

			// System.out.println("middle : " + middle);
			// mod = middle;
		}

		System.out.println(middle);



	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];

		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
			higher += arr[i];

			//입력과정에서 lower 값을 저장
			if(arr[i] > lower){
				lower = arr[i];
			}
		}

		binarySearch();



	}	
}
