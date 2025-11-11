import java.util.*;

public class SimpleOptimal {
    public static void main(String[] args) {
        int pages[] = {7, 0, 1, 2, 0, 3};
        int n = pages.length;
        int frames = 3;
        ArrayList<Integer> memory = new ArrayList<>();
        int pageFaults = 0;

        System.out.println("Page\tFrames");

        for (int i = 0; i < n; i++) {
            int page = pages[i];

            // If page already exists, skip
            if (memory.contains(page)) {
                // no page fault
            } else {
                // Page fault occurs
                pageFaults++;

                if (memory.size() < frames) {
                    // Empty frame available
                    memory.add(page);
                } else {
                    // Need to replace one page
                    int farthest = -1, indexToReplace = -1;

                    // Find the page which will not be used for the longest time
                    for (int j = 0; j < memory.size(); j++) {
                        int nextUse = Integer.MAX_VALUE;
                        for (int k = i + 1; k < n; k++) {
                            if (memory.get(j) == pages[k]) {
                                nextUse = k;
                                break;
                            }
                        }
                        if (nextUse > farthest) {
                            farthest = nextUse;
                            indexToReplace = j;
                        }
                    }
                    memory.set(indexToReplace, page);
                }
            }

            // Print current memory state
            System.out.print(page + "\t");
            for (int p : memory) System.out.print(p + " ");
            System.out.println();
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
    }
}
