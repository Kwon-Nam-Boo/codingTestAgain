package SAMSUNG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SW_MAZE_RUNNER {

    private static int N,M, K;
    private static int[][] map;
    private static int[][] pMap ;

    private static int[][] mapClone;
    private static int[][] pMapClone;

    private static List<Pair> entry;
    private static Pair exit;
    private static Pair start, end;

    private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}}; //상하좌우

    /*
        미로는 3가지 상태
        빈칸
        벽   --> 회전할때마다 내구도 깎임
        출구

        [[ 1초 ]]
        1. 이동
        최단거리는 == 대각선
        동시이동, 상하좌우이동
        이동위치는 무조건 전보다 가까운곳
        우선도 상하 > 좌우
        !! 모험가 둘이상 가능 !!

        2. 회전

        !출구와 한명의 참가자가 포함된 가장 작은 정사각형이 회전!
        우선도 상>> 하 , 좌 >> 우
        !! 정사각형은 시계 방향으로 90도 회전 !!
        내구도 1 씩 깎임

       [결과]
       참가자 이동 거리의 합 및 출구 위치 출력

     */


    private static void cloneRotate() {

        int len = end.r - start.r + 1;

        mapClone = new int[len][len];
        pMapClone = new int[len][len];

        int[][] tmp = new int[N][N];
        int[][] tmp2 = new int[N][N];


        for (int r = 0; r < N; r++){
            for(int c = 0; c< N;c++){
                tmp[r][c] = map[r][c];
                tmp2[r][c] = pMap[r][c];
            }
        }

        // clone만들기
        for(int r = start.r, a = 0 ; r  <= end.r; r++,a++){
            for(int c = start.c, b = 0; c <= end.c; c++, b++){
                mapClone[a][b] = map[r][c];
                pMapClone[a][b] = pMap[r][c];
            }
        }

        // 회전시키기
        for(int r = 0, a = start.r; r  < len; r++,a++){
            for(int c = 0, b = start.c; c < len; c++, b++){

                 tmp[a][b] = mapClone[len-1-c][r];
                 tmp2[a][b] = pMapClone[len-1-c][r];

                 if(tmp[a][b]>0) tmp[a][b]--;

            }
        }
        //System.out.println();

        entry.clear();

        for (int r = 0; r < N; r++){
            for(int c = 0; c< N;c++){
                map[r][c] = tmp[r][c];
                pMap[r][c] = tmp2[r][c];

                if(pMap[r][c] >0){
                    entry.add(new Pair(r,c));
                }else if(pMap[r][c] == -1){
                    exit.r = r;
                    exit.c = c;
                }
            }
        }

//         for (int r = 0; r < N; r++){
//            System.out.println(Arrays.toString(map[r]));
//        }
//        System.out.println();
//                  for (int r = 0; r < N; r++){
//            System.out.println(Arrays.toString(pMap[r]));
//        }
//
//        System.out.println(entry);
//        System.out.println(exit);
//        System.out.println("------------");


    }



    private static void findSquare() {

        // 종료 플래그
        boolean eFlag = false;

        // 회전시킬 정사각형 시작 끝점
        start = new Pair(0,0);
        end = new Pair(0,0);

        // 길이가 t인 정사작형
        for(int t = 1; t < N; t++){
            if(eFlag) break;
            for(int r = 0 ; r  < N - t; r++){
                if(eFlag) break;
                for(int c = 0; c < N- t; c++){
                    if(eFlag) break;
                    //System.out.println(r + ":" + c + "-----"  + (r+t) + ":" + (c +t));
                    int endR = r+t;
                    int endC = c+t;

                    // 참가자가 정사각형 내부에 있는가 ?
                    for(int l = 0 ; l< entry.size();l++){
                        if(eFlag) break;
                        if(entry.get(l).r >= r && entry.get(l).c >= c &&entry.get(l).r <= endR && entry.get(l).c <= endC){
                            if(exit.r >= r && exit.c >= c && exit.r <= endR && exit.c <= endC){
                                start.r = r;
                                start.c = c;
                                end.r = endR;
                                end.c = endC;
                                eFlag = true;
                            }
                        }
                    }
                    //
                }
            }
        }
        //System.out.println(start + ":" + end);

    }


    private static void rotate() {

        findSquare();
        cloneRotate();

    }


    private static int move() {

        int[][] tmp = new int[N][N];
        tmp[exit.r][exit.c] = -1;
        int mv = 0;


        for(int i = 0; i< entry.size();i++){

            boolean ismoved = false;

            int r = entry.get(i).r;
            int c = entry.get(i).c;

            // 기준거리
            int stand = Math.abs(exit.r - r) + Math.abs(exit.c -c);

            for(int d = 0 ;d < 4; d++){
                int nr = entry.get(i).r + dir[d][0];
                int nc = entry.get(i).c + dir[d][1];

                // 만약 이동위치가 출구라면 참가자 삭제 필요
                if(nr == exit.r && nc == exit.c) {
                    //tmp[nr][nc]+=pMap[r][c];
                    mv+=pMap[r][c];
                    //mv++;
                    ismoved = true;
                    //pMap[r][c] = 0;
                    //entry.remove(i);
                    //entry.set(i, new Pair(-1,-1));
                    break;
                }
                // 이동했을시 거리
                int dis2 = Math.abs(exit.r - nr) + Math.abs(exit.c - nc);
                // 이동했을시 짧을때만 이동
                if( isIn(nr,nc)  && (stand >= dis2)){
                    stand = dis2;
                    // 다만 이동할 위치 수 있는 위치일 경우만 이동, 이외에는 이동하지 않음
                    if(map[nr][nc] == 0){

                        mv+=pMap[r][c];
                        //mv++;
                        tmp[nr][nc]+=pMap[r][c];
                        ismoved = true;
//                        pMap[nr][nc]+= pMap[r][c] ;
//                        pMap[r][c] = 0;
                        break;
                    }

                }

            }
            if(!ismoved) tmp[r][c]+=pMap[r][c];

        }

        for (int r = 0; r < N; r++){
            for(int c = 0; c< N;c++){
                pMap[r][c] = tmp[r][c];

            }
        }

        entry.clear();

        for (int r = 0; r < N; r++){
            for(int c = 0; c< N;c++){
                if(pMap[r][c] > 0)
                    entry.add(new Pair(r,c));
            }
        }
        //System.out.println(entry);

//        for (int r = 0; r < N; r++){
//            System.out.println(Arrays.toString(map[r]));
//        }
//        System.out.println();
//        for (int r = 0; r < N; r++){
//            System.out.println(Arrays.toString(pMap[r]));
//        }

        return mv;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int r = 0; r < N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c< N;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        pMap = new int[N][N];

        entry = new ArrayList<Pair>();

        for (int r = 0; r < M; r++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            if(pMap[a][b] == 0)
                entry.add(new Pair(a, b));
            pMap[a][b]++;
        }

        st = new StringTokenizer(br.readLine());
        exit = new Pair(Integer.parseInt(st.nextToken()) -1, Integer.parseInt(st.nextToken())-1);

        pMap[exit.r][exit.c] = -1;

//        for (int r = 0; r < N; r++){
//            System.out.println(Arrays.toString(map[r]));
//        }
//        System.out.println();
//        for (int r = 0; r < N; r++){
//            System.out.println(Arrays.toString(pMap[r]));
//        }
        //System.out.println("start------------");
        // 1초마다 rotate를 돌게 될것이다.
        int ans = 0;

        for(int t = 1;t <=K;t++){
             ans+=move();
             //System.out.println(ans);
             rotate();
        }
        System.out.println(ans);
        System.out.println((exit.r+1) + " " + (exit.c+1));
    }


    static class Pair{

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

    public static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r < N && c < N;
    }
}
