package Boj_Practice.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_3197_백조의호수 {

    private static int R, C;
    private static Queue<Pair> queue, water, nQueue;
    private static Pair[] swan;
    private static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static char[][] map;
    private static boolean[][] visited;
    private static boolean meet;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        swan = new Pair[2];
        visited = new boolean[R][C];
        queue = new LinkedList<>();
        water = new LinkedList<>();

        int swanIdx = 0;

        for (int r = 0; r < R; r++) {
            char[] line = br.readLine().toCharArray();
            for (int c = 0; c < C; c++) {
                map[r][c] = line[c];
                if(map[r][c] == 'L') swan[swanIdx++] = new Pair(r,c);
                if(map[r][c] != 'X') water.offer(new Pair(r,c));
            }
        }
        //System.out.println(swan[0]);

        // 1. 백조부터 시작
        queue.offer(swan[0]);
        visited[swan[0].r][swan[0].c] = true;

        meet = false;
        int day = 0;

        while(true){
            
            // 사이클 한바퀴를 돌아보자
            bfs();
            // 백조끼리 만났다면 종료
            if(meet) break;
            //System.out.println(meet);
            // 사이클 돌면서 만난 얼음위치는 물에 인접했던 이후에 녹아 모두 시작위치가 되게되므로 queue로 치환
            queue = nQueue;
            // 얼음을 녹인다
            melt();
            day++;

//            for (int r = 0; r < R; r++) {
//                System.out.println(Arrays.toString(map[r]));
//            }
//            System.out.println();
        }
        System.out.println(day);

    }

    private static void melt() {

        int size = water.size();

        for (int w = 0; w < size; w++) {

            Pair wt = water.poll();

            for (int d = 0; d < dir.length; d++) {
                int wr = dir[d][0] + wt.r;
                int wc = dir[d][1] + wt.c;
                if(isIn(wr,wc)){
                    if(map[wr][wc] == 'X'){
                        map[wr][wc] = '.';
                        water.offer(new Pair(wr,wc));
                    }
                }
            }
        }

    }

    private static void bfs() {
        nQueue = new LinkedList<>();

        while(!queue.isEmpty()){
            Pair p = queue.poll();

            if(swan[1].r == p.r && swan[1].c == p.c){
                meet = true;
                return;
            }

            for (int d = 0; d < dir.length; d++) {
                int nr = dir[d][0] + p.r;
                int nc = dir[d][1] + p.c;
                
                if(isIn(nr,nc) && !visited[nr][nc]){
                    // 일단 위치해본 곳은 방문처리
                    visited[nr][nc] = true;
                    // 얼음을 만났을 경우, nQueue에 일단 넣어주고 넘긴다. 막혔기 때문에 queue에는 넣지않음
                    if(map[nr][nc] == 'X'){
                        nQueue.offer(new Pair(nr,nc));
                        continue;
                    }
                    // 큐에 삽입
                    queue.offer(new Pair(nr,nc));
                }
            }
        }
    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r < R && c < C;
    }

    public static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
}
