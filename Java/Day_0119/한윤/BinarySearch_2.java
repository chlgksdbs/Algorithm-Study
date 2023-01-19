import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch_2 {
    public static long BinarySearch(int[] lans, int lan_size, int key) {
        long start = 1; // 자를 수 있는 랜선의 최솟값
        long end = lans[lan_size - 1]; // 자를 수 있는 랜선의 최댓값
        long answer = 0; // 정답을 저장할 변수

        while (start <= end) {
            long mid = (start + end) / 2; // 중간점 초기화
            int lan_count = 0; // 만들 수 있는 랜선의 개수
            
            for (int i = 0; i < lan_size; i++) {
                lan_count += lans[i] / mid; // 해당 랜선으로 부터 만들 수 있는 개수 count
            }
            
            if (lan_count < key) { // 목표 랜선 개수보다 작은 개수라면
                end = mid - 1;
            }
            else { // 목표 랜선 개수보다 많은 개수라면
                answer = mid; // 현재 시점 최적의 경우를 저장
                start = mid + 1;
            }  
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt(); // 이미 가지고 있는 랜선의 개수
        int n = sc.nextInt(); // 필요한 랜선의 개수
        int[] lans = new int[k];

        for (int i = 0; i < k; i++) {
            lans[i] = sc.nextInt();
        }

        Arrays.sort(lans); // 오름차순 정렬

        System.out.println(BinarySearch(lans, k, n));
    }
}
