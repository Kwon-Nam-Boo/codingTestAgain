package Algo_book_prac;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTest_선행큐테스트_prac {

    public static void main(String[] args) {

        PriorityQueue<String> pq = new PriorityQueue(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                Integer I1 = o1.length();
                Integer I2 = o2.length();
                return I1 == I2 ? o1.compareTo(o2) : I1.compareTo(I2);
            }
        });
        pq.offer("true");
        pq.offer("my");
        pq.offer("dream");
        pq.offer("is");
        while(!pq.isEmpty()) {
            System.out.println((String)pq.poll());
        }
        System.out.println();

        PriorityQueue<Word> pq2 = new PriorityQueue<>();
        pq2.offer(new Word("true"));
        pq2.offer(new Word("my"));
        pq2.offer(new Word("dream"));
        pq2.offer(new Word("is"));
        while(!pq2.isEmpty()) {
            System.out.println(pq2.poll());
        }
    }

    public static class Word implements Comparable<Word>{

        private String word;

        public Word(String word){
            this.word = word;
        }

        public String getWord() {
            return word;
        }

        @Override
        public int compareTo(Word o1) {

            Integer I1 = this.word.length();
            Integer I2 = o1.getWord().length();

            return I1 == I2 ? this.word.compareTo(o1.getWord()) : I1.compareTo(I2);
        }

        @Override
        public String toString() {
            return "Word{" +
                    "word='" + word + '\'' +
                    '}';
        }
    }
}
