package study.day0216;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_2263 {

    static int N;
    static int[] inOrder;
    static int[] postOrder;
    static StringBuilder sb= new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());

        inOrder = new int[N+1];
        postOrder = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++)
            inOrder[i] =  Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++)
            postOrder[i] =  Integer.parseInt(st.nextToken());

        getPreOrder(1,N,1,N);

        System.out.println(sb);
    }

    private static void getPreOrder(int ins, int ine, int posts, int poste) {

        if(ins>ine ) return;

        sb.append(postOrder[poste]+" ");

        if(ins ==ine) return;

        int inRootIndex=-1;
        for(int i=ins; i <= ine;i++){
            if(inOrder[i] == postOrder[poste]){
                inRootIndex = i;
            }
        }

        int leftSize = inRootIndex - ins;

        //왼쪽 subTree
        getPreOrder(ins, inRootIndex-1, posts,  posts+leftSize-1);

        //오른쪽 subTree
        getPreOrder(inRootIndex+1, ine, posts+leftSize,  poste-1);


    }
}