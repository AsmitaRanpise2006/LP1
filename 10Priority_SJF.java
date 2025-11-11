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