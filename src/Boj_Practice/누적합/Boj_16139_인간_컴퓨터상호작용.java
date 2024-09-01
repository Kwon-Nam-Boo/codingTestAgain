package Boj_Practice.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_16139_인간_컴퓨터상호작용 {


    private static String word;
    private static int[][] prefix;
    private static int q;
    private static char alpha;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        word = br.readLine();

        // 1차: 해당 위치   2차: 알파벳개수   값: 해당위치까지의 알파벳개수
        prefix = new int[word.length()][26];

        // 첫 위치의 알파벳 1증가
        prefix[0][word.charAt(0) - 'a']++;

        for (int i = 1; i < word.length(); i++) {
            int alpha = word.charAt(i) - 'a';
            // 이전 알파벳까지의 개수를 현재로 세팅
            for (int a = 0; a < 26; a++) {
                prefix[i][a] = prefix[i-1][a];
            }
            // 현재 알파벳만 1증가
            prefix[i][alpha]++;
        }

        q = Integer.parseInt(br.readLine());

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            char alpha = st.nextToken().charAt(0);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // 시작점이 0이라면
            if(start == 0){
                sb.append(prefix[end][alpha - 'a'] + "\n");
            }else{
                sb.append(prefix[end][alpha- 'a'] - prefix[start-1][alpha- 'a']+ "\n");
            }

        }
        System.out.println(sb);
    }
}
