

// Simple LRU (Least Recently Used) Page Replacement Algorithm
import java.util.*;

class LRU {
    public static void main(String[] args) {
        int pages[] = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1};
        int n = pages.length;    // Total number of pages
        int frames = 3;          // Number of available frames
        ArrayList<Integer> memory = new ArrayList<>();
        int pageFaults = 0;

        System.out.println("Page\tFrame 1\tFrame 2\tFrame 3");

        // Go through each page one by one
        for (int i = 0; i < n; i++) {
            int page = pages[i];

            // If page is already in memory, remove it (to update its recent use)
            if (memory.contains(page)) {
                memory.remove(Integer.valueOf(page));
            } 
            // If memory is full and page not found, remove least recently used page
            else if (memory.size() == frames) {
                memory.remove(0);   // remove first page (oldest one)
                pageFaults++;
            } 
            // If page not found but there is space
            else {
                pageFaults++;
            }

            // Add the current page (most recently used)
            memory.add(page);

            // Print current frame contents
            System.out.print(page + "\t");
            for (int j = 0; j < frames; j++) {
                if (j < memory.size())
                    System.out.print(memory.get(j) + "\t");
                else
                    System.out.print("-\t");
            }
            System.out.println();
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
    }
}