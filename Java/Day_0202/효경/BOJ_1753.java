package day0202;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1753 {

    public static final int INF = Integer.MAX_VALUE; // 무한을 의미하는 값 설정
    static int vn;
    static int e;
    static int k;
    static ArrayList<ArrayList<Node>> al;
    static boolean[] visit;
    static int[] distance;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        vn = scan.nextInt();//정점의 개수
        e = scan.nextInt(); //간서의 개수
        k = scan.nextInt(); //시작점


        //초기화
        al = new ArrayList<>();
        for(int i=0;i<=vn;i++){
            al.add(new ArrayList<Node>());
        }
        visit = new boolean[vn+1];
        distance = new int[vn+1];
        Arrays.fill(distance,INF);


        for(int i=0;i<e;i++) {

            int u = scan.nextInt(); //u->v
            int v = scan.nextInt();
            int w = scan.nextInt(); //가중치

            al.get(u).add(new Node(v, w));
        }


        visit[k]=true;
        distance[k]=0;
        updateDistance(k); //시작노드부터 거리 update

        for(int i=1;i<=vn;i++){

            if(i==k) continue;
            int now = searchLow();
            visit[now] =true;
            updateDistance(now);

        }


        for(int i=1;i<=vn;i++) {
            if(distance[i] == INF)
                System.out.println("INF");
            else System.out.println(distance[i]);
        }
    }

    static void updateDistance(int n){

        int nowdis = distance[n];

        for( Node tmp :al.get(n)){
            int tmpN = tmp.getIndex();
            int tmpD =tmp.getDistance();

            if(distance[tmpN] > nowdis +tmpD ){
                distance[tmpN] = nowdis +tmpD;
            }

        }
    }

    static int searchLow(){

        int min= INF;
        int lowIndex=0;
        for(int i=1;i<=vn;i++){
            if(i== k) continue;
            if(visit[i] == true) continue;

            if(distance[i]<min){
                min = distance[i];
                lowIndex=i;
            }

        }
        return lowIndex;
    }
}
class Node {
    private int index;
    private int distance;

    Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

    public int getIndex() {
        return index;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return index+", "+distance;
    }
}