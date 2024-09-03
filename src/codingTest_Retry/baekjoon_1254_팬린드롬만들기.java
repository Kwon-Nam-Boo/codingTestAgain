package codingTest_Retry;

import java.io.*;

public class baekjoon_1254_팬린드롬만들기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        System.out.print(solution(S));
    }

    public static int solution(String S){
        
        // 앞글자부터 하나씩 빼보면서 팬린드롬인지 확인한다
        for(int i = 0; i < S.length(); i++){
            // 가장 먼저 발견한 팬린드롬은 가장 긴 팬린드롬이므로 앞글자 개수만큼만 붙여주면 완성
            if(isPalindrome(S.substring(i))){
                return S.length() + i;
            }
        }
        // 팬드롬이 없다면 최대길이인 문자열길이*2-1
        return S.length()*2-1;
    }

    public static boolean isPalindrome(String s){   // 팬린드롬인지 확인하는 로직

        int left = 0;
        int right = s.length()-1;
        
        // 앞뒤 좌표가 동시에 같은 글자여야함
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
