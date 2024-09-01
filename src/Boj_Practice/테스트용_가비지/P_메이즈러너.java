package Boj_Practice.테스트용_가비지;

import java.util.*;
import java.io.*;

public class P_메이즈러너 {

    private static int N, M ,K, len, ans;
    private static boolean noCus;
    private static int[][] map;
    private static int[][] cus;
    private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    private static Pair end, start, ed;

    private static int[][] mapTmp, cusTmp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        cus = new int[N][N];

        for(int r = 0 ; r < N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0 ;c < N; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            cus[r][c]++;
        }
        //System.out.println(cus);

        st = new StringTokenizer(br.readLine());
        int er = Integer.parseInt(st.nextToken())-1;
        int ec = Integer.parseInt(st.nextToken())-1;
        cus[er][ec] = -1;
        end = new Pair(er, ec);

        int t = 1;
        ans = 0;
        noCus = false;

//        for(int r = 0 ;r < N;r++){
//            System.out.println(Arrays.toString(cus[r]));
//        }
//        System.out.println();

        while(t<=K){

//          System.out.println("-----" + t + "----");
//                      for(int r = 0 ;r < N;r++){
//              System.out.println(Arrays.toString(cus[r]));
//          }
          move();
//            System.out.println();
//            for(int r = 0 ;r < N;r++){
//                System.out.println(Arrays.toString(cus[r]));
//            }
//            for(int r = 0 ;r < N;r++){
//                System.out.println(Arrays.toString(cus[r]));
//            }
//            System.out.println();
          if(noCus) break;
          rotate();
//
//          for(int r = 0 ;r < N;r++){
//              System.out.println(Arrays.toString(map[r]));
//          }
//
//          System.out.println();
//

          t++;
        }
        System.out.println(ans);
        System.out.println((end.r+1) + " " + (end.c+1));



    }

    private static void rotate(){
        Pair start = null;
        Pair ed = null;
        len = 0;
        findSquare();
        rotateTest();
    }

    private static void rotateTest(){

        mapTmp = new int[len][len];
        cusTmp = new int[len][len];
        //System.out.println(len);
        for (int r = start.r, a = 0; r < start.r+len; r++, a++) {
            for (int c = start.c, b = 0; c < start.c +len; c++, b++) {
                mapTmp[b][len-1-a] = map[r][c];
                cusTmp[b][len-1-a] = cus[r][c];
            }
        }

        for (int r = start.r, a = 0; r < start.r+len; r++, a++) {
            for (int c = start.c, b = 0; c < start.c +len; c++, b++) {
                if(mapTmp[a][b]> 0) mapTmp[a][b]--;
                if(cusTmp[a][b] == -1) end = new Pair(r,c);
                map[r][c] = mapTmp[a][b];
                cus[r][c] = cusTmp[a][b];
            }
        }

    }

    private static void findSquare(){

        boolean endFlag = false;

        // 정사각형의 길이
        for(int i = 2; i <= N; i++){
            if(endFlag) break;

            for(int x = 0 ; x <= N - i; x++){
                if(endFlag) break;
                for(int y = 0 ;y <= N - i; y++){
                    if(endFlag) break;
                    boolean cusIn = false;
                    boolean exitIn = false;

                    for(int r = x ; r < x+i; r++){
                        for(int c = y ;c < y+i; c++){
                            //System.out.println(r + " " + c);
                            if(cus[r][c] > 0) cusIn = true;
                            if(end.r == r && end.c ==c ) exitIn = true;
                        }
                    }
                    if(cusIn && exitIn){
                        start = new Pair(x,y);
                        endFlag = true;
                        len = i;
                    }

                }
            }

        }
//        System.out.println(start + " " + len);
//        System.out.println();

    }



    private static void move(){

        // list 만들기
        List<Pair> list = new ArrayList<>();

        for(int r = 0 ; r < N; r++){
            for(int c = 0 ;c < N; c++){
                if(cus[r][c] >0){
                    list.add(new Pair(r,c));
                }
            }
        }

        if(list.isEmpty()) {
            noCus = true;
            return;
        }
       // System.out.println(list);
//        System.out.println(end);

        cusTmp = new int[N][N];

        for(int i = 0; i < list.size(); i++){

            // 시작점
            Pair p = list.get(i);
            // 기준거리
            int stnd = Math.abs(end.r  - p.r) + Math.abs(end.c - p.c);
            boolean mv = false;

            for(int d = 0 ; d < dir.length; d++){
                int nr = dir[d][0] + p.r;
                int nc = dir[d][1] + p.c;

                int next = Math.abs(end.r  - nr) + Math.abs(end.c - nc);

                // 도착지로 이동했다면
                if(isIn(nr, nc) && cus[nr][nc] == -1){
                    ans+=cus[p.r][p.c];
                    //cus[p.r][p.c] = 0;
                    mv = true;
                    break;
                }
                // 벽밖 혹은 벽돌을 만나는게 아니면서 최단거리일경우 --> 이동하기
                else if(isIn(nr, nc) && map[nr][nc] == 0 && next < stnd) {
                    // 기준거리는 현재거리로 일단 변경
                    //System.out.println(nr + " " + nc);
                    //stnd = next;
                    ans += cus[p.r][p.c];
                    //cus[nr][nc] += cus[p.r][p.c];
                    //cus[p.r][p.c] = 0;
                    cusTmp[nr][nc] += cus[p.r][p.c];
                    mv = true;
                    break;
                }
            }
            if(!mv) cusTmp[p.r][p.c]+= cus[p.r][p.c];

        }

        for(int r = 0 ; r < N; r++){
            for(int c = 0 ;c < N; c++){
                if(cus[r][c] == -1) continue;
                cus[r][c] = cusTmp[r][c];
            }
        }
//                  for(int r = 0 ;r < N;r++){
//              System.out.println(Arrays.toString(cusTmp[r]));
//          }

    }

    public static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r < N && c < N;
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

}
