import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TimeTest {
    public static void main(String[] args) throws IOException {
        ArrayList<FibonacciHeap> heaps = new ArrayList<>();
        ArrayList<FibonacciHeap> mergeHeaps = new ArrayList<>();
        int N = -1;
        for (int i = 1; i < 101; i++) {
            Scanner in = new Scanner(new FileInputStream("text" + i + ".txt"));
            FibonacciHeap H1 = new FibonacciHeap();
            while (N != 0 && in.hasNext()) {
                N = in.nextInt();
                for (int k = 0; k < N; k++) {
                    int number = in.nextInt();
//                    Node node = new Node("fff", number);
                    H1.insert("fff", number);
//                    H1.insert("df", 5);
                }
            }
            heaps.add(H1);
            in.close();
        }
        File file = new File("src/TimeMerge.txt");
        Writer writer = new FileWriter("src/TimeMerge.txt");
        double start1 = System.nanoTime();
        mergeHeaps.add(FibonacciHeap.merge(heaps.get(0), heaps.get(1)));
        double end1 = System.nanoTime();
        double finish1 = end1 - start1;
        writer.write(String.format("%.2f", finish1) + "\n");

        double start = System.nanoTime();
        for (int j = 2; j < 100; j++) {
            mergeHeaps.add(FibonacciHeap.merge(mergeHeaps.get(j-2), heaps.get(j)));
            double end = System.nanoTime();
            double finish = end - start;
            writer.write(String.format("%.2f", finish) + "\n");
        }
        writer.close();
        // System.out.println(heaps);

//
//        File file = new File("src/TimeRemove.txt");
//        Writer writer = new FileWriter("src/TimeRemove.txt");
//
//        for (int i = 1; i < 101; i++) {
//            Scanner in = new Scanner(new FileInputStream("text" + i + ".txt"));
//            double start = System.nanoTime();
//
//            N = -1;
//
//            while (N != 0 && in.hasNext()) {
//                N = in.nextInt();
//                for (int k = 0; k < 40; k++) {
//                    int number = in.nextInt();
////                    Node node = new Node("fff", number);
//                    H1.insert("fff", number);
////                    H1.insert("df", 5);
//                }
//            }
//            H1.removeMin();

//            in.close();
//            double end = System.nanoTime();
//            double result = end - start;
//            writer.write(String.format("%.2f", result) + "\n");
    }
//        writer.close();
}