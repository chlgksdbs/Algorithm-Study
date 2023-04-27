package Samsung_Type_A_Test;
//타순 고정, N이닝 반복, 9명으로 구성, 9번까지 쳤는데 노아웃> 다시 1번부터 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 4번까지 치고 3아웃 다음 공격때 5번부터 시작된다 

// 타순을 정하려는 문제 
//1-4는 고정되어있고 5-9까지 순서대로 짜를거 
// 순열 돌려서 최고 점을 따는걸로 진행하면 되지 않능ㄹ까?

// 아웃 카운트, 현재 선수 플래그, 최대 점수는 최대로 루에 사람이 있는 상태에서 홈런을 치는 것 
// 고려할 사항은? 56789의 타 순 




import java.util.Scanner;

// 1번 선수를 4번타자로 고정하고 나머지 경우의 수를 구하는 프로그램

// 남은 8명을 돌려서 최대의 값을 가지고 가는 알고리즘을 만드는 것 목표일것 인데 

// 그르트포스 알고리즘과 순열, 재귀를 이용하여 문제 해결을 하면 좋을 것 같다

// 선수 1-9번까지 선수의 순서를 입력받을 배열 선언

public class BOJ_17281_야구 {
	static int person[] = new int[10];
	static boolean isSelected[] = new boolean[10];
	static int innings;
	// 이닝 마다 정보를 입력받을 배열 선언
	static int play_innings[][];
	static int Max = Integer.MIN_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		innings = sc.nextInt();

		play_innings = new int[innings + 1][10];

		// 각 이닝에 for 선수의 정보를 넣어주는 것

		for (int i = 1; i <= innings; i++) {
			for (int j = 1; j < 10; j++) {
				play_innings[i][j] = sc.nextInt();
			}
		}
		// 나머지 8명 선수에 대한 값을 저장하는 배열이
		person[4] = 1; // 4번째 자리에 첫번쨰 선수를 넣어줌 -> 1번 선수를 4번 타석에 배치
		isSelected[1] = true; // 1번 선수는 사용을 했다.

		//print(play_innings);
		perm(1); // 순열을 돌리면서 각 배치에 해당하는 최대 점수를 받아오는 방식을 선택할 예정임
		System.out.println(Max);

	}

	static void perm(int idx) {
		if (idx > 9) {
			// 저장된 값을 다른 메서드 전송시켜서 결과를 도출해야겠지
			//System.out.println("나 안들어와?");
			
			max_score();
			return;
		}

		if (idx == 4) {
			perm(idx + 1); // 만약에 1번 선수인 4번 타자를 움직일려고하면 제외시키는거지
			return;
		}

		// 1번 선수는 이미 사용중이고 고정이니까 나머지 선수들로만 생각해보자
		for (int i = 2; i <= 9; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				person[idx] = i;
				perm(idx + 1);
				isSelected[i] = false;
			}
		}
	}

	private static void max_score() {
		int score = 0;
		int inning = 1;
		int out = 0;
		int base[] = new int[4];
		int cnt = 1;

		while (inning <= innings) {
			if (out == 3) {
				inning += 1;
				out = 0;
				for (int i = 0; i < base.length; i++) {
					base[i] = 0;
				}
				continue;
			}

			int hitter = person[cnt];
			int result = play_innings[inning][hitter];

			base[0] = 1; // 타석

			switch (result) {
			// 만루 - 안타 
			case 1:
				score += base[3];
				base[3] = base[2];
				base[2] = base[1];
				base[1] = base[0];
				base[0] = 0;
				break;
				// 만루에서 2루타 
			case 2:
				score += base[2] + base[3];
				base[3] = base[1];
				base[2] = base[0];
				base[0] = base[1] = 0;
				break;
				
				// 만루에서 3루타 
			case 3:
				score += base[1] + base[2] + base[3];
				base[3] = base[0];
				base[0] = base[1] = base[2] = 0;
				break;
				// 만루 홈런 
			case 4:
				score += base[0] + base[1] + base[2] + base[3];
				base[0] = base[1] = base[2] = base[3] = 0;
				break;
				// 못 쳤다... 
			default:
				out++;
				base[0] = 0;
				break;
			}
			
	
			 Max = Math.max(Max, score);
			 
			 // 타자가 9번까지 돌았으면 
			 if(cnt == 9)
				 cnt = 1;
			 else
				 cnt++;
			 // 안돌았으면 
		}
	}

	

	static void print(int[][] arr) {

		for (int i = 1; i <= innings; i++) {
			for (int j = 1; j < 10; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("---------------------------");

	}
}
