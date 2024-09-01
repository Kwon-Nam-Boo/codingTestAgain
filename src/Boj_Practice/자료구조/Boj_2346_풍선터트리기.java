package Boj_Practice.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_2346_풍선터트리기 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Deque<Pair> dq = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 1;i <= N;i++){
            dq.offer(new Pair(i, Integer.parseInt(st.nextToken())));
        }

        StringBuilder sb = new StringBuilder();

        while(dq.size() > 1){
            Pair cur = dq.poll();
            sb.append(cur.idx +" ");

            int move = cur.num;


            if(move > 0){
                move--;     // 현재위치는 이미 poll했으니깐..
                while(move-->0){
                    dq.addLast(dq.poll());
                }
            }else{
                while(move++ < 0){
                    dq.addFirst(dq.pollLast());
                }
            }
        }
        System.out.println(sb.append(dq.poll().idx));
    }


    static class Pair{
        int idx, num;

        Pair(int idx, int num){
            this.idx = idx;
            this.num = num;
        }
    }
}
