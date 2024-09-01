package Algo_book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PCSpractice_순열조합부분집합 {
    public static char[] N = new char[]{'a','b','c','d'};
    //public static char[] N = new char[]{'d','c','b','a'};
    public static int R = 2;
    public static char[] result;
    public static boolean[] visited;
    public static ArrayList<Character> list = new ArrayList<>();

    public PCSpractice_순열조합부분집합(){}

    public static void main(String[] args) {
        visited = new boolean[N.length];
        result = new char[R];

        nPrVisited(0);
        System.out.println();
        nPrSwap(0);
        System.out.println();
        Arrays.sort(N);
        NP();
        System.out.println();
        Arrays.sort(N);
        result = new char[R];
        nCrRecursion(0, 0);
        result = new char[R];
        nCrDef(N.length, R);
        System.out.println();
        subSetbit();

        System.out.println();

        list = new ArrayList<>();
        subSetList(0,0);
        visited = new boolean[N.length];
        System.out.println();
        subSetRecursion(0);
    }

    private static void nPrVisited(int r) {

        if(r == R){
            System.out.println(Arrays.toString(result));
            return;
        }

        for (int i = 0; i < N.length; i++) {
            if(!visited[i]){
                visited[i] = true;
                result[r] = N[i];
                nPrVisited(r+1);
                visited[i] = false;
            }
        }
    }
    private static void nPrSwap(int r){

        if(r == R) {
            System.out.println("swap:" + Arrays.toString(Arrays.copyOfRange(N, 0, R)));
            return;
        }
        for (int i = r ; i < N.length; i++){
            swap(r,i);
            nPrSwap(r+1);
            swap(r,i);
        }
    }
    private static void swap(int a, int b){
        char tmp = N[a];
        N[a] = N[b];
        N[b] = tmp;
    }

    private static void NP() {
        do{
            System.out.println("NP: " + Arrays.toString(N));
        }while(nPrNP());
    }
    private static boolean nPrNP() {
        int i;
        // 1 i구하기
        for(i = N.length-2; i>=0 && N[i] >=N[i+1];i--){}

        if(i<0)  return false;
        else{
            // 2 j구하기
            int j;
            for(j = N.length -1;j>=0 && N[i] >= N[j];j--){}

            // 3 스왑
            swap(i , j);

            // 4그사이값도 스왑
            int a = i +1;
            for(int b = N.length-1;a<b;--b,a++){
                swap(a,b);
            }
        }
        return true;
    }
    private static void nCrRecursion(int r, int k) {

        if(R == r ){
            System.out.println("nCr: " + Arrays.toString(result));
            return;
        }
        for(int i = k;i< N.length;i++){
            result[r] = N[i];
            nCrRecursion(r+1, i+1);
        }
    }
    private static void nCrDef(int n, int r) {

        if(r == 0){
            System.out.println(Arrays.toString(result));
        }else if(n >= r){
            result[r-1] = N[n-1];
            nCrDef(n-1, r-1);
            nCrDef(n-1, r);
        }
    }

    private static void subSetbit(){

        for(int i = 0;i < 1 << N.length;i++){
            List<Character> list = new LinkedList<>();
            for (int j = 0;j< N.length;j++){
                if( (i & 1 << j) > 0){
                    list.add(N[j]);
                }
            }
            System.out.println(list);
        }

    }

    private static void subSetList(int r, int k){
        System.out.println(list);

        if(r == N.length) return;

        for(int i= k;i < N.length;i++){
            list.add(N[i]);
            subSetList(r+1,i+1);
            list.remove(list.size()-1);
        }
    }

    private static void subSetRecursion(int i){
        if(i == N.length){
            print();
        }else{
            visited[i] = false;
            subSetRecursion(i+1);
            visited[i] = true;
            subSetRecursion(i+1);
        }

    }

    private static void print() {

        for (int i = 0; i < visited.length; i++) {
            if(visited[i]) System.out.print(N[i] + " ");
        }
        System.out.println();
    }


}
