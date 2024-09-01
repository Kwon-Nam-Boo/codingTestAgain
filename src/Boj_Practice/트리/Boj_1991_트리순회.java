package Boj_Practice.트리;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1991_트리순회 {

    private static int N;
    private static Node root;
    private static Node[] tree;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        tree = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char p = st.nextToken().charAt(0);
            char cl = st.nextToken().charAt(0);
            char cr = st.nextToken().charAt(0);
            Node pNode = getNode(p);
            Node lNode = getNode(cl);
            Node rNode = getNode(cr);
            pNode.l =lNode;
            pNode.r = rNode;
        }

//        for (int i= 0; i < N; i++) {
//            System.out.println(tree[i]);
//        }

        preOrder(tree[0]);
        System.out.println();
        inOrder(tree[0]);
        System.out.println();
        postOrder(tree[0]);
        System.out.println();

    }


    private static void preOrder(Node node) {
       if(node != null){
           System.out.print(node.val);
           preOrder(node.l);
           preOrder(node.r);
        }
    }

    private static void inOrder(Node node) {

        if(node != null){
            inOrder(node.l);
            System.out.print(node.val);
            inOrder(node.r);
        }
    }

    private static void postOrder(Node node) {

        if(node != null){
            postOrder(node.l);
            postOrder(node.r);
            System.out.print(node.val);
        }
    }

    private static Node getNode(char c) {

        if(c == '.'){
            return null;
        }else if(tree[c - 65] == null){
            tree[c - 65] = new Node(c);
        }
        return tree[c - 65];
    }

    public static class Node{

        char val;
        Node l, r;

        public Node(char val){
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", l=" + l +
                    ", r=" + r +
                    '}';
        }
    }
}


