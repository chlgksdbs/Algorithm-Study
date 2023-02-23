package day0213;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class BJ_5639 {

    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data=data;
        }

        @Override
        public String toString() {
            return data+" ";
        }
    }


    public static void main(String[] args) throws Exception{

        Scanner scan = new Scanner(System.in);
        List<Node> list = new LinkedList<>();
        int depth=0;
        Stack<Node> stack = new Stack<>();
        Node root=null ;

        boolean depthleft = true;


        
        
        while(scan.hasNext()){  //자바  eof 처리 -> ctrl+z
            int n = scan.nextInt();

            if(stack.isEmpty()){
                root = new Node(n);
                stack.add(root);
            }
            else if(stack.peek().data > n){
                stack.peek().left = new Node(n);
                stack.push( stack.peek().left);

            }else {

                    Node popNode =null;
                    while(stack.peek().data < n && stack.peek()!=root){
                        popNode = stack.pop();
                    }

                    if(stack.peek() == root && n > root.data && root.right==null){
                        stack.peek().right= new Node(n);
                        stack.push(stack.peek().right);
                    }else {
                        popNode.right= new Node(n);
                        stack.push(popNode.right);
                    }


            }
          //  System.out.println(stack);
        }

        PostOrder(root);

    }

    private static void PostOrder(Node current) {

        // 왼쪽 자식
        if(current.left != null )PostOrder(current.left);
        // 오른쪽 자식
        if(current.right != null ) PostOrder(current.right);

        System.out.println(current.data);

    }


}
