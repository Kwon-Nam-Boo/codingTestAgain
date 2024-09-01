package Boj_Practice.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_7576_토마토 {

    private static int N, M, cnt;
    private static int[][] map;
    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};   //상우하좌

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 0) cnt++;
            }
        }

        // 모두익은토마토 뿐이라면 0
        if(cnt == 0){
            sb.append("0");
        }
        else { // 아니면 bfs 돌려야지
            sb.append(bfs());
        }

        System.out.println(sb);
    }

    public static int bfs() {
        Queue<Pair> queue = new LinkedList<>();
        int ans = 0;

        // 우선 한바퀴 순회하면, 한 사이클에 돌아갈 바이러스를 Queue에 삽입
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 1) queue.offer(new Pair(r, c));

            }
        }
        // 으잉? 처음 바이러스가 없다?? 퍼지기 불가능
        if(queue.isEmpty()) return -1;

        while (!queue.isEmpty()) {
            int tmp = cnt;
            // 한 사이클에서의 바이러스토마토 수
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Pair p = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int dr = p.r + dir[d][0];
                    int dc = p.c + dir[d][1];

                    // 해당 바이러스토마토의 4방향에서 들어가지 못한곳이라면? 퍼지기
                    if(isIn(dr,dc) && map[dr][dc] == 0){
                        map[dr][dc] = 1;
                        queue.offer(new Pair(dr, dc));
                        cnt--;

                    }
                }

            }

//            for(int r = 0 ;r <N;r++){
//                System.out.println(Arrays.toString(map[r]));
//            }
//            System.out.println();

            // 사이클횟수
            ans++;
            if(cnt == 0) return ans;
            if(tmp == cnt) return -1;
        }

        return ans;
    }

    public static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<N && c<M;
    }

    public static class Pair {
        int r;
        int c;

        public Pair(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Pair [r=" + r + ", c=" + c + "]";
        }
    }
}
