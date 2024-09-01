package Boj_Practice.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1806_부분합 {

    private static int N, S;
    // 시작점, 끝점, 합
    private static int start, end, sum;
    private static int seq[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        // 투포인터
        seq = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        //System.out.println(Arrays.toString(seq));

        start = 0;
        end = 0;
        sum = 0;
        int min = Integer.MAX_VALUE;

        while(start <= N && end <= N){
            // 현재 sum이 기준인 S보다 크면서, 현재의 길이 최소값이라면 세팅
            if(sum >= S && end - start < min) min = end - start;

            //만약 현재 sum이 기준인 S보다 크다면, start증가
            if(sum >= S) {
                sum-=seq[start++];
            }
            //만약 현재 sum이 기준인 S보다 작다면, end 증가
            else if(sum < S){
                sum+=seq[end++];
            }
            //System.out.println(sum);
        }
        if(min == Integer.MAX_VALUE) min = 0;
        System.out.println(min);

    }
}
