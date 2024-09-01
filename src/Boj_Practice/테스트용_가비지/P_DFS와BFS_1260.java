package Boj_Practice.테스트용_가비지;


import java.util.*;
import java.io.*;

public class P_DFS와BFS_1260 {

    private static int N, M, V;
    private static boolean visited[];
    private static List<Integer>[] list;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            sb = new StringBuilder();

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());

            visited = new boolean[N+1];

            list = new ArrayList[N+1];

            for(int i = 1 ; i <= N ; i++){
                list[i] = new ArrayList<>();
            }

            for(int i = 0 ; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list[a].add(b);
                list[b].add(a);
            }
            DFS(V , 0);
            System.out.println(sb);
            sb = new StringBuilder();
            visited = new boolean[N+1];
            BFS(V);
            System.out.println(sb);
    }

    public static void BFS(int v){

        Queue<Integer> queue = new LinkedList<>();

        visited[v] = true;
        queue.offer(v);

        while(!queue.isEmpty()){

            int tmp = queue.poll();
            sb.append(tmp + " ");

            for(int i = 0; i < list[tmp].size(); i++){
                Integer child = list[tmp].get(i);

                if(!visited[child]){
                    visited[child] = true;
                    queue.offer(child);

                }
            }

        }

    }

    public static void DFS(int v, int d){

        visited[v] = true;
        sb.append(v + " ");

        for (int i = 0; i < list[v].size(); i++){
            int tmp = list[v].get(i);
            if(!visited[tmp]){
                DFS(tmp , d+1);
            }

        }

        if(!visited[v]){
            for(int i = 1 ; i <= N+1; i ++){

            }
        }

    }

}
