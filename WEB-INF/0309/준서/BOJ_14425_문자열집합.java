package String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_14425_문자열집합 {
    static class Node{

        Map<Character,Node> childNode = new HashMap<>();

        boolean isEnd;
    }

    static class Trie{

        Node root = new Node();
        void insert(String str) {

            Node node = this.root;

            int size = str.length();

            for (int i = 0; i < size; i++) {
                node = node.childNode.computeIfAbsent(str.charAt(i), key -> new Node());
            }

            node.isEnd = true;
        }

        boolean search(String str){
            Node node = this.root;

            int size = str.length();

            for (int i = 0; i < size; i++) {
                node = node.childNode.getOrDefault(str.charAt(i),null);

                if(node == null){
                    return false;
                }
            }

        return node.isEnd;
        }

    }




    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Trie trie = new Trie();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M  = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            trie.insert(temp);
        }

        int cnt = 0;

        for (int i = 0; i < M; i++) {
            String temp = br.readLine();
            if(trie.search(temp)){
                cnt++;
            }

        }
        System.out.println(cnt);
    }
}
