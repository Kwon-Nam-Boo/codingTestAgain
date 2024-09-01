package Boj_Practice.테스트용_가비지;

import java.util.*;

public class P_순열조합부분집합 {

    public static char[] N = {'a','b','c','d'};
    public static boolean[] visited;
    public static int R = 2;
    public static char[] result;
    public static ArrayList<Character> list = new ArrayList<>();

    public static void main(String[] args) {

        visited = new boolean[N.length];
        result = new char[R];

        //nPrVisited(0);

        //nPrSwap(0);
        //NP();
        //nCrRecursion(0,0);
        nCrDef(N.length,  R);
        //subsetBit();
        //subsetList(0,0);
        //subsetRecursion(0);


    }

    public static void nCrDef(int n, int r){

        if(r==0){
            System.out.println(Arrays.toString(result));
        }else if(n>=r){
            result[r-1] = N[n-1];
            nCrDef(n-1, r-1);
            nCrDef(n-1, r);

        }

    }

    public static void nCrRecursion(int d, int r){

        if(r == R){
            System.out.println(Arrays.toString(result));
        }else{
            for(int i = d; i < N.length;i++){
                result[r] = N[i];
                nCrRecursion(i+1,r+1);
            }
        }

    }


        private static void swap(int a, int b){
            char tmp = N[a];
            N[a] = N[b];
            N[b] = tmp;
        }

    public static void nPrSwap(int r){

        if(r == R){
            System.out.println(Arrays.toString(Arrays.copyOfRange(N,0,R)));
        }else{
            for(int i = r; i < N.length; i++){
                    swap(r, i);
                    nPrSwap(r+1);
                    swap(r, i);
            }
        }

    }

     public static void nPrVisited(int r){

        if(r == R){
            System.out.println(Arrays.toString(result));
        }else{
            for(int i = 0; i < N.length; i++){
                if(!visited[i]){
                    visited[i] = true;
                    result[r] =N[i];
                    nPrVisited(r+1);
                    visited[i] = false;
                }
            }
        }

     }


    private static void print() {
        for(int i = 0; i < visited.length; ++i) {
            if (visited[i]) {
                char var10001 = N[i];
                System.out.print("" + var10001 + " ");
            }
        }

        System.out.println();
    }

    private static void subsetRecursion(int r){
        if(r == N.length){
            print();
        }else{
            visited[r] = true;
            subsetRecursion(r+1);
            visited[r] = false;
            subsetRecursion(r+1);
        }
    }

//    private static void subsetList(int n, int k){
//
//        System.out.println(list);
//        if(n != N.length){
//            for(int i = k ; i < N.length; i++){
//                list.add(N[i]);
//                subsetList(n +1 , i+1);
//                list.remove(list.size() - 1);
//            }
//        }
//
//    }
//
//    private static void subsetBit(){
//
//        for(int i = 0 ; i < (1 << N.length); i++){
//            List<Character> list = new LinkedList<>();
//
//            for(int k = 0; k < N.length; k++){
//                if((i & 1 << k) > 0){
//                    list.add(N[k]);
//                }
//            }
//            System.out.println(list);
//        }
//
//    }

//    private static void nCrDef(int n, int r){
//
//        if(r == 0){
//            System.out.println(Arrays.toString(result));
//        }else if(n>=r){
//            result[r-1] = N[n-1];
//            nCrDef(n-1, r-1);
//            nCrDef(n-1, r);
//        }
//
//    }
//
//    private static void nCrRecursion(int n, int r){
//
//        if(n == R){
//            System.out.println(Arrays.toString(result));
//            return;
//        }else{
//            for(int i = r ; i < N.length; i++){
//                result[n] = N[i];
//                nCrRecursion(n+1, i+1);
//            }
//        }
//
//    }

//    private static boolean nPrNp(){
//        int i;
//        for(i = N.length - 2; i >=0 && N[i]>= N[i+1] ; i--){
//                // I 위치 지정
//        }
//
//        if(i < 0){
//            return false;
//        }else{
//            int j;
//            for(j = N.length - 1; j>=0 && N[i] >= N[j]; j--){
//                // J 위치 지정
//            }
//
//            swap(i,j);
//            int a = i + 1;
//
//            for(int b = N.length -1 ; a < b; a++, b--){
//                swap(a,b);
//            }
//            return true;
//        }
//
//    }
//
//    public static void NP(){
//        do {
//            System.out.println(Arrays.toString(N));
//        } while(nPrNp());
//    }


//    private static void nPrVisited(int r){
//
//        if(r == R){
//            System.out.println(Arrays.toString(result));
//            return;
//        }else{
//            for(int i = 0; i < N.length; i++){
//                if(!visited[i]){
//                    visited[i] = true;
//                    result[r] = N[i];
//                    nPrVisited(r+1);
//                    visited[i] = false;
//                }
//            }
//        }
//
//    }
//
//    private static void nPrSwap(int r){
//
//        if(R == r){
//            System.out.println(Arrays.toString(Arrays.copyOfRange(N, 0 , R)));
//            return;
//        }else{
//            for(int i = 0; i < N.length; i++){
//                swap(r, i);
//                nPrSwap(r+1);
//                swap(r, i);
//            }
//        }
//
//    }
//    private static void swap(int a, int b){
//        char tmp = N[a];
//        N[a] = N[b];
//        N[b] = tmp;
//    }
}
