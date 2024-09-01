package Boj_Practice.테스트용_가비지;

import java.io.*;
import java.util.*;

public class P_트리순회_1991 {
    private static int N;
    private static Node root;
    private static Node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        tree = new Node[N];

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            char p = st.nextToken().charAt(0);
            char cl = st.nextToken().charAt(0);
            char cr = st.nextToken().charAt(0);

            Node pNode = getNode(p);
            Node clNode = getNode(cl);
            Node crNode = getNode(cr);
            pNode.l =clNode;
            pNode.r =crNode;

        }
        preOrder(tree[0]);
        System.out.println();
        inOrder(tree[0]);
        System.out.println();
        postOrder(tree[0]);

    }

    public static void postOrder(Node n){
        if(n != null){
            postOrder(n.l);
            postOrder(n.r);
            System.out.print(n.v);
        }
    }

    public static void inOrder(Node n){
        if(n != null){
            inOrder(n.l);
            System.out.print(n.v);
            inOrder(n.r);
        }
    }

    public static void preOrder(Node n){

        if(n != null){
            System.out.print(n.v);
            preOrder(n.l);
            preOrder(n.r);
        }

    }

    public static class Node{
        char v;
        Node l, r;

        public Node(char v) {
            this.v = v;
        }

        @Override
        public String toString() {
            StringBuilder sb =  new StringBuilder();
            sb.append("(").append(v).append(",")
                    .append(l == null ? "n": l.v).append(",")
                    .append(r == null ? "n": r.v).append(")");
            return sb.toString();
        }
    }

    public static Node getNode(char c) {
        if(c == '.') {
            return null;
        }
        if(tree[c-65]== null) {		//아직 tree배멸이 null이면
            tree[c-65] = new Node(c);
        }
        return tree[c-65];
    }
}
