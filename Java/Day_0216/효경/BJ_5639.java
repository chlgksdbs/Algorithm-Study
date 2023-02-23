package study.day0216;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BJ_5639 {
    static List<Integer> list= new ArrayList<>();

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        while(scan.hasNext())
            list.add(scan.nextInt());

        fromPretoPost(0,list.size()-1);


    }

    private static void fromPretoPost(int start,int end){

        int root =list.get(start);
        int left =-1;
        int right = -1;

        //right찾기
        for(int i= start+1;i<=end;i++){
            if(list.get(i)> root){
                right = i;
                break;
            }
        }
        
        //오른쪽 노드가 존재함
        if(right >-1) {
            if (start + 1 < right) //왼쪽 노드 존재함
                fromPretoPost(start + 1, right - 1);
            fromPretoPost(right, end);

        }else {//오른쪽 노드가 존재하지 않음
            if(end >start)  //왼쪽 노드가 존재함
                fromPretoPost(start + 1, end);
            
            
        }

        System.out.println(root);


    }

}
