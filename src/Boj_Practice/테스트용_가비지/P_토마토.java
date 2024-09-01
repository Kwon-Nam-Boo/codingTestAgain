package Boj_Practice.테스트용_가비지;

import java.util.*;
import java.io.*;

public class P_토마토 {

    private static int N,M,zero;
    private static int[][] map;
    private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        zero = 0;
        map = new int[N][M];
        for(int r = 0 ;r < N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < M ;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == 0) zero++;
            }
        }

        if(zero == 0){
            sb.append("0");
        }else{
            sb.append(bfs());
        }
        System.out.println(sb);

    }

    private static int bfs(){
        Queue<Pair> queue = new LinkedList<>();

        for(int r = 0 ;r < N; r++){
            for(int c = 0; c < M ;c++){
                if(map[r][c] == 1) {
                    queue.offer(new Pair(r,c));
                }
            }
        }

        int ans = 0;
        int zeroTmp = zero;

        if(queue.isEmpty()) return -1;

        while(!queue.isEmpty()){

            int cycle = queue.size();

            zeroTmp = zero;

            for(int c = 0; c < cycle; c++){
                Pair p = queue.poll();

                for(int d = 0; d < dir.length ; d++){
                    int nr = p.r + dir[d][0];
                    int nc = p.c + dir[d][1];

                    if(isIn(nr, nc) && map[nr][nc] == 0){
                        queue.offer(new Pair(nr,nc));
                        map[nr][nc] = 1;
                        zero--;
                    }

                }
            }
            ans++;
            if(zero == zeroTmp) return -1;
            if(zero == 0) return ans;

        }
        return ans;

    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r < N && c < M;
    }

    public static class Pair{
        int r,c;

        public Pair(int r , int c){
            super();
            this.r = r;
            this.c =c;
        }
    }

}
