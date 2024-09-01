package Boj_Practice.테스트용_가비지;


import java.util.*;
import java.io.*;

public class P_이진검색트리_5639 {

    private static Node root;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        String s ="";

        while((s = br.readLine())  != null && s.length() != 0){
            addNode(Integer.parseInt(s));

        }
        postOrder(root);
        System.out.println(sb);

    }

    public static void postOrder(Node d) {
        if(d!=null) {
            postOrder(d.l);
            postOrder(d.r);
            sb.append(d.v).append("\n");
        }
    }

    public static void addNode(int v){
        Node newNode = new Node(v);

        if(root == null){
            root = newNode;
            return;
        }

        Node currNode = root;

        while(true){
            if(currNode.v > newNode.v){     // 왼쪽으로 보낸다
                if(currNode.l == null ){
                    currNode.l = newNode;
                    return;
                }else{
                    currNode = currNode.l;
                }
            }else{
                if(currNode.r == null){
                    currNode.r = newNode;
                    return;
                }else{
                    currNode = currNode.r;
                }
            }
        }

    }


    public static class Node{

        int v;
        Node l,r;

        public Node(int v){
            super();
            this.v = v;
        }
        @Override
        public String toString() {
            return "[" + v +
                    ","+ (l == null? "n":l.v) +
                    "," + (r == null? "n":r.v) + "]";
        }
    }
}
