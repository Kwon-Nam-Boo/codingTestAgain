package codingTest_Retry;


import java.util.*;
import java.io.*;

public class baekjoon_1764_듣보잡 {

    private static int N,M,ans;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ans = 0;
        HashSet<String> nameCheck = new HashSet<>();
        ArrayList<String> list = new ArrayList<>();

        for(int i = 0; i < N;i++){
            nameCheck.add(br.readLine());
        }

        for(int i = 0; i < M;i++){
            String tmp = br.readLine();
            if(nameCheck.contains(tmp)){
                ans++;
                list.add(tmp);
            }
        }
        Collections.sort(list);
        System.out.println(ans);
        for(String li: list){
            System.out.println(li);
        }
    }

}
