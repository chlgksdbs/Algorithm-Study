package day0213;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_1991 {
    static ArrayList<Integer>[] al;
    static int n;
    static StringBuilder postsb = new StringBuilder();
    static StringBuilder insb = new StringBuilder();
    static StringBuilder presb = new StringBuilder();


    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         n = Integer.parseInt(br.readLine());

        al = new ArrayList[26];

        for(int i=0;i<26;i++)
            al[i] = new ArrayList<Integer>();

        for(int i=0;i<n;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            char root = st.nextToken().charAt(0);
            int numRoot = root-'A';
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            if(left =='.')  al[numRoot].add(0,null);
            else al[numRoot].add(0,left-'A');

            if(right =='.')  al[numRoot].add(1,null);
            else al[numRoot].add(1,right-'A');

        }


        preOrder(0,al[0].get(0),al[0].get(1));
        inOrder(0,al[0].get(0),al[0].get(1));
        postOrder(0,al[0].get(0),al[0].get(1));

        System.out.println(presb);
        System.out.println(insb);
        System.out.println(postsb);
        
    }

    private static void postOrder(int i, Integer left, Integer right) {

        if(al[i].size()==0)
            return;

        if(left!=null)  postOrder(left,al[left].get(0),al[left].get(1));
        if(right!=null)  postOrder(right,al[right].get(0),al[right].get(1));
        postsb.append((char)(i+'A')+"");
    }

    private static void inOrder(int i, Integer left, Integer right) {

        if(al[i].size()==0)
            return;
        if(left!=null)  inOrder(left,al[left].get(0),al[left].get(1));
        insb.append((char)(i+'A')+"");
        if(right!=null)  inOrder(right,al[right].get(0),al[right].get(1));

    }

    private static void preOrder(int i, Integer left, Integer right) {

        if(al[i].size()==0)
            return;
        presb.append((char)(i+'A')+"");
        if(left!=null)  preOrder(left,al[left].get(0),al[left].get(1));
        if(right!=null)  preOrder(right,al[right].get(0),al[right].get(1));


    }

}