package Boj_Practice.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_11660_구가간합구하기5 {

    private static int N, M;
    private static int[][] map, prefix_num;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N +1];
        prefix_num = new int[N + 1][N + 1];

        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                prefix_num[r][c] = map[r][c];
            }
        }

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                prefix_num[r][c] += prefix_num[r][c - 1];
            }
        }

        for (int c = 1; c <= N; c++) {
            for (int r = 1; r <= N; r++) {
                prefix_num[r][c] += prefix_num[r-1][c];
            }
        }

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int ans = prefix_num[x2][y2];

            if(isIn(x1-1,y1-1)){
                ans+=prefix_num[x1-1][y1-1];
            }
            if(isIn(x2,y1-1)){
                ans-=prefix_num[x2][y1-1];
            }
            if(isIn(x1-1,y2)){
                ans-=prefix_num[x1-1][y2];
            }
            sb.append(ans + "\n");
        }
        System.out.println(sb);

    }

    public static boolean isIn(int r, int c){
        return r >=0 && c >=1 && r<=N && c<=N;
    }

}
