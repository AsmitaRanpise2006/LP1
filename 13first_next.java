import java.util.*;

public class FirstFit {
    public static void main(String[] args) {
        int[] blocks = {100, 50, 30, 120, 35};
        int[] process = {20, 60, 70, 40};
        int m = blocks.length, n = process.length;

        int[] allocate = new int[n];
        Arrays.fill(allocate, -1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (blocks[j] >= process[i]) {
                    allocate[i] = j;
                    blocks[j] -= process[i];
                    break;
                }
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

public class NextFit {
    public static void main(String[] args) {
        int[] blocks = {100, 50, 30, 120, 35};
        int[] process = {20, 60, 70, 40};
        int m = blocks.length, n = process.length;

        int[] allocate = new int[n];
        Arrays.fill(allocate, -1);

        int lastAllocated = 0; // Remember where last allocation happened

        for (int i = 0; i < n; i++) { // For each process
            int j = lastAllocated;
            int count = 0;

            while (count < m) { // Loop through all blocks once
                if (blocks[j] >= process[i]) {
                    allocate[i] = j;
                    blocks[j] -= process[i];
                    lastAllocated = j; // Start next search from here
                    break;
                }
                j = (j + 1) % m; // Move to next block circularly
                count++;
            }
        }

        // Display results
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
