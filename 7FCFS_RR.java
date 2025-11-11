import java.util.*;

public class FCFS_Simple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] at = new int[n];  // Arrival Time
        int[] bt = new int[n];  // Burst Time
        int[] ct = new int[n];  // Completion Time
        int[] tat = new int[n]; // Turnaround Time
        int[] wt = new int[n];  // Waiting Time

        for (int i = 0; i < n; i++) {
            System.out.println("\nProcess P" + (i + 1));
            System.out.print("Arrival Time: ");
            at[i] = sc.nextInt();
            System.out.print("Burst Time: ");
            bt[i] = sc.nextInt();
        }

        // Sort by arrival time
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (at[j] > at[j + 1]) {
                    int temp = at[j]; at[j] = at[j + 1]; at[j + 1] = temp;
                    temp = bt[j]; bt[j] = bt[j + 1]; bt[j + 1] = temp;
                }
            }
        }

        int time = 0;
        for (int i = 0; i < n; i++) {
            if (time < at[i]) time = at[i];
            ct[i] = time + bt[i];
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
            time = ct[i];
        }

        System.out.println("\nProcess\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
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
