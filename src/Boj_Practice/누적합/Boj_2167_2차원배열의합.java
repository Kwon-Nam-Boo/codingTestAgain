package Boj_Practice.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2167_2차원배열의합 {

    private static int N, M;
    private static int i, j, x, y, K;
    private static int[][] map;

//    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    private static  StringTokenizer st = new StringTokenizer(br.readLine());
//    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        solution1(br,st,sb);    // 누적합으로 구한 값
        solution2(br,st,sb);    // 누적합으로 구한 값


    }

    private static void solution2(BufferedReader br, StringTokenizer st, StringBuilder sb) {

    }

    private static void solution1(BufferedReader br,StringTokenizer st, StringBuilder sb) throws IOException{

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M + 1];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= M; c++) {
                map[r][c] = map[r][c - 1] + Integer.parseInt(st.nextToken());
            }
        }

        K =  Integer.parseInt(br.readLine());

        while(K > 0){
            int sum = 0;
            st = new StringTokenizer(br.readLine());
            i = Integer.parseInt(st.nextToken());
            j = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            for (int r = i-1; r < x; r++) {
                sum+=(map[r][y] - map[r][j-1]);
            }
            sb.append(sum + "\n");
            K--;
        }
        System.out.println(sb);
    }

}
