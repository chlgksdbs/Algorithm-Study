import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_6236 {
    static int maxValue;
    static int sumValue;

    public static int binarySearch(int[] arr, int cnt) {
        int arrSize = arr.length;
        int start = maxValue; // 최소 금액
        int end = sumValue; // 최대 금액
        int answer = 0; // 필요한 최소 금액

        while (start <= end) {
            int mid = (start + end) / 2; // 중간 값 갱신
            int tempCount = 1; // 처음의 경우도 인출한 경우이기 때문에 1로 초기화
            int tempMid = mid;

            for (int i = 0; i < arrSize; i++) {
                if (arr[i] > tempMid) { // 현재 사용할 금액보다 남은 금액이 적은 경우
                    tempMid = mid; // 남은 금액을 통장에 집어넣고 다시 인출
                    tempCount++;
                }
                tempMid -= arr[i];
            }

            if (tempCount > cnt) { // 현재 인출 횟수가 목표 인출 횟수보다 많은 경우
                start = mid + 1;
            }
            else {
                answer = mid; // 최적의 값 저장
                end = mid - 1;
            }
            
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken()); // 돈을 인출하는 횟수

        int[] money = new int[n]; // n일 동안 사용할 금액

        for (int i = 0; i < n; i++) {
            money[i] = Integer.parseInt(br.readLine());
            if (maxValue < money[i]) maxValue = money[i];
            sumValue += money[i];
        }

        System.out.println(binarySearch(money, m));
    }
}
