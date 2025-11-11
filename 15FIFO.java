// Simple FIFO Page Replacement Algorithm
import java.util.*;

    class FIFO {
    public static void main(String[] args) {
        int pages[] = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1};
        int n = pages.length;     // Total number of pages
        int frames = 3;           // Number of available frames
        int temp[] = new int[frames];
        int pageFaults = 0;
        int next = 0;             // Points to the frame to replace next (FIFO order)

        // Initially, all frames are empty
        for (int i = 0; i < frames; i++)
            temp[i] = -1;

        System.out.println("Page\tFrame 1\tFrame 2\tFrame 3");

        // Traverse each page one by one
        for (int i = 0; i < n; i++) {
            int page = pages[i];
            boolean found = false;

            // Check if page already exists in any frame
            for (int j = 0; j < frames; j++) {
                if (temp[j] == page) {
                    found = true;
                    break;
                }
            }

            // If page not found (page fault)
            if (!found) {
                temp[next] = page;        // Replace page using FIFO order
                next = (next + 1) % frames; // Move pointer to next frame
                pageFaults++;
            }

            // Print current frame status
            System.out.print(page + "\t");
            for (int j = 0; j < frames; j++) {
                if (temp[j] != -1)
                    System.out.print(temp[j] + "\t");
                else
                    System.out.print("-\t");
            }
            System.out.println();
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
    }
}
