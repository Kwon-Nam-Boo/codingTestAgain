package Boj_Practice.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_2667_단지번호붙이기 {

    private static int N, cnt;
    private static List<Integer> list;
    private static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
    private static char[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new char[N][N];
        visited = new boolean[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            map[r] = st.nextToken().toCharArray();
        }
        list = new ArrayList<Integer>();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == '1' && !visited[r][c]){
                    visited[r][c] = true;
                    cnt = 0;
                    DFS(new Pair(r,c));
                    list.add(cnt);
                }
            }
        }
        Collections.sort(list);
        System.out.println(list.size());
        for(int i = 0 ;i< list.size();i++){
            System.out.println(list.get(i));
        }

    }
    public static void DFS(Pair p){

        cnt++;

        for(int d = 0;d< 4;d++){
            int nr = dir[d][0] + p.x;
            int nc = dir[d][1] + p.y;
            if(isIn(nr,nc) && !visited[nr][nc] && map[nr][nc] == '1'){
                visited[nr][nc] = true;
                DFS(new Pair(nr,nc));
            }
        }

    }

    public static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<N && c<N;
    }

    public static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x =x;
            this.y =y;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
