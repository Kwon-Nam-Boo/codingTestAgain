package Boj_Practice.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_1068_트리 {

    // 단방향 인접리스트
    private static List<Integer>[] graph;
    private static int N, start , count;
    private static Integer D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < graph.length; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            // 뿌리라면
            if(tmp == -1){
                start = i;
            }else{
                graph[tmp].add(i);
            }
        }
        
        D = Integer.parseInt(br.readLine());

        //  부모와의 연결을 끊는다. 즉, D가 불려질 일이 없다
        for (int i = 0; i < graph.length; i++) {
            graph[i].remove(D);
        }

        count = 0;
        dfs(start);
        System.out.println(count);
    }

    private static void dfs(int start) {

        // 시작점이 지워질경우
        if(start == D) return;
        // 리프노드일 경우
        if(graph[start].isEmpty()){
            count++;
            return;
        }
        for(int i=0;i<graph[start].size();i++){
            int tmp = graph[start].get(i);
            dfs(tmp);
        }

    }
}
