package BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1920_수찾기 {
    static int[] arr;


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        arr= new int[n];
        for(int i=0;i<n;i++){
            arr[i]= scan.nextInt();
        }
        Arrays.sort(arr);

        int k =  scan.nextInt();
        for(int i=0;i<k;i++){
            int tmp= scan.nextInt();
            int result = BinarySearch(tmp);
            if(result>=0) System.out.println(1);
            else System.out.println(0);
        }
    }


    static int BinarySearch(int n){

        int left =0;
        int right = arr.length-1;

        while(left <=right){
            int median = (left + right) / 2;

            if (arr[median] == n) {
                return median;
            }
            else if (arr[median] > n) {
                right = median - 1;
            }
            else {
                left = median + 1;
            }

        }
        return -1;
    }


}