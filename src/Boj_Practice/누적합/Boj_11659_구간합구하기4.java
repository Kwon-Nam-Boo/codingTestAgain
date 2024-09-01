package Boj_Practice.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Boj_11659_구간합구하기4 {

    private static int N, M;
    private static int[] num, prefix_num;

    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        num = new int[N + 1];
        prefix_num = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
            prefix_num[i] = prefix_num[i - 1] + num[i];
        }
        //System.out.println(Arrays.toString(num));
        //System.out.println(Arrays.toString(prefix_num));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(prefix_num[b] - prefix_num[a-1] + "\n");
        }
        System.out.println(sb);
    }
}
