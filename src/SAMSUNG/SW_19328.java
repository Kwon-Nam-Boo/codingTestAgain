package SAMSUNG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SW_19328 {

    /*

        Pair --> x.y 좌표값 및 가스값 에 대한 객체
        customer, dest --> 고객 및 도착점에 대한 Pair 리스트

        goNextCustomer()  --> 가까운 손님에게 이동하는 메소드
            checkCustomer() --> 현재 위치에 고객이 있는지 확인하는 메소드
        goNextDest()    --> 손님이 가야하는 곳으로 이동하는 메소드

        start.g == 시작가스량
        p.g-1  == 남은가스량
        사용 = 2시 - 2남

        남은 가스량 + 사용가스량*2

     */

    private static int N,M,G, ans, desIdx;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    private static Pair start;
    private static List<Pair> customer;
    private static List<Pair> dest;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        start = new Pair(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, G);

        customer = new ArrayList<>();
        dest = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            customer.add(new Pair(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,0));
            dest.add(new Pair(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,0));
        }

        ans = 0;
        desIdx = -1;

        for (int i = 0; i < M; i++) {
            goNextCustomer();
            if(ans == -1) break;
            goNextDest();
            if(ans == -1) break;
        }
        System.out.println(ans);

    }



    private static void goNextCustomer() {

        // 만약 현재위치에 고객이있다면? 고객지우고 끝!
        if(checkCustomer(start.r , start.c)){
            customer.remove(desIdx);
            return;
        }

        visited = new boolean[N][N];

        Queue<Pair> queue = new LinkedList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        queue.offer(start);
        visited[start.r][start.c] = true;

        // 우선순위 고객찾기
        while(!queue.isEmpty()){

            Pair p = queue.poll();

            for (int d = 0; d < dir.length; d++) {
                int nr = p.r + dir[d][0];
                int nc = p.c + dir[d][1];

                if(isIn(nr,nc) && !visited[nr][nc] && map[nr][nc] == 0){
                    // 고객이 있을경우 pq에 넣어 우선순위 만들어두기~
                    if(checkCustomer(nr,nc)){
                        pq.offer(new Pair(nr,nc,p.g-1));
                    }
                    // 가스가 떨어진게 아니라면 ..? 진행시켜
                    if(p.g-1 > 0){
                        queue.offer(new Pair(nr,nc,p.g-1));
                        visited[nr][nc] = true;
                    }

                }
            }

        }
        // 허나 고객을 못찾았다면?
        if(pq.isEmpty()) {
            ans = -1;
        }
        // 찾았다면 pq의 첫번째값이 저희의 고객님이십니다.
        else{
            Pair cus = pq.poll();
            start = new Pair(cus.r,cus.c,cus.g);

            checkCustomer(cus.r, cus.c);
            customer.remove(desIdx);
        }
        return;
    }

    private static boolean checkCustomer(int r, int c) {

        for (int i = 0; i < customer.size(); i++) {
            if(c == customer.get(i).c && r == customer.get(i).r)
                {
                    desIdx = i;
                    return true;
                }
        }
        return false;
    }

    private static void goNextDest() {

        // 근데 현재위치가 도착지라면? 도착지 삭제 및 현재까지 가스만 측정
        if(start.r == dest.get(desIdx).r && start.c == dest.get(desIdx).c){
            dest.remove(desIdx);
            ans = start.g;
            return;
        }

        visited = new boolean[N][N];

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.r][start.c] = true;

        // 우선순위 고객찾기
        while(!queue.isEmpty()){

            Pair p = queue.poll();

            for (int d = 0; d < dir.length; d++) {
                int nr = p.r + dir[d][0];
                int nc = p.c + dir[d][1];

                if(isIn(nr,nc) && !visited[nr][nc] && map[nr][nc] == 0){
                    // 도착지라면 ...끝
                    // 시작점 변경 및 가스충전, 도착지 제거, 이동거리 추가하기
                    if(nr == dest.get(desIdx).r && nc == dest.get(desIdx).c){
                        int cg = 2*start.g - p.g +1;
                        start = new Pair(nr,nc,cg);
                        dest.remove(desIdx);
                        ans = start.g;
                        return;
                    }
                    if(p.g-1 >0){
                        queue.offer(new Pair(nr,nc,p.g-1));
                        visited[nr][nc] = true;
                    }
                }
            }

        }
        ans = -1;
        return;

    }

    public static class Pair implements Comparable<Pair>{

        int r,c,g;

        public Pair(int r, int c, int g) {
            this.r = r;
            this.c = c;
            this.g = g;
        }

        @Override
        public int compareTo(Pair o) {

            if(o.g == this.g){
                if(this.r == o.r){
                    return Integer.compare(this.c, o.c);
                }
                return Integer.compare(this.r, o.r);
            }
            return Integer.compare(o.g, this.g);

        }
    }

    public static boolean isIn(int r, int c) {
        return r>=0 && c>=0 && c< N && r<N;
    }
}
