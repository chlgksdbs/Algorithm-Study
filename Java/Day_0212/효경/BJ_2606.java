package day0213;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_2606 {

    static int n;
    static ArrayList<Integer>[] al;
    static  boolean[] visit;
    static int count;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        al = new ArrayList[n+1];
        visit = new boolean[n+1];

        for(int i=1;i<=n;i++){
            al[i] = new ArrayList<>();
        }

        int k=scan.nextInt();
        for(int i=0;i<k;i++){
            int a = scan.nextInt();
            int b =scan.nextInt();

            al[a].add(b);
            al[b].add(a);

        }

        BFS(1);
        System.out.println(count);

    }
    static void BFS(int i){

        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        visit[i] =true;

        while(!q.isEmpty()){

            int num = q.poll();
            for(int tmp : al[num]){
                if(!visit[tmp]){
                    count++;
                    visit[tmp] =true;
                    q.offer(tmp);}
            }
        }
    }
}
