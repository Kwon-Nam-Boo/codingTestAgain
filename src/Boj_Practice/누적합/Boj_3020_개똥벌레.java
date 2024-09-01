package Boj_Practice.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_3020_개똥벌레 {

    private static int N, H;

    // 석순, 종유석의 높이에 따른 누적합 구하기
    private static int[] down,up;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        down = new int[H+1];
        up = new int[H+1];

        for (int i = 0; i < N; i++) {
            if(i % 2 == 0) down[Integer.parseInt(br.readLine())]++;
            else up[H-Integer.parseInt(br.readLine())]++;
        }
//        System.out.println(Arrays.toString(down));
//        System.out.println(Arrays.toString(up));

        // 석순파트: 위에서부터 하나씩 누적합을 구하기
        for (int i = H-1; i > 0; i--) {
            down[i] += down[i+1];
            up[i] += up[i+1];
        }
//        System.out.println(Arrays.toString(down));
//        System.out.println(Arrays.toString(up));

        for (int i = 1; i <= H; i++) {
            up[i] = (N/2) - up[i];
            down[i]+=up[i];
        }
       // System.out.println(Arrays.toString(down));
//        System.out.println(Arrays.toString(up));
        Arrays.sort(down);

        int ans = down[1];
        int cnt = 0;

        for (int i = 1; i <= H; i++) {
            if(ans == down[i]) cnt++;
            else break;
        }
        System.out.println(ans + " " + cnt);
//       System.out.println(cnt);
//
//        System.out.println(Arrays.toString(down));
//        System.out.println(Arrays.toString(up));
    }
}
