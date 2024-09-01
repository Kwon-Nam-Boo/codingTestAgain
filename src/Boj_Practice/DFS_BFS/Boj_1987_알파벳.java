package Boj_Practice.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1987_알파벳 {

    private static int R, C, count, ans;
    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static char[][] map;
    private static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
        }

        visited = new boolean[26];
        count = 0;
        ans = 0;
        visited[map[0][0]- 65] = true;
        dfs(0,0);
        System.out.println(ans);
    }

    private static void dfs(int r, int c){

        count++;
        ans = Math.max(count, ans);

        for(int d = 0; d < dir.length ; d++){
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];

            // 알파벳을 방문한적이 없다면
            if(isIn(nr, nc) && !visited[map[nr][nc] - 65]){
                int alpha = map[nr][nc] - 65;
                visited[alpha] = true;
                dfs(nr,nc);
                count--;
                visited[alpha] = false;
            }
        }

    }

    private static boolean isIn(int r , int c){
        return r>=0 && c>=0 && r<R && c<C;
    }
}
