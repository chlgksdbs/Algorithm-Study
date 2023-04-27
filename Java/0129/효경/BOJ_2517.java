package day0129;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

    public class BOJ_2517 {
        static int N;
        static int[][] arr;
        static int[] rank;

        public static void main(String[] args) throws Exception{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());

            arr = new int[N+1][2]; //실력, index
            rank = new int[N+1];

            for(int i=1;i<=N;i++){
                arr[i][0]=Integer.parseInt(br.readLine());
                arr[i][1] =i;
                rank[i]=i;
            }

            merge(1,N);

          //  출력한다.
            StringBuffer sb = new StringBuffer();
            for(int i=1;i<=N;i++){
                sb.append(rank[i]+"\n");
            }

            System.out.println(sb);

        }

        private static void merge(int left, int right) {
            if(right-left<1)
                return;

            int mid = (left + right) / 2;

            //데이터를 2개로 분할
            merge(left, mid);
            merge(mid+1, right);

            mergeSort(left, mid, right);

        }


        private static void mergeSort( int left, int mid, int right) {

            int[][] temp = new int[right - left+1][3]; //정렬된 데이터를 담아둘 임시 배열
            int t = 0, l = left, r = mid+1; //포인터 선언

            //두 그룹을 병합
            while (l <= mid && r <= right) {
                if (arr[l][0] >= arr[r][0]) {  //오름차순 앞에가 더 크면 tmep배열에 넣는다.
                    temp[t][0] = arr[l][0];
                    temp[t++][1] = arr[l++][1];
                    //temp[t++][2] = arr[l++][2];

                } else { //오름차순인데 뒤가 더 크다?? inversions발생(자신보다 앞에있는 숫자 중에 자신보다 큰 수)


                    temp[t][0] = arr[r][0];
                    temp[t][1] = arr[r][1];
                    rank[arr[r][1]] -=(mid-l+1); //인덱스를 가지고 rank에 접근해서 inversions의 개수만큼 등수를 빼준다.
                    t++;
                    r++;
                }
            }

            //한쪽 그룹이 모두 선택된 후 남아 있는 값 정리
            while (l <= mid) {
                temp[t][0] = arr[l][0];
                temp[t++][1] = arr[l++][1];
            }

            while (r<= right) {
                temp[t][0] = arr[r][0];
                temp[t++][1] = arr[r++][1];
            }

            //임시 배열을 원배열에 복사
            for (int i = left; i <= right; i++) {
                arr[i][0] = temp[i-left][0];
                arr[i][1] = temp[i-left][1];
            }


           // System.out.println("["+left+", "+right+"] :"+Arrays.toString(rank));
        }




    }
