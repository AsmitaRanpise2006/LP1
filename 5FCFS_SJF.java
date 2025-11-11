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

public class SJF_Preemptive_Simple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] at = new int[n];
        int[] bt = new int[n];
        int[] rt = new int[n];
        int[] ct = new int[n];
        int[] tat = new int[n];
        int[] wt = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nProcess P" + (i + 1));
            System.out.print("Arrival Time: ");
            at[i] = sc.nextInt();
            System.out.print("Burst Time: ");
            bt[i] = sc.nextInt();
            rt[i] = bt[i];
        }

        int time = 0, completed = 0;

        while (completed < n) {
            int minIndex = -1, minTime = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (at[i] <= time && rt[i] > 0 && rt[i] < minTime) {
                    minTime = rt[i];
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

        System.out.println("\nProcess\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        sc.close();
    }
}
