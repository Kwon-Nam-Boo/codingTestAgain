package Boj_Practice.테스트용_가비지;

import java.util.*;
import java.io.*;


public class P_포탑부수기 {

    public static class Tower{
        int v,t;

        public Tower(int v, int t) {
            this.v = v;
            this.t = t;
        }

        @Override
        public String toString() {
            return "Tower{" +
                    "v=" + v +
                    ", t=" + t +
                    '}';
        }
    }

    public static class Pair{

        int r,c;

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

    private static int N,M,K, turn;

    private static boolean[][] visited;
    private static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}}; // 우하좌상
    private static Tower[][] map, mapTmp;
    private static int [][] backR;
    private static int [][] backC;
    private static Pair atk, tar, atkTmp, tarTmp, atkBef;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N =Integer.parseInt(st.nextToken());
        M =Integer.parseInt(st.nextToken());
        K =Integer.parseInt(st.nextToken());

        map = new Tower[N][M];

        for(int r = 0 ;r < N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = new Tower(Integer.parseInt(st.nextToken()), 0);
            }
        }
        atk = null;
        tar = null;
        atkBef = null;

        turn=0;
        selectAttacker();
        findTarget();



//        if(!laserAttack()){
//            bombAttack();
//        }

        System.out.println(atk);
        atk.r++;
        atk.c++;
        System.out.println(atk);

        System.out.println(tar);
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(mapTmp[i]));
        }
    }

    private static void bombAttack() {

    }

//    private static boolean laserAttack() {
//
//        mapCopy();
//        boolean exitYn = false;
//        backR = new int[N][M];
//        backC = new int[N][M];
//
//        Queue<Pair> queue = new LinkedList<>();
//        visited = new boolean[N][M];
//
//        queue.offer(atk);
//        visited[atk.r][atk.c] = true;
//
//        while(!queue.isEmpty()){
//
//            Pair p = queue.poll();
//
//            if(tar.r == p.r && tar.c == p.c){
//                exitYn = true;
//                break;
//            }
//
//            for (int d = 0; d < dir.length; d++) {
//
//                int nr = dir[d][0] + p.r;
//                if(nr == N) nr = 0;
//                else if(nr  < 0) nr = N-1;
//
//                int nc = dir[d][1] + p.c;
//                if(nc == M) nc = 0;
//                else if(nc < 0) nc = M-1;
//
//                // 방문한적이 없고 포탄이 있는 곳으로 간다면?
//                if(!visited[nr][nc] && map[nr][nc].v > 0){
//                    visited[nr][nc] = true;
//                    queue.offer(new Pair(nr, nc));
//                    // 이전 경로 저장
//                    backR[nr][nc] = p.r;
//                    backC[nr][nc] = p.c;
//                }
//
//            }
//
//        }
//        // 만약 도착지에 도착한다면
//        while(exitYn){
//
//            // 뒤에서 부터 점수 차감 드가자
//            int pow = map[atk.r][atk.c].v -;
//
//
//            map[tar.r][tar.c].t = new Tower()
//
//
//        }
//
//        return false;
//    }

    private static void mapCopy() {

        mapTmp = new Tower[N][M];

        for(int r = 0 ;r < N;r++){
            for (int c = 0; c < M; c++) {
                mapTmp[r][c] = new Tower(map[r][c].v, map[r][c].t);
            }
        }
    }

    private static void findTarget() {

        mapCopy();

        tarTmp = null;
        int pow = 0;

        for(int r = 0 ;r < N;r++){
            for (int c = 0; c < M; c++) {
                
                // 공격자 자신은 타겟 제외
                if(r == atk.r && c == atk.c) continue;

                // 부서진 포탑이 아니면서 기준보다 강한포탑찾기
                if(map[r][c].v > 0 && map[r][c].v > pow){
                    pow = map[r][c].v;
                    tarTmp = new Pair(r,c);
                }
                // 부서진 포탑이 아니면서 공격력이 같은 포탑일경우?
                else if(map[r][c].v > 0 && map[r][c].v == pow){

                    // 1. 현재 조회한 포탑이 최근공격포탑이라면?
                    if(map[r][c].t < map[tarTmp.r][tarTmp.c].t){
                        tarTmp = new Pair(r,c);
                    }else if(map[r][c].t == map[tarTmp.r][tarTmp.c].t){
                        // 2. 행과 열의 합이 큰포탑을 약한 포탑으로 선정
                        int be, cu = 0;
                        be = tarTmp.r + tarTmp.c;
                        cu = r + c ;
                        if(be > cu){
                            tarTmp = new Pair(r,c);
                        }
                        // 3. 열이 큰 포탑을 약한 포탑을 선정
                        else if(be == cu){
                            if(tarTmp.c > c){
                                tarTmp = new Pair(r,c);
                            }
                        }
                    }

                }

            }
        }
        // 타겟 최종 지정
        tar = new Pair(tarTmp.r , tarTmp.c);
    }


    private static void selectAttacker() {
        atkTmp = null;
        int pow = 5001;

        for(int r = 0 ;r < N;r++){
            for (int c = 0; c < M; c++) {

                // 부서진 포탑이 아니면서 기준보다 강한포탑찾기
                if(map[r][c].v > 0 && map[r][c].v < pow){
                    pow = map[r][c].v;
                    atkTmp = new Pair(r,c);
                }
                // 부서진 포탑이 아니면서 공격력이 같은 포탑일경우?
                else if(map[r][c].v > 0 && map[r][c].v == pow){

                    // 1. 현재 조회한 포탑이 최근공격포탑이라면?
                    if(map[r][c].t > map[atkTmp.r][atkTmp.c].t){
                        atkTmp = new Pair(r,c);
                    }else if(map[r][c].t == map[atkTmp.r][atkTmp.c].t){
                        // 2. 행과 열의 합이 큰포탑을 약한 포탑으로 선정
                        int be, cu = 0;
                        be = atkTmp.r + atkTmp.c;
                        cu = r + c ;
                        if(be < cu){
                            atkTmp = new Pair(r,c);
                        }
                        // 3. 열이 큰 포탑을 약한 포탑을 선정
                        else if(be == cu){
                            if(atkTmp.c < c){
                                atkTmp = new Pair(r,c);
                            }
                        }
                    }

                }

            }
        }
        // 공격자 최종 지정 및 공격력 및 turn정보 업데이트
        atk = new Pair(atkTmp.r , atkTmp.c);
        map[atkTmp.r][atkTmp.c] = new Tower(map[atkTmp.r][atkTmp.c].v + N+M , ++turn);

    }
}
