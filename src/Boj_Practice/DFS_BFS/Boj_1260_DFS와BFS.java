package Boj_Practice.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Boj_1260_DFS와BFS {

    private static int N;
    private static int M;
    private static int V;
    private static List<Integer>[] list;
    private static boolean[] visited;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        //String[] word = br.readLine();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];

        // 그래프 만들기
        list = new ArrayList[N+1];

        for(int i = 1; i <=N ;i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0;i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        for(int i = 1;i <= N;i++){
            Collections.sort(list[i]);
        }

        //System.out.println(Arrays.toString(list));

        DFS(V, 0);
        sb.append("\n");
        visited = new boolean[N+1];
        BFS(V);
        System.out.println(sb);
    }

    public static void DFS(int v, int cnt){


        visited[v] = true;
        sb.append(v + " ");

        for(int i = 0 ; i < list[v].size() ;i++){
            int tmp = list[v].get(i);
           if(!visited[tmp]){
               DFS(tmp, ++cnt);
               //visited[tmp] = false;
           }
        }
    }

    public static void BFS(int v){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;

        while(!queue.isEmpty()){
            int tmp = queue.poll();

            // 정점을 찍는다
            sb.append(tmp + " ");

            // BFS탐색
            List<Integer> childs = list[tmp];
            for(int i =0; i <childs.size();i++){
                Integer child = childs.get(i);
                if(!visited[child]){
                    visited[child] = true;
                    queue.offer(child);
                }
            }
        }

    }
}
