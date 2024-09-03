package codingTest_Retry;

import java.io.*;

public class baekjoon_1541_잃어버린괄호 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();

        // 그리디하게 일단 마이너스를 기준으로 그룹핑을 한다
        // 마이너스 이후 숫자는 더하기로 제일 크게할 예정
        String[] group = expression.split("-");

        // 맨앞은 빼기가 없으니깐..더해두고 시작
        int ans = sum(group[0]);

        // 각 더하기그룹을 완성했다면 순서대로 빼준다
        for(int i = 1; i < group.length;i++){
            ans-=sum(group[i]);
        }
        System.out.println(ans);
    }

    public static int sum(String s){        // 현재그룹에 + 를 다 진행해둔다.

        String[] group = s.split("\\+");

        int total = 0;
        for(String g: group){
            total+=Integer.parseInt(g);
        }

        return total;
    }
}
