package Boj_Practice.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_3584_가장가까운공통조상 {

    private static int TC, N;
    private static int[] parent;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb  = new StringBuilder();
        TC = Integer.parseInt(br.readLine());


        for (int t = 0; t < TC; t++) {

            N = Integer.parseInt(br.readLine());

            parent = new int[N+1];
            Arrays.fill(parent, -1);

            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                parent[y] = x;
            }

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            visited = new boolean[N+1];
            sb.append(goToRoot(a,b) + "\n");

        }
        System.out.println(sb);
    }

    private static int goToRoot(int a, int b) {

        // a가 뿌리일때까지 이동. 방문내역 저장
        while(parent[a] != -1){
            visited[a] = true;
            a = parent[a];
        }
        // b가 뿌리일때 까지 이동. 방문내역에 있다면 해당값이 정답
        while(parent[b] != -1){
            if(visited[b]) return b;
            else b = parent[b];
        }
        return a;
    }
}
