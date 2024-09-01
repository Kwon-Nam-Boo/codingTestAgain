package Algo_book;

public class BinarySearch_이진탐색 {

    private static int[] test = {32,41,6,8,10,41,53,46,21,89,57};

    public static void main(String[] args) {
        // 이진탐색
        System.out.println(binarySearch(test, 21));
    }

    public static int binarySearch(int[] test, int key){
        int start = 0;
        int end = test.length - 1;

        while(start<=end){
            int mid = (start+end)/2;
            System.out.println(mid);
            if(test[mid] == key) return mid;
            else if(test[mid] > key) end = mid -1;
            else start = mid +1;

        }


        return -1;
    }

}
