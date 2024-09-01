package Boj_Practice.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2559_수열 {

    private static int N, K;
    private static int start, end, total;
    private static int seq[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        seq = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        start = 0;
        end = K-1;

        for (int i = 0; i < K; i++) {
            total += seq[i];
        }
        int ans = total;


        while(end <N-1){
            int a = seq[start];
            int b = seq[end+1];
            total = total - seq[start++] + seq[++end];
            if(ans <= total) ans = total ;
            //System.out.println(a + ":" + b + ":" + ans);
        }
        System.out.println(ans);
    }
}


