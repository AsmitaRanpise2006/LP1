import java.util.*;

public class Priority_Preemptive_Simple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] at = new int[n];
        int[] bt = new int[n];
        int[] rt = new int[n];
        int[] pr = new int[n];
        int[] ct = new int[n];
        int[] tat = new int[n];
        int[] wt = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nProcess P" + (i + 1));
            System.out.print("Arrival Time: ");
            at[i] = sc.nextInt();
            System.out.print("Burst Time: ");
            bt[i] = sc.nextInt();
            System.out.print("Priority (smaller = higher): ");
            pr[i] = sc.nextInt();
            rt[i] = bt[i];
        }

        int time = 0, completed = 0;

        while (completed < n) {
            int minIndex = -1, minPriority = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (at[i] <= time && rt[i] > 0 && pr[i] < minPriority) {
                    minPriority = pr[i];
                    minIndex = i;
                }
            }

            if (minIndex == -1) {
                time++;
                continue;
            }

            rt[minIndex]--;
            time++;

            if (rt[minIndex] == 0) {
                completed++;
                ct[minIndex] = time;
                tat[minIndex] = ct[minIndex] - at[minIndex];
                wt[minIndex] = tat[minIndex] - bt[minIndex];
            }
        }

        System.out.println("\nProcess\tAT\tBT\tPR\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t" + at[i] + "\t" + bt[i] + "\t" + pr[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        sc.close();
    }
}


import java.util.*;

public class RR_Simple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Input number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int bt[] = new int[n];   // Burst time
        int rt[] = new int[n];   // Remaining time
        int wt[] = new int[n];   // Waiting time
        int tat[] = new int[n];  // Turnaround time

        // Step 2: Input burst times
        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time for P" + (i + 1) + ": ");
            bt[i] = sc.nextInt();
            rt[i] = bt[i];  // Initially remaining = burst
        }

        // Step 3: Input time quantum
        System.out.print("Enter time quantum: ");
        int q = sc.nextInt();

        int time = 0; // Total time counter
        System.out.print("\nGantt Chart: " + time);

        // Step 4: Round Robin logic
        while (true) {
            boolean done = true; // check if all are done

            for (int i = 0; i < n; i++) {
                if (rt[i] > 0) { // if process still remaining
                    done = false; // not yet finished

                    System.out.print(" | P" + (i + 1) + " | ");

                    if (rt[i] > q) {     // if more than quantum
                        time += q;
                        rt[i] -= q;
                    } else {             // if less than quantum
                        time += rt[i];
                        wt[i] = time - bt[i];
                        rt[i] = 0;       // process finished
                    }

                    System.out.print(time);
                }
            }

            if (done) break; // exit when all done
        }

        // Step 5: Calculate Turnaround Time
        for (int i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];
        }

        // Step 6: Display Results
        System.out.println("\n\nProcess\tBT\tWT\tTAT");
        int totalWT = 0, totalTAT = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t" + bt[i] + "\t" + wt[i] + "\t" + tat[i]);
            totalWT += wt[i];
            totalTAT += tat[i];
        }

        // Step 7: Averages
        System.out.printf("\nAverage WT = %.2f", (double) totalWT / n);
        System.out.printf("\nAverage TAT = %.2f", (double) totalTAT / n);
    }
}
