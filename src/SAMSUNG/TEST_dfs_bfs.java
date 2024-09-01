package SAMSUNG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TEST_dfs_bfs {

    private static int N,M,V;

    private static List<Integer>[] list;
    private static boolean[] visited;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];
        visited = new boolean[N+1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(list[i]);
        }

        DFS(V, 0);
        sb.append("\n");
        visited = new boolean[N+1];
        BFS(V);
        System.out.println(sb);
    }

    private static void BFS(int v) {
        Queue<Integer> queue = new LinkedList<>();
        visited[v] = true;
        queue.offer(v);

        while(!queue.isEmpty()){

            int a = queue.poll();
            sb.append(a + " ");

            List<Integer> child = list[a];

            for (int i = 0; i < child.size(); i++) {
                Integer c = child.get(i);

                if(!visited[c]){
                    visited[c] = true;
                    queue.offer(c);
                }
            }

        }


    }

    private static void DFS(int v, int d) {

        visited[v] = true;
        sb.append(v + " ");

        for (int i = 0; i < list[v].size(); i++) {

            int a = list[v].get(i);

            if(!visited[a]){
                DFS(a, ++d);
            }
        }

    }
}
