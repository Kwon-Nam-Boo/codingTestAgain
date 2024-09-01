package Boj_Practice.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Boj_11724_연결요소의개수 {

    private static int N, M;
    private static boolean[] visited;
    private static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];

        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }
        int ans = 0;
        //각 숫자당 한사이클씩 DFS를 돌려본다
        for(int i = 1 ;i <= N; i++){

            if(!visited[i]){
                DFS(i,0);
                ans++;
            }
        }
        System.out.println(ans);
    }

    private static void DFS(int s, int d) {
        //if
        if(!visited[s]){
            visited[s] = true;
        }
        // for
        for (int i = 0; i < list[s].size(); i++) {
            int tmp = list[s].get(i);
            if (!visited[tmp]) {
                DFS(tmp, ++d);
            }
        }
    }
}