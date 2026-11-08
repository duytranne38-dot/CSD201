package studentmanagement;

import java.util.Random;

/*
 * Experiment for our Research Question:
 * Search by student ID - Linked List (linear search) vs BST.
 *
 * For each n we create n students, insert them in random order,
 * then search 1000 random IDs and measure the total time.
 * Each size is run 5 times and we take the average.
 */
public class PerformanceTest {

    static final int[] SIZES = {100, 500, 1000, 5000, 10000};
    static final int SEARCHES = 1000;
    static final int TRIALS = 5;

    public static void run() {
        Random random = new Random(2026);

        // Warm up the JVM first so the first result is not too slow
        measure(1000, random);

        System.out.println("(" + SEARCHES + " searches, average of " + TRIALS + " trials)");
        System.out.printf("%-8s %-20s %-20s%n", "n", "Linked List (ms)", "BST (ms)");

        for (int n : SIZES) {
            double listTotal = 0;
            double bstTotal = 0;
            for (int t = 0; t < TRIALS; t++) {
                double[] result = measure(n, random);
                listTotal += result[0];
                bstTotal += result[1];
            }
            System.out.printf("%-8d %-20.3f %-20.3f%n", n,
                    listTotal / TRIALS, bstTotal / TRIALS);
        }
    }

    // Returns {linkedListMs, bstMs} for one trial
    private static double[] measure(int n, Random random) {
        String[] ids = new String[n];
        for (int i = 0; i < n; i++) {
            ids[i] = String.format("SE%05d", i);
        }

        // Shuffle the IDs so they are inserted in random order
        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            String temp = ids[i];
            ids[i] = ids[j];
            ids[j] = temp;
        }

        StudentList list = new StudentList();
        CourseBST bst = new CourseBST();
        for (String id : ids) {
            list.add(new Student(id, "Student " + id, "SE"));
            bst.addCourse(id, "CSD201");
        }

        // The same random IDs are searched in both structures
        String[] targets = new String[SEARCHES];
        for (int i = 0; i < SEARCHES; i++) {
            targets[i] = ids[random.nextInt(n)];
        }

        long start = System.nanoTime();
        for (String id : targets) {
            list.find(id);
        }
        long listTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (String id : targets) {
            bst.search(id);
        }
        long bstTime = System.nanoTime() - start;

        return new double[]{listTime / 1e6, bstTime / 1e6};
    }
}
