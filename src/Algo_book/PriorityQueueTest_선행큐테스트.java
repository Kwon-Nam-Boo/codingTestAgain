package Algo_book;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTest_선행큐테스트 {

    public static void main(String[] args) {
        new PriorityQueue();
        PriorityQueue<String> pq = new PriorityQueue(new Comparator<String>() {
            public int compare(String o1, String o2) {
                Integer len1 = o1.length();
                Integer len2 = o2.length();
                System.out.println(o1+ ":"+len1 + " " + o2+ ":" +len2);
                return len1 == len2 ? o1.compareTo(o2) : len1.compareTo(len2);
            }
        });
        pq.offer("true");
        System.out.println("--------------");
        pq.offer("my");
        System.out.println("--------------");
        pq.offer("dream");
        System.out.println("--------------");
        pq.offer("is");

        while(!pq.isEmpty()) {
            System.out.println((String)pq.poll());
        }

    }
}
