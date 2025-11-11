import java.util.*;

public class BestFit {
    public static void main(String[] args) {
        int[] blocks = {100, 50, 30, 120, 35};
        int[] process = {20, 60, 70, 40};
        int m = blocks.length, n = process.length;

        int[] allocate = new int[n];
        Arrays.fill(allocate, -1);

        for (int i = 0; i < n; i++) {
            int bestIndex = -1;
            for (int j = 0; j < m; j++) {
                if (blocks[j] >= process[i]) {
                    if (bestIndex == -1 || blocks[j] < blocks[bestIndex])
                        bestIndex = j;  // Find the smallest suitable block
                }
            }
            if (bestIndex != -1) {
                allocate[i] = bestIndex;
                blocks[bestIndex] -= process[i];
            }
        }

        System.out.println("\nProcess\tSize\tBlock");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + (i + 1) + "\t" + process[i] + "\t");
            if (allocate[i] != -1)
                System.out.println("Block " + (allocate[i] + 1));
            else
                System.out.println("Not Allocated");
        }
    }
}
import java.util.*;

public class WorstFit {
    public static void main(String[] args) {
        int[] blocks = {100, 50, 30, 120, 35};
        int[] process = {20, 60, 70, 40};
        int m = blocks.length, n = process.length;

        int[] allocate = new int[n];
        Arrays.fill(allocate, -1);

        for (int i = 0; i < n; i++) {
            int worstIndex = -1;
            for (int j = 0; j < m; j++) {
                if (blocks[j] >= process[i]) {
                    if (worstIndex == -1 || blocks[j] > blocks[worstIndex])
                        worstIndex = j;  // Find the largest suitable block
                }
            }
            if (worstIndex != -1) {
                allocate[i] = worstIndex;
                blocks[worstIndex] -= process[i];
            }
        }

        System.out.println("\nProcess\tSize\tBlock");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + (i + 1) + "\t" + process[i] + "\t");
            if (allocate[i] != -1)
                System.out.println("Block " + (allocate[i] + 1));
            else
                System.out.println("Not Allocated");
        }
    }
}
